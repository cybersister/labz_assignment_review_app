package com.hcc.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorityTest {

    // (1) once the database is set up, test that the generated value <id> is persisted
    //  correctly to the database
    // (2) once the database is set up, test that an Assignment object with null for the
    //  value of <user> is unable to persist

    @Test
    public void Authority_instantiatesAnObjectCorrectly() {
        // given
        String authority = "authority";

        LocalDate localDate = LocalDate.now();
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority());

        User user = new User(localDate, "username", "password",
                authorityList);

        // when
        Authority authoriTEA = new Authority(authority, user);

        // then
        assertEquals(authoriTEA.getAuthority(), authority);
        assertEquals(authoriTEA.getUser(), user);
    }

}
