package com.example.FilRougeFrontOffice.service;

import com.example.FilRougeFrontOffice.controller.dto.InteractEntityDto;
import com.example.FilRougeFrontOffice.controller.dto.InteractEntityDtoByPlanning;
import com.example.FilRougeFrontOffice.controller.dto.InteractEntityDtoByUserAndPermission;
import com.example.FilRougeFrontOffice.repository.InteractRepository;
import com.example.FilRougeFrontOffice.repository.PlanningRepository;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InteractService {

    @Autowired
    InteractRepository interactRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlanningRepository planningRepository;

    public void addInteraction(InteractEntityPK interaction){
        InteractEntity interactEntityTosave = new InteractEntity(interaction);
        interactRepository.save(interactEntityTosave);
    }


    // GROUP BY PLANNING ID POUR LE HEADER ET MENU DEROULANT
    public List<InteractEntityDto> findByUserId(int id){

        Optional<UsersEntity> user = userRepository.findById(id);
        if(user.isPresent()) {
            List<InteractEntity> list = interactRepository.findByUsersByUserIdGroupByPlanning(user.get().getUserId());
    /*        List<InteractEntity> list = interactRepository.findByPlanningsByPlanningIdGroupByUsersByUserId(planning.get());*/
            return list.stream()
                    .map(i -> InteractEntityDto.from(i))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<InteractEntityDtoByPlanning> findByPlanningId(int id){

        Optional<PlanningsEntity> planning = planningRepository.findById(id);
        if(planning.isPresent()) {
          /*  List<InteractEntity> list = interactRepository.findByPlanningsByPlanningIdOrderByUsersByUserId(planning.get());*/
              List<InteractEntity> list = interactRepository.findByPlanningIdGroupByUserId(planning.get().getPlanningId());
            return list.stream()
                    .map(i -> InteractEntityDtoByPlanning.from(i))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<InteractEntityDtoByUserAndPermission> findByPlanningIdAndUserId(int planningId, int userId){
        Optional<PlanningsEntity> planning = planningRepository.findById(planningId);
        Optional<UsersEntity> user = userRepository.findById(userId);

        if(planning.isPresent() && user.isPresent()){
            List<InteractEntity> list = interactRepository.findByPlanningsByPlanningIdAndUsersByUserId(planning.get(), user.get());
                    return list.stream()
                            .map(i -> InteractEntityDtoByUserAndPermission.from(i))
                            .collect(Collectors.toList());
        }
        return null;
    }

    public Optional<InteractEntity> findById(InteractEntityPK interactId) {
        return interactRepository.findById(interactId);
    }

    public void deleteInteract(InteractEntityPK interactEntity) {
        interactRepository.deleteById(interactEntity);
    }
}



/*
 return userList.stream()
         .map(u -> UserDto.from(u))
         .collect(Collectors.toList());*/
