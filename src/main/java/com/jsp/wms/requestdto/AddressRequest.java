package com.jsp.wms.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
	@NotNull(message = "Addressline cannot be null")
	@NotBlank(message = "Addressline cannot be blank")
	private String addressLine;
	@NotNull(message = "City cannot be null")
	@NotBlank(message = "City cannot be blank")
	private String city;
	@NotNull(message = "State cannot be null")
	@NotBlank(message = "State cannot be blank")
	private String state;
	@NotNull(message = "Country cannot be null")
	@NotBlank(message = "Country name cannot be blank")
	private String country;
	
	private int pincode;
	private String longitude;
	private String latitude;

}
