package com.tousime_alternative.controller;

import com.tousime_alternative.OAuth2.OAuthUserService;
import com.tousime_alternative.dto.auth.AuthenticationRequest;
import com.tousime_alternative.dto.auth.AuthenticationResponse;
import com.tousime_alternative.dto.auth.RegisterRequest;
import com.tousime_alternative.dto.auth.RegisterRequestPartner;
import com.tousime_alternative.model.User;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserRepository repo;

    private final OAuthUserService oAuthUserService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/registerPartner")
    public ResponseEntity<?> registerPartner(
            @RequestBody RegisterRequestPartner request
    ) {
        try {
            return ResponseEntity.ok(service.registerPartner(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/oAuth2Token/{email}")
    public ResponseEntity<AuthenticationResponse> getTokenFromAuth2(@RequestParam String email) {
        return ResponseEntity.ok(oAuthUserService.generateTokenAfterLogin(email));
    }


}
