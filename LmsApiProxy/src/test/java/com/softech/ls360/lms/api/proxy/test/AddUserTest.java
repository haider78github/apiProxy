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
import org.springframework.ws.client.core.WebServiceTemplate;

import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserRequest;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.types.address.Address;
import com.softech.vu360.lms.webservice.message.lmsapi.types.orggroup.OrganizationalGroups;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.User;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.Users;

public class AddUserTest extends LmsApiProxyAbstractTest{

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
    protected WebServiceTemplate lmsApiWebServiceTemplate;
	
	//@Test
	public void test1() {
		
	}
	
	@Test
	public void addUser() {

		String customerCode = "VUCUS-1";
		String apiKey = "0f9ef1ea-c634-4797-a490-de6cc88d48a3";
		List<User> usersList = getUserList();
		AddUserRequest request = getAddUserRequest(customerCode, apiKey, usersList);
		
		try {
			AddUserResponse response = (AddUserResponse)lmsApiWebServiceTemplate.marshalSendAndReceive(request);
			logger.info(response);
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private AddUserRequest getAddUserRequest(String customerCode, String apiKey, List<User> usersList) {
		
		Users users = new Users();
		users.setUser(usersList);

		AddUserRequest request = new AddUserRequest();
		request.setCustomerCode(customerCode);
		request.setKey(apiKey);
		request.setUsers(users);
		
		return request;
	}
	
	private List<User> getUserList() {

		OrganizationalGroups organizationalGroup = getOrganizationalGroup("Alert Demo Customer", "Alert Demo Customer>Test");
		LocalDate accountExpirationDate = LocalDate.of(2014, Month.DECEMBER, 13);

		List<User> userList = new ArrayList<User>();
		String middleName = "";
		String phone = "";
		String mobilePhone = "";
		String extension = "";
		Address address = getUserAddress();
		Address alternateAddress = null;

		for (int i = 0; i < 1; i++) {
			String firstName = "LmsApiProxyTestUser" + i;
			String lastName = firstName;
			String emailAddress = firstName + "@lms.com";
			String userName = emailAddress;
			String password = "a123456789";

			User user = getUser(firstName, middleName, lastName, emailAddress,
					phone, mobilePhone, extension, address, alternateAddress,
					userName, password, organizationalGroup, accountExpirationDate);

			userList.add(user);
		}

		return userList;
	}

	private User getUser(String firstName, String middleName, String lastName,
			String emailAddress, String phone, String mobilePhone,
			String extension, Address address, Address alternateAddress,
			String userName, String password,
			OrganizationalGroups organizationalGroup, LocalDate accountExpirationDate) {

		User user = new User();
		user.setFirstName(firstName);
		user.setMiddleName(middleName);
		user.setLastName(lastName);
		user.setEmailAddress(emailAddress);
		user.setPhone(phone);
		user.setMobilePhone(mobilePhone);
		user.setExtension(extension);
		user.setAddress(address);
		user.setAlternateAddress(alternateAddress);
		user.setUserName(userName);
		user.setPassword(password);
		user.setOrganizationalGroups(organizationalGroup);
		user.setExpirationDate(accountExpirationDate);
		return user;

	}
	
	private Address getUserAddress() {
		
		String streetAddress1 = "abcStreet1";
		String streetAddress2 = "abcStreet2";
		String city = "Austin";
		String state = "TX"; 
		String zipCode = "12345";
		String country = "US";
		
		return getUserAddress(streetAddress1, streetAddress2, city, state, zipCode, country);
	}
	
	private Address getUserAddress(String streetAddress1, String streetAddress2, String city, String state, String zipCode, String country) {
		
		Address userAddress = new Address();
		userAddress.setStreetAddress1(streetAddress1);
		userAddress.setStreetAddress2(streetAddress2);
		userAddress.setCity(city);
		userAddress.setState(state);
		userAddress.setZipCode(zipCode);
		userAddress.setCountry(country);
		
		return userAddress;
		
	}
	
	private OrganizationalGroups getOrganizationalGroup(String... orgGroupHierarchieArray) {
		
		List<String> orgGroupHierarchies = Arrays.asList(orgGroupHierarchieArray);
		
		OrganizationalGroups organizationalGroup = new OrganizationalGroups();
		organizationalGroup.setOrgGroupHierarchy(orgGroupHierarchies);
		
		return organizationalGroup;
		
	}
	
}
