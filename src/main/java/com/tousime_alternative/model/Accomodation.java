package com.tousime_alternative.model;

import com.tousime_alternative.model.enumr.ComodityList;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Accomodation extends Offer {
    @ElementCollection
    @CollectionTable(name = "table_comodity_List")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<ComodityList> comodityList;
    @ElementCollection
    @CollectionTable(name = "table_regulations_accom")
    @Column()
    private List<String> regulations;
    @ElementCollection
    @CollectionTable(name = "table_photos_accom")
    @Column()
    private List<String> photo;
    @ElementCollection
    @CollectionTable(name = "table_social_Media_Links_accom")
    @Column()
    private List<String> socialMediaLinks;

    @Column(length = 8)
    private float price;
    @Column(length = 8)
    private float promotion;
    @ManyToOne()
    @JoinColumn(name = "idPartner")
    private Partner partner;

}
