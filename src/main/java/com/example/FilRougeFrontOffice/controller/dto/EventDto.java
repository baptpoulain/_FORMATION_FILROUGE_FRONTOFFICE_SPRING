package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;


import java.time.LocalDateTime;
import java.util.Date;




public class EventDto {

    private int id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;

    public EventDto() {
    }


     public EventDto(int eventId, String eventName, String eventDescription, LocalDateTime eventStartDate, LocalDateTime eventEndDate) {
        this.id = eventId;
        this.title = eventName;
        this.start = eventStartDate;
        this.end = eventEndDate;
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
