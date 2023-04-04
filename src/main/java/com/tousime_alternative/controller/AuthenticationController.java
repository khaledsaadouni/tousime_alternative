package com.tousime_alternative.controller;

import com.tousime_alternative.OAuth2.OAuthUserService;
import com.tousime_alternative.dto.auth.AuthenticationRequest;
import com.tousime_alternative.dto.auth.AuthenticationResponse;
import com.tousime_alternative.dto.auth.RegisterRequest;
import com.tousime_alternative.dto.auth.RegisterRequestPartner;
import com.tousime_alternative.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final OAuthUserService oAuthUserService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/registerPartner")
    public ResponseEntity<AuthenticationResponse> registerPartner(
            @RequestBody RegisterRequestPartner request
    ) {
        return ResponseEntity.ok(service.registerPartner(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/oAuth2Token")
    public ResponseEntity<AuthenticationResponse> getTokenFromAuth2() {
        AuthenticationResponse response = new AuthenticationResponse(oAuthUserService.getTokenFromOauth2(), OAuthUserService.getUserFromOuth2());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/deleteoAuth2Token")
    public void deleteTokenFromAuth2() {
        oAuthUserService.deleteOAuthPostLogin();
    }


}
