package com.domanski.movieclub.domain.user;

import com.domanski.movieclub.domain.user.Dto.UserRoleDto;
import com.domanski.movieclub.domain.user.mapper.UserRoleDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRoleDto> findAllUserRole() {
        return userRoleRepository.findAll().stream()
                .map(UserRoleDtoMapper::map)
                .toList();
    }
}
