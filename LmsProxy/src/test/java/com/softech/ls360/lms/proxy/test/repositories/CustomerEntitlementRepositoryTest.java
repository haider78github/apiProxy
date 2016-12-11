package com.softech.ls360.lms.proxy.test.repositories;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.softech.ls360.lms.proxy.entities.CustomerEntitlement;
import com.softech.ls360.lms.proxy.repositories.CustomerEntitlementRepository;
import com.softech.ls360.lms.proxy.test.LmsProxyAbstractTest;

public class CustomerEntitlementRepositoryTest extends LmsProxyAbstractTest {
	
	@Inject
	private CustomerEntitlementRepository customerEntitlementRepository;
	
	@Test
	public void test1() {
		
	}
	
	//@Test
	public void findByKey() {
		
		String customerGuid = "dcda5167-8af6-48d3-99cf-4337d19d378a";  
		try {
			List<CustomerEntitlement> customerEntitlements = customerEntitlementRepository.findByCustomerCustomerGuid(customerGuid);
			System.out.println(customerEntitlements);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void findById() {
		
		Long id = 208205L; 
		try {
			CustomerEntitlement customerEntitlementList = customerEntitlementRepository.findOne(id);
			System.out.println(customerEntitlementList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
