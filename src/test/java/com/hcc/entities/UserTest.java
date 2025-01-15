package com.hcc.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    // (1) once the database has been set up, test the <@OneToMany> annotation
    // (2) once the database has been set up, test that the <id> is correctly generated
    //  when the object is persisted to the database

    @Test
    public void User_instantiatesObjectCorrectly() {
        // given
        String username = "username";
        String password = "password";

        LocalDate localDate = LocalDate.now();
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority());

        // when
        User user = new User(localDate, username, password, authorityList);

        // then
        assertEquals(user.getCohortStartDate(), localDate);
        assertEquals(user.getUsername(), username);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getAuthorities(), authorityList);
    }

}
