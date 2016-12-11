package com.softech.ls360.proxy.api.environment;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DevEnvironment implements Condition {

	private static final Logger log = LogManager.getLogger();
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		
		boolean matches = false;
		
		Environment environment = context.getEnvironment();
		String operatingSystem = environment.getProperty("os.name");
		String ls360Environment = environment.getProperty("LS360_ENVIRONMENT");
		if (StringUtils.isNotBlank(operatingSystem) && StringUtils.isNotBlank(ls360Environment)) {
			boolean windowsOperatingSystem = operatingSystem.indexOf("Win") >= 0;
			if (windowsOperatingSystem && ls360Environment.equalsIgnoreCase("development")) {
				matches = true;
			}
		} else if (StringUtils.isNotBlank(operatingSystem)) {
			matches = operatingSystem.indexOf("Win") >= 0;
		}
		
		if (matches) {
			log.info("Condition matches. Dev Environment");
		}
		
		return matches;
	}
	
}
