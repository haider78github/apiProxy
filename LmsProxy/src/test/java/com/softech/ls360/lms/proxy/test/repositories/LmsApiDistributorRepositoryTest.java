package com.softech.ls360.lms.proxy.test.repositories;

import javax.inject.Inject;

import org.junit.Test;

import com.softech.ls360.lms.proxy.entities.LmsApiDistributor;
import com.softech.ls360.lms.proxy.repositories.LmsApiDistributorRepository;
import com.softech.ls360.lms.proxy.test.LmsProxyAbstractTest;

public class LmsApiDistributorRepositoryTest extends LmsProxyAbstractTest {

	@Inject
	private LmsApiDistributorRepository lmsApiDistributorRepository;
	
	@Test
	public void test1() {
		
	}
	
	//@Test
	public void findLmsApiDistributor() {
		
		String userName = "LinuxFoundation";
		String password = "576wJkHsajRXT5C";
		try {
			LmsApiDistributor lmsApiDistributor = lmsApiDistributorRepository.findByUserNameAndPassword(userName, password);;
			System.out.println(lmsApiDistributor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
