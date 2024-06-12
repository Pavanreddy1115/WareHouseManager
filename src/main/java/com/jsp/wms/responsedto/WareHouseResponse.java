package com.jsp.wms.responsedto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WareHouseResponse {
	private int warehouseId;
	private String wareHouseName;
	private int totalCapacity;

}
