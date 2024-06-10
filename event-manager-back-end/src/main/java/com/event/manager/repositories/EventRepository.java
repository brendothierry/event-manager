package com.event.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.manager.model.EventModel;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Integer>{
	
}
