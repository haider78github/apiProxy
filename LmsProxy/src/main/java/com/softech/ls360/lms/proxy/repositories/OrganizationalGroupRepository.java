package com.softech.ls360.lms.proxy.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.OrganizationalGroup;

public interface OrganizationalGroupRepository extends CrudRepository<OrganizationalGroup, Long> {

	List<OrganizationalGroup> findByCustomerId(Long customerId);
	
}
