package com.softech.ls360.web.proxy.service.impl.lms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softech.ls360.lms.proxy.entities.LmsRole;
import com.softech.ls360.lms.proxy.entities.LmsRoleLmsFeature;
import com.softech.ls360.lms.proxy.entities.VU360User;
import com.softech.ls360.lms.proxy.repositories.LmsRoleLmsFeatureRepository;
import com.softech.ls360.lms.proxy.repositories.VU360UserRepository;
import com.softech.ls360.web.proxy.exception.restful.RestAuthenticationException;
import com.softech.ls360.web.proxy.security.UserPrincipal;
import com.softech.ls360.web.proxy.service.MessageService;
import com.softech.ls360.web.proxy.service.lms.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private MessageService messageService;
	
	@Inject
	private VU360UserRepository vu360UserRepository;
	
	@Inject
	private LmsRoleLmsFeatureRepository lmsRoleLmsFeatureRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		UserDetails userDetails = null;
		VU360User user = vu360UserRepository.findUserAndRolesByUsername(username);
		
		if (user == null) {
			throw new RestAuthenticationException("error.username.password", messageService.getLocalizeMessage("error.username.password"));
		}
		
		Set<String> userRoles = getUserRoles(user);
		if (!CollectionUtils.isEmpty(userRoles)) {
			List<GrantedAuthority> authorities = getGrantedAuthorityList(userRoles);
			boolean enabled = user.getEnabledTf();
			boolean accountNonExpired = user.getAccountNonExpiredTf();
			boolean credentialsNonExpired = user.getCredentialsNonExpiredTf();
			boolean accountNonLocked = user.getAccountNonLockedTf();
			String password = user.getPassword();
			String userGuid = user.getUserGuid();
			userDetails = new UserPrincipal(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, userGuid);
		}
		
		return userDetails;
	}
	
	private Set<String> getUserRoles(VU360User user) {
		
		Set<String> userRoles = null;
		if (user != null) {
			Set<LmsRole> lmsRoles = user.getLmsRoles();
			if (!CollectionUtils.isEmpty(lmsRoles)) {
				List<Long> lmsRoleIds = lmsRoles
						.stream()
						.map(LmsRole::getId)
						.collect(Collectors.toList());
				
				List<LmsRoleLmsFeature> lmsRoleLmsFeatures = lmsRoleLmsFeatureRepository.findByLmsRoleIdIn(lmsRoleIds);
				if (!CollectionUtils.isEmpty(lmsRoleLmsFeatures)) {
					userRoles = new HashSet<>();
					for (LmsRole lmsRole : lmsRoles) {
						String roleType = getRoleName(lmsRole, lmsRoleLmsFeatures);
						if (StringUtils.isNotBlank(roleType)) {
							userRoles.add(roleType);
						}
					}
				}
			}
		}
		return userRoles;
	}
	
	private String getRoleName(LmsRole lmsRole, List<LmsRoleLmsFeature> lmsRoleLmsFeatures) {
		
		String roleType = null;
		if (!CollectionUtils.isEmpty(lmsRoleLmsFeatures)) {
			Long lmsRoleId = lmsRole.getId();
			Optional<LmsRoleLmsFeature> lmsRoleLmsFeature = lmsRoleLmsFeatures.stream()
					.filter(p -> (p.getLmsRole().getId().equals(lmsRoleId)) && (p.getEnabledTf() == Boolean.TRUE))
					.findFirst();
				
			if (lmsRoleLmsFeature.isPresent()) {
				roleType =  lmsRole.getRoleType();
			}
		}
		
		return roleType;
	}
	
	private List<GrantedAuthority> getGrantedAuthorityList(Set<String> authorityRole) {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(String role :  authorityRole) {
			GrantedAuthority authority = getGrantedAuthority(role);
			authorities.add(authority);
		}
		
		return authorities;
	}
	
	private GrantedAuthority getGrantedAuthority(String role) {
		GrantedAuthority authority = new SimpleGrantedAuthority(role);
		return authority;
	}

}
