package com.example.FilRougeFrontOffice.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "events")
public class EventsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "event_id")
    private int eventId;
    @Basic
    @Column(name = "event_name")
    private String eventName;
    @Basic
    @Column(name = "event_description")
    private String eventDescription;
    @Basic
    @Column(name = "event_startDate")
    private LocalDateTime eventStartDate;
    @Basic
    @Column(name = "event_endDate")
    private LocalDateTime eventEndDate;
    @Basic
    @JsonIgnore
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic
    @Column(name = "planning_id")
    private int planningId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private CategoriesEntity categoriesByCategoryId;
    @ManyToOne
    @JoinColumn(name = "planning_id", referencedColumnName = "planning_id", insertable = false, updatable = false)
    private PlanningsEntity planningsByPlanningId;

    public EventsEntity(int eventId, String eventName, String eventDescription, LocalDateTime eventStartDate, LocalDateTime eventEndDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public EventsEntity(String eventName, String eventDescription, LocalDateTime eventStartDate, LocalDateTime eventEndDate) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public EventsEntity() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDateTime getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDateTime eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public LocalDateTime getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDateTime eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public int getPlanningId() {
        return planningId;
    }

    public void setPlanningId(int planningId) {
        this.planningId = planningId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsEntity that = (EventsEntity) o;

        if (eventId != that.eventId) return false;
        if (categoryId != that.categoryId) return false;
        if (planningId != that.planningId) return false;
        if (!Objects.equals(eventName, that.eventName)) return false;
        if (!Objects.equals(eventDescription, that.eventDescription))
            return false;
        if (!Objects.equals(eventStartDate, that.eventStartDate))
            return false;
        return Objects.equals(eventEndDate, that.eventEndDate);
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (eventDescription != null ? eventDescription.hashCode() : 0);
        result = 31 * result + (eventStartDate != null ? eventStartDate.hashCode() : 0);
        result = 31 * result + (eventEndDate != null ? eventEndDate.hashCode() : 0);
        result = 31 * result + categoryId;
        result = 31 * result + planningId;
        return result;
    }

    public CategoriesEntity getCategoriesByCategoryId() {
        return categoriesByCategoryId;
    }

    public void setCategoriesByCategoryId(CategoriesEntity categoriesByCategoryId) {
        this.categoriesByCategoryId = categoriesByCategoryId;
    }

    public PlanningsEntity getPlanningsByPlanningId() {
        return planningsByPlanningId;
    }

    public void setPlanningsByPlanningId(PlanningsEntity planningsByPlanningId) {
        this.planningsByPlanningId = planningsByPlanningId;
    }
}
