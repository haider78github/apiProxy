package com.softech.ls360.web.proxy.config.spring.properties.linuxfoundation;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.softech.ls360.proxy.api.environment.LinuxEnvironment;

@Configuration
@Conditional(LinuxEnvironment.class)
@PropertySources({
	@PropertySource("classpath:linux-foundation/lf_linux.properties")
})
public class LinuxFoundationLinuxPropertyConfig {

}
