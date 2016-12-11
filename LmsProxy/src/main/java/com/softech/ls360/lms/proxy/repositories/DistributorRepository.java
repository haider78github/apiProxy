package com.softech.ls360.lms.proxy.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.Distributor;

public interface DistributorRepository extends CrudRepository<Distributor, Long> {

	Distributor findByDistributorCode(String distributorCode);
	
}
