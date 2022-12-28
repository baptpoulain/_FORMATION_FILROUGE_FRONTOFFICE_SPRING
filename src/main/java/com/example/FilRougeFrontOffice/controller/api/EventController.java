package com.example.FilRougeFrontOffice.controller.api;

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
    public ResponseEntity<EventsEntity> createEvent(@RequestBody EventsEntity event){
        try{
            eventService.addEvent(event);
            return  ResponseEntity.status(HttpStatus.CREATED).body(event);

        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
