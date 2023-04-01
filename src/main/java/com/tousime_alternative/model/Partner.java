package com.tousime_alternative.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Partner extends User {
    @Column(length = 50)
    private String commercial_name;
    @Column(length = 100)
    private String address;
    @OneToMany(mappedBy = "partner")
    private List<Offer> offers;
    @OneToMany(mappedBy = "partner")
    private List<Artisan> artisans;
}
