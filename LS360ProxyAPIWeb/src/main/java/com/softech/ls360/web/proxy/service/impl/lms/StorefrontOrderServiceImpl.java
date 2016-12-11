package com.softech.ls360.web.proxy.service.impl.lms;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.softech.ls360.storefront.proxy.service.OrderService;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderServiceResponseType;
import com.softech.ls360.web.proxy.service.lms.StorefrontOrderService;

@Service
public class StorefrontOrderServiceImpl implements StorefrontOrderService {

	@Inject
	private OrderService sfProxyOrderService;
	
	@Override
	public OrderServiceResponseType getOrderList(String storeId, String startDate, String endDate, String status) throws Exception {
		
		LocalDateTime fromDate = LocalDateTime.parse(startDate);
		LocalDateTime toDate = LocalDateTime.parse(startDate);
		
		return sfProxyOrderService.getOrderList(storeId, fromDate, toDate, status);
	}

	@Override
	public OrderServiceResponseType getRefundList(String storeId, String startDate, String endDate, String status) throws Exception {
		return getOrderList(storeId, startDate, endDate, status);
	}

}
