package com.softech.ls360.lms.proxy.test.repositories;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.softech.ls360.lms.proxy.entities.OrganizationalGroupEntitlement;
import com.softech.ls360.lms.proxy.repositories.OrganizationalGroupEntitlementRepository;
import com.softech.ls360.lms.proxy.test.LmsProxyAbstractTest;

public class OrganizationalGroupEntitlementRepositoryTest extends LmsProxyAbstractTest {
	
	@Inject
	private OrganizationalGroupEntitlementRepository  organizationalGroupEntitlementRepository;
	
	@Test
	public void test1() {
		
	}
	
	//@Test
	public void findOrganizationalGroupByCustomerEntitlementId() {
		
		Long customerEntitlementId = 208205L;  
		try {
			List<OrganizationalGroupEntitlement> organizationalGroupEntitlementList = organizationalGroupEntitlementRepository.findByCustomerEntitlementId(customerEntitlementId);
			System.out.println(organizationalGroupEntitlementList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
