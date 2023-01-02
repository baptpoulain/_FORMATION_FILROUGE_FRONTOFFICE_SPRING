package com.example.FilRougeFrontOffice.service;

import com.example.FilRougeFrontOffice.repository.EventRepository;
import com.example.FilRougeFrontOffice.repository.PlanningRepository;
import com.example.FilRougeFrontOffice.repository.entity.EventsEntity;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningService {

    @Autowired
    PlanningRepository planningRepository;

    @Autowired
    EventRepository eventRepository;

    public void createPlanning(PlanningsEntity planning) {
        planningRepository.save(planning);
    }
    public Optional<PlanningsEntity> getPlanning(int id) {
        return planningRepository.findById(id);
    }
    public EventsEntity createEvent(EventsEntity event) {
        return eventRepository.save(event);
    }

    public Optional<List<EventsEntity>> findEventListByPlanningId(int id) {
        return eventRepository.findEventsEntitiesByPlanningId(id);
    }

    public Optional<PlanningsEntity> findPlanningById(int id) {
        return planningRepository.findById(id);
    }

    public void updEvent(EventsEntity eventData, EventsEntity event) {
        eventData.setEventName(event.getEventName());
        eventData.setEventDescription(event.getEventDescription());
        eventData.setEventStartDate(event.getEventStartDate());
        eventData.setEventEndDate(event.getEventEndDate());
        eventRepository.save(eventData);
    }

    public void delEvent(int id) {
        eventRepository.deleteById(id);
    }

    public Optional<PlanningsEntity> findPlanningByUserId(int id) {
        return planningRepository.findPlanningsEntityByUserId(id);
    }
}
