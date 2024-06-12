package com.jsp.wms.mapper;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.responsedto.WareHouseResponse;

public class WareHouseMapper {

	public WareHouseResponse mapToWarehouseResponse(WareHouse wareHouse) {
	
		return WareHouseResponse.builder()
				.warehouseId(wareHouse.getWareHouseId())
				.wareHouseName(wareHouse.getWareHouseName())
				.totalCapacity(wareHouse.getTotalCapacity())
				.build();
	}
	
	public WareHouse mapToWareHouse(WareHouseRequest wareRequest, WareHouse wareHouse) {
		wareHouse.setWareHouseName(wareRequest.getWareHouseName());
		wareRequest.setTotalCapacity(0);
		
		return wareHouse ;
	}
}
