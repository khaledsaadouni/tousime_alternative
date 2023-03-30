package com.tousime_alternative.dto;

import com.tousime_alternative.model.Offer;

import com.tousime_alternative.model.User;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfferDto {
    private long id;
    private int  capacity;
    private String description;
    private String emplacement;
    private String name;
    private String photo;
    private String socialMediaLink;
    private String type;

    public static OfferDto fromEntity(Offer offer) {
        if (offer == null) {
            return null;
        }

        return OfferDto.builder()
                .id(offer.getId())
                .name(offer.getName())
                .capacity(offer.getCapacity())
                .emplacement(offer.getEmplacement())
                .photo(offer.getPhoto())
                .socialMediaLink(offer.getSocialMediaLink())
                .type(offer.getType())
                .build();
    }

    public static Offer toEntity(OfferDto dto) {
        if (dto == null) {
            return null;
        }

        Offer offer = new Offer();
        offer.setId(dto.getId());
        offer.setName(dto.getName());
        offer.setCapacity(dto.getCapacity());
        offer.setEmplacement(dto.getEmplacement());
        offer.setPhoto(dto.getPhoto());
        offer.setSocialMediaLink(dto.getSocialMediaLink());
        offer.setPhoto(dto.getType());

        return offer;
    }
}
