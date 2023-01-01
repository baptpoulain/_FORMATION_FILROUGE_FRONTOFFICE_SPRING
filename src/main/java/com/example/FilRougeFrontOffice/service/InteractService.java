package com.example.FilRougeFrontOffice.service;

import com.example.FilRougeFrontOffice.repository.InteractRepository;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntity;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntityPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InteractService {

    @Autowired
    InteractRepository interactRepository;

    public void addInteraction(InteractEntityPK interaction){
        InteractEntity interactEntityTosave = new InteractEntity(interaction);
        interactRepository.save(interactEntityTosave);
    }

    public Optional<InteractEntity> findById(Integer id){
        InteractEntityPK newInteract = new InteractEntityPK(id);
        return interactRepository.findById(newInteract);
    }
}
