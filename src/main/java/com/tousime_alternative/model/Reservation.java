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
    @Column(nullable = false)
    private Date date;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;
    @Column(nullable = false)
    private Integer count_people;
    @ManyToOne()
    @JoinColumn(name = "idOffer")
    private Offer offer;
    @ManyToOne()
    @JoinColumn(name = "idUser")
    private User user;
}