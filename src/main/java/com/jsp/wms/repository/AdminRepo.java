package com.jsp.wms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.enums.AdminType;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
	
public Optional<Admin> findByEmail(String email);
	public boolean existsByAdminType(AdminType superAdmin);

}
