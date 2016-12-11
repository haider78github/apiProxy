package com.softech.ls360.lms.proxy.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.ApiFrequencyRestriction;

public interface ApiFrequencyRestrictionRepository extends CrudRepository<ApiFrequencyRestriction, Long> {

	ApiFrequencyRestriction findByDistributorIdAndOperation(Long distributorId, String operation);
	
}
