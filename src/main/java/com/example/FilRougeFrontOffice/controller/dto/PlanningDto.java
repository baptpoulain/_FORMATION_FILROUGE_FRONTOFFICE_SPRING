package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.sql.Date;
import java.util.Collection;
import java.util.stream.Collectors;

public class PlanningDto {

    private int planningId;

    private String planningTitle;

    private String planningDescription;

    private Date planningCreatedAt;

    private Collection<EventDto> eventsByPlanningId;

    public Collection<EventDto> getEventsByPlanningId() {
        return eventsByPlanningId;
    }

    public void setEventsByPlanningId(Collection<EventDto> eventsByPlanningId) {
        this.eventsByPlanningId = eventsByPlanningId;
    }

    public PlanningDto() {
    }

    public PlanningDto(String planningTitle, String planningDescription, Date planningCreatedAt,Collection<EventsEntity> eventsByPlanningId ) {
        this.planningTitle = planningTitle;
        this.planningDescription = planningDescription;
        this.planningCreatedAt = planningCreatedAt;
        this.eventsByPlanningId = eventsByPlanningId.stream().map(e -> EventDto.from(e)).collect(Collectors.toList());
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
