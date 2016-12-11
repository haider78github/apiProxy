package com.softech.ls360.web.proxy.config.spring.properties.webproxy;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:webproxy.properties")
})
@Import({DevWebProxyProperties.class, QAWebProxyProperties.class, ProdWebProxyProperties.class})
public class WebProxyProperties {

}
