package com.jsp.wms.requestdto;

import com.jsp.wms.entity.Admin;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseRequest {
	private String wareHouseName;
	private int totalCapacity;
}
