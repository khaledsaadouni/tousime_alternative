package com.tousime_alternative.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
public class Activity {
    @Id
    @GeneratedValue
    private long id;
    @Column()
    private String name;
    @Column()
    private Time start;
    @Column()
    private Time end;
    @ManyToOne()
    @JoinColumn(name = "idProgram")
    private Program program;
}
