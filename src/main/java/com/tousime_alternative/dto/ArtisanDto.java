package com.tousime_alternative.dto;

import com.tousime_alternative.model.Artisan;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class ArtisanDto {
    private long id;
    private String Emplacement;
    private LocalTime Heure_ouverture;
    private LocalTime Heure_fermeture;
    private String nom_commercial;
    private String photo;
    private String type ;
    public static ArtisanDto fromEntity(Artisan artisan) {
        if (artisan == null) {
            return null;
        }

        return ArtisanDto.builder()
                .id(artisan.getId())
                .Emplacement(artisan.getEmplacement())
                .Heure_ouverture(artisan.getHeure_ouverture())
                .Heure_fermeture(artisan.getHeure_fermeture())
                .nom_commercial(artisan.getNom_commercial())
                .photo(artisan.getPhoto())
                .type(artisan.getType())
                .build();
    }
    public static Artisan toEntity(ArtisanDto artisanDto) {
        if (artisanDto == null) {
            return null;
        }

        Artisan artisan = new Artisan();
        artisan.setId(artisanDto.getId());
        artisan.setEmplacement(artisanDto.getEmplacement());
        artisan.setHeure_ouverture(artisanDto.getHeure_ouverture());
        artisan.setHeure_fermeture(artisanDto.getHeure_fermeture());
        artisan.setNom_commercial(artisanDto.getNom_commercial());
        artisan.setPhoto(artisanDto.getPhoto());
        artisan.setType(artisanDto.getType());
        return artisan;

    }

}
