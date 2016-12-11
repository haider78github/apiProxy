package com.softech.ls360.web.proxy.service.lmsapi;

import com.softech.ls360.web.proxy.endpoint.model.response.enrollment.LearnerEnrollmentsResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.enrollment.LearnerCoursesEnrollResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.UpdateUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.RegisterUser;

public interface WebProxyLmsApiEnrollmentResponseService {

	LearnerEnrollmentsResponse getLearnerEnrollmentsResponse(String userName, AddUserResponse addUserResponse);
	LearnerEnrollmentsResponse getLearnerEnrollmentsResponse(RegisterUser registerUser, AddUserResponse addUserResponse);
	LearnerEnrollmentsResponse processLearnerCoursesEnrollResponse(RegisterUser registerUser, LearnerCoursesEnrollResponse learnerCoursesEnrollResponse) throws Exception;
	
	LearnerEnrollmentsResponse getLearnerEnrollmentsResponse(String userName, UpdateUserResponse updateUserResponse);
	LearnerEnrollmentsResponse getLearnerEnrollmentsResponse(RegisterUser registerUser, UpdateUserResponse updateUserResponse);
	
}
