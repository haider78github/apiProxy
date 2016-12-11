package com.softech.ls360.web.proxy.endpoint.restful;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softech.ls360.web.proxy.config.annotation.RestEndpoint;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.WebProxyCourseGroupDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.WebProxyCourseGroupDetailsResponse;
import com.softech.ls360.web.proxy.service.lms.CourseGroupDetailRequestService;

@RestEndpoint
@RequestMapping(value="/lms/customer/coursegroup")
public class CourseGroupRestEndpoint {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private CourseGroupDetailRequestService courseGroupDetailRequestService;
	
	@RequestMapping(value = "/details/get", method = RequestMethod.POST)
	@ResponseBody
	public WebProxyCourseGroupDetailsResponse getCourseGroupDetails(@RequestBody WebProxyCourseGroupDetailsRequest request) {
		
		logger.info("Request received at " + getClass().getName() + " for coursegroup details");
		try {
			return courseGroupDetailRequestService.getCourseDetail(request);
		} catch (Exception e) {
			return courseGroupDetailRequestService.getRequestError(e);
		}
	}
	
}
