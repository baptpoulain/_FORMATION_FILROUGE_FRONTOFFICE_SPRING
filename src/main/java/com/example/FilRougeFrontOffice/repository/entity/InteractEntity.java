package com.example.FilRougeFrontOffice.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "interact", schema = "bdd_fil_rouge")
@IdClass(InteractEntityPK.class)
public class InteractEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "planning_id")
    private int planningId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permission_id")
    private int permissionId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UsersEntity usersByUserId;
    @ManyToOne
    @JoinColumn(name = "planning_id", referencedColumnName = "planning_id", insertable = false, updatable = false)
    private PlanningsEntity planningsByPlanningId;
    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id", insertable = false, updatable = false)
    private PermissionsEntity permissionsByPermissionId;

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

        InteractEntity that = (InteractEntity) o;

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

