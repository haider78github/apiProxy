package com.softech.ls360.lms.api.proxy.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.softech.ls360.lms.api.proxy.service.impl.LmsApiProxyServiceMarker;

@Configuration
@Import({LmsApiWebServiceConfig.class})
@ComponentScan(basePackageClasses = { LmsApiProxyServiceMarker.class })
public class LmsApiProxyAppConfig {

}
