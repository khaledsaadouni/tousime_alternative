package com.tousime_alternative.dto.auth;

import com.tousime_alternative.model.enumr.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String lastname;
    private String firstname;
    private int phone;
    private String password;
    private String photo;
    private String email;
    private Instant birthday;
    private Role role;
}