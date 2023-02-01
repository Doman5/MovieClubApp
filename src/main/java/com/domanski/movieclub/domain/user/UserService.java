package com.domanski.movieclub.domain.user;

import com.domanski.movieclub.domain.user.Dto.*;
import com.domanski.movieclub.domain.user.mapper.UserCredentialsDtoMapper;
import com.domanski.movieclub.domain.user.mapper.UserInfoDtoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialsDtoMapper userCredentialsDtoMapper;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, UserCredentialsDtoMapper userCredentialsDtoMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userCredentialsDtoMapper = userCredentialsDtoMapper;
    }

    public Optional<UserCredentialsDto> findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userCredentialsDtoMapper::map);
    }

    @Transactional
    public void RegisterUser(UserRegistrationDto userToSava) {
        String DEFAULT_ROLE = "USER";
        UserRole role = userRoleRepository.findByName(DEFAULT_ROLE).orElseThrow();
        User user = new User();
        user.setEmail(userToSava.getEmail());
        user.setPassword(passwordEncoder.encode(userToSava.getPassword()));
        user.getUserRoles().add(role);
        userRepository.save(user);
    }

    public List<UserInfoDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserInfoDtoMapper::map)
                .toList();
    }

    public void editByAdmin(UserEditByAdminDto user) {
        User userToSave = userRepository.findByEmail(user.getEmail()).orElseThrow();
        userToSave.setUserRoles(user.getRoles());
        userRepository.save(userToSave);
    }

    public void editByUser(UserEditDto user) {
        User userToSave = userRepository.findByEmail(user.getEmail()).orElseThrow();
        if(!user.getEmail().equals(user.getNewEmail())) {
            userToSave.setEmail(user.getNewEmail());
        }

        if(!user.getNewPassword().equals("")) {
            userToSave.setPassword(passwordEncoder.encode(user.getNewPassword()));
        }
        userRepository.save(userToSave);
    }
}
