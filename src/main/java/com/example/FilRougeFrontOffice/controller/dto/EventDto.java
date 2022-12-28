package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;

import java.util.Date;

public class EventDto {

    private int eventId;

    private String eventName;

    private String eventDescription;

    private Date eventStartDate;

    private Date eventEndDate;

    public EventDto() {
    }

    public EventDto(int eventId, String eventName, String eventDescription, Date eventStartDate, Date eventEndDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public static EventDto from(EventsEntity eventsEntity){
        EventDto dto = new EventDto();
        dto.setEventId(eventsEntity.getEventId());
        dto.setEventDescription(eventsEntity.getEventDescription());
        dto.setEventName(eventsEntity.getEventName());
        dto.setEventEndDate(eventsEntity.getEventEndDate());
        dto.setEventStartDate(eventsEntity.getEventStartDate());
        return dto;
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

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }
}
