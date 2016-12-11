package com.softech.ls360.web.proxy.config.spring.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.softech.ls360.web.proxy.config.spring.properties.linuxfoundation.LinuxFoundationPropertiesConfig;
import com.softech.ls360.web.proxy.config.spring.properties.webproxy.WebProxyProperties;

@Configuration
@Import({LinuxFoundationPropertiesConfig.class, WebProxyProperties.class})
public class WebProxyPropertiesConfig {

}
