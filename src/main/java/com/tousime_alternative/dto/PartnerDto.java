package com.tousime_alternative.dto;

import com.tousime_alternative.model.Partner;
import com.tousime_alternative.model.enumr.Role;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class PartnerDto {
    private long id;
    private String RIB;
    private String lastname;
    private String firstname;
    private int phone;
    private String photo;
    private String email;
    private Date birthday;
    private Role role;
    private String address;
    private String commercial_name;

    public static PartnerDto fromEntity(Partner user) {
        if (user == null) {
            return null;
        }

        return PartnerDto.builder()
                .id(user.getId())
                .lastname(user.getLastname())
                .RIB(user.getRIB())
                .firstname(user.getFirstname())
                .phone(user.getPhone())
                .photo(user.getPhoto())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .address(user.getAddress())
                .commercial_name(user.getCommercial_name())
                .build();
    }

    public static Partner toEntity(PartnerDto dto) {
        if (dto == null) {
            return null;
        }

        Partner user = new Partner();
        user.setId(dto.getId());
        user.setRIB(dto.getRIB());
        user.setLastname(dto.getLastname());
        user.setFirstname(dto.getFirstname());
        user.setEmail(dto.getEmail());
        user.setBirthday(dto.getBirthday());
        user.setRole(dto.getRole());
        user.setPhoto(dto.getPhoto());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setCommercial_name(dto.getCommercial_name());
        return user;
    }
}
