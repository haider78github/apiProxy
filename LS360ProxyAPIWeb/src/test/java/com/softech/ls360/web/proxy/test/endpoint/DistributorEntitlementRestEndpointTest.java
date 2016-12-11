package com.softech.ls360.web.proxy.test.endpoint;

import org.junit.Test;

import com.softech.ls360.proxy.api.util.json.JsonUtil;
import com.softech.ls360.proxy.api.util.xml.XmlUtil;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.Distributor;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.DistributorEntitlement;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.DistributorEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.DistributorEntitlementRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.WebProxyDistributorEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.WebProxyDistributorEntitlementRequest;
import com.softech.ls360.web.proxy.test.WebProxyAbstractTest;

public class DistributorEntitlementRestEndpointTest extends WebProxyAbstractTest {

	@Test
	public void test1() {
		
	}
	
	//@Test
	public void getDistributorEntitlements() {
		String distributorEntitlementRestEndPoint = LOCAL_URL + "distributor/entitlement/get";
		String distributorEntitlementRequestJson = getDistributorEntitlementRequestJson();
		System.out.println(distributorEntitlementRequestJson); 
		
		//String distributorEntitlementRequestXml = getDistributoEntitlementRequestXML();
		//System.out.println(distributorEntitlementRequestXml);
		//TestUtil.callwebService(DEV_USER_PASS, DEV_API_KEY, distributorEntitlementRestEndPoint, "POST", distributorEntitlementRequestJson);
	}
	
	//@Test
	public void getDistributorEntitlementDetails() {
		String distributorEntitlementRestEndPoint = LOCAL_URL + "distributor/entitlement/details/get";
		String requestJson = getDistributorEntitlementDetailsRequestJson();
		System.out.println(requestJson); 
		
		//String requestXml = getDistributorEntitlementDetailsRequestXML();
		//System.out.println(requestXml);
		//TestUtil.callwebService(DEV_USER_PASS, DEV_API_KEY, distributorEntitlementRestEndPoint, "POST", requestJson);
	}
	
	private String getDistributorEntitlementRequestJson() {
		
		WebProxyDistributorEntitlementRequest request = getDistributorEntitlementRequest();
		return JsonUtil.convertObjectToJson(request);
	}
	
	private String getDistributoEntitlementRequestXML() {
		
		WebProxyDistributorEntitlementRequest request = getDistributorEntitlementRequest();
		return XmlUtil.convertObjectToXml(request, request.getClass());
	}
	
	private WebProxyDistributorEntitlementRequest getDistributorEntitlementRequest() {
		
		Long distributorId = 1L;
		//Long distributorId = 0L;
		
		Distributor reqDistributor = new Distributor();
		reqDistributor.setId(distributorId);
		
		DistributorEntitlementRequest distributorEntitlementRequest = new DistributorEntitlementRequest();
		distributorEntitlementRequest.setDistributor(reqDistributor);
		
		WebProxyDistributorEntitlementRequest request = new WebProxyDistributorEntitlementRequest();
		request.setDistributorEntitlementRequest(distributorEntitlementRequest);
		return request;
		
	}
	
	private String getDistributorEntitlementDetailsRequestJson() {
		
		WebProxyDistributorEntitlementDetailsRequest request= getDistributorEntitlementDetailsRequest();
		return JsonUtil.convertObjectToJson(request);
	}
	
	private String getDistributorEntitlementDetailsRequestXML() {
		
		WebProxyDistributorEntitlementDetailsRequest request = getDistributorEntitlementDetailsRequest();
		return XmlUtil.convertObjectToXml(request, request.getClass());
	}
	
	private WebProxyDistributorEntitlementDetailsRequest getDistributorEntitlementDetailsRequest() {
		
		Long distributorEntitlementId = 601L;
		
		//Long distributorEntitlementId = 0L;
		
		DistributorEntitlement reqDistributorEntitlement = new DistributorEntitlement();
		reqDistributorEntitlement.setId(distributorEntitlementId);
		
		DistributorEntitlementDetailsRequest distributorEntitlementDetailsRequest = new DistributorEntitlementDetailsRequest();
		distributorEntitlementDetailsRequest.setDistributorEntitlement(reqDistributorEntitlement);
		
		WebProxyDistributorEntitlementDetailsRequest request = new WebProxyDistributorEntitlementDetailsRequest();
		request.setDistributorEntitlementDetailsRequest(distributorEntitlementDetailsRequest);
		return request;
		
	}

}
