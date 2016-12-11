package com.softech.ls360.lms.proxy.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.Learner;

public interface LearnerRepository extends CrudRepository<Learner, Long> {

	Learner findByVu360UserUsername(String userName);
	
}
