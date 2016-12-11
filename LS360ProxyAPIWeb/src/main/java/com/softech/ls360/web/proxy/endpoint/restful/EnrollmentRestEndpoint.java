package com.softech.ls360.web.proxy.endpoint.restful;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softech.ls360.lms.proxy.dao.LearnerEnrollmentsDao;
import com.softech.ls360.lms.proxy.entities.Enrollments;
import com.softech.ls360.web.proxy.config.annotation.RestEndpoint;
import com.softech.ls360.web.proxy.endpoint.model.request.enrollment.LearnerEnrollmentsRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.enrollment.LearnerEnrollmentsResponse;
import com.softech.ls360.web.proxy.endpoint.model.response.enrollment.ResellerEnrollments;
import com.softech.ls360.web.proxy.security.RestUserPrincipal;
import com.softech.ls360.web.proxy.service.AuthorizationService;
import com.softech.ls360.web.proxy.service.lmsapi.WebProxyLmsApiEnrollmentService;
import com.softech.ls360.web.proxy.validation.NotBlank;

@RestEndpoint
@RequestMapping(value="/lms")
public class EnrollmentRestEndpoint {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private AuthorizationService webProxyAuthorizationService;
	
	@Inject
	private WebProxyLmsApiEnrollmentService webProxyLmsApiEnrollmentService;
	
	@Inject
	private LearnerEnrollmentsDao learnerEnrollmentsDao;
	
	@RequestMapping(value = "/customer/learner/enroll", method = RequestMethod.POST)
	@ResponseBody
	public LearnerEnrollmentsResponse enrollUser(@RequestBody LearnerEnrollmentsRequest learnerEnrollments,
			@RequestHeader(value="key", required=true) String clientApiKey,
			@AuthenticationPrincipal RestUserPrincipal principal) throws Exception {
		
		logger.info("Request received at " + getClass().getName() + " for learner enrollment");
		
		Long customerId = principal.getCustomerId();
		String customerCode = principal.getCustomerCode();
		LearnerEnrollmentsResponse learnerEnrollmentsResponse = webProxyLmsApiEnrollmentService.learnerCoursesEnroll(learnerEnrollments, customerId, customerCode, clientApiKey);
		return learnerEnrollmentsResponse;
			
	}
	
	@RequestMapping(value = "/reseller/enrollments/get", method = RequestMethod.GET)
	@ResponseBody
	public ResellerEnrollments getResellerLearnerEnrollments(
			@RequestHeader(value="key", required=true) String clientApiKey,
			@RequestParam(value="fromDate", required = true) 
			@NotBlank(message="validate.from.date") 
			String fromDate,
			@RequestParam(value="toDate", required = true) 
			@NotBlank(message="validate.to.date") 
			String toDate,
			@AuthenticationPrincipal RestUserPrincipal principal) throws Exception {
		
		logger.info("Request received at " + getClass().getName() + "  for reseller enrollments");
		
		String operation = "getResellerLearnerEnrollments";
		
		Long distributorId = principal.getDistributorId();
		String lmsApiDistributorApiKey = principal.getApiKey();
		String distributorAllowFrequency = principal.getAllowFrequency(); 
		
		webProxyAuthorizationService.authorizeOperationAllowFrequency(distributorId, lmsApiDistributorApiKey, distributorAllowFrequency, clientApiKey, operation);
		
		List<Enrollments> enrollmentsList = learnerEnrollmentsDao.findEnrollmentByDistributorId(distributorId.longValue(), fromDate, toDate);
		return new ResellerEnrollments(enrollmentsList);
		
	}
	
}
