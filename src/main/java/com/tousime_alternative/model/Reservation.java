package com.tousime_alternative.model;

import com.tousime_alternative.model.enumr.State;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends AbstractEntity {
    @Column()
    private Date date;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;
    @Column()
    private Integer count_people;
    @Column()
    private Date checkout;
    @Column()
    private float price;
    @ManyToOne()
    @JoinColumn(name = "idOffer")
    private Offer offer;
    @ManyToOne()
    @JoinColumn(name = "idUser")
    private User user;
    @Column()
    private boolean payed;
}
