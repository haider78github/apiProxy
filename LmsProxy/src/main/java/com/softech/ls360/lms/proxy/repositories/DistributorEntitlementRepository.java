package com.softech.ls360.lms.proxy.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.DistributorEntitlement;

public interface DistributorEntitlementRepository extends CrudRepository<DistributorEntitlement, Long> {

	List<DistributorEntitlement> findByDistributorId(Long distributorId);
	
}
