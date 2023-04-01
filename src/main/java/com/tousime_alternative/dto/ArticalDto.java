package com.tousime_alternative.dto;

import com.tousime_alternative.model.Artical;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticalDto {
    private Long id;
    private String name;
    private String photo;
    private float price;

    public static ArticalDto fromEntity(Artical artical) {
        if (artical == null) {
            return null;
        }

        return ArticalDto.builder()
                .id(artical.getId())
                .name(artical.getName())
                .photo(artical.getPhoto())
                .price(artical.getPrice())
                .build();
    }

    public static Artical toEntity(ArticalDto dto) {
        if (dto == null) {
            return null;
        }
        Artical artical = new Artical();
        artical.setId(dto.getId());
        artical.setPrice(dto.getPrice());
        artical.setName(dto.getName());
        artical.setPhoto(dto.getPhoto());
        return artical;

    }


}
