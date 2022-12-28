package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;

public class UserCalendarDto {

    private int userId;
    private String userName;

    private String userFirstname;

    private String userEmail;

    private String userPassword;

    private String UserPicture;
    private String userCity;

    private Byte isActive;

    private int roleId;

    private int calendarId;

    public Byte getIsActive() {
        return isActive;
    }

    public UserCalendarDto() {
    }
    //todo modif pour l'email et le password
    public static UserCalendarDto from(UsersEntity userEntity){
        UserCalendarDto dto = new UserCalendarDto();
        dto.setUserName(userEntity.getUserName());
        dto.setUserFirstname(userEntity.getUserFirstname());
        dto.setIsActive(userEntity.getIsActive());
        dto.setRoleId(userEntity.getRoleId());
        dto.setUserPicture(userEntity.getUserPicture());
        dto.setUserCity(userEntity.getUserCity());
        return dto;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }
}
