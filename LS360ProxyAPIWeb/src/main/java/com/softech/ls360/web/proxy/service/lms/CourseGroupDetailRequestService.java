package com.softech.ls360.web.proxy.service.lms;

import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.WebProxyCourseGroupDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.WebProxyCourseGroupDetailsResponse;

public interface CourseGroupDetailRequestService {

	WebProxyCourseGroupDetailsResponse getCourseDetail(WebProxyCourseGroupDetailsRequest request) throws Exception;
	WebProxyCourseGroupDetailsResponse getRequestError(Exception e);
	
}
