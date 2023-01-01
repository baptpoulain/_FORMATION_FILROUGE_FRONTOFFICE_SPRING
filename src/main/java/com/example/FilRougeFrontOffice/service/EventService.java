package com.example.FilRougeFrontOffice.service;

import com.example.FilRougeFrontOffice.repository.EventRepository;
import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;


    public EventsEntity addEvent(EventsEntity event) {
        eventRepository.save(event);
        return event;
    }

    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }
}
