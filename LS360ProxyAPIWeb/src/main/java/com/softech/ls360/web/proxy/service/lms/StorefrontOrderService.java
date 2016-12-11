package com.softech.ls360.web.proxy.service.lms;

import com.softech.ls360.lms.proxy.entities.Distributor;
import com.softech.ls360.storefront.proxy.webservice.messages.orderdetails.OrderServiceResponseType;

public interface StorefrontOrderService {

	OrderServiceResponseType getOrderList(String storeId, String startDate, String endDate, String status) throws Exception;
	OrderServiceResponseType getRefundList(String storeId, String startDate, String endDate, String status) throws Exception;

}
