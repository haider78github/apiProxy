package com.softech.ls360.storefront.proxy.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.softech.ls360.storefront.proxy.service.impl.StorefrontProxyServiceMarker;

@Configuration
@Import({StorefrontWebServiceConfig.class})
@ComponentScan(basePackageClasses = { StorefrontProxyServiceMarker.class })
public class StorefrontProxyAppConfig {

}
