package com.tousime_alternative.dto;

import com.tousime_alternative.model.Offer;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OfferDto {
    private long id;
    private int capacity;
    private String description;
    private String emplacement;

    private String destination;
    private String name;
    private List<String> photo;
    private Instant creationDate;
    private List<String> socialMediaLink;
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
                .type(offer.getType())
                .destination(offer.getDestination())
                .creationDate(offer.getCreationDate())
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
        offer.setType(dto.getType());
        offer.setDestination(dto.getDestination());
        return offer;
    }
}
