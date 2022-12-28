package com.example.FilRougeFrontOffice.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "plannings", schema = "bdd_fil_rouge")
public class PlanningsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "planning_id")
    private int planningId;
    @Basic
    @Column(name = "planning_title")
    private String planningTitle;
    @Basic
    @Column(name = "planning_description")
    private String planningDescription;
    @Basic
    @Column(name = "planning_createdAt")
    private Date planningCreatedAt;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @OneToMany(mappedBy = "planningsByPlanningId")
    @JsonIgnore
    private Collection<EventsEntity> eventsByPlanningId;
    @OneToMany(mappedBy = "planningsByPlanningId")
    @JsonIgnore
    private Collection<InteractEntity> interactsByPlanningId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UsersEntity usersByUserId;


    public PlanningsEntity(String planningTitle, String planningDescription, Date planningCreatedAt) {
        this.planningTitle = planningTitle;
        this.planningDescription = planningDescription;
        this.planningCreatedAt = planningCreatedAt;
    }

    public PlanningsEntity() {

    }

    public int getPlanningId() {
        return planningId;
    }

    public void setPlanningId(int planningId) {
        this.planningId = planningId;
    }

    public String getPlanningTitle() {
        return planningTitle;
    }

    public void setPlanningTitle(String planningTitle) {
        this.planningTitle = planningTitle;
    }

    public String getPlanningDescription() {
        return planningDescription;
    }

    public void setPlanningDescription(String planningDescription) {
        this.planningDescription = planningDescription;
    }

    public Date getPlanningCreatedAt() {
        return planningCreatedAt;
    }

    public void setPlanningCreatedAt(Date planningCreatedAt) {
        this.planningCreatedAt = planningCreatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanningsEntity that = (PlanningsEntity) o;

        if (planningId != that.planningId) return false;
        if (userId != that.userId) return false;
        if (planningTitle != null ? !planningTitle.equals(that.planningTitle) : that.planningTitle != null)
            return false;
        if (planningDescription != null ? !planningDescription.equals(that.planningDescription) : that.planningDescription != null)
            return false;
        if (planningCreatedAt != null ? !planningCreatedAt.equals(that.planningCreatedAt) : that.planningCreatedAt != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = planningId;
        result = 31 * result + (planningTitle != null ? planningTitle.hashCode() : 0);
        result = 31 * result + (planningDescription != null ? planningDescription.hashCode() : 0);
        result = 31 * result + (planningCreatedAt != null ? planningCreatedAt.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }

    public Collection<EventsEntity> getEventsByPlanningId() {
        return eventsByPlanningId;
    }

    public void setEventsByPlanningId(Collection<EventsEntity> eventsByPlanningId) {
        this.eventsByPlanningId = eventsByPlanningId;
    }

    public Collection<InteractEntity> getInteractsByPlanningId() {
        return interactsByPlanningId;
    }

    public void setInteractsByPlanningId(Collection<InteractEntity> interactsByPlanningId) {
        this.interactsByPlanningId = interactsByPlanningId;
    }

    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}

