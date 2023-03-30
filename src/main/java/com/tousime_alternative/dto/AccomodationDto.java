package com.tousime_alternative.dto;

import com.tousime_alternative.model.Accomodation;
import com.tousime_alternative.model.enumr.ComodityList;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccomodationDto {
    private long id;
    private int  capacity;
    private String description;
    private String emplacement;
    private String name;
    private String photo;
    private String socialMediaLink;
    private String type;
    private ComodityList comodityList;
    private String regulations;
    private int  price;
    private int  promotion;


    public static AccomodationDto fromEntity(Accomodation accomodation) {
        if (accomodation == null) {
            return null;
        }

        return AccomodationDto.builder()
                .id(accomodation.getId())
                .name(accomodation.getName())
                .capacity(accomodation.getCapacity())
                .emplacement(accomodation.getEmplacement())
                .photo(accomodation.getPhoto())
                .socialMediaLink(accomodation.getSocialMediaLink())
                .type(accomodation.getType())
                .comodityList(accomodation.getComodityList())
                .regulations(accomodation.getRegulations())
                .price(accomodation.getPrice())
                .promotion(accomodation.getPromotion())
                .build();
    }

    public static Accomodation toEntity(AccomodationDto dto) {
        if (dto == null) {
            return null;
        }

        Accomodation accomodation = new Accomodation();
        accomodation.setId(dto.getId());
        accomodation.setName(dto.getName());
        accomodation.setCapacity(dto.getCapacity());
        accomodation.setEmplacement(dto.getEmplacement());
        accomodation.setPhoto(dto.getPhoto());
        accomodation.setSocialMediaLink(dto.getSocialMediaLink());
        accomodation.setPhoto(dto.getType());
        accomodation.setComodityList(dto.getComodityList());
        accomodation.setRegulations(dto.getRegulations());
        accomodation.setPrice(dto.getPrice());
        accomodation.setPromotion(dto.getPromotion());
        return accomodation;
    }
}
