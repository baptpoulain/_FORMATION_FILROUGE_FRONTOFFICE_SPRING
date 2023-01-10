package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.EventDto;
import com.example.FilRougeFrontOffice.controller.dto.InteractEntityDtoByUserAndPermission;
import com.example.FilRougeFrontOffice.controller.dto.PlanningDto;

import com.example.FilRougeFrontOffice.message.ResponseMessage;
import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.service.InteractService;
import com.example.FilRougeFrontOffice.service.PlanningService;
import com.example.FilRougeFrontOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.FilRougeFrontOffice.controller.api.AuthController.defaultProperties;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class PlanningRestController {

    @Autowired
    PlanningService planningService;

    @Autowired
    UserService userService;

    @Autowired
    InteractService interactService;


    @GetMapping("user/{id}/planning")
    public ResponseEntity<PlanningDto> findPlanningFromUser(@PathVariable int id) {
        Optional<PlanningsEntity> planningFromUser = planningService.findPlanningByUserId(id);
        if (planningFromUser.isPresent()) {
            PlanningDto dto = new PlanningDto(
                    planningFromUser.get().getPlanningId(),
                    planningFromUser.get().getPlanningTitle(),
                    planningFromUser.get().getPlanningDescription(),
                    planningFromUser.get().getPlanningCreatedAt(),
                    planningFromUser.get().getEventsByPlanningId()
            );
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/planning")
    public ResponseEntity<PlanningsEntity> createPlanning(@RequestBody PlanningsEntity planning) {
        try {
            Optional<UsersEntity> userBelongThisPlanning = userService.findById(planning.getUserId());

            if (userBelongThisPlanning.isPresent()) {
                planning.setUsersByUserId(userBelongThisPlanning.get());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            Optional<PlanningsEntity> planningFromUser = planningService.findPlanningByUserId(planning.getUserId());

            if (planningFromUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                planningService.createPlanning(planning);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("planning/{id}")
    public ResponseEntity<PlanningsEntity> updatePlanning(@PathVariable int id, @RequestBody PlanningDto planningDto) {
        Optional<PlanningsEntity> planningData = planningService.findPlanningById(id);

        if (planningData.isPresent()) {
            planningService.updPlanning(planningData.get(), planningDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

     int userLoginId = (int) defaultProperties.get("userLoginId");

     try{

        if(event.getEventEndDate().isBefore(event.getEventStartDate())){
            throw new IllegalArgumentException();
        }


         Optional<PlanningsEntity> planningByEvent = planningService.findPlanningById(event.getPlanningId());

        // Vérifie si l'utilisateur est le propriétaire du calendrier ou qu'il possède les autorisations d'écriture

         List<InteractEntityDtoByUserAndPermission> interaction = interactService.findByPlanningIdAndUserId(event.getPlanningId(), userLoginId);
         Optional<InteractEntityDtoByUserAndPermission> isReading = interaction.stream()
                   .filter(p -> p.getPermissionsByPermissionId().getPermissionId() == 2)
                 .findFirst();


         if(planningByEvent.isPresent()){

             if(isReading.isPresent() || planningByEvent.get().getUserId() == userLoginId ){
                 EventsEntity eventEntityAdd = planningService.createEvent(event);
                 EventDto eventToSend = EventDto.from(eventEntityAdd);
                 return ResponseEntity.status(HttpStatus.CREATED).body(eventToSend);
             }else{
                 throw new RuntimeException();
             }

         }else{
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
     }catch (IllegalArgumentException e){
         return ResponseEntity.badRequest().build();
     } catch (Exception e){
         return ResponseEntity.noContent().build();
     }
 }

 @PutMapping("/planning/event/{id}")

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
    public ResponseEntity<?> deleteEventInPlanning(@PathVariable int id){
        String message = "";
        try {
            planningService.delEvent(id);
            message = "Event is delete successfully";
            return ResponseEntity.status((HttpStatus.OK)).body(new ResponseMessage(message));
        }catch (Exception e) {
            message = "Fail to delete the event";
            return  ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ResponseMessage(message));
        }
     }
 }