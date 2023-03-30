package com.tousime_alternative.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalTime;
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Artisan extends AbstractEntity{
    @Column(nullable = false, length = 50)
    private String Emplacement;
    @Column()
    private LocalTime Heure_ouverture;
    @Column()
    private LocalTime Heure_fermeture;
    @Column( length = 50)
    private String nom_commercial;
    @Column()
    private String photo;
    @Column(length = 50)
    private String type ;


}
