package com.example.FilRougeFrontOffice.controller.dto;

public class UserPasswordDto {

    private String userNewPassword;
    private String userOldPassword;

    public UserPasswordDto() {
    }

    public UserPasswordDto(String userNewPassword, String userOldPassword) {
        this.userNewPassword = userNewPassword;
        this.userOldPassword = userOldPassword;
    }

    public String getUserNewPassword() {
        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {
        this.userNewPassword = userNewPassword;
    }

    public String getUserOldPassword() {
        return userOldPassword;
    }

    public void setUserOldPassword(String userOldPassword) {
        this.userOldPassword = userOldPassword;
    }
}
