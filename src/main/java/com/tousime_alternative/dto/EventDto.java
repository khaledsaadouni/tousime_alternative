package com.tousime_alternative.dto;

import com.tousime_alternative.model.Event;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class EventDto {
    private long id;

    private String destination;
    private int capacity;
    private String description;
    private String emplacement;
    private String name;
    private List<String> photo;
    private List<String> socialMediaLink;
    private String type;
    private List<String> regulations;
    private String duration;
    private Date eventDate;
    private PartnerDto partner;
    private float price;
    private Instant creationDate;

    private List<ReviewDto> reviews;
    public static EventDto fromEntity(Event event) {
        if (event == null) {
            return null;
        }

        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .capacity(event.getCapacity())
                .description(event.getDescription())
                .emplacement(event.getEmplacement())
                .photo(event.getPhoto())
                .socialMediaLink(event.getSocialMediaLinks())
                .type(event.getType())
                .regulations(event.getRegulations())
                .duration(event.getDuration())
                .eventDate(event.getEventDate())
                .partner(PartnerDto.fromEntity(event.getPartner()))
                .price(event.getPrice())
                .destination(event.getDestination())
                .creationDate(event.getCreationDate())
                .reviews(event.getReviews().stream().map(ReviewDto::fromEntity).collect(Collectors.toList()))
                .build();
    }

    public static Event toEntity(EventDto dto) {
        if (dto == null) {
            return null;
        }

        Event event = new Event();
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setCapacity(dto.getCapacity());
        event.setEmplacement(dto.getEmplacement());
        event.setPhoto(dto.getPhoto());
        event.setSocialMediaLinks(dto.getSocialMediaLink());
        event.setType(dto.getType());
        event.setEventDate(dto.getEventDate());
        event.setRegulations(dto.getRegulations());
        event.setDuration(dto.getDuration());
        event.setPrice(dto.getPrice());
        event.setDestination(dto.getDestination());
        return event;
    }
}
