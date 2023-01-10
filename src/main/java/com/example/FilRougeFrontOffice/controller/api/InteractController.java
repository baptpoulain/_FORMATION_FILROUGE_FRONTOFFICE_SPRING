package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.InteractEntityDtoByUserAndPermission;
import com.example.FilRougeFrontOffice.message.ResponseMessage;
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
        String message ="";
        try{
            interactService.addInteraction(interactPlanning);
            message = "Create interaction successfully";
            return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
        }catch (Exception e) {
            message = "Fail to create interaction";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(message));
        }
    }

    @GetMapping("interact/user/{id}")
    public ResponseEntity<List<InteractEntityDto>> findInteractionByUserId(@PathVariable("id") int id){
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
        return interactData.isPresent();
    }
    
    @DeleteMapping("interact/planning/{userId}/{planningId}/{permissionId}")
    public ResponseEntity<?> deleteInteractionByPlanningIdAndUserId(@PathVariable("userId") int userId,@PathVariable("planningId") int planningId, @PathVariable("permissionId") int permissionId ){
        String message = "";
        InteractEntityPK interact = new InteractEntityPK(userId,planningId, permissionId);

        Optional<InteractEntity> interactData = interactService.findById(interact);
        if(interactData.isPresent()){
            interactService.deleteInteract(interact);
            message = "Interaction is delete successfully";

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }else{
            message = "Fail to delete the interaction";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
        }

    }

}
