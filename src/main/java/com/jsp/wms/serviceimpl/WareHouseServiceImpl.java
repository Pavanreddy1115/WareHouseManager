package com.jsp.wms.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.enums.Privilege;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.WareHouseMapper;
import com.jsp.wms.repository.WareHouseRepo;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.WareHouseResponse;
import com.jsp.wms.service.WareHouseService;
import com.jsp.wms.util.ResponseStructure;

@Service
public class WareHouseServiceImpl implements WareHouseService {
	
	@Autowired
	private WareHouseMapper wareHouseMapper;
	
	@Autowired
	private WareHouseRepo wareHouseRepo;

	

	

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) {
		WareHouse wareHouse = wareHouseMapper.mapToWareHouse(wareHouseRequest, new WareHouse());
		wareHouseRepo.save(wareHouse);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<WareHouseResponse>().setStatuscode(HttpStatus.CREATED.value()).setMessage("ware House Created").setData(wareHouseMapper.mapToWarehouseResponse(wareHouse)));
}





	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(WareHouseRequest wareHouseRequest,int wareHouseId) {
		return wareHouseRepo.findById(wareHouseId).map(exwareHouse ->{
			exwareHouse =wareHouseMapper.mapToWareHouse(wareHouseRequest, exwareHouse);
			WareHouse wareHouse = wareHouseRepo.save(exwareHouse);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<WareHouseResponse>()
							.setStatuscode(HttpStatus.OK.value())
							.setMessage("Warehouse Updated")
							.setData(wareHouseMapper.mapToWarehouseResponse(exwareHouse)));
		}).orElseThrow(()-> new WarehouseNotFoundByIdException("WareHouse Not Found"));
			}
	
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(int warehouseId) {
		
	return	wareHouseRepo.findById(warehouseId).map(warehouse ->{
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<WareHouseResponse>()
					.setStatuscode(HttpStatus.FOUND.value())
					.setMessage("Warehouse found")
					.setData(wareHouseMapper.mapToWarehouseResponse(warehouse)));
		}).orElseThrow(()-> new WarehouseNotFoundByIdException("wareHouse not Found"));
	}
	
	
}