package com.domanski.movieclub.domain.user.Dto;

public class UserInfoDto {
    private String email;
    private String roles;

    public UserInfoDto() {
    }
    public UserInfoDto(String email, String roles) {
        this.email = email;
        this.roles = roles;
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
}
