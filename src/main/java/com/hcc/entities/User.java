package com.hcc.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cohort_start_date")
    private LocalDate cohortStartDate;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Authority> authorities = new ArrayList<>();

    public User() {}
    // NO-ARGS CONSTRUCTOR

    public User(LocalDate cohortStartDate, String username, String password,
                List<Authority> authorities) {
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.password = password;
        this.authorities = authorities;

        for (Authority authority : authorities) {
            authority.setUser(this);
        }
    }
    // ALL-ARGS EXPECT ID CONSTRUCTOR

    public User(User user) {
        this.id = user.id;
        this.cohortStartDate = user.cohortStartDate;
        this.username = user.username;
        this.password = user.password;
        this.authorities
                = user.authorities != null ? new ArrayList<>(user.authorities) : null;

        if (this.authorities != null) {
            for (Authority authority : this.authorities) {
                authority.setUser(this);
            }
        }
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
        return new ArrayList<>(authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public LocalDate getCohortStartDate() {
        return cohortStartDate;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;

        for (Authority authority : authorities) {
            authority.setUser(this);
        }
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
        authority.setUser(this);
    }

    public void removeAuthority(Authority authority) {
        authorities.remove(authority);
        authority.setUser(null);
    }

}
