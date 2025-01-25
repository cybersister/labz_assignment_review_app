package com.hcc.services;

import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Responsible for loading the user details from the database.
 *
 * This class is used when Spring Security needs to authenticate a user. The returned
 * {@link UserDetails} object includes the encoded password that Spring uses to validate the
 * user's credentials.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CustomPasswordEncoder passwordEncoder;
    // this was provided in the ticket, but i don't think that it is actually applied ... or
    //  else it will be applicable in the future <3

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User no found with username: "
                        + username + "."));
    }

}
