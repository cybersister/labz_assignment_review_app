package com.hcc.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// possible use cases for this class include:
// (1) password hashing -> before storing a user's password into a database, the password is
//  encoded to ensure it's not stored in plain text
// (2) password verification -> when a user attempts to log in, the entered password is
//  hashed and compared with the hashed password stored in the database
// (3) custom encoding schema -> if you have specific security requirements, you may need a
//  custom encoding strategy

/**
 * Used to encode user passwords in a secure manner before storing them in the database.
 */
@Component
public class CustomPasswordEncoder {

    private PasswordEncoder passwordEncoder;

    public CustomPasswordEncoder(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public PasswordEncoder getPasswordEncoder(){
        return passwordEncoder;
    }

}
