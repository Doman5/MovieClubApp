package com.domanski.movieclub.domain.user.mapper;

import com.domanski.movieclub.domain.user.Dto.UserInfoDto;
import com.domanski.movieclub.domain.user.User;

public class UserInfoDtoMapper {
    public static UserInfoDto map(User user) {
        return new UserInfoDto(user.getEmail(),
                user.getUserRoles().toString(),
                user.isBlocked());
    }
}
