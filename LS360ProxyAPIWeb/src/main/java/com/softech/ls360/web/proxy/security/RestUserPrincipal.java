package com.softech.ls360.web.proxy.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class RestUserPrincipal extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	private Long distributorId;
	private String apiKey;
	private String environment;
	private String privilege;
	private String allowFrequency;
	private Long customerId;
	private String customerCode;

	public RestUserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Long distributorId, String apiKey, String environment, String privilege, String allowFrequency) {

		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.distributorId = distributorId;
		this.apiKey = apiKey;
		this.environment = environment;
		this.privilege = privilege;
		this.allowFrequency = allowFrequency;
	}

	public Long getDistributorId() {
		return distributorId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getEnvironment() {
		return environment;
	}

	public String getPrivilege() {
		return privilege;
	}

	public String getAllowFrequency() {
		return allowFrequency;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
}
