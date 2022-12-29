package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;

import java.util.Date;

public class EventDto {
//todo pour pouvoir bien recuperer sur fullcalendar
    private int id;

    private String title;

    private Date start;

    private Date end;

    public EventDto() {
    }

    public EventDto(int eventId, String eventName, String eventDescription, Date eventStartDate, Date eventEndDate) {
        this.id = eventId;
        this.title = eventName;
        this.start = eventStartDate;
        this.end = eventEndDate;
    }

    public static EventDto from(EventsEntity eventsEntity) {
        EventDto dto = new EventDto();
        dto.setId(eventsEntity.getEventId());
        dto.setTitle(eventsEntity.getEventName());
        dto.setEnd(eventsEntity.getEventEndDate());
        dto.setStart(eventsEntity.getEventStartDate());
        return dto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
