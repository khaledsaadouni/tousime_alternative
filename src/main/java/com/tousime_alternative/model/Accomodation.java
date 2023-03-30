package com.tousime_alternative.model;

import com.tousime_alternative.model.enumr.ComodityList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Accomodation extends Offer{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComodityList comodityList;
    @Column()
    private String regulations;
    @Column(length = 8)
    private int  price;
    @Column(length = 8)
    private int  promotion;

}
