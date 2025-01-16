package com.hcc.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthCredentialsRequestTest {

    @Test
    public void AuthCredentialsRequest_testSettersAndGetters() {
        // given
        String username = "maddiodie";
        String password = "slayTheBoots";

        // when
        AuthCredentialsRequest request = new AuthCredentialsRequest(username, password);

        // then
        assertEquals(username, request.getUsername());
        assertEquals(password, request.getPassword());
    }

}
