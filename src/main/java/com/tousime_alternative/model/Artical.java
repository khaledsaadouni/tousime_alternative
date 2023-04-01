package com.tousime_alternative.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Artical extends AbstractEntity {
    @Column
    private String name;
    @Column
    private String photo;
    @Column
    private float price;
    @ManyToOne()
    @JoinColumn(name = "idArtisan")
    private Artisan artisan;
}
