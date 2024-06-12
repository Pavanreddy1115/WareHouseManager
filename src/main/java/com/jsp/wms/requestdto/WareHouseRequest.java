package com.jsp.wms.requestdto;

import com.jsp.wms.entity.Admin;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WareHouseRequest {
	
	
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Name should only contain alphabetic characters")
	private String wareHouseName;
	private int totalCapacity;
	
	private Admin admin;
	

}
