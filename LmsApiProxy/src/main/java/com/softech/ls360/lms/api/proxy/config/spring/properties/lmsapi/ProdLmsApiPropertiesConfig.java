package com.softech.ls360.lms.api.proxy.config.spring.properties.lmsapi;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.softech.ls360.proxy.api.environment.ProdEnvironment;

@Configuration
@Conditional(ProdEnvironment.class)
@PropertySources({
	@PropertySource("classpath:lmsapi-webservice-prod.properties")
})
public class ProdLmsApiPropertiesConfig {

}
