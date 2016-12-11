package com.softech.ls360.web.proxy.config.spring.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import com.softech.ls360.web.proxy.endpoint.soap.SoapEndpointMarker;


@Configuration
@ComponentScan(
    basePackageClasses = {SoapEndpointMarker.class}, 
    useDefaultFilters = false, 
    includeFilters = @ComponentScan.Filter(Endpoint.class)
)

@ImportResource("classpath:config/soapServletContext.xml")
public class SoapServletContextConfiguration {
	
	@Bean
	public WebServiceMessageFactory messageFactory() {
		SaajSoapMessageFactory factory = new SaajSoapMessageFactory();
		factory.setSoapVersion(SoapVersion.SOAP_11);
		return factory;
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}
