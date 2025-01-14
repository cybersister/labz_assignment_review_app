package com.hcc.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Authority() {}

    public Authority(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public User getUser() {
        return user;
    }

}
