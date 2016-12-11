package com.softech.ls360.lms.proxy.test.enrollments;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.softech.ls360.lms.proxy.dao.LearnerEnrollmentsDao;
import com.softech.ls360.lms.proxy.entities.Enrollments;
import com.softech.ls360.lms.proxy.test.LmsProxyAbstractTest;

public class EnrollmentsTest extends LmsProxyAbstractTest {

	@Inject
	private LearnerEnrollmentsDao lmsProxyEnrollmentsDao;
	
	@Test
	public void test1() {
		
	}
	
	//@Test
	public void findResellerLearnerEnrollments() {
		
		Long distributorId = new Long(1);
		String enrollmentStartDate = "2013-08-01T00:00:00";
		String enrollmentEndDate = "2014-08-18T23:59:59";
		
		try {
			List<Enrollments> resellerEnrollments = lmsProxyEnrollmentsDao.findEnrollmentByDistributorId(distributorId, enrollmentStartDate, enrollmentEndDate);
			System.out.println(resellerEnrollments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void findEnrollmentsByUserNameAndCoursesGuid() {
		
		String userName = "lmsapitestsqa497@lms.com";
		List<String> coursesGuid = new ArrayList<String>();
		coursesGuid.add("A0D79B03F1864F698B4D1473DFF2CCBD");
		coursesGuid.add("6D4BBE019289409A9F74C9378265089D");
		coursesGuid.add("70d5f5443ecb48b883d9d054412872aa");
		coursesGuid.add("D72C76FEB464495BA2DF79992AF5D3CC");
		coursesGuid.add("A0D9583497AF488B9C2902E76F2CDB67");
		coursesGuid.add("DE9E3B06B01240F89A9D945F2463C346");
		coursesGuid.add("DB623133FAA9443B93585E70D1079FE9");
		coursesGuid.add("D1C8CA1C1AF64C9486B1248D2936E87F");
		
		try {
			List<Enrollments> resellerEnrollments = lmsProxyEnrollmentsDao.findEnrollmentsByUserNameAndCoursesGuid(userName, coursesGuid);
			System.out.println(resellerEnrollments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
