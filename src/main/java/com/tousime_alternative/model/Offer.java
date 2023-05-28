package com.tousime_alternative.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;

@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue
    private long id;
    @CreatedDate
    @Column(length = 8)
    private int capacity;
    @Column(length = 10000)
    private String description;
    @Column()
    private String generic_Type;
    @Column()
    private String emplacement;
    @Column()
    private String google_map;
    @Column()
    private String name;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant creationDate;
    @Column()
    private String destination;

    @Column()
    private String type;
    @ManyToOne()
    @JoinColumn(name = "idPartner")
    private Partner partner;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

}
