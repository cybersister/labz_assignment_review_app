package com.hcc.services;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserDetailServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomPasswordEncoder customPasswordEncoder;

    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsername_existingUsername_returnsAssociatedUser() {
        // given
        String username = "existingUsername";
        String password = "password";

        LocalDate localDate = LocalDate.now();
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority());

        User existingUser = new User(localDate, username, password, authorityList);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));

        // when
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);

        // then
        assertNotNull(userDetails, "UserDetails for the given username should " +
                "not be null: " + username + ".");
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertEquals(authorityList, userDetails.getAuthorities());
    }

    @Test
    public void loadUserByUsername_nonExistingUsername_throwsUserNameNotFoundException() {
        // given
        String username = "nonExistingUsername";
        when(userRepository.findByUsername(username))
                .thenThrow(UsernameNotFoundException.class);

        // when + then
        assertThrows(UsernameNotFoundException.class, () ->
                userDetailServiceImpl.loadUserByUsername(username));
    }

}
