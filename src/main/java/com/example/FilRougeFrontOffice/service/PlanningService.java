package com.example.FilRougeFrontOffice.service;

import com.example.FilRougeFrontOffice.repository.PlanningRepository;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanningService {


    @Autowired
    PlanningRepository planningRepository;

    public void createPlanning(PlanningsEntity planning) {
        planningRepository.save(planning);
    }
}
