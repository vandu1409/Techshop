package com.vandu.controller.site;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.AddressDto;
import com.vandu.dto.response.AddressResponseDto;
import com.vandu.helper.UserHelper;
import com.vandu.model.Address;
import com.vandu.model.User;
import com.vandu.service.AddressService;
import com.vandu.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import net.minidev.json.writer.BeansMapper.Bean;

@RestController
@RequestMapping("address")
public class AddressApiController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@GetMapping("getDefaultAddress")
	public ResponseEntity<AddressDto> getDefaultAddress() {
		User user = UserHelper.getCurrentUser(request, userService);

		Address address = addressService.getDefaultAddress(user.getUserId()).orElse(null);
		AddressDto addressDto = new AddressDto();
		if(address!=null) {
			BeanUtils.copyProperties(address, addressDto);
		}

		return ResponseEntity.ok(addressDto);
	}
	
	@GetMapping("getAddressByUser")
	public ResponseEntity<List<AddressResponseDto>> getAddressByUser(){
		User user = UserHelper.getCurrentUser(request, userService);
		
		List<AddressResponseDto> list =  addressService.findAllByUser(user.getUserId(),false).stream().map(item->{
			AddressResponseDto addressResponseDto = new AddressResponseDto();
			
			BeanUtils.copyProperties(item, addressResponseDto);
			
			return addressResponseDto;
		}).toList();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("edit/{id}")
	public ResponseEntity<?> edit(@PathVariable("id")Long id){
		Address address = addressService.findById(id).orElse(null);
		AddressDto addressDto = new AddressDto();
		
		BeanUtils.copyProperties(address, addressDto);
		
		return ResponseEntity.ok(addressDto);
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody AddressDto addressDto){
		User user = UserHelper.getCurrentUser(request, userService);
		Address currentAddress = addressService.findById(addressDto.getAddressId()).orElse(null);
		
		Address address = new Address();
		BeanUtils.copyProperties(addressDto, address);
		
		if(currentAddress!=null && currentAddress.getOrders().size()==0) {
			address.setAddressId(currentAddress.getAddressId());
			address.setUser(user);
			addressService.save(address);
			
			return ResponseEntity.ok("Success");
		}
		
		
		address.setUser(user);
		
		if(currentAddress!=null) {
			System.err.println(currentAddress.getOrders());
			currentAddress.setIsDelete(true); // set địa cũ thàng đã xóa để các order được đúng địa chỉ khi đặt
			addressService.save(currentAddress);
		}
		
		address.setAddressId(null);
		
		BeanUtils.copyProperties(addressService.save(address), addressDto);
		
		return ResponseEntity.ok(addressDto);
	}
	
	@PutMapping("deleteAddress/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable("id")Long id){
		Address currentAddress = addressService.findById(id).orElse(null);
		
		if(currentAddress!=null) {
			currentAddress.setIsDelete(true);
			addressService.save(currentAddress);
		}
		
		return ResponseEntity.ok("Success");
	}
	
	@PostMapping("addToAddress")
	public ResponseEntity<?> addToAddress(@RequestBody AddressDto addressDto){
		User user = UserHelper.getCurrentUser(request, userService);
		
		Address address = new Address();
		BeanUtils.copyProperties(addressDto, address);
		address.setIsActive(true);
		address.setIsDelete(false);
		
		address.setUser(user);
		
		addressService.changeDefaultAddressesToRegular(user.getUserId());
		addressService.save(address);
		
		return ResponseEntity.ok("Success");
		
	}
	
	@PutMapping("changeAddressDefault/{addressId}")
	public ResponseEntity<?> changeAddressDefault(@PathVariable("addressId") Long addressId){
		Address address = addressService.findById(addressId).orElse(null);
		
		if(address!=null) {
			User user = UserHelper.getCurrentUser(request, userService);
			address.setIsActive(true);
			addressService.changeDefaultAddressesToRegular(user.getUserId());
			addressService.save(address);
		}
		
		return ResponseEntity.ok("success");
	}
 }
