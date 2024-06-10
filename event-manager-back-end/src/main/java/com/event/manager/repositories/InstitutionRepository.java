package com.event.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.manager.model.EventModel;
import com.event.manager.model.InstitutionModel;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionModel, Integer>{
    
	List<InstitutionModel> save(Integer institutionId);
}
