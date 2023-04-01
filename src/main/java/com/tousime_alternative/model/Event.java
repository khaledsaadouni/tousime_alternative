package com.tousime_alternative.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event extends Offer {
    @Column()
    private Date eventDate;

    @Column()
    private String duration;
    @Column()
    private float price;
    @ElementCollection
    @CollectionTable(name = "table_regulations_event")
    @Column()
    private List<String> regulations;
    @ElementCollection
    @CollectionTable(name = "table_photos_events")
    @Column()
    private List<String> photo;
    @ElementCollection
    @CollectionTable(name = "table_social_Media_Links_events")
    @Column()
    private List<String> socialMediaLinks;

    @ManyToOne()
    @JoinColumn(name = "idPartner")
    private Partner partner;


}
