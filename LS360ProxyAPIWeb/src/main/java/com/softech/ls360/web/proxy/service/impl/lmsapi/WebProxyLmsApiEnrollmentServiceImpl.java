package com.softech.ls360.web.proxy.service.impl.lmsapi;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.softech.ls360.lms.api.proxy.service.LmsApiAddUserService;
import com.softech.ls360.lms.api.proxy.service.LmsApiLearnerCoursesEnrollService;
import com.softech.ls360.lms.api.proxy.service.LmsApiRegisterUserService;
import com.softech.ls360.lms.api.proxy.service.LmsApiUpdateUserService;
import com.softech.ls360.lms.proxy.entities.VU360User;
import com.softech.ls360.lms.proxy.repositories.VU360UserRepository;
import com.softech.ls360.web.proxy.endpoint.model.request.enrollment.LearnerEnrollmentsRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.enrollment.LearnerEnrollmentsResponse;
import com.softech.ls360.web.proxy.service.lmsapi.WebProxyLmsApiEnrollmentResponseService;
import com.softech.ls360.web.proxy.service.lmsapi.WebProxyLmsApiEnrollmentService;
import com.softech.ls360.web.proxy.service.lmsapi.WebProxyLmsApiUserService;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.enrollment.LearnerCoursesEnrollResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.UpdateUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.types.enrollment.DuplicatesEnrollment;
import com.softech.vu360.lms.webservice.message.lmsapi.types.enrollment.LearnerEnrollCourses;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.RegisterUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.UpdateableUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.User;

@Service
public class WebProxyLmsApiEnrollmentServiceImpl implements WebProxyLmsApiEnrollmentService {

	@Inject
	private VU360UserRepository vu360UserRepository;
	
	@Inject
	private WebProxyLmsApiUserService webProxyLmsApiUserService;
	
	@Inject
	private LmsApiAddUserService lmsApiAddUserService;
	
	@Inject
	private LmsApiUpdateUserService lmsApiUpdateUserService;
	
	@Inject
	private WebProxyLmsApiEnrollmentResponseService webProxyLmsApiEnrollmentResponseService;
	
	@Inject
	private LmsApiRegisterUserService lmsApiRegisterUserService;
	
	@Inject
	private LmsApiLearnerCoursesEnrollService lmsApiLearnerCoursesEnrollService;
	
	@Override
	public LearnerEnrollmentsResponse learnerCoursesEnroll(LearnerEnrollmentsRequest request, Long customerId, 
			String customerCode, String apiKey) throws Exception {
		
		LearnerEnrollmentsResponse learnerEnrollmentsResponse = null;
		DuplicatesEnrollment duplicatesEnrollment = request.getDuplicatesEnrollment();
		
		User user = request.getUser();
		LearnerEnrollCourses learnerEnrollCourses = request.getLearnerEnrollCourses();
		/**
		 * We are doing this beacuse we call add or update user LmsApi Call. If that call returns error then we
		 * return this response.
		 */
		String userName = user.getUserName();
		VU360User vu360User = vu360UserRepository.findByUsername(userName);
		if (vu360User == null) {
			learnerEnrollmentsResponse = addAndEnrollUser(user, learnerEnrollCourses, duplicatesEnrollment, customerId, customerCode, apiKey);
		} else {
			learnerEnrollmentsResponse = updateAndEnrollUser(user, learnerEnrollCourses, duplicatesEnrollment, customerId, customerCode, apiKey);
		}
		
		return learnerEnrollmentsResponse;
		
	}
	
	private LearnerEnrollmentsResponse addAndEnrollUser(User user, LearnerEnrollCourses learnerEnrollCourses,  DuplicatesEnrollment duplicatesEnrollment, Long customerId, String customerCode, String apiKey) throws Exception {
		
		LearnerEnrollmentsResponse learnerEnrollmentsResponse = null;
		AddUserResponse addUserResponse = webProxyLmsApiUserService.createUser(user, customerId, customerCode, apiKey);
		RegisterUser registerUser = lmsApiAddUserService.getRegisterUser(addUserResponse);
		
		//User not created and some error happened on LMSAPI side server. So get the error from response, make LearnerEnrollmentsResponse and return
		if (isNotUserCreated(registerUser, addUserResponse)) {
			String userName = user.getUserName();
			return webProxyLmsApiEnrollmentResponseService.getLearnerEnrollmentsResponse(userName, addUserResponse);
		}
		
		if (isSuccessfulRegisterUser(registerUser)) {
			learnerEnrollmentsResponse = processEnrollments(customerCode, apiKey, registerUser, learnerEnrollCourses, duplicatesEnrollment);
		} else {
			learnerEnrollmentsResponse = webProxyLmsApiEnrollmentResponseService.getLearnerEnrollmentsResponse(registerUser, addUserResponse);
		}
		
		return learnerEnrollmentsResponse;
	}
	
	private LearnerEnrollmentsResponse updateAndEnrollUser(User user, LearnerEnrollCourses learnerEnrollCourses, DuplicatesEnrollment duplicatesEnrollment, Long customerId, String customerCode, String apiKey) throws Exception {
		
		LearnerEnrollmentsResponse learnerEnrollmentsResponse = null;
		UpdateableUser updateableUser =  lmsApiUpdateUserService.getUpdateableUser(user);
		UpdateUserResponse updateUserResponse = webProxyLmsApiUserService.updateUser(updateableUser, customerId, customerCode, apiKey);
		RegisterUser registerUser = lmsApiUpdateUserService.getRegisterUser(updateUserResponse);
		
		//User not updated and some error happened on LMSAPI side server. So get the error from response, make LearnerEnrollmentsResponse and return
		if (isNotUserUpdated(registerUser, updateUserResponse)) {
			String userName = user.getUserName();
			return webProxyLmsApiEnrollmentResponseService.getLearnerEnrollmentsResponse(userName, updateUserResponse);
		}
		
		if (isSuccessfulRegisterUser(registerUser)) {
			learnerEnrollmentsResponse = processEnrollments(customerCode, apiKey, registerUser, learnerEnrollCourses, duplicatesEnrollment);
		} else {
			learnerEnrollmentsResponse = webProxyLmsApiEnrollmentResponseService.getLearnerEnrollmentsResponse(registerUser, updateUserResponse);
		}
		return learnerEnrollmentsResponse;
		
		
	}
	
	private boolean isNotUserCreated(RegisterUser registerUser, AddUserResponse addUserResponse) {
		return registerUser == null && addUserResponse != null;
	}
	
	private boolean isNotUserUpdated(RegisterUser registerUser, UpdateUserResponse updateUserResponse) {
		return registerUser == null && updateUserResponse != null;
	}
	
	private boolean isSuccessfulRegisterUser(RegisterUser registerUser) {
		return lmsApiRegisterUserService.isSuccessfulRegisterUser(registerUser);
	}
	
	public LearnerEnrollmentsResponse processEnrollments(String customerCode, String apiKey, RegisterUser registerUser, LearnerEnrollCourses learnerEnrollCourses, DuplicatesEnrollment duplicatesEnrollment) throws Exception {
		
		String userName = registerUser.getUserName();
		
		LearnerCoursesEnrollResponse learnerCoursesEnrollResponse = lmsApiLearnerCoursesEnrollService.processEnrollments(userName, learnerEnrollCourses, duplicatesEnrollment, customerCode, apiKey);;
		LearnerEnrollmentsResponse learnerEnrollmentsResponse = webProxyLmsApiEnrollmentResponseService.processLearnerCoursesEnrollResponse(registerUser, learnerCoursesEnrollResponse);
		return learnerEnrollmentsResponse;
		
	}
	
}
