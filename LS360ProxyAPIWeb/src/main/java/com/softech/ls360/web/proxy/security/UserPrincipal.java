package com.softech.ls360.web.proxy.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserPrincipal extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	
	private String userGuid;

	public UserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	
	}
	
	public UserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, 
			String userGuid) {

		this(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userGuid = userGuid;
	
	}
	
	public String getUserGuid() {
		return userGuid;
	}

}
