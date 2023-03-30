package com.tousime_alternative.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event extends Offer{
    @Column()
    private Date eventDate;

    @Column()
    private String duration;
    @Column()
    private String regulations;

}
