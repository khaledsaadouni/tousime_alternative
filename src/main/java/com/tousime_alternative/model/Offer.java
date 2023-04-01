package com.tousime_alternative.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue
    private long id;
    @CreatedDate
    @Column(length = 8)
    private int capacity;
    @Column()
    private String description;
    @Column()
    private String emplacement;
    @Column()
    private String name;

    @Column()
    private String type;
    @ManyToOne()
    @JoinColumn(name = "idPartner")
    private Partner partner;

//    @OneToOne
//    private Reservation reservation;
//    @OneToMany(mappedBy = "offer")
//    private List<Review> reviews;

}
