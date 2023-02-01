package com.domanski.movieclub.domain.user.mapper;

import com.domanski.movieclub.domain.user.Dto.UserCredentialsDto;
import com.domanski.movieclub.domain.user.User;
import com.domanski.movieclub.domain.user.UserRole;
import com.domanski.movieclub.domain.user.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserCredentialsDtoMapper {
    private final UserRoleRepository userRoleRepository;

    public UserCredentialsDtoMapper(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserCredentialsDto map(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles  = user.getUserRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(email,password,roles);
    }

    public User map(UserCredentialsDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserRoles(userDto.getRoles().stream().map(this::convertStringToUserRole).collect(Collectors.toSet()));
        return user;
    }

    private UserRole convertStringToUserRole(String stringRole) {
        return userRoleRepository.findByName(stringRole).orElseThrow();
    }
}
