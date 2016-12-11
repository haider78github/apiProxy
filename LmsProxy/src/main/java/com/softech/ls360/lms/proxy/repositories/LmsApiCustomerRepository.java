package com.softech.ls360.lms.proxy.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.softech.ls360.lms.proxy.entities.LmsApiCustomer;

public interface LmsApiCustomerRepository extends CrudRepository<LmsApiCustomer, Long> {

	@Query("select lac from #{#entityName} lac "
    		+ "join fetch lac.customer c "
    		+ "where lac.apiKey = :apiKey")
	LmsApiCustomer findByApiKey(@Param("apiKey") String apiKey);
	
}
