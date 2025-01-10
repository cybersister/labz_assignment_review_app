package com.hcc.entities;

import java.time.LocalDate;

public class User {

    private Long id;
    private LocalDate cohortStartDate;
    private String username;
    private String password;

    public User() {}
    // NO-ARGS CONSTRUCTOR

    public User(LocalDate cohortStartDate, String username, String password) {
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.password = password;
    }
    // ALL-ARGS EXPECT ID CONSTRUCTOR




}
