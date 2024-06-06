package com.jsp.wms.requestdto;


import com.jsp.wms.enums.AdminType;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AdminRequest {
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Name should only contain alphabetic characters")
	private String name;
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should end with @gmail.com")
	private String email;
	 @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
             message = "Password must be alpha-numeric, contain at least 1 capital letter, lowercase letter, special character, and numeric character. It must be at least 8 characters in length.")
	private String password;
	 private AdminType adminType;

}
