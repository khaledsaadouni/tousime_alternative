package com.tousime_alternative.dto;

import com.tousime_alternative.model.Accomodation;
import com.tousime_alternative.model.enumr.ComodityList;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AccomodationDto {
    private long id;
    private int capacity;
    private String description;
    private String emplacement;
    private String name;
    private String destination;
    private List<String> photo;
    private List<String> socialMediaLink;
    private String type;
    private List<ComodityList> comodityList;
    private List<String> regulations;
    private float price;
    private float promotion;
    private PartnerDto partner;
    private Instant creationDate;

    private List<ReviewDto> reviews;

    public static AccomodationDto fromEntity(Accomodation accomodation) {
        if (accomodation == null) {
            return null;
        }

        return AccomodationDto.builder()
                .id(accomodation.getId())
                .name(accomodation.getName())
                .capacity(accomodation.getCapacity())
                .description(accomodation.getDescription())
                .emplacement(accomodation.getEmplacement())
                .photo(accomodation.getPhoto())
                .socialMediaLink(accomodation.getSocialMediaLinks())
                .type(accomodation.getType())
                .comodityList(accomodation.getComodityList())
                .regulations(accomodation.getRegulations())
                .price(accomodation.getPrice())
                .promotion(accomodation.getPromotion())
                .partner(PartnerDto.fromEntity(accomodation.getPartner()))
                .destination(accomodation.getDestination())
                .creationDate(accomodation.getCreationDate())
                .reviews(accomodation.getReviews() != null ? accomodation.getReviews().stream().map(ReviewDto::fromEntity).collect(Collectors.toList()) : null)
                .build();
    }

    public static Accomodation toEntity(AccomodationDto dto) {
        if (dto == null) {
            return null;
        }

        Accomodation accomodation = new Accomodation();
        accomodation.setId(dto.getId());
        accomodation.setDescription(dto.getDescription());
        accomodation.setName(dto.getName());
        accomodation.setCapacity(dto.getCapacity());
        accomodation.setEmplacement(dto.getEmplacement());
        accomodation.setPhoto(dto.getPhoto());
        accomodation.setSocialMediaLinks(dto.getSocialMediaLink());
        accomodation.setType(dto.getType());
        accomodation.setComodityList(dto.getComodityList());
        accomodation.setRegulations(dto.getRegulations());
        accomodation.setPrice(dto.getPrice());
        accomodation.setPromotion(dto.getPromotion());
        accomodation.setDestination(dto.getDestination());
        return accomodation;
    }
}