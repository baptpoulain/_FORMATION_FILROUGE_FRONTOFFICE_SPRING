package com.example.FilRougeFrontOffice.repository.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class RolesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private int roleId;
    @Basic
    @Column(name = "role_name")
    private String roleName;
    @OneToMany (fetch = FetchType.EAGER,  mappedBy = "rolesByRoleId")
    private Collection<UsersEntity> usersByRoleId;


    public RolesEntity(String roleName) {
        this.roleName = roleName;
    }

    public RolesEntity() {

    }

    public RolesEntity(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (roleId != that.roleId) return false;
        return Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    public Collection<UsersEntity> getUsersByRoleId() {
        return usersByRoleId;
    }

    public void setUsersByRoleId(Collection<UsersEntity> usersByRoleId) {
        this.usersByRoleId = usersByRoleId;
    }
}

