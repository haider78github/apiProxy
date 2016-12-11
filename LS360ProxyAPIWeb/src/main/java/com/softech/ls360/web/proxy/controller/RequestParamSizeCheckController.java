package com.softech.ls360.web.proxy.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softech.ls360.web.proxy.config.annotation.WebController;

@WebController
@RequestMapping(value="/header/size")
public class RequestParamSizeCheckController {

	private static final Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public void getCourseCompletion(@RequestParam(value="param", required = true) String param) throws Exception {
		
		logger.info("Request received at " + getClass().getName() + " for size limit" );
		if (StringUtils.isNotBlank(param)) {
			logger.info("param: " + param);
			logger.info("param length: " + param.length());
		}
		
	}
	
}
