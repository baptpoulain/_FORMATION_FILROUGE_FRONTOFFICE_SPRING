package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;

public class UserDto {


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

    public Byte getIsActive() {
        return isActive;
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

    public UserDto() {
    }

    public static UserDto from(UsersEntity userEntity){
        UserDto dto = new UserDto();
        dto.setUserName(userEntity.getUserName());
        dto.setUserFirstname(userEntity.getUserFirstname());
        dto.setUserPassword(userEntity.getUserPassword());
        dto.setUserEmail(userEntity.getUserEmail());
        dto.setIsActive(userEntity.getIsActive());
        dto.setRoleId(userEntity.getRoleId());
        dto.setUserPicture(userEntity.getUserPicture());
        dto.setUserCity(userEntity.getUserCity());
        return dto;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
