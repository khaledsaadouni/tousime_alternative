package com.tousime_alternative.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Favoris extends AbstractEntity{

}
