package com.domanski.movieclub.domain.user.Dto;

public class UserInfoDto {
    private String email;
    private String roles;
    private boolean isBlocked;

    public UserInfoDto() {
    }
    public UserInfoDto(String email, String roles, boolean isBlocked) {
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
