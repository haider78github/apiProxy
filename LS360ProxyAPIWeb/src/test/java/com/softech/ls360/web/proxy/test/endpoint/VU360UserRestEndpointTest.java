package com.softech.ls360.web.proxy.test.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.softech.ls360.proxy.api.util.json.JsonUtil;
import com.softech.ls360.web.proxy.endpoint.model.request.vu360user.UserRequest;
import com.softech.ls360.web.proxy.test.TestUtil;
import com.softech.ls360.web.proxy.test.WebProxyAbstractTest;
import com.softech.ls360.web.proxy.test.dummy.LmsApiUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.address.Address;
import com.softech.vu360.lms.webservice.message.lmsapi.types.orggroup.OrganizationalGroups;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.User;

public class VU360UserRestEndpointTest extends WebProxyAbstractTest {

	private static final Logger logger = LogManager.getLogger();
	
	//@Test
	public void test1() {
		
	}
	
	@Test
	public void addUser() {
	
		String userRestEndPoint = LOCAL_URL + "customer/user/add";
		String userJson = getUserJson();
		logger.info(userJson); 
		TestUtil.callwebService(DEV_USER_PASS, DEV_API_KEY, userRestEndPoint, "POST", userJson);
		//TestUtil.callHttpsWebService(userPass, apiKey, addUserControllerUrl, "POST", userJson);
	}
	
	private String getUserJson() {
		
		//User user = getUser();
		User user = getMinimumUser();
		
		UserRequest jsonUserRequest = new UserRequest();
		jsonUserRequest.setUser(user);
		return JsonUtil.convertObjectToJson(jsonUserRequest);
	}
	
	private User getUser() {
		
		String[] orgGroupHierarchieArray = {"Alert Demo Customer", "Alert Demo Customer>Test123", "Alert Demo"};
		OrganizationalGroups organizationalGroup = LmsApiUser.getOrganizationalGroup(orgGroupHierarchieArray);
		//OrganizationalGroups organizationalGroup = null;
		
		String middleName = "";
		String phone = "";
		String mobilePhone = "";
		String extension = "";
		String firstName = "LmsApiProxyTestUser2";
		String lastName = firstName;
		String emailAddress = firstName + "@lms.com";
		String userName = emailAddress;
		String password = "a123456789";
			
		Address address = getUserAddress();
		Address alternateAddress = null;
		
		User user = LmsApiUser.getUser(firstName, middleName, lastName, emailAddress, phone, mobilePhone, extension, address, 
				alternateAddress, userName, password, organizationalGroup);
		
		return user;	
	}
	
	private User getMinimumUser() {
		
		String firstName = "LmsApiProxyTestUser2";
		String lastName = firstName;
		String emailAddress = firstName + "@lms.com";
		String userName = emailAddress;
		String password = "a123456789";
			
		User user = LmsApiUser.getMinimumUser(firstName, lastName, emailAddress, userName, password);
		
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
	
}
