package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;

@Component
public class AdminMapper {
	
	public Admin mapToAdmin(AdminRequest userRequest, Admin admin) {
		admin.setName(userRequest.getName());
		admin.setEmail(userRequest.getEmail());
		admin.setPassword(userRequest.getPassword());
		return admin;
	}
	public AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder()
				.adminId(admin.getAdminId())
				.name(admin.getName())
				.email(admin.getEmail())
				.adminType(admin.getAdminType())
				.build();
	}
	

}
