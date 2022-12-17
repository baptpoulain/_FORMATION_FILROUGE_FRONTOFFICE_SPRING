package com.example.FilRougeFrontOffice.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class InteractEntityPK implements Serializable {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "planning_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planningId;
    @Column(name = "permission_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionId;

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
}
