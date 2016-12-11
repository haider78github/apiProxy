package com.softech.ls360.web.proxy.endpoint.restful;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softech.ls360.lms.proxy.repositories.LearnerCourseStatisticsRepository;
import com.softech.ls360.web.proxy.config.annotation.RestEndpoint;
import com.softech.ls360.web.proxy.endpoint.model.request.lcs.CourseStatsUpdateRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.lcs.CourseCompletion;
import com.softech.ls360.web.proxy.endpoint.model.response.lcs.CourseCompletionStatsUpdateResponse;
import com.softech.ls360.web.proxy.security.RestUserPrincipal;
import com.softech.ls360.web.proxy.service.AuthorizationService;
import com.softech.ls360.web.proxy.service.lms.LearnerCourseStatisticsService;
import com.softech.ls360.web.proxy.validation.NotBlank;

@RestEndpoint
@RequestMapping(value="/lms")
public class LearnerCourseStatisticsRestEndpoint {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private AuthorizationService webProxyAuthorizationService;
	
	@Inject
	private LearnerCourseStatisticsService learnerCourseStatisticsService;
	
	@Inject 
	private LearnerCourseStatisticsRepository learnerCourseStatisticsRepository;
	
	@RequestMapping(value = "/customer/enrollment/course/stats/update", method = {RequestMethod.PUT, RequestMethod.POST})
	@ResponseBody
	public CourseCompletionStatsUpdateResponse courseCompletion(@RequestBody @Valid CourseStatsUpdateRequest courseCompletionStatsUpdateRequest) throws Exception {
		
		logger.info("Request received at LearnerCourseStatisticsRestEndpoint for update statistice");
		return learnerCourseStatisticsService.updateLearnerCourseStatistics(courseCompletionStatsUpdateRequest);
		
	}
	
	@RequestMapping(value = "reseller/coursecompletion", method = RequestMethod.GET)
	@ResponseBody
	public CourseCompletion getCourseCompletion(@RequestHeader(value="Authorization", required=true) String authorization,
			@RequestHeader(value="key", required=true) String clientApiKey, 
			@RequestParam(value="fromDate", required = true) @NotBlank(message="validate.from.date") String fromDate,
			@RequestParam(value="toDate", required = true)  @NotBlank(message="validate.to.date") String toDate,
			@AuthenticationPrincipal RestUserPrincipal principal) throws Exception {
		
		logger.info("Request received at " + getClass().getName() + " for reseller course completion");
		
		String operation = "getCourseCompletion";
		
		Long distributorId = principal.getDistributorId();
		String lmsApiDistributorApiKey = principal.getApiKey();
		String distributorAllowFrequency = principal.getAllowFrequency(); 
		
		webProxyAuthorizationService.authorizeOperationAllowFrequency(distributorId, lmsApiDistributorApiKey, distributorAllowFrequency, clientApiKey, operation);
		List<com.softech.ls360.lms.proxy.entities.LearnerCourseStatistics1> learnerCourseStatisticsList = learnerCourseStatisticsRepository.getLearnerCourseStatistics(distributorId.toString(), fromDate, toDate);
		return new CourseCompletion(learnerCourseStatisticsList);
		
	}
	
}
