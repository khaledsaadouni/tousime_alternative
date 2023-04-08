package com.tousime_alternative.dto;

import com.tousime_alternative.model.Artisan;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ArtisanDto {
    private long id;
    private String emplacement;
    private LocalTime opening_hour;
    private LocalTime closing_hour;
    private String commercial_name;
    private String description;
    private List<String> photo;
    private String type;
    private List<ArticalDto> articals;
    private String destination;
    private Instant creationDate;
    private PartnerDto partner;

    public static ArtisanDto fromEntity(Artisan artisan) {
        if (artisan == null) {
            return null;
        }

        return ArtisanDto.builder()
                .id(artisan.getId())
                .emplacement(artisan.getEmplacement())
                .destination(artisan.getDestination())
                .creationDate(artisan.getCreationDate())
                .opening_hour(artisan.getOpening_hour())
                .closing_hour(artisan.getClosing_hour())
                .commercial_name(artisan.getCommercial_name())
                .description(artisan.getDescription())
                .photo(artisan.getPhoto())
                .type(artisan.getType())
                .articals(artisan.getArticals() != null ? artisan.getArticals().stream().map(ArticalDto::fromEntity).collect(Collectors.toList()) : null)
                .partner(PartnerDto.fromEntity(artisan.getPartner()))
                .build();
    }
    public static Artisan toEntity(ArtisanDto artisanDto) {
        if (artisanDto == null) {
            return null;
        }

        Artisan artisan = new Artisan();
        artisan.setId(artisanDto.getId());
        artisan.setDestination(artisanDto.getDestination());
        artisan.setEmplacement(artisanDto.getEmplacement());
        artisan.setOpening_hour(artisanDto.getOpening_hour());
        artisan.setDescription(artisanDto.getDescription());
        artisan.setClosing_hour(artisanDto.getClosing_hour());
        artisan.setCommercial_name(artisanDto.getCommercial_name());
        artisan.setPhoto(artisanDto.getPhoto());
        artisan.setType(artisanDto.getType());
        return artisan;

    }

}
