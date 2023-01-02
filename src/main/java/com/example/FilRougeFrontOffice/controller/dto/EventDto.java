package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;

import java.sql.Date;

public class EventDto {

    private int id;
    private String title;
    private Date start;
    private Date end;

    public EventDto() {
    }

    public EventDto(int id, String title, Date start, Date end) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public static EventDto from(EventsEntity eventsEntity){
        EventDto dto = new EventDto();
        dto.setId(eventsEntity.getEventId());
        dto.setTitle(eventsEntity.getEventName());
        dto.setStart(eventsEntity.getEventStartDate());
        dto.setEnd(eventsEntity.getEventEndDate());
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
