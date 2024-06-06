package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.util.ResponseStructure;

@Service
public interface AdminService {
	

//	public ResponseEntity<ResponseStructure<AdminResponse>> register(AdminRequest adminRequest);

	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(AdminRequest adminRequest);


}
