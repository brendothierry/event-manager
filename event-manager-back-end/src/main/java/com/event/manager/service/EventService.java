package com.event.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.manager.model.EventModel;
import com.event.manager.model.InstitutionModel;
import com.event.manager.repositories.EventRepository;
import com.event.manager.repositories.InstitutionRepository;

@Service
@Component
public class EventService {

	private final EventRepository eventRepository;
	private final InstitutionRepository institutionRepository;

	public EventService(EventRepository eventRepository, InstitutionRepository institutionRepository) {
        this.eventRepository = eventRepository;
        this.institutionRepository = institutionRepository;
    }

	
	@Transactional
	public InstitutionModel createInstitution(InstitutionModel institution) {
        return institutionRepository.save(institution);
    }

	@Transactional
	public List<InstitutionModel> findAllInstitutions() {
        return institutionRepository.findAll();
    }
	
	@Transactional
	public EventModel createEvent(EventModel event) {
        event.setActive(true);
        return eventRepository.save(event);
    }
	
	@Transactional
	public List<EventModel> findAllEvents() {
        return eventRepository.findAll();
    }
	
	@Transactional
	public EventModel updateEvent(EventModel event) {
		return eventRepository.save(event);
	}

	@Transactional
	public Optional<EventModel> findEventById(Integer id) {
		return eventRepository.findById(id);
	}

}