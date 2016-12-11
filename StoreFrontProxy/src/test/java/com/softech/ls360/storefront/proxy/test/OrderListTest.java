package com.softech.ls360.storefront.proxy.test;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.softech.ls360.storefront.proxy.service.OrderService;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.Order;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderDetailResponse;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderItem;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderItemsList;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderList;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderServiceResponseType;

public class OrderListTest extends StorefrontProxyAbstractTest {

	@Inject
	private OrderService sfProxyOrderService;
	
	@Test
	public void test() {
	
	}
	
	//@Test
	public void orderListTest() {
		//String storeId = "165252";   // Prod Linux Foundation
		String storeId = "21701";      // Prod mega site
		String startDate = "2014-06-11T00:00:00";
		String endDate = "2014-06-11T23:59:59";
		String status = "C";
		
		LocalDateTime fromDate = LocalDateTime.parse(startDate);
		LocalDateTime toDate = LocalDateTime.parse(endDate);
		
		try {
			OrderServiceResponseType orderServiceResponseType =  sfProxyOrderService.getOrderList(storeId, fromDate, toDate, status);
			OrderList orderList = orderServiceResponseType.getOrders();
			if (orderList == null) {
				System.out.println("orderList is null");
			} else {
				List<OrderDetailResponse> orderDetailResponseList = orderList.getOrder();
				for (OrderDetailResponse orderDetailResponse : orderDetailResponseList) {
					Order order = orderDetailResponse.getOrderdetails();
					OrderItemsList orderItems = order.getOrderItems();
					List<OrderItem> orderItemList = orderItems.getOrderItem();
				    for (OrderItem orderItem : orderItemList) {
				    	int quantity = orderItem.getQuantity() ;
				    	System.out.println(quantity);
				    }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
