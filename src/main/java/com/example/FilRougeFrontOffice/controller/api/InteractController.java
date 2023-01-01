package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.EventDto;
import com.example.FilRougeFrontOffice.repository.InteractRepository;
import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntity;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntityPK;
import com.example.FilRougeFrontOffice.service.InteractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("interact/{id}")
    public ResponseEntity<?> findInteractionByUserId(@PathVariable("id") int id){
        try{
            interactService.findById(id);
            return  ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
