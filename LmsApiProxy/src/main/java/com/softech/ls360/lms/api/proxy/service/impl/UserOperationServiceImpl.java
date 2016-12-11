package com.softech.ls360.lms.api.proxy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softech.ls360.lms.api.proxy.service.UserOperationService;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserRequest;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.UpdateUserRequest;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.UpdateUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.types.orggroup.OrganizationalGroups;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.RegisterUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.RegisterUsers;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.UpdateableUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.UpdateableUsers;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.User;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.Users;

@Service
public class UserOperationServiceImpl implements UserOperationService{

	@Override
	public AddUserRequest getAddUserRequest(User user, String customerCode, String apiKey) throws Exception {
		
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		
		AddUserRequest addUserRequest = getAddUserRequest(userList, customerCode, apiKey);
		return addUserRequest;
		
	}
	
	private AddUserRequest getAddUserRequest(List<User> userList, 
			String customerCode, String apiKey) {
		
		Users users = new Users();
		users.setUser(userList);
		
		AddUserRequest addUserRequest = new AddUserRequest();
		addUserRequest.setCustomerCode(customerCode);
		addUserRequest.setKey(apiKey);
		addUserRequest.setUsers(users);
		
		return addUserRequest;
		
	}
	
	@Override
	public UpdateUserRequest getUpdateUserRequest(UpdateableUser updateableUser, String customerCode, String apiKey) throws Exception {
		
		List<UpdateableUser> updateableUserList = new ArrayList<UpdateableUser>();
		updateableUserList.add(updateableUser);
		
		UpdateUserRequest updateUserRequest = getUpdateUserRequest(updateableUserList, customerCode, apiKey);
		return updateUserRequest;
		
	}
	
	private UpdateUserRequest getUpdateUserRequest(List<UpdateableUser> updateableUserList, 
			String customerCode, String apiKey) {
		
		UpdateableUsers updateableUsers = new UpdateableUsers();
		updateableUsers.setUpdateableUser(updateableUserList);
		
		UpdateUserRequest updateUserRequest = new UpdateUserRequest();
		updateUserRequest.setCustomerCode(customerCode);
		updateUserRequest.setKey(apiKey);
		updateUserRequest.setUsers(updateableUsers);
		
		return updateUserRequest;
		
	}
	
	@Override
	public RegisterUser getRegisterUser(AddUserResponse addUserResponse) throws Exception {
		
		RegisterUsers registerUsers = addUserResponse.getRegisterUsers();
		if (registerUsers == null) {
			return null;
		}
		return getRegisterUser(registerUsers);
	}
	
	@Override
	public RegisterUser getRegisterUser(UpdateUserResponse updateUserResponse) throws Exception {
		
		RegisterUsers registerUsers = updateUserResponse.getRegisterUsers();
		if (registerUsers == null) {
			return null;
		}
		return getRegisterUser(registerUsers);
	}
	
	private RegisterUser getRegisterUser(RegisterUsers registerUsers) {
		
		RegisterUser user = null;
		List<RegisterUser> registerUserList = registerUsers.getRegisterUser();
		if (!CollectionUtils.isEmpty(registerUserList)) {
			user = registerUserList.get(0);
		}
		return user;
	}
	
	@Override
	public boolean isOrganizationGroupExist(User user) throws Exception {
		
		OrganizationalGroups organizationalGroups = user.getOrganizationalGroups();
		if (organizationalGroups == null) {
			return false;
		}
		
		List<String> orgGroupHierarchyList = organizationalGroups.getOrgGroupHierarchy();
		if (CollectionUtils.isEmpty(orgGroupHierarchyList)) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isOrganizationGroupExist(UpdateableUser updateableUser) throws Exception {
		
		OrganizationalGroups organizationalGroups = updateableUser.getOrganizationalGroups();
		if (organizationalGroups == null) {
			return false;
		}
		
		List<String> orgGroupHierarchyList = organizationalGroups.getOrgGroupHierarchy();
		if (CollectionUtils.isEmpty(orgGroupHierarchyList)) {
			return false;
		}
		return true;
	}
	
	public OrganizationalGroups getOrganizationalGroups(String orgGroupName) throws Exception {
		
		List<String> orgGroupHierarchyList = new ArrayList<String>();
		orgGroupHierarchyList.add(orgGroupName);
		return getOrganizationalGroups(orgGroupHierarchyList);
		
	}
	
	public OrganizationalGroups getOrganizationalGroups(List<String> orgGroupHierarchyList) throws Exception {
		
		OrganizationalGroups organizationalGroups = new OrganizationalGroups();
		organizationalGroups.setOrgGroupHierarchy(orgGroupHierarchyList);
		return organizationalGroups;
		
	}
	
	@Override
	public UpdateableUser getUpdateableUser(User user) throws Exception {

		if (user == null) {
			throw new Exception("User is null. Can not get UpdateableUser from User.");
		}
		
		UpdateableUser updateableUser = new UpdateableUser();
		updateableUser.setFirstName(user.getFirstName());
		updateableUser.setMiddleName(user.getMiddleName());
		updateableUser.setLastName(user.getLastName());
		updateableUser.setEmailAddress(user.getEmailAddress());
		updateableUser.setPhone(user.getPhone());
		updateableUser.setMobilePhone(user.getMobilePhone());
		updateableUser.setExtension(user.getExtension());
		updateableUser.setAddress(user.getAddress());
		updateableUser.setAlternateAddress(user.getAlternateAddress());
		updateableUser.setUserName(user.getUserName());
		updateableUser.setPassword(user.getPassword());
		updateableUser.setOrganizationalGroups(user.getOrganizationalGroups());
		updateableUser.setAccountLocked(user.isAccountLocked());
		updateableUser.setAccountExpired(user.isAccountExpired());
		updateableUser.setAccountDisabled(user.isAccountDisabled());
		updateableUser.setVisibleOnReport(user.isVisibleOnReport());
		updateableUser.setExpirationDate(user.getExpirationDate());
		updateableUser.setChangePasswordOnNextLogin(user.isChangePasswordOnNextLogin());
		
		return updateableUser;
	}
	
	
	
}
