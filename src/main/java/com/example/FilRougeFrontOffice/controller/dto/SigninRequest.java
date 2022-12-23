package com.example.FilRougeFrontOffice.controller.dto;

public class SigninRequest {

    private String userEmail;

    private String userPassword;

    public String getUserEmail() {
        return userEmail;
    }

    public SigninRequest() {
    }

    public void setUserEmail(String userName) {
        this.userEmail = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
