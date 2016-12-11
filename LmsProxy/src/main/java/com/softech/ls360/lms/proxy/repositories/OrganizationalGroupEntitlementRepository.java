package com.softech.ls360.lms.proxy.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.OrganizationalGroupEntitlement;

public interface OrganizationalGroupEntitlementRepository extends CrudRepository<OrganizationalGroupEntitlement, Long> {

	List<OrganizationalGroupEntitlement> findByCustomerEntitlementId(Long customerEntitlementId);
	
}
