package com.example.FilRougeFrontOffice.security.jwt;

import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;

import java.util.Collection;

public class JwtResponse {

    private int userId;

    private String username;

    private String userFirstname;

    private String userEmail;

    private String userPicture;

    private Byte isActive;

    private int roldeId;

    private String UserCity;

    private String token;

    private Collection<PlanningsEntity> planningsByUserId;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int uderId) {
        this.userId = uderId;
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

    public JwtResponse(int userId, String username, String userFirstname, String userEmail, String userPicture, Byte isActive, int roldeId, String userCity, Collection<PlanningsEntity> planningsByUserId, String token) {
        this.userId = userId;
        this.username = username;
        this.userFirstname = userFirstname;
        this.userEmail = userEmail;
        this.userPicture = userPicture;
        this.isActive = isActive;
        this.roldeId = roldeId;
        this.UserCity = userCity;
        this.token = token;
        this.planningsByUserId = planningsByUserId;
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

    public Collection<PlanningsEntity> getPlanningsByUserId() {
        return planningsByUserId;
    }

    public void setPlanningsByUserId(Collection<PlanningsEntity> planningsByUserId) {
        this.planningsByUserId = planningsByUserId;
    }
}

