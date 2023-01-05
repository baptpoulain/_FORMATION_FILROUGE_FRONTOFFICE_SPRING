package com.example.FilRougeFrontOffice.repository.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Embeddable
public class InteractEntityPK implements Serializable {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "planning_id")
    private int planningId;
    @Column(name = "permission_id")
    private int permissionId;

    public InteractEntityPK(int userId) {
        this.userId = userId;
    }

    public InteractEntityPK() {

    }

    public InteractEntityPK(int userId, int planningId, int permissionId) {
        this.userId = userId;
        this.planningId = planningId;
        this.permissionId = permissionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InteractEntityPK that = (InteractEntityPK) o;

        if (userId != that.userId) return false;
        if (planningId != that.planningId) return false;
        if (permissionId != that.permissionId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + planningId;
        result = 31 * result + permissionId;
        return result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlanningId() {
        return planningId;
    }

    public void setPlanningId(int planningId) {
        this.planningId = planningId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
