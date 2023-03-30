package com.tousime_alternative.dto;

import com.tousime_alternative.model.Restauration;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class RestaurationDto {
    private long id;
    private int  capacity;
    private String description;
    private String emplacement;
    private String name;
    private String photo;
    private String socialMediaLink;
    private String type;
    private Date opening;
    private Date closing;
    private String menu;
    public static RestaurationDto fromEntity(Restauration restauration) {
        if (restauration == null) {
            return null;
        }

        return RestaurationDto.builder()
                .id(restauration.getId())
                .name(restauration.getName())
                .capacity(restauration.getCapacity())
                .emplacement(restauration.getEmplacement())
                .photo(restauration.getPhoto())
                .socialMediaLink(restauration.getSocialMediaLink())
                .type(restauration.getType())
                .opening(restauration.getOpening())
                .closing(restauration.getClosing())
                .menu(restauration.getMenu())
                .build();
    }

    public static Restauration toEntity(RestaurationDto dto) {
        if (dto == null) {
            return null;
        }

        Restauration restauration = new Restauration();
        restauration.setId(dto.getId());
        restauration.setName(dto.getName());
        restauration.setCapacity(dto.getCapacity());
        restauration.setEmplacement(dto.getEmplacement());
        restauration.setPhoto(dto.getPhoto());
        restauration.setSocialMediaLink(dto.getSocialMediaLink());
        restauration.setPhoto(dto.getType());
        restauration.setClosing(dto.getClosing());
        restauration.setOpening(dto.getOpening());
        restauration.setMenu(dto.getMenu());

        return restauration;
    }
}
