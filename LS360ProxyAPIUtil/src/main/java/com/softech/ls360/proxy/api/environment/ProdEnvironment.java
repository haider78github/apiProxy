package com.softech.ls360.proxy.api.environment;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.softech.ls360.proxy.api.util.SpringUtil;

public class ProdEnvironment implements Condition {
	
	private static final Logger log = LogManager.getLogger();
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean prodEnvironment = false;
		Environment environment = context.getEnvironment();
		try {
			boolean linuxEnvironment = SpringUtil.isLinuxEnvironment(environment);
			if (linuxEnvironment) {
				prodEnvironment = SpringUtil.matchEnvironment(environment, "production", "10.0.205.205", "172.16.9.205");
				if (!prodEnvironment) {
					boolean qaEnvironment = SpringUtil.matchEnvironment(environment, "qa", "10.0.215.78");
					if (!qaEnvironment) {
						log.info("try reading environment from properties file environment.properties in LS360Util");
						Properties environmentProperties = SpringUtil.loadPropertiesFileFromClassPath("environment.properties");
						String prodEnvironmentValue  = environmentProperties.getProperty("prod.environment");
						if (StringUtils.isNotBlank(prodEnvironmentValue) && prodEnvironmentValue.equalsIgnoreCase("production")) {
							prodEnvironment = true;
						}
					}	
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		
		if (prodEnvironment) {
			log.info("Condition matches. Production Environment");
		} else {
			log.warn("If not production Environment then igonore this warning. For Production environment. Condition Not matches. Either environment variable \"LS360_ENVIRONMENT\" is not set to \"production\" OR ipaddresses \"10.0.205.205, 172.16.9.205\" do not match with system Ip Address");
		}
		
		return prodEnvironment;
	}
	
}
