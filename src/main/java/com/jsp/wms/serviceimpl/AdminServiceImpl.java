package com.jsp.wms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.enums.Privilege;
import com.jsp.wms.exception.AdminNotFoundByEmailException;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.repository.AdminRepo;
import com.jsp.wms.repository.WareHouseRepo;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;
import com.jsp.wms.util.ResponseStructure;

import jakarta.validation.Valid;

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
	
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@Valid AdminRequest adminRequest) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		
		return adminRepository.findByEmail(name).map(exAdmin ->{
			
			exAdmin = adminMapper.mapToAdmin(adminRequest, exAdmin);
			
			Admin admin = adminRepository.save(exAdmin);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatuscode(HttpStatus.OK.value())
							.setMessage("Admin Updated")
							.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(()-> new AdminNotFoundByEmailException("Admin Not Found"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(@Valid AdminRequest adminRequest,int adminId) {
		
		return adminRepository.findById(adminId).map(exAdmin -> {
			
			exAdmin = adminMapper.mapToAdmin(adminRequest, exAdmin);
			
					Admin admin = adminRepository.save(exAdmin);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatuscode(HttpStatus.OK.value())
							.setMessage("Admin Updated")
							.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(()-> new AdminNotFoundByEmailException("Admin Not Found"));
		
	}
	
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId) {
		
		return adminRepository.findById(adminId).map(admin -> {
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<AdminResponse>()
							.setStatuscode(HttpStatus.FOUND.value())
							.setMessage("Admin Found")
							.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(()-> new AdminNotFoundByEmailException("Admin Not Found"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAdmins() {
		
		List<AdminResponse> admins = adminRepository.findAll().stream().map(admin ->
		adminMapper.mapToAdminResponse(admin)).toList();
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<AdminResponse>>()
						.setStatuscode(HttpStatus.FOUND.value())
						.setMessage("Admins Found")
						.setData(admins));
	}
		



	


	

}
