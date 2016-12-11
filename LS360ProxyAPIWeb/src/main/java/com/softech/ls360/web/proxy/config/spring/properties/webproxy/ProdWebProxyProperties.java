package com.softech.ls360.web.proxy.config.spring.properties.webproxy;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.softech.ls360.proxy.api.environment.ProdEnvironment;

@Configuration
@Conditional(ProdEnvironment.class)
@PropertySources({
	@PropertySource("classpath:webproxy-prod.properties")
})
public class ProdWebProxyProperties {

}
