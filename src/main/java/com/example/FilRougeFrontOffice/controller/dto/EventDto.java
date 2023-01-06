package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;


import java.time.LocalDateTime;
import java.util.Date;




public class EventDto {

    private int id;
    private String title;


    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public EventDto() {
    }


     public EventDto(int eventId, String eventName, String eventDescription, LocalDateTime eventStartDate, LocalDateTime eventEndDate, String description) {
        this.id = eventId;
        this.title = eventName;
        this.start = eventStartDate;
        this.end = eventEndDate;
        this.description = description;
    }


    public static EventDto from(EventsEntity eventsEntity){
        EventDto dto = new EventDto();
        dto.setId(eventsEntity.getEventId());
        dto.setTitle(eventsEntity.getEventName());
        dto.setStart(eventsEntity.getEventStartDate());
        dto.setEnd(eventsEntity.getEventEndDate());
        dto.setDescription(eventsEntity.getEventDescription());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
