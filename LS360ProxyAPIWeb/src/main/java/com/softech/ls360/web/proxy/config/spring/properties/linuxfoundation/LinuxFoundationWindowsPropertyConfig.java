package com.softech.ls360.web.proxy.config.spring.properties.linuxfoundation;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.softech.ls360.proxy.api.environment.DevEnvironment;

@Configuration
@Conditional(DevEnvironment.class)
@PropertySources({
	@PropertySource("classpath:linux-foundation/lf_win.properties")
})
public class LinuxFoundationWindowsPropertyConfig {

}
