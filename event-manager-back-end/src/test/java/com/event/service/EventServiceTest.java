package com.event.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.event.manager.model.EventModel;
import com.event.manager.repositories.EventRepository;
import com.event.manager.service.EventService;

public class EventServiceTest {

	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventService eventService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateEvent() {
		// Mock data
		EventModel event = new EventModel();
		event.setName("Test Event");
		event.setActive(true);

		when(eventRepository.save(any(EventModel.class))).thenReturn(event);

		// Call the method
		EventModel createdEvent = eventService.createEvent(event);

		// Verify the result
		assertNotNull(createdEvent);
		assertTrue(createdEvent.getActive());
		assertEquals("Test Event", createdEvent.getName());
	}

	@Test
	void testFindAllEvents() {
		// Mock data
		List<EventModel> events = new ArrayList<>();
		events.add(new EventModel());
		events.add(new EventModel());

		when(eventRepository.findAll()).thenReturn(events);

		// Call the method
		List<EventModel> foundEvents = eventService.findAllEvents();

		// Verify the result
		assertNotNull(foundEvents);
		assertEquals(2, foundEvents.size());
	}

	@Test
	void testUpdateEvent() {
		// Mock data
		EventModel event = new EventModel();
		event.setId(1);
		event.setName("Updated Event");
		event.setActive(true);

		when(eventRepository.save(any(EventModel.class))).thenReturn(event);

		// Call the method
		EventModel updatedEvent = eventService.updateEvent(event);

		// Verify the result
		assertNotNull(updatedEvent);
		assertEquals(1, updatedEvent.getId());
		assertEquals("Updated Event", updatedEvent.getName());
		assertTrue(updatedEvent.getActive());
	}
}
