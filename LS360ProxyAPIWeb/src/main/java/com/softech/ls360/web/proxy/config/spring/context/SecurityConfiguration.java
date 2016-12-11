package com.softech.ls360.web.proxy.config.spring.context;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import com.softech.ls360.web.proxy.filter.FilterMarker;
import com.softech.ls360.web.proxy.filter.RestAuthenticationFilter;
import com.softech.ls360.web.proxy.service.endpoint.RestUserService;
import com.softech.ls360.web.proxy.service.lms.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true, 
    order = 0, 
    mode = AdviceMode.PROXY,
    proxyTargetClass = false
)
public class SecurityConfiguration {
	
	private static final Logger log = LogManager.getLogger();
	
	@Configuration
	@ComponentScan(
			basePackageClasses = {FilterMarker.class}      
	)
	@Order(1)
	public static class RestApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Inject 
		private RestUserService restUserService;
		
		@Inject 
		private RestAuthenticationFilter restAuthenticationFilter;
		
		@Inject
		private AuthenticationEntryPoint restAuthenticationEntryPoint;
		
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	    
		@Override
		protected void configure(AuthenticationManagerBuilder builder) throws Exception {
			 builder
	             .userDetailsService(this.restUserService)
	                // .passwordEncoder(new BCryptPasswordEncoder())
	         .and()
	             .eraseCredentials(true);
		}
		
		protected void configure(HttpSecurity security) throws Exception {
			
			log.info("Configuring Rest API Security.");
			
			security
				.antMatcher("/restful/**") // only process URLs that begin with /restful/
				.authorizeRequests()
					.antMatchers("/restful/lms/**").hasAuthority("ROLE_REST_USER")
				.and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // RESTful web services are stateless
				.and()
					.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
				.and()
					.csrf()
						.disable();		
			
			security.addFilterAfter(restAuthenticationFilter, ExceptionTranslationFilter.class);
		}
	}
	
	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Inject 
		private UserService userService;
	
		@Bean
	    protected SessionRegistry sessionRegistryImpl() {
	        return new SessionRegistryImpl();
	    }
		
	    
		@Override
		protected void configure(AuthenticationManagerBuilder builder) throws Exception {
			
			ReflectionSaltSource saltSource = new ReflectionSaltSource();
	        saltSource.setUserPropertyToUse("userGuid");
			
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setSaltSource(saltSource);
	        
	        provider.setUserDetailsService(this.userService);
	        provider.setPasswordEncoder(new ShaPasswordEncoder());
	        
	        builder.authenticationProvider(provider);
	        
		}
		
		@Override
		public void configure(WebSecurity security) {
			security.ignoring().antMatchers("/resource/**", "/header/size/**");
		}
		
		@Override
		protected void configure(HttpSecurity security) throws Exception {
			
			log.info("Configuring Web Security.");
			
			security
				.authorizeRequests()
				    .antMatchers("/sso/cas/lf/**").hasAuthority("ROLE_ANONYMOUS")
					
					// Any request simply requires authentication, regardless of permissions.
					.anyRequest()
					.authenticated()
				.and()
					.formLogin()
						.loginPage("/login")
						.failureUrl("/login?loginFailed")
						.defaultSuccessUrl("/dashboard/list")
						.usernameParameter("username")
						.passwordParameter("password")
						.permitAll()
				.and()
					.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?loggedOut")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll()
				.and()
					.sessionManagement()
						.sessionFixation()
						.changeSessionId()
						.maximumSessions(1)
						.maxSessionsPreventsLogin(true)
						.sessionRegistry(this.sessionRegistryImpl())
					.and()
				.and()
					.csrf()
						.requireCsrfProtectionMatcher((r) -> {
							String m = r.getMethod();
							String servletPath = r.getServletPath();
							return !(servletPath.startsWith("/services/") || servletPath.startsWith("/restful/")) && ("POST".equals(m) || "PUT".equals(m) ||
                                "DELETE".equals(m) || "PATCH".equals(m));
						});		
		}	
	}
}
