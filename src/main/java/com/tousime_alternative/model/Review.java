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
public class Review extends AbstractEntity {
    @Column()
    private String comment;
    @Column(name = "rate")
    private Integer rate = -1;
    @ManyToOne()
    @JoinColumn(name = "idUser")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "idOffer")
    private Offer offer;
}
