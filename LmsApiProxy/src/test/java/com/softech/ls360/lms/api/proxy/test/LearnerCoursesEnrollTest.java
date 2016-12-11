package com.softech.ls360.lms.api.proxy.test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.enrollment.LearnerCoursesEnrollRequest;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.enrollment.LearnerCoursesEnrollResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.types.enrollment.DuplicatesEnrollment;
import com.softech.vu360.lms.webservice.message.lmsapi.types.enrollment.LearnerCourses;
import com.softech.vu360.lms.webservice.message.lmsapi.types.enrollment.LearnerEnrollCourses;

public class LearnerCoursesEnrollTest extends LmsApiProxyAbstractTest{

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
    protected WebServiceTemplate lmsApiWebServiceTemplate;
	
	//@Test
	public void test1() {
		
	}
	
	@Test
	public void learnerCoursesEnroll() {

		String customerCode = "VUCUS-1";
		String apiKey = "0f9ef1ea-c634-4797-a490-de6cc88d48a3";
		Boolean notifyLearnersByEmail = Boolean.FALSE;
		List<String> userNames = getUserNames("LmsApiTestUser1@lms.com", "LmsApiTestUser2@lms.com");
		LearnerCoursesEnrollRequest request = getLearnerCoursesEnrollRequest(customerCode, apiKey, notifyLearnersByEmail, userNames);
		//String xmlRequest = TestUtil.convertObjectToXml(request, LearnerCoursesEnrollRequest.class);
		//logger.info(xmlRequest);
		
		try {
			LearnerCoursesEnrollResponse response = (LearnerCoursesEnrollResponse)lmsApiWebServiceTemplate.marshalSendAndReceive(request);
			logger.info(response);
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private LearnerCoursesEnrollRequest getLearnerCoursesEnrollRequest(String customerCode, String apiKey, 
			Boolean notifyLearnersByEmail, List<String> userNames) {
		
		List<LearnerCourses> learnerCoursesList = getLearnerCoursesList(userNames);
		
		LearnerCoursesEnrollRequest request = new LearnerCoursesEnrollRequest();
		request.setCustomerCode(customerCode);
		request.setKey(apiKey);
		request.setNotifyLearnersByEmail(notifyLearnersByEmail);
		request.setLearnerCourses(learnerCoursesList);
		request.setDuplicatesEnrollment(DuplicatesEnrollment.UPDATE);
		
		return request;
		
	}
	
	private List<LearnerCourses> getLearnerCoursesList(List<String> userNames) {
		
		List<LearnerCourses> learnerCoursesList = null;
		if (!CollectionUtils.isEmpty(userNames)) {
			learnerCoursesList = new ArrayList<>(userNames.size());
			for (String userName : userNames) {
				List<String> coursesGuidList = getCourseGuids();
				LocalDate enrollmentStartDate = LocalDate.now();
				LocalDate enrollmentEndDate = LocalDate.of(2014, Month.DECEMBER, 13);
				
				LearnerCourses learnerCourses = getLearnerCourses(userName, coursesGuidList, enrollmentStartDate, enrollmentEndDate);
				learnerCoursesList.add(learnerCourses);
				
			}
		}
	
		return learnerCoursesList;
	}
	
	private LearnerCourses getLearnerCourses(String userName, List<String> coursesGuidList, LocalDate enrollmentStartDate, LocalDate enrollmentEndDate) {
		
		LearnerEnrollCourses learnerEnrollCourses = getLearnerEnrollCourses(coursesGuidList, enrollmentStartDate, enrollmentEndDate);
		
		LearnerCourses learnerCourses = new LearnerCourses();
		learnerCourses.setUserId(userName);
		learnerCourses.setCourses(learnerEnrollCourses);
		return learnerCourses;
		
	}
	
	private LearnerEnrollCourses getLearnerEnrollCourses(List<String> coursesGuidList, LocalDate enrollmentStartDate, LocalDate enrollmentEndDate) {
		
		LearnerEnrollCourses learnerEnrollCourses = new LearnerEnrollCourses();
		learnerEnrollCourses.setCourseId(coursesGuidList);
		learnerEnrollCourses.setEnrollmentStartDate(enrollmentStartDate);
		learnerEnrollCourses.setEnrollmentEndDate(enrollmentEndDate);
		return learnerEnrollCourses;
	}
	
	
	private List<String> getUserNames(String... userNames) {
		return Arrays.asList(userNames);
	}
	
	
	
	private List<String> getCourseGuids() {
		
		List<String> coursesGuids = new ArrayList<String>(5);
		coursesGuids.add("asda");
		coursesGuids.add("asd");
		coursesGuids.add("asd");
		coursesGuids.add("abc123");
		coursesGuids.add("asd");
		
		return coursesGuids;
	}
	
}
