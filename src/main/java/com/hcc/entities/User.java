package com.hcc.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate cohortStartDate;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;

    public User() {}
    // NO-ARGS CONSTRUCTOR

    public User(LocalDate cohortStartDate, String username, String password,
                List<Authority> authorities) {
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    // ALL-ARGS EXPECT ID CONSTRUCTOR

    public User(User user) {
        this.id = user.id;
        this.cohortStartDate = user.cohortStartDate;
        this.username = user.username;
        this.password = user.password;
        this.authorities = user.authorities != null ? new ArrayList<>(user.authorities) : null;
    }
    // COPY CONSTRUCTOR

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();

        for (int i = 0; i < authorities.size(); i++) {
            roles.add(authorities.get(i));
        }
        // safe copy

        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
