package com.softech.ls360.lms.proxy.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByCustomerGuid(String customerGuid);
	
}
