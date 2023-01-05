package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.InteractEntityDtoByUserAndPermission;
import com.example.FilRougeFrontOffice.repository.InteractRepository;
import com.example.FilRougeFrontOffice.controller.dto.InteractEntityDto;
import com.example.FilRougeFrontOffice.controller.dto.InteractEntityDtoByPlanning;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntity;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntityPK;
import com.example.FilRougeFrontOffice.service.InteractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class InteractController {
    @Autowired
    InteractService interactService;

    @Autowired
    InteractRepository interactRepository;

    @PostMapping("interact")
    public ResponseEntity<?> createInteraction(@RequestBody InteractEntityPK interactPlanning){
        try{
            interactService.addInteraction(interactPlanning);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("interact/user/{id}")
    public ResponseEntity<?> findInteractionByUserId(@PathVariable("id") int id){
        try{
            List<InteractEntityDto> list = interactService.findByUserId(id);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("interact/planning/{id}")
    public ResponseEntity<?> findInteractionByPlanningId(@PathVariable("id") int id){
        try{
            List<InteractEntityDtoByPlanning> list = interactService.findByPlanningId(id);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("interact/planning/{planningId}/{userId}")
    public ResponseEntity<?> findInteractionByPlanningIdAndUserId(@PathVariable("planningId") int planningId, @PathVariable("userId") int userId){
        try{
            List<InteractEntityDtoByUserAndPermission> list = interactService.findByPlanningIdAndUserId(planningId, userId);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("interact/planning/{userId}/{planningId}/{permissionId}")
    public boolean findInteractionByPlanningIdAndUserId(@PathVariable("userId") int userId, @PathVariable("planningId") int planningId, @PathVariable("permissionId") int permissionId ){
        InteractEntityPK interactId = new InteractEntityPK(userId,planningId, permissionId);

        Optional<InteractEntity> interactData = interactService.findById(interactId);
        if(interactData.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }
    
    @DeleteMapping("interact/planning/{userId}/{planningId}/{permissionId}")
    public ResponseEntity<?> deleteInteractionByPlanningIdAndUserId(@PathVariable("userId") int userId,@PathVariable("planningId") int planningId, @PathVariable("permissionId") int permissionId ){
        InteractEntity interact = new InteractEntity(userId,planningId, permissionId);
        interactService.deleteInteract(interact);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
