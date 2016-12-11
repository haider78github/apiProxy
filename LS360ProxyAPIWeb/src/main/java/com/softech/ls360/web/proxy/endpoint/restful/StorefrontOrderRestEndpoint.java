package com.softech.ls360.web.proxy.endpoint.restful;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderServiceResponseType;
import com.softech.ls360.web.proxy.config.annotation.RestEndpoint;
import com.softech.ls360.web.proxy.service.lms.StorefrontOrderService;
import com.softech.ls360.web.proxy.validation.NotBlank;

@RestEndpoint
@RequestMapping(value="/sf")
public class StorefrontOrderRestEndpoint {

	//@Inject
	//private AuthenticationService webProxyAuthenticationService;
	
	@Inject
	private StorefrontOrderService storefrontOrderService;
	
	@RequestMapping(value = "/orderlist", method = RequestMethod.GET, produces={"application/xml", "application/json"})
	@ResponseBody
	public OrderServiceResponseType getOrderList(@RequestHeader(value="Authorization", required=true) String authorization,
			@RequestHeader(value="key", required=true) String clientApiKey,
			@RequestParam(value="storeId", required=true) @NotBlank(message="validate.store.id") String storeId,
			@RequestParam(value="fromDate", required=true) @NotBlank(message="validate.from.date") String fromDate,
			@RequestParam(value="toDate", required=true) @NotBlank(message="validate.to.date") String toDate) throws Exception {
		
		String operation = "getOrderList";
		String status = "C";
		
		//webProxyAuthenticationService.authenticate(authorization, clientApiKey, storeId, operation);
		OrderServiceResponseType orderServiceResponseType = storefrontOrderService.getOrderList(storeId, fromDate, toDate, status);
		return orderServiceResponseType;
		
	}
	
	@RequestMapping(value = "/refundlist", method = RequestMethod.GET, produces={"application/xml", "application/json"})
	@ResponseBody
	public OrderServiceResponseType getRefundList(@RequestHeader(value="Authorization", required=true) String authorization,
			@RequestHeader(value="key", required=true) String clientApiKey,
			@RequestParam(value="storeId", required=true) @NotBlank(message="validate.store.id") String storeId,
			@RequestParam(value="fromDate", required=true) @NotBlank(message="validate.from.date") String fromDate,
			@RequestParam(value="toDate", required=true) @NotBlank(message="validate.to.date") String toDate) throws Exception {
		
		String operation = "getRefundList";
		String status = "X";
		
		//webProxyAuthenticationService.authenticate(authorization, clientApiKey, storeId, operation);
		OrderServiceResponseType orderServiceResponseType = storefrontOrderService.getRefundList(storeId, fromDate, toDate, status);
		return orderServiceResponseType;
		
	}
	
}
