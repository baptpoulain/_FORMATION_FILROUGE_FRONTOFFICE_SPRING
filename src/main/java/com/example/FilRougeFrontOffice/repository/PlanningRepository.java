package com.example.FilRougeFrontOffice.repository;

import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanningRepository extends CrudRepository<PlanningsEntity, Integer> {

    Optional<PlanningsEntity> findPlanningsEntityByUserId(int id);


}
