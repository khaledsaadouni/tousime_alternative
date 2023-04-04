package com.tousime_alternative.dto;

import com.tousime_alternative.model.Restoration;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class RestorationDto {
    private long id;
    private Instant creationDate;
    private int capacity;
    private String description;

    private String destination;
    private String emplacement;
    private String name;
    private List<String> photo;
    private List<String> socialMediaLink;
    private String type;
    private Instant opening;
    private Instant closing;
    private String menu;

    //private List<ReviewDto> reviews;
    private PartnerDto partner;
    private List<ReviewDto> reviews;

    public static RestorationDto fromEntity(Restoration restauration) {
        if (restauration == null) {
            return null;
        }

        return RestorationDto.builder()
                .id(restauration.getId())
                .name(restauration.getName())
                .description(restauration.getDescription())
                .capacity(restauration.getCapacity())
                .emplacement(restauration.getEmplacement())
                .photo(restauration.getPhoto())
                .socialMediaLink(restauration.getSocialMediaLinks())
                .type(restauration.getType())
                .opening(restauration.getOpening())
                .closing(restauration.getClosing())
                .menu(restauration.getMenu())
                .destination(restauration.getDestination())
                .partner(PartnerDto.fromEntity(restauration.getPartner()))
                .creationDate(restauration.getCreationDate())
                .reviews(restauration.getReviews().stream().map(ReviewDto::fromEntity).collect(Collectors.toList()))
                .build();
    }

    public static Restoration toEntity(RestorationDto dto) {
        if (dto == null) {
            return null;
        }

        Restoration restauration = new Restoration();
        restauration.setId(dto.getId());
        restauration.setDescription(dto.getDescription());
        restauration.setName(dto.getName());
        restauration.setCapacity(dto.getCapacity());
        restauration.setEmplacement(dto.getEmplacement());
        restauration.setPhoto(dto.getPhoto());
        restauration.setSocialMediaLinks(dto.getSocialMediaLink());
        restauration.setType(dto.getType());
        restauration.setClosing(dto.getClosing());
        restauration.setOpening(dto.getOpening());
        restauration.setMenu(dto.getMenu());
        restauration.setDestination(dto.getDestination());

        return restauration;
    }
}
