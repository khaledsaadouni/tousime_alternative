package com.tousime_alternative.dto;

import com.tousime_alternative.model.Program;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProgramDto {
    private long id;
    private String generic_Type;
    private int capacity;
    private String description;
    private String emplacement;
    private String name;
    private String destination;
    private List<String> photo;
    private float price;
    private PartnerDto partner;
    private Instant creationDate;
    private List<ReviewDto> reviews;
    private List<ReservationDto> reservations;
    private String google_map;
    private List<ActivityDto> activities;
    private Date startDate;
    private Date endDate;

    public static ProgramDto fromEntity(Program program) {
        if (program == null) {
            return null;
        }

        return ProgramDto.builder()
                .id(program.getId())
                .name(program.getName())
                .google_map(program.getGoogle_map())
                .generic_Type("program")
                .capacity(program.getCapacity())
                .description(program.getDescription())
                .emplacement(program.getEmplacement())
                .photo(program.getPhoto())
                .price(program.getPrice())
                .partner(PartnerDto.fromEntity(program.getPartner()))
                .destination(program.getDestination())
                .creationDate(program.getCreationDate())
                .reviews(program.getReviews() != null ? program.getReviews().stream().map(ReviewDto::fromEntity).collect(Collectors.toList()) : null)
                .reservations(program.getReservations() != null ? program.getReservations().stream().map(ReservationDto::fromEntity).collect(Collectors.toList()) : null)
                .activities(program.getActivities() != null ? program.getActivities().stream().map(ActivityDto::fromEntity).collect(Collectors.toList()) : null)
                .startDate(program.getStartDate())
                .endDate(program.getEndDate())
                .build();
    }

    public static Program toEntity(ProgramDto dto) {
        if (dto == null) {
            return null;
        }

        Program program = new Program();
        program.setGeneric_Type("program");
        program.setId(dto.getId());
        program.setGoogle_map(dto.getGoogle_map());
        program.setDescription(dto.getDescription());
        program.setName(dto.getName());
        program.setCapacity(dto.getCapacity());
        program.setEmplacement(dto.getEmplacement());
        program.setPhoto(dto.getPhoto());
        program.setPrice(dto.getPrice());
        program.setDestination(dto.getDestination());
        program.setEndDate(dto.getEndDate());
        program.setStartDate(dto.getStartDate());
        return program;
    }
}
