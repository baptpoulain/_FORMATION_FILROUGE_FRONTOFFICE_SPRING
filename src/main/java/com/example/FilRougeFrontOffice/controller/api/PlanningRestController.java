package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.EventDto;
import com.example.FilRougeFrontOffice.controller.dto.PlanningDto;

import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.service.PlanningService;
import com.example.FilRougeFrontOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
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
    public ResponseEntity<PlanningDto> findPlanningFromUser(@PathVariable int id){
        Optional<PlanningsEntity> planningFromUser = planningService.findPlanningByUserId(id);
        if(planningFromUser.isPresent()){
            PlanningDto dto = new PlanningDto(
                    planningFromUser.get().getPlanningId(),
                    planningFromUser.get().getPlanningTitle(),
                    planningFromUser.get().getPlanningDescription(),
                    planningFromUser.get().getPlanningCreatedAt(),
                    planningFromUser.get().getEventsByPlanningId()
                    );
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 @PostMapping("/planning")
    public ResponseEntity<PlanningsEntity> createPlanning(@RequestBody PlanningsEntity planning){
     try {
         Optional<UsersEntity> userBelongThisPlanning = userService.findById(planning.getUserId());

         if(userBelongThisPlanning.isPresent() ){
             planning.setUsersByUserId(userBelongThisPlanning.get());
         }else{
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
         }

         Optional<PlanningsEntity> planningFromUser = planningService.findPlanningByUserId(planning.getUserId());

         if(planningFromUser.isPresent()){
             return ResponseEntity.status(HttpStatus.CONFLICT).build();
         } else{
             planningService.createPlanning(planning);
             return ResponseEntity.status(HttpStatus.CREATED).build();
         }
     }catch (Exception e){
         return ResponseEntity.noContent().build();
     }
 }


 @GetMapping("/planning/{id}")
 public ResponseEntity<PlanningDto> getPlanning(@PathVariable("id") int id) {
     try {
         Optional<PlanningsEntity> planning = planningService.getPlanning(id);
         if (planning.isPresent()) {
             PlanningDto planningDto = new PlanningDto(planning.get().getPlanningId(), planning.get().getPlanningTitle(), planning.get().getPlanningDescription(), planning.get().getPlanningCreatedAt(), planning.get().getEventsByPlanningId());
             return ResponseEntity.status(HttpStatus.OK).body(planningDto);
         } else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
     } catch (Exception e) {
         return ResponseEntity.noContent().build();
     }
 }

 @PostMapping("/planning/event")
    public ResponseEntity<EventDto> createEventInPlanning(@RequestBody EventsEntity event){
     try{
         Optional<PlanningsEntity> planningByEvent = planningService.findPlanningById(event.getPlanningId());

         if(planningByEvent.isPresent()){
             EventsEntity eventEntityAdd = planningService.createEvent(event);
             EventDto eventToSend = EventDto.from(eventEntityAdd);
             return ResponseEntity.status(HttpStatus.CREATED).body(eventToSend);
         }else{
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
     }catch (Exception e){
         return ResponseEntity.noContent().build();
     }
 }

 @PutMapping("/planning/event/{id}")
/*    public ResponseEntity<EventsEntity> updateEventInPlanning(@RequestBody EventsEntity eventsEntity){
     Optional<List<EventsEntity>> eventData = planningService.findEventListByPlanningId(eventsEntity.getPlanningId());
     if(eventData.isPresent()){
         planningService.updEvent(eventData.get().stream().filter(event-> event.getEventId() == eventsEntity.getEventId()).collect(Collectors.toList()).get(0), eventsEntity);
         return new ResponseEntity<>(HttpStatus.OK);
     } else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }*/
     public ResponseEntity<EventDto> updateEventInPlanning(@PathVariable int id, @RequestBody EventsEntity eventsEntity){
         Optional<EventsEntity> event = planningService.findEventById(id);
         if(event.isPresent()){
             EventsEntity eventEntityUpdate = planningService.updEvent(event.get(), eventsEntity);
             EventDto eventToSend = EventDto.from(eventEntityUpdate);
             return ResponseEntity.status(HttpStatus.CREATED).body(eventToSend);
         } else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
 }

 @DeleteMapping("/planning/event/{id}")
    public ResponseEntity<EventsEntity> deleteEventInPlanning(@PathVariable int id){
        try {
            planningService.delEvent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }
 }