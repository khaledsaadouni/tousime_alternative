package com.tousime_alternative.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Extras {
    @Id
    @GeneratedValue
    private long id;
    @Column()
    private String name;
    @Column()
    private float price;
    @ManyToOne()
    @JoinColumn(name = "idAccomodation")
    private Accomodation accomodation;
    @ManyToOne()
    @JoinColumn(name = "idReservation")
    private Reservation reservation;
}
