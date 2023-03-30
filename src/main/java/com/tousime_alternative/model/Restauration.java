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
public class Restauration extends Offer {
    @Column()
    private Date opening;
    @Column()
    private Date closing;
    @Column()
    private String menu;
}
