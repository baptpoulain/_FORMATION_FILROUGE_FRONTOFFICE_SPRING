package com.example.FilRougeFrontOffice.controller.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.sql.Date;

public class PlanningDto {

    private int planningId;

    private String planningTitle;

    private String planningDescription;

    private Date planningCreatedAt;

    public PlanningDto() {
    }

    public PlanningDto(String planningTitle, String planningDescription, Date planningCreatedAt) {
        this.planningTitle = planningTitle;
        this.planningDescription = planningDescription;
        this.planningCreatedAt = planningCreatedAt;
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
}
