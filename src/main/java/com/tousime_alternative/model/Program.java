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
public class Program extends Offer {
    @Column()
    private Date startDate;
    @Column()
    private Date endDate;
    @ElementCollection
    @CollectionTable(name = "table_photos_program")
    @Column()
    private List<String> photo;
    @Column(length = 8)
    private float price;
    @OneToMany(mappedBy = "program", cascade = CascadeType.REMOVE)
    private List<Activity> activities;
}
