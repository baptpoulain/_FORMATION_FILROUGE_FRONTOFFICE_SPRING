package com.example.FilRougeFrontOffice.repository.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "users", schema = "bdd_fil_rouge")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "user_firstname")
    private String userFirstname;
    @Basic
    @Column(name = "user_password")
    private String userPassword;
    @Basic
    @Column(name = "user_email")
    private String userEmail;
    @Basic
    @Column(name = "user_picture")
    private String userPicture;
    @Basic
    @Column(name = "isActive")
    private Byte isActive;
    @Basic
    @Column(name = "role_id")
    private int roleId;

    @Basic
    @Column(name = "user_city")
    private String userCity;


    @OneToMany(mappedBy = "usersByUserId",cascade = {CascadeType.REMOVE})
    private Collection<InteractEntity> interactsByUserId;
    @OneToMany(mappedBy = "usersByUserId" , cascade = {CascadeType.REMOVE})
    private Collection<PlanningsEntity> planningsByUserId;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
    private RolesEntity rolesByRoleId;

    public UsersEntity(String userName, String userFirstname, String userPassword, String userEmail, String userPicture, Byte isActive, int roleId, String userCity) {
        this.userName = userName;
        this.userFirstname = userFirstname;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPicture = userPicture;
        this.isActive = isActive;
        this.roleId = roleId;
        this.userCity = userCity;
    }

    public UsersEntity(String userName, String userFirstname, String userPassword, String userEmail, String userPicture, Byte isActive, int roleId, String userCity, Collection<InteractEntity> interactsByUserId, Collection<PlanningsEntity> planningsByUserId, RolesEntity rolesByRoleId) {
        this.userName = userName;
        this.userFirstname = userFirstname;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPicture = userPicture;
        this.isActive = isActive;
        this.roleId = roleId;
        this.userCity = userCity;
        this.interactsByUserId = interactsByUserId;
        this.planningsByUserId = planningsByUserId;
        this.rolesByRoleId = rolesByRoleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userId != that.userId) return false;
        if (roleId != that.roleId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userFirstname != null ? !userFirstname.equals(that.userFirstname) : that.userFirstname != null)
            return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        if (userPicture != null ? !userPicture.equals(that.userPicture) : that.userPicture != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userFirstname != null ? userFirstname.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userPicture != null ? userPicture.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + roleId;
        return result;
    }

    public Collection<InteractEntity> getInteractsByUserId() {
        return interactsByUserId;
    }

    public void setInteractsByUserId(Collection<InteractEntity> interactsByUserId) {
        this.interactsByUserId = interactsByUserId;
    }

    public Collection<PlanningsEntity> getPlanningsByUserId() {
        return planningsByUserId;
    }

    public void setPlanningsByUserId(Collection<PlanningsEntity> planningsByUserId) {
        this.planningsByUserId = planningsByUserId;
    }

    public RolesEntity getRolesByRoleId() {
        return rolesByRoleId;
    }

    public void setRolesByRoleId(RolesEntity rolesByRoleId) {
        this.rolesByRoleId = rolesByRoleId;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }
}
