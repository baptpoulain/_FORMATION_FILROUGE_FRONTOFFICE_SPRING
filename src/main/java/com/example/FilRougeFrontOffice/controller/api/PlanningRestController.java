package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.PlanningDto;
import com.example.FilRougeFrontOffice.repository.EventRepository;
import com.example.FilRougeFrontOffice.repository.PlanningRepository;
import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.service.PlanningService;
import com.example.FilRougeFrontOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class PlanningRestController {

    @Autowired
    PlanningService planningService;

    @Autowired
    UserService userService;

    @GetMapping("user/{id}/planning")
    public ResponseEntity<PlanningsEntity> findPlanningFromUser(@PathVariable int id){
        Optional<PlanningsEntity> planningFromUser = planningService.findPlanningByUserId(id);
        if(planningFromUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(planningFromUser.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

 @PostMapping("/planning")
    public ResponseEntity<PlanningsEntity> createPlanning(@RequestBody PlanningsEntity planning){
     try {
         Optional<UsersEntity> userBelongThisPlanning = userService.findById(planning.getUserId());
         if(userBelongThisPlanning.isPresent()){
             planning.setUsersByUserId(userBelongThisPlanning.get());
         }
         Optional<PlanningsEntity> planningFromUser = planningService.findPlanningByUserId(planning.getUserId());
         if(planningFromUser.isPresent()){
             return ResponseEntity.status(HttpStatus.CONFLICT).build();
         }
         else{
             planningService.createPlanning(planning);
             return ResponseEntity.status(HttpStatus.CREATED).body(planning);
         }
     }catch (Exception e){
         return ResponseEntity.noContent().build();
     }
 }

 @PostMapping("/planning/{id}/event")
    public ResponseEntity<EventsEntity> createEventInPlanning(@PathVariable int id, @RequestBody EventsEntity event){
     try{
         event.setPlanningId(id);
         Optional<PlanningsEntity> planningByEvent = planningService.findPlanningById(id);
         if(planningByEvent.isPresent()){
             event.setPlanningsByPlanningId(planningByEvent.get());
         }
         planningService.createEvent(event);
         return ResponseEntity.status(HttpStatus.CREATED).body(event);
     }catch (Exception e){
         return ResponseEntity.noContent().build();
     }
 }

 @PutMapping("/planning/{id}/event")
    public ResponseEntity<EventsEntity> updateEventInPlanning(@PathVariable int id, @RequestBody EventsEntity eventsEntity){
     Optional<List<EventsEntity>> eventData = planningService.findEventListByPlanningId(id);
     if(eventData.isPresent()){
         planningService.updEvent(eventData.get().stream().filter(event-> event.getEventId() == eventsEntity.getEventId()).collect(Collectors.toList()).get(0), eventsEntity);
         return new ResponseEntity<>(HttpStatus.OK);
     } else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
 }

 @DeleteMapping("/planning/{id}/event")
    public ResponseEntity<EventsEntity> deleteEventInPlanning(@PathVariable int id, @RequestBody EventsEntity eventsEntity){
     Optional<List<EventsEntity>> eventData = planningService.findEventListByPlanningId(id);
     if(eventData.isPresent()){
         planningService.delEvent(eventData.get().stream().filter(event-> event.getEventId() == eventsEntity.getEventId()).collect(Collectors.toList()).get(0));
         return new ResponseEntity<>(HttpStatus.OK);
     } else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
 }



}
