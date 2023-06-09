package com.tousime_alternative.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tousime_alternative.model.enumr.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements UserDetails {
    //    @Id
//    @GeneratedValue
//    private long id;
    @Column(nullable = false, length = 50)
    private String lastname;
    @Column(nullable = false, length = 50)
    private String firstname;
    @Column(length = 8)
    private int phone;
    @Column()
    private String password;
    @Column()
    private String photo;
    @Column(nullable = false, unique = true)
    private String email;
    @Column()
    private Date birthday;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Reservation> reservations;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Review> reviews;
    @Enumerated(EnumType.STRING)
    private AuthenticationProvider authProvider;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
