package com.softech.ls360.storefront.proxy.service.impl;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.softech.ls360.storefront.proxy.service.OrderService;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderServiceRequest;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderServiceRequestType;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderServiceResponseType;

/**
 * You use Spring’s @Service annotation to specify that the bean provides services that other beans may require, passing in the 
 * bean name as the parameter. When bootstrapping Spring’s ApplicationContext with the XML configuration, Spring will seek out
 * those components and instantiate the beans with the specified names.
 * 
 * The @Service annotation is to identify that it’s a Spring component that provides business services to another layer and 
 * assigns the Spring bean the name learnerCourseStatisticsService.
 * 
 * @author Basit
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private WebServiceTemplate orderDetailWebServiceTemplate;
	
	@Override
	public OrderServiceResponseType getOrderList(String storeId, LocalDateTime startDate, LocalDateTime endDate, String status) {
		
		OrderServiceRequest orderDetailRequest = getOrderServiceRequest(storeId, startDate, endDate, status);
		
		logger.info( "Calling webservice ..." + orderDetailRequest);
		logger.info("......Waiting for Response Object.....");
		
		OrderServiceResponseType orderServiceResponseType = (OrderServiceResponseType)orderDetailWebServiceTemplate.marshalSendAndReceive(orderDetailRequest);
		return orderServiceResponseType;
	}

	@Override
	public OrderServiceResponseType getRefundList(String storeId, LocalDateTime startDate, LocalDateTime endtDate, String status) {
		return getOrderList(storeId, startDate, endtDate, status);
	}
	
	private OrderServiceRequest getOrderServiceRequest(String storeId, LocalDateTime startDate, LocalDateTime endDate, String status) {
		
		OrderServiceRequestType orderServiceRequestType = new OrderServiceRequestType();
		orderServiceRequestType.setStoreId(storeId);
		orderServiceRequestType.setStartDate(startDate);
		orderServiceRequestType.setEndDate(endDate);
		orderServiceRequestType.setStatus(status);
			
		OrderServiceRequest orderServiceRequest = new OrderServiceRequest();
		orderServiceRequest.setIn(orderServiceRequestType);
		
		return orderServiceRequest;
	}

}
