package com.jsp.wms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController {
	
	
	@GetMapping("/warehouses")
	public String createWareHouse(){
	return "warehouse FOUND" ;
	}

}
