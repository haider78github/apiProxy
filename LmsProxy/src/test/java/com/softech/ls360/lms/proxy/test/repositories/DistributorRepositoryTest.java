package com.softech.ls360.lms.proxy.test.repositories;

import javax.inject.Inject;

import org.junit.Test;

import com.softech.ls360.lms.proxy.entities.Distributor;
import com.softech.ls360.lms.proxy.repositories.DistributorRepository;
import com.softech.ls360.lms.proxy.test.LmsProxyAbstractTest;

public class DistributorRepositoryTest extends LmsProxyAbstractTest  {
	
	@Inject
	private DistributorRepository distributorRepository;
	
	@Test
	public void test1() {
		
	}
	
	//@Test
	public void findByDistributorCode() {
		String distributorCode = "10302";
		Distributor distributor = distributorRepository.findByDistributorCode(distributorCode);
		System.out.println(distributor);
	}
	
}
