package com.domanski.movieclub.domain.user.Dto;

import com.domanski.movieclub.domain.user.UserRole;

import java.util.Set;

public class UserEditByAdminDto {
    private String email;
    private Set<UserRole> roles;
    private boolean isBlocked;

    public UserEditByAdminDto() {
    }

    public UserEditByAdminDto(String email, Set<UserRole> roles, boolean isBlocked) {
        this.email = email;
        this.roles = roles;
        this.isBlocked = isBlocked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
