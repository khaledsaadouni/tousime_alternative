package com.tousime_alternative.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Offer extends AbstractEntity{
    @Column(length = 8)
    private int  capacity;
    @Column()
    private String description;
    @Column()
    private String emplacement;
    @Column()
    private String name;
    @Column()
    private String photo;
    @Column()
    private String socialMediaLink;
    @Column()
    private String type;

}
