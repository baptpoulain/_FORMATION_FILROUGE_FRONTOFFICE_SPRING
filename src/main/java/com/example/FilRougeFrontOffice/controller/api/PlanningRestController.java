package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.PlanningDto;
import com.example.FilRougeFrontOffice.repository.PlanningRepository;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import com.example.FilRougeFrontOffice.service.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class PlanningRestController {

    @Autowired
    PlanningService planningService;

    @Autowired
    PlanningRepository planningRepository;

 @PostMapping("/planning")
    public ResponseEntity<PlanningsEntity> createPlanning( @RequestBody PlanningsEntity planning){
     try {
         planningService.createPlanning(planning);
         return ResponseEntity.status(HttpStatus.CREATED).body(planning);
     }catch (Exception e){
         return ResponseEntity.noContent().build();
     }
 }

 @GetMapping("/planning/{id}")
 public ResponseEntity<PlanningDto> getPlanning(@PathVariable("id") int id) {
     try {
         Optional<PlanningsEntity> planning = planningService.getPlanning(id);
         if (planning.isPresent()) {
             PlanningDto planningDto = new PlanningDto(planning.get().getPlanningId(), planning.get().getPlanningTitle(),planning.get().getPlanningDescription(), planning.get().getPlanningCreatedAt(), planning.get().getEventsByPlanningId());
             return ResponseEntity.status(HttpStatus.OK).body(planningDto);
         } else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
     } catch (Exception e) {
         return ResponseEntity.noContent().build();
     }
 }

}
