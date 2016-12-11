package com.softech.ls360.web.proxy.test.endpoint;

import org.junit.Test;

import com.softech.ls360.proxy.api.util.json.JsonUtil;
import com.softech.ls360.proxy.api.util.xml.XmlUtil;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.Customer;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlement;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementRequest;
import com.softech.ls360.web.proxy.test.TestUtil;
import com.softech.ls360.web.proxy.test.WebProxyAbstractTest;

public class CustomerEntitlementRestEndpointTest extends WebProxyAbstractTest {

	//@Test
	public void test1() {
		
	}
	
	@Test
	public void getCustomerEntitlements() {
		String customerEntitlementRestEndPoint = LOCAL_URL + "customer/entitlement/get";
		String customerEntitlementRequestJson = getCustomerEntitlementRequestJson();
		System.out.println(customerEntitlementRequestJson); 
		
		//String customerEntitlementRequestXml = getCustomerEntitlementRequestXML();
		//System.out.println(customerEntitlementRequestXml);
		TestUtil.callwebService(DEV_USER_PASS, DEV_API_KEY, customerEntitlementRestEndPoint, "POST", customerEntitlementRequestJson);
	}
	
	//@Test
	public void getCustomerEntitlementDetails() {
		
		String customerEntitlementRestEndPoint = LOCAL_URL + "customer/entitlement/details/get";
		String requestJson = getCustomerEntitlementDetailsRequestJson();
		System.out.println(requestJson); 
		
		//String requestXml = getCustomerEntitlementRequestXML();
		//System.out.println(requestXml);
		TestUtil.callwebService(DEV_USER_PASS, DEV_API_KEY, customerEntitlementRestEndPoint, "POST", requestJson);
	}
	
	
	private String getCustomerEntitlementRequestJson() {
		
		CustomerEntitlementRequest customerEntitlementRequest = getCustomerEntitlementReques();
		return JsonUtil.convertObjectToJson(customerEntitlementRequest);
	}
	
	private String getCustomerEntitlementRequestXML() {
		
		CustomerEntitlementRequest customerEntitlementRequest = getCustomerEntitlementReques();
		return XmlUtil.convertObjectToXml(customerEntitlementRequest, customerEntitlementRequest.getClass());
	}
	
	private CustomerEntitlementRequest getCustomerEntitlementReques() {
		
		String customerGuid = "dcda5167-8af6-48d3-99cf-4337d19d378a";
		String customerCode = "VUCUS-1";
		
		CustomerEntitlementRequest customerEntitlementRequest = new CustomerEntitlementRequest();
		
		Customer customer = new Customer();
		//customer.setCustomerGuid(customerGuid);
		customer.setCustomerCode(customerCode);
		
		customerEntitlementRequest.setCustomer(customer);
		return customerEntitlementRequest;
		
	}
	
	private String getCustomerEntitlementDetailsRequestJson() {
		
		CustomerEntitlementDetailsRequest request= getCustomerEntitlementDetailsReques();
		return JsonUtil.convertObjectToJson(request);
	}
	
	private String getCustomerEntitlementDetailsRequestXML() {
		
		CustomerEntitlementDetailsRequest request = getCustomerEntitlementDetailsReques();
		return XmlUtil.convertObjectToXml(request, request.getClass());
	}
	
	private CustomerEntitlementDetailsRequest getCustomerEntitlementDetailsReques() {
		
		Long courseCustomerEntitlementId = 179454L;
		Long courseCustomerEntitlementIdProd =  729757L;
		Long courseGroupCustomerEntitlementId = 105104L;
		Long organizationalGroupCustomerEntitlementId = 208205L;
		
		CustomerEntitlementDetailsRequest request = new CustomerEntitlementDetailsRequest();
		
		CustomerEntitlement reqCustomerEntitlement = new CustomerEntitlement();
		reqCustomerEntitlement.setEntitlementId(courseCustomerEntitlementIdProd);
		
		request.setCustomerEntitlement(reqCustomerEntitlement);
		return request;
		
	}
	

}
