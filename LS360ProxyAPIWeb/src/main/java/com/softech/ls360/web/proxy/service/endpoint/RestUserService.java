package com.softech.ls360.web.proxy.service.endpoint;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RestUserService extends UserDetailsService {

	@Override
    UserDetails loadUserByUsername(String username);
	
}
