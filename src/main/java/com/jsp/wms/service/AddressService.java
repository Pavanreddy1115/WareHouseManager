package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.util.ResponseStructure;

import jakarta.validation.Valid;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@Valid AddressRequest addressRequest, int warehouseId);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(int addressId, AddressRequest addressRequest);

	ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId);

}
