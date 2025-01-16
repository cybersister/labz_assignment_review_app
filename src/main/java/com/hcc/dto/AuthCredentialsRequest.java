package com.hcc.dto;

public class AuthCredentialsRequest {

    private String username;
    private String password;

    public AuthCredentialsRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
