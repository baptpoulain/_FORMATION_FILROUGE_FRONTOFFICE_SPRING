package com.example.FilRougeFrontOffice.repository;

import com.example.FilRougeFrontOffice.repository.entity.InteractEntity;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntityPK;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractRepository extends CrudRepository<InteractEntity, InteractEntityPK> {


    List<InteractEntity> findByUsersByUserId(UsersEntity usersByUserId);

    List<InteractEntity> findByPlanningsByPlanningIdOrderByUsersByUserId(PlanningsEntity planningsByPlanningId);

    @Query("select i from InteractEntity i where i.usersByUserId.userId = ?1 group by i.planningsByPlanningId")
    List<InteractEntity>  findByUsersByUserIdGroupByPlanning(int userId);


    @Query("select i from InteractEntity i where i.planningsByPlanningId.planningId = ?1 group by i.usersByUserId")
    List<InteractEntity> findByPlanningsByPlanningId_PlanningId(int planningId);

}
