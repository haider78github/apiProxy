package com.softech.ls360.web.proxy.config.spring.context;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softech.ls360.web.proxy.config.annotation.WebController;
import com.softech.ls360.web.proxy.config.spring.properties.WebProxyPropertiesConfig;
import com.softech.ls360.web.proxy.controller.WebControllerMarker;

@Configuration
@EnableWebMvc
@ComponentScan(
		basePackageClasses = {WebControllerMarker.class},
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({WebController.class})
)
@Import({WebProxyPropertiesConfig.class})
public class WebServletContextConfiguration extends WebMvcConfigurerAdapter {
	
	private static final Logger log = LogManager.getLogger();
	
	@Inject 
	private ApplicationContext applicationContext;
	
	@Inject 
	private ObjectMapper objectMapper;
    
	@Inject
	@Qualifier("webProxyJaxb2Marshaller")
	private Marshaller marshaller;
    
	@Inject
	@Qualifier("webProxyJaxb2Marshaller")
	private Unmarshaller unmarshaller;

	@Inject 
	private SpringValidatorAdapter validator;
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new FormHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());

        MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
        xmlConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "xml"),
                new MediaType("text", "xml")
        ));
        xmlConverter.setMarshaller(this.marshaller);
        xmlConverter.setUnmarshaller(this.unmarshaller);
        converters.add(xmlConverter);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json"),
                new MediaType("text", "json")
        ));
        jsonConverter.setObjectMapper(this.objectMapper);
        converters.add(jsonConverter);
    }
	
	@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
        	.favorParameter(false)
            .parameterName("mediaType")
            .ignoreAcceptHeader(false)
            .useJaf(false)
            .defaultContentType(MediaType.APPLICATION_XML)
            .mediaType("xml", MediaType.APPLICATION_XML)
            .mediaType("json", MediaType.APPLICATION_JSON);
    }
	
	@Override
    public Validator getValidator() {
        return this.validator;
    }
	
	@Bean
	public ViewResolver viewResolver() {
	        
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setViewClass(JstlView.class);
	    resolver.setPrefix("/WEB-INF/jsp/view/");
	    resolver.setSuffix(".jsp");
	    return resolver;
	}
	
	@Bean
	public RequestToViewNameTranslator viewNameTranslator() {
		return new DefaultRequestToViewNameTranslator();
	}
	
	@Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
	    registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        
		Sort defaultSort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable defaultPageable = new PageRequest(0, 10, defaultSort);

        SortHandlerMethodArgumentResolver sortResolver = new SortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter("paging.sort");
        sortResolver.setFallbackSort(defaultSort);

        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver(sortResolver);
        pageableResolver.setMaxPageSize(100);
        pageableResolver.setOneIndexedParameters(true);
        pageableResolver.setPrefix("paging.");
        pageableResolver.setFallbackPageable(defaultPageable);

        resolvers.add(sortResolver);
        resolvers.add(pageableResolver);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        if(!(registry instanceof FormattingConversionService)) {
            log.warn("Unable to register Spring Data JPA converter.");
            return;
        }

        DomainClassConverter<FormattingConversionService> converter = new DomainClassConverter<>((FormattingConversionService)registry);
        converter.setApplicationContext(this.applicationContext);
    }
	
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
    
}
