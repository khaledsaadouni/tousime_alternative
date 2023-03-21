package com.tousime_alternative.model;

import com.tousime_alternative.model.enumr.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {
    @Column(nullable = false, length = 50)
    private String lastname;
    @Column(nullable = false, length = 50)
    private String firstname;
    @Column(length = 8)
    private int phone;
    @Column(nullable = false)
    private String password;
    @Column()
    private String photo;
    @Column(nullable = false)
    private String email;
    @Column()
    private Instant birthday;
    @Column(nullable = false)
    private Role role;

}
