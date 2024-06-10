package com.event.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.event.manager.controller.EventController;
import com.event.manager.model.EventModel;
import com.event.manager.model.InstitutionModel;
import com.event.manager.service.EventService;

public class EventControllerTest {

	@InjectMocks
	private EventController eventController;

	@Mock
	private EventService eventService;

	public EventControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateInstitution() {
		InstitutionModel institution = new InstitutionModel();
		institution.setId(1);
		institution.setName("Test Institution");

		when(eventService.createInstitution(institution)).thenReturn(institution);

		ResponseEntity<InstitutionModel> response = eventController.createInstitution(institution);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(institution, response.getBody());
	}

	@Test
	public void testGetAllInstitutions() {
		List<InstitutionModel> institutions = Arrays.asList(new InstitutionModel());

		when(eventService.findAllInstitutions()).thenReturn(institutions);

		ResponseEntity<List<InstitutionModel>> response = eventController.getAllInstitutions();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(institutions, response.getBody());
	}
	
	@Test
    public void testCreateEvent() {
        EventModel event = new EventModel();
        event.setId(1);
        event.setName("Test Event");

        when(eventService.createEvent(event)).thenReturn(event);

        ResponseEntity<EventModel> response = eventController.createEvent(event);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(event, response.getBody());
    }

    @Test
    public void testFindAllEvents() {
        List<EventModel> events = Arrays.asList(new EventModel());

        when(eventService.findAllEvents()).thenReturn(events);

        ResponseEntity<List<EventModel>> response = eventController.findAllEvents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(events, response.getBody());
    }

    @Test
    public void testUpdateEvent() {
        EventModel event = new EventModel();
        event.setId(1);
        event.setName("Test Event");
        Optional<EventModel> optionalEvent = Optional.of(event);

        when(eventService.findEventById(1)).thenReturn(optionalEvent);
        when(eventService.updateEvent(event)).thenReturn(event);

        ResponseEntity<EventModel> response = eventController.updateEvent(event);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(event, response.getBody());
    }
}
