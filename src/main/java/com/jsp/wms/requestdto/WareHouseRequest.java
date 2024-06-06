package com.jsp.wms.requestdto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WareHouseRequest {
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Name should only contain alphabetic characters")
	private String wareHouse;
	

}
