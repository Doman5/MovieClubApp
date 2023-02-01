package com.domanski.movieclub.domain.user.Dto;

public class UserEditDto {
    private String email;
    private String newEmail;
    private String password;
    private String newPassword;

    public UserEditDto(String email, String newEmail, String password, String newPassword) {
        this.email = email;
        this.newEmail = newEmail;
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
