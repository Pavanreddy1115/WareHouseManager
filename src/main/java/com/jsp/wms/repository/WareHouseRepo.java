package com.jsp.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.WareHouse;

public interface WareHouseRepo extends JpaRepository<WareHouse, Integer> {

}
