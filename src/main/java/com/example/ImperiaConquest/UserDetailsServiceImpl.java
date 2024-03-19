package com.example.ImperiaConquest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        if (userRepository.getUserByUsername(usernameOrEmail) != null) {
            return new MyUserDetails(userRepository.getUserByUsername(usernameOrEmail));
        } else if (userRepository.getUserByEmail(usernameOrEmail) != null) {
            return new MyUserDetails(userRepository.getUserByEmail(usernameOrEmail));
        }
        throw new UsernameNotFoundException("Could not find user");
    }

}
