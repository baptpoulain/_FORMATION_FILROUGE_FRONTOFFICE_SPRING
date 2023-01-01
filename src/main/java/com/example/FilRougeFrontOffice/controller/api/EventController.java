package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.EventDto;
import com.example.FilRougeFrontOffice.repository.EventRepository;
import com.example.FilRougeFrontOffice.repository.PlanningRepository;
import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import com.example.FilRougeFrontOffice.service.EventService;
import com.example.FilRougeFrontOffice.service.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

    @PostMapping("event")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventsEntity event){
        try{
            EventsEntity eventEntityAdd = eventService.addEvent(event);
            EventDto eventToSend = EventDto.from(eventEntityAdd);
            return  ResponseEntity.status(HttpStatus.CREATED).body(eventToSend);

        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") int id){
        try{
        eventService.deleteEvent(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
