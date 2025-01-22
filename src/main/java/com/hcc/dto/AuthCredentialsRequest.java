package com.hcc.dto;

import javax.validation.constraints.NotBlank;

public class AuthCredentialsRequest {

    @NotBlank(message = "The username field cannot be blank.")
    private String username;
    @NotBlank(message = "The username field cannot be blank.")
    private String password;

    public AuthCredentialsRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthCredentialsRequest() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "AuthCredentialsRequest {" + "username = " + username + "}";
    }

}
