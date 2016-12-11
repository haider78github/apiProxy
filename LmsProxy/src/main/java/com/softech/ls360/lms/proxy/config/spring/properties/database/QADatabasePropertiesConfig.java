package com.softech.ls360.lms.proxy.config.spring.properties.database;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.softech.ls360.proxy.api.environment.QAEnvironment;

@Configuration
@Conditional(QAEnvironment.class)
@PropertySources({
	@PropertySource("classpath:database/jdbc-qa.properties")
})
public class QADatabasePropertiesConfig {

}
