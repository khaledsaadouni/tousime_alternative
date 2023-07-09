package com.tousime_alternative.dto;

import com.tousime_alternative.model.Extras;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtrasDto {
    private long id;
    private String name;
    private float price;

    public static ExtrasDto fromEntity(Extras extra) {
        if (extra == null) {
            return null;
        }

        return ExtrasDto.builder()
                .id(extra.getId())
                .name(extra.getName())
                .price(extra.getPrice())
                .build();
    }

    public static Extras toEntity(ExtrasDto dto) {
        if (dto == null) {
            return null;
        }
        Extras extras = new Extras();
        extras.setId(dto.getId());
        extras.setName(dto.getName());
        extras.setPrice(dto.getPrice());
        return extras;
    }
}
