package com.example.FilRougeFrontOffice.controller.dto;

public class SignupRequest {

    private int userId;

    private String name;
    private String userName;

    private String userFirstname;

    private String userEmail;

    private String userPassword;

    private String UserPicture;

    private String userCity;

    private Byte isActive;

    private int roleId;

    public SignupRequest(String userName, String userFirstname, String userEmail, String userPassword, String userPicture, Byte isActive, int roleId, String userCity) {
        this.userName = userName;
        this.userFirstname = userFirstname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        UserPicture = userPicture;
        this.isActive = isActive;
        this.roleId = roleId;
        this.userCity = userCity;
    }

    public SignupRequest() {
    }

    public SignupRequest(String userName, String userFirstname,  String userPassword, String userEmail, String userPicture, String userCity) {
        this.userName = userName;
        this.userFirstname = userFirstname;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.UserPicture = userPicture;
        this.userCity = userCity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPicture() {
        return UserPicture;
    }

    public void setUserPicture(String userPicture) {
        UserPicture = userPicture;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }
}
