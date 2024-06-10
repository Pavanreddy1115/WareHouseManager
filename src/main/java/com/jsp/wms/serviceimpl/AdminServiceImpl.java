package com.jsp.wms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.enums.Privilege;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.repository.AdminRepo;
import com.jsp.wms.repository.WareHouseRepo;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;
import com.jsp.wms.util.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminRepo adminRepository;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private WareHouseRepo wareHouseRepo;
	private Admin admin;



	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(AdminRequest adminRequest) {
		if(adminRepository.existsByAdminType(AdminType.SUPER_ADMIN))
			throw new IllegalOperationException("Admin Already Exists");
		Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		Admin admin2 = adminRepository.save(admin);	
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AdminResponse>()
				.setStatuscode(HttpStatus.CREATED.value())
				.setMessage(" Super Admin created")
				.setData(adminMapper.mapToAdminResponse(admin2)));

	
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest, int wareHouseId) {
		
		 return wareHouseRepo.findById(wareHouseId).map(warehouse -> {
		        Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
		        admin.setAdminType(AdminType.ADMIN);
		        adminRepository.save(admin);
		        warehouse.setAdmin(admin);
		        wareHouseRepo.save(warehouse);

		        return ResponseEntity
		                .status(HttpStatus.CREATED)
		                .body(new ResponseStructure<AdminResponse>()
		                        .setStatuscode(HttpStatus.CREATED.value())
		                        .setMessage("Admin saved successfully")
		                        .setData(adminMapper.mapToAdminResponse(admin)));
		    }).orElseThrow(() -> new RuntimeException("Warehouse not found"));
	    }
		



	


	

}
