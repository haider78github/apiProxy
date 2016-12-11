package com.softech.ls360.lms.proxy.test.repositories;

import javax.inject.Inject;

import org.junit.Test;

import com.softech.ls360.lms.proxy.entities.Customer;
import com.softech.ls360.lms.proxy.repositories.CustomerRepository;
import com.softech.ls360.lms.proxy.test.LmsProxyAbstractTest;

public class CustomerRepositoryTest extends LmsProxyAbstractTest {
	
	@Inject
	private CustomerRepository customerRepository;
	
	@Test
	public void test1() {
		
	}
	
	//@Test
	public void findByKey() {
		String apiKey = "dcda5167-8af6-48d3-99cf-4337d19d378a";   // apiKey here is actually customerGuid (QA2)
		
		try {
			Customer customer = customerRepository.findByCustomerGuid(apiKey);
			System.out.println(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
