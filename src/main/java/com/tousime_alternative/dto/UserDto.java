package com.tousime_alternative.dto;

import com.tousime_alternative.model.User;
import com.tousime_alternative.model.enumr.Role;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
@Data
@Builder
public class UserDto {
    private long id;
    private String lastname;
    private String firstname;
    private int phone;
    private String password;
    private String photo;
    private String email;
    private Instant birthday;
    private Role role;
    public static UserDto fromEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .password(user.getPassword())
                .phone(user.getPhone())
                .photo(user.getPhoto())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setLastname(dto.getLastname());
        user.setFirstname(dto.getFirstname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBirthday(dto.getBirthday());
        user.setRole(dto.getRole());
        user.setPhoto(dto.getPhoto());
        user.setPhone(dto.getPhone());

        return user;
    }
}
