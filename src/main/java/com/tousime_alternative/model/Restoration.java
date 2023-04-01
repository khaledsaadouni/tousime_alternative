package com.tousime_alternative.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restoration extends Offer {
    @ElementCollection
    @CollectionTable(name = "table_photos_resto")
    @Column()
    private List<String> photo;
    @ElementCollection
    @CollectionTable(name = "table_social_Media_Links_resto")
    @Column()
    private List<String> socialMediaLinks;
    @Column()
    private Instant opening;
    @Column()
    private Instant closing;
    @Column()
    private String menu;
}
