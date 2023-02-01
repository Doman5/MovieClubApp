package com.domanski.movieclub.domain.user.mapper;

import com.domanski.movieclub.domain.user.Dto.UserRoleDto;
import com.domanski.movieclub.domain.user.UserRole;

public class UserRoleDtoMapper {
    static public UserRoleDto map(UserRole userRole) {
        return new UserRoleDto(userRole.getId(),
                userRole.getName(),
                userRole.getDescription());
    }
}
