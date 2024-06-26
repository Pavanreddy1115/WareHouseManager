package com.jsp.wms.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.wms.entity.Admin;

import lombok.AllArgsConstructor;

public class UserDetailImpl implements UserDetails {
	@Autowired
	private Admin admin;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return admin.getAdminType().getPrivileges().stream().map(privilege->
			new SimpleGrantedAuthority(privilege.name())).toList();
		
	}

	@Override
	public String getPassword() {
		
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		
		return admin.getEmail();
	}

	public UserDetailImpl(Admin admin) {
		super();
		this.admin = admin;
	}

}
