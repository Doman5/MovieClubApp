package com.domanski.movieclub.config.security;

import com.domanski.movieclub.domain.user.Dto.UserCredentialsDto;
import com.domanski.movieclub.domain.user.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findUserByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("użytkownik %s nie został odnaleziony".formatted(username)));
    }

    private UserDetails createUserDetails(UserCredentialsDto user) {
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .accountLocked(user.isBlocked())
                .roles(user.getRoles().toArray(String[]::new))
                .build();
    }
}
