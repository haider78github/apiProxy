package com.softech.ls360.web.proxy.controller;


import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softech.ls360.web.proxy.config.annotation.WebController;

@WebController
@RequestMapping("dashboard")
public class DashBoardController {

	private static final Logger log = LogManager.getLogger();
	
	@RequestMapping(value = { "", "list" }, method = RequestMethod.GET)
	public String list(Map<String, Object> model) {
		log.debug("Listing tickets.");
		//model.put("tickets", this.ticketService.getAllTickets());
		
		/**
	     * Finally, the logical view name dashboard/list is returned. In the DispatcherServlet configuration, the 
	     * InternalResourceViewResolver is configured as the view resolver, and the file has the prefix /WEB-INF/jsp/view/ and
	     * the suffix .jsp. As a result, Spring MVC will pick up the file /WEB-INF/jsp/view/ticket/list.jsp as the view.
	     */
		return "dashboard/list";
	}
	
}
