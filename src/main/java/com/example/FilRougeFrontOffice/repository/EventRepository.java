package com.example.FilRougeFrontOffice.repository;

import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<EventsEntity, Integer> {

    Optional<List<EventsEntity>> findEventsEntitiesByPlanningId(int id);

}
