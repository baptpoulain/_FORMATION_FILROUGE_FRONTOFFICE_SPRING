package com.example.FilRougeFrontOffice.security.jwt;

public class JwtResponse {

    private int uderId;

    private String username;

    private String userFirstname;

    private String userPassword;

    private String userEmail;

    private String userPicture;

    private Byte isActive;

    private int roldeId;

    private String UserCity;


    private String token;

    public int getUderId() {
        return uderId;
    }

    public void setUderId(int uderId) {
        this.uderId = uderId;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public int getRoldeId() {
        return roldeId;
    }

    public void setRoldeId(int roldeId) {
        this.roldeId = roldeId;
    }

    public String getUserCity() {
        return UserCity;
    }

    public void setUserCity(String userCity) {
        UserCity = userCity;
    }

    public JwtResponse(int uderId, String username, String userFirstname, String userEmail, String userPicture, Byte isActive, int roldeId, String userCity, String token) {
        this.uderId = uderId;
        this.username = username;
        this.userFirstname = userFirstname;
        this.userEmail = userEmail;
        this.userPicture = userPicture;
        this.isActive = isActive;
        this.roldeId = roldeId;
        this.UserCity = userCity;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

