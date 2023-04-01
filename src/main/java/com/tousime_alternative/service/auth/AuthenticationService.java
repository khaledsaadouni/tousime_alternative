package com.tousime_alternative.service.auth;

import com.tousime_alternative.dto.PartnerDto;
import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.dto.auth.AuthenticationRequest;
import com.tousime_alternative.dto.auth.AuthenticationResponse;
import com.tousime_alternative.dto.auth.RegisterRequest;
import com.tousime_alternative.dto.auth.RegisterRequestPartner;
import com.tousime_alternative.model.AuthenticationProvider;
import com.tousime_alternative.model.Partner;
import com.tousime_alternative.model.User;
import com.tousime_alternative.model.enumr.Role;
import com.tousime_alternative.repository.PartnerRepository;
import com.tousime_alternative.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PartnerRepository partnerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void processOAuthPostLogin(String email) {
        var user = repository.findByEmail(email)
                .orElseThrow();

        if (user == null) {


            System.out.println("Created new user: " + email);
        }

    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .authProvider(AuthenticationProvider.LOCAL)
                .build();
        user.setCreationDate(Instant.now());
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(UserDto.fromEntity(user))
                .build();
    }

    public AuthenticationResponse registerPartner(RegisterRequestPartner request) {
        Partner user = new Partner();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setCommercial_name(request.getCommercial_name());
        user.setAddress(request.getAddress());
        user.setCreationDate(Instant.now());
        partnerRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(PartnerDto.fromEntity(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        if (user.getRole() == Role.Partner) {
            var partner = partnerRepository.findByEmail(request.getEmail()).orElseThrow();
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(PartnerDto.fromEntity(partner))
                    .build();
        } else {
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(UserDto.fromEntity(user))
                    .build();
        }
    }
}