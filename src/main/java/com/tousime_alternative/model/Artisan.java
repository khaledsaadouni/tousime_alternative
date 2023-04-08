package com.tousime_alternative.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Artisan extends AbstractEntity {
    @Column(length = 50)
    private String emplacement;
    @Column()
    private LocalTime opening_hour;
    @Column()
    private String google_map;
    @Column()
    private String destination;
    @Column()
    private String description;
    @Column()
    private LocalTime closing_hour;
    @Column(length = 50)
    private String commercial_name;
    @ElementCollection
    @CollectionTable(name = "table_photos_artisan")
    @Column()
    private List<String> photo;
    @Column(length = 50)
    private String type;
    @ManyToOne()
    @JoinColumn(name = "idPartner")
    private Partner partner;
    @OneToMany(mappedBy = "artisan", cascade = CascadeType.REMOVE)
    private List<Artical> articals;


}
