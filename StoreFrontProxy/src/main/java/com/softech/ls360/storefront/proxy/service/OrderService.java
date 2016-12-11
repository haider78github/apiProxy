package com.softech.ls360.storefront.proxy.service;

import java.time.LocalDateTime;

import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderServiceResponseType;

public interface OrderService {

	OrderServiceResponseType getOrderList(String storeId, LocalDateTime startDate, LocalDateTime endDate, String status);
	OrderServiceResponseType getRefundList(String storeId, LocalDateTime startDate, LocalDateTime endDate, String status);
	
}
