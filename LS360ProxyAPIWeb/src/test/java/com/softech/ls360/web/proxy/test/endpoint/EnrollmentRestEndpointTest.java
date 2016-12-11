package com.softech.ls360.web.proxy.test.endpoint;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.softech.ls360.proxy.api.util.json.JsonUtil;
import com.softech.ls360.web.proxy.endpoint.model.request.enrollment.LearnerEnrollmentsRequest;
import com.softech.ls360.web.proxy.test.TestUtil;
import com.softech.ls360.web.proxy.test.WebProxyAbstractTest;
import com.softech.ls360.web.proxy.test.dummy.LmsApiUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.address.Address;
import com.softech.vu360.lms.webservice.message.lmsapi.types.enrollment.DuplicatesEnrollment;
import com.softech.vu360.lms.webservice.message.lmsapi.types.enrollment.LearnerEnrollCourses;
import com.softech.vu360.lms.webservice.message.lmsapi.types.orggroup.OrganizationalGroups;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.User;


public class EnrollmentRestEndpointTest extends WebProxyAbstractTest {

	private static final Logger logger = LogManager.getLogger();
	
	//@Test
	public void test1() {
		
	}
	
	@Test
	public void enrollUser() {
		String enrollmentRestEndPoint = LOCAL_URL + "customer/learner/enroll";
		String learnerEnrollmentsJson = getLearnerEnrollmentsJson();
		logger.info(learnerEnrollmentsJson); 
		TestUtil.callwebService(QA1_API_KEY, QA1_API_KEY, enrollmentRestEndPoint, "POST", learnerEnrollmentsJson);
	}
	
	//@Test
	public void getResellerLearnerEnrollments() {
		String enrollmentRestEndPoint = LOCAL_URL + "reseller/enrollments/get";
		String fromDate = "2013-08-01T00:00:00";
		String toDate = "2014-08-18T23:59:59";
		String charset = "UTF-8";	
		try {
			String requestParameters = String.format("?fromDate=%s&toDate=%s",
			    URLEncoder.encode(fromDate, charset),
				URLEncoder.encode(toDate, charset));
			String resellerEnrollmentsControllerUrl = enrollmentRestEndPoint + requestParameters;
			TestUtil.callwebService(DEV_USER_PASS, DEV_API_KEY, resellerEnrollmentsControllerUrl, "GET", null);
			//TestUtil.callHttpsWebService(userPass, apiKey, resellerEnrollmentsControllerUrl, "GET", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getLearnerEnrollmentsJson() {
		LearnerEnrollmentsRequest learnerEnrollments = getLearnerEnrollments();
		return JsonUtil.convertObjectToJson(learnerEnrollments);
	}
	
	private LearnerEnrollmentsRequest getLearnerEnrollments() {
		
		User user = getUser();
		LearnerEnrollCourses learnerEnrollCourses = getLearnerCourses();
		
		LearnerEnrollmentsRequest learnerEnrollments = new LearnerEnrollmentsRequest();
		learnerEnrollments.setUser(user);
		learnerEnrollments.setLearnerEnrollCourses(learnerEnrollCourses);
		learnerEnrollments.setDuplicatesEnrollment(DuplicatesEnrollment.UPDATE);
		return learnerEnrollments;
		
	}
	
	private LearnerEnrollCourses getLearnerCourses() {
		
		List<String> coursesGuidList = new ArrayList<String>();
		coursesGuidList.add("asda");
		coursesGuidList.add("asd");
		coursesGuidList.add("asd");
		coursesGuidList.add("abc123");
		coursesGuidList.add("asd");
		
		LocalDate enrollmentStartDate = LocalDate.now();
		
		// Create a local date May 10, 2012
		LocalDate enrollmentEndDate = LocalDate.of(2014, Month.DECEMBER, 13);
		
		LearnerEnrollCourses learnerEnrollCourses = new LearnerEnrollCourses();
		learnerEnrollCourses.setCourseId(coursesGuidList);
		learnerEnrollCourses.setEnrollmentStartDate(enrollmentStartDate);
		learnerEnrollCourses.setEnrollmentEndDate(enrollmentEndDate);
		
		return learnerEnrollCourses;
		
	}
	
	private User getUser() {
		
		String[] orgGroupHierarchieArray = {"Alert Demo Customer"};
		OrganizationalGroups organizationalGroup = LmsApiUser.getOrganizationalGroup(orgGroupHierarchieArray);
		
		String middleName = "";
		String phone = "";
		String mobilePhone = "";
		String extension = "";
		Address address = getUserAddress();
		Address alternateAddress = null;
	
		String firstName = "LmsApiProxyTestUser";
		String lastName = firstName;
		String emailAddress = firstName + "@lms.com";
		String userName = emailAddress;
		String password = "a123456789";
			
		User user = LmsApiUser.getUser(firstName, middleName, lastName, emailAddress, phone, mobilePhone, extension, address, 
				alternateAddress, userName, password, organizationalGroup);
		
		return user;
			
	}
	
	private Address getUserAddress() {
		
		String streetAddress1 = "abcStreet1";
		String streetAddress2 = "abcStreet2";
		String city = "Austin";
		String state = "TX"; 
		String zipCode = "12345";
		String country = "US";
		
		return LmsApiUser.getUserAddress(streetAddress1, streetAddress2, city, state, zipCode, country);
	}
	
	private Address getUserAlternateAddress() {
		
		String streetAddress1 = "xyzStreet1";
		String streetAddress2 = "xyzStreet2";
		String city = "Austin";
		String state = "TX"; 
		String zipCode = "12345";
		String country = "US";
		
		return LmsApiUser.getUserAddress(streetAddress1, streetAddress2, city, state, zipCode, country);
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String strDate = dateFormat.format(date);
		try {
			XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(strDate);
			return xmlDate;
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

}
