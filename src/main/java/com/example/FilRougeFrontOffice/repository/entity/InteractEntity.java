package com.example.FilRougeFrontOffice.repository.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "interact")

public class InteractEntity implements Serializable {
    @EmbeddedId
    private InteractEntityPK id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UsersEntity usersByUserId;
    @ManyToOne
    @JoinColumn(name = "planning_id", referencedColumnName = "planning_id", insertable = false, updatable = false)
    private PlanningsEntity planningsByPlanningId;
    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id", insertable = false, updatable = false)
    private PermissionsEntity permissionsByPermissionId;

    public InteractEntity(int userId, int planningId, int permissionId, UsersEntity usersByUserId, PlanningsEntity planningsByPlanningId, PermissionsEntity permissionsByPermissionId) {
        this.usersByUserId = usersByUserId;
        this.planningsByPlanningId = planningsByPlanningId;
        this.permissionsByPermissionId = permissionsByPermissionId;
    }

    public InteractEntity(UsersEntity usersByUserId, PlanningsEntity planningsByPlanningId, PermissionsEntity permissionsByPermissionId) {
        this.usersByUserId = usersByUserId;
        this.planningsByPlanningId = planningsByPlanningId;
        this.permissionsByPermissionId = permissionsByPermissionId;
    }

    public InteractEntity() {

    }

    public InteractEntity(InteractEntityPK id) {
        this.id = id;
    }

    public InteractEntity(int userId, int planningId, int permissionId) {
    }

    public InteractEntityPK getId() {
        return id;
    }

    public void setId(InteractEntityPK id) {
        this.id = id;
    }

    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    public PlanningsEntity getPlanningsByPlanningId() {
        return planningsByPlanningId;
    }

    public void setPlanningsByPlanningId(PlanningsEntity planningsByPlanningId) {
        this.planningsByPlanningId = planningsByPlanningId;
    }

    public PermissionsEntity getPermissionsByPermissionId() {
        return permissionsByPermissionId;
    }

    public void setPermissionsByPermissionId(PermissionsEntity permissionsByPermissionId) {
        this.permissionsByPermissionId = permissionsByPermissionId;
    }
}

