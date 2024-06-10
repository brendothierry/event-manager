package com.event.manager.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.manager.model.EventModel;
import com.event.manager.model.InstitutionModel;
import com.event.manager.service.EventService;

@Controller
@RequestMapping("/events")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EventController {

	final EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@PostMapping("/createInstitution")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<InstitutionModel> createInstitution(@RequestBody @Valid InstitutionModel institutionModel) {
		InstitutionModel savedEvent = eventService.createInstitution(institutionModel);
		return ResponseEntity.status(HttpStatus.OK).body(savedEvent);
	}

	@GetMapping("/findAllInstitutions")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<InstitutionModel>> getAllInstitutions() {
		return ResponseEntity.status(HttpStatus.OK).body(eventService.findAllInstitutions());
	}

	@PostMapping("/createEvent")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<EventModel> createEvent(@RequestBody @Valid EventModel eventModel) {
		EventModel savedEvent = eventService.createEvent(eventModel);
		return ResponseEntity.status(HttpStatus.OK).body(savedEvent);
	}

	@GetMapping("/findAllEvents")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<EventModel>> findAllEvents() {
		return ResponseEntity.status(HttpStatus.OK).body(eventService.findAllEvents());
	}

	@PutMapping("/updateEvent")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<EventModel> updateEvent(@RequestBody EventModel event) {
		try {
            Optional<EventModel> existingEvent = eventService.findEventById(event.getId());

            if (existingEvent.isPresent()) {
            	event.setActive(false);
            	event.setEndDate(event.getEndDate());
            	event.setName(event.getName());
            	event.setStartDate(event.getStartDate());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventService.updateEvent(event));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
