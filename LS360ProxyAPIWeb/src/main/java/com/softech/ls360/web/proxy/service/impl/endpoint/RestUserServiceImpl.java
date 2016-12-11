package com.softech.ls360.web.proxy.service.impl.endpoint;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.softech.ls360.lms.proxy.entities.Distributor;
import com.softech.ls360.lms.proxy.entities.LmsApiDistributor;
import com.softech.ls360.lms.proxy.repositories.LmsApiDistributorRepository;
import com.softech.ls360.web.proxy.exception.restful.RestAuthenticationException;
import com.softech.ls360.web.proxy.security.RestUserPrincipal;
import com.softech.ls360.web.proxy.service.MessageService;
import com.softech.ls360.web.proxy.service.endpoint.RestUserService;

@Service
public class RestUserServiceImpl implements RestUserService {

	@Inject
	private LmsApiDistributorRepository lmsApiDistributorRepository;
	
	@Inject
	private MessageService messageService;
	
	@Override
	public UserDetails loadUserByUsername(String userName)  {
		
		LmsApiDistributor lmsApiDistributor = lmsApiDistributorRepository.findByUserName(userName);
		if (lmsApiDistributor == null) {
			throw new RestAuthenticationException("error.username.password", messageService.getLocalizeMessage("error.username.password"));
		}
		
		Distributor distributor = lmsApiDistributor.getDistributor();
		Long distributorId = distributor.getId();
		String apiKey = lmsApiDistributor.getApiKey();
		String environment = lmsApiDistributor.getEnvironment();
		String privilege = lmsApiDistributor.getPrivilege();
		String restUserName = lmsApiDistributor.getUserName();
		String restUserPassword = lmsApiDistributor.getPassword();
		String allowFrequency = lmsApiDistributor.getAllowFrequency();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_REST_USER");
		UserDetails userDetails = new RestUserPrincipal(restUserName, restUserPassword, enabled, accountNonExpired, 
				credentialsNonExpired, accountNonLocked, Arrays.asList(authority), distributorId, apiKey, environment, privilege, 
				allowFrequency);
		return userDetails;
	}
	
}
