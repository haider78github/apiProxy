package com.softech.ls360.web.proxy.test.endpoint;

import org.junit.Test;

import com.softech.ls360.proxy.api.util.json.JsonUtil;
import com.softech.ls360.proxy.api.util.xml.XmlUtil;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.CourseGroup;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.CourseGroupDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.WebProxyCourseGroupDetailsRequest;
import com.softech.ls360.web.proxy.test.TestUtil;
import com.softech.ls360.web.proxy.test.WebProxyAbstractTest;

public class CourseGroupRestEndpointTest extends WebProxyAbstractTest {

	//@Test
	public void test1() {
		
	}
	
	@Test
	public void getCourseGroupDetails() {
		
		String courseGroupRestEndPoint = LOCAL_URL + "customer/coursegroup/details/get";
		String requestJson = getCourseGroupDetailsRequestJson();
		//String requestXml = getCourseGroupDetailsRequestXML();
		System.out.println(requestJson); 
		TestUtil.callwebService(QA1_USER_PASS, QA1_API_KEY, courseGroupRestEndPoint, "POST", requestJson);
		
	}
	
	private String getCourseGroupDetailsRequestJson() {
		
		WebProxyCourseGroupDetailsRequest request= getWebProxyCourseGroupDetailsRequest();
		return JsonUtil.convertObjectToJson(request);
	}
	
	private String getCourseGroupDetailsRequestXML() {
		
		WebProxyCourseGroupDetailsRequest request = getWebProxyCourseGroupDetailsRequest();
		return XmlUtil.convertObjectToXml(request, request.getClass());
	}
	
	private WebProxyCourseGroupDetailsRequest getWebProxyCourseGroupDetailsRequest() {
		
		//String courseGroupGuid = "d968b7b8d9d14a7497e95ed8f5936681";  //dev
		String courseGroupGuid = "5511a9c2ce744d9da84ac34a627c6f6c";  //QA
		//String courseGroupGuid = "fcc1421d317a4cf5a42ee4e8cff46099";
		//String courseGroupGuid = "ba53da74c05e4dc09eb8eedeffae3e45";   //qa
		//Long customerEntitlementId = 227048L; //dev
		Long customerEntitlementId = 334965L; //QA
		

		CourseGroup courseGroup = new CourseGroup();
		courseGroup.setCourseGroupGuid(courseGroupGuid);
		
		CourseGroupDetailsRequest courseGroupDetailsRequest = new CourseGroupDetailsRequest();
		courseGroupDetailsRequest.setCourseGroup(courseGroup);
		courseGroupDetailsRequest.setCustomerEntitlementId(customerEntitlementId);
		
		WebProxyCourseGroupDetailsRequest request = new WebProxyCourseGroupDetailsRequest();
		request.setCourseGroupDetailsRequest(courseGroupDetailsRequest);
		
		return request;
		
	}

}
