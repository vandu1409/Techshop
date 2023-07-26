package com.vandu.controller.admin;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.FeatureDto;
import com.vandu.model.Attributes;
import com.vandu.model.Feature;
import com.vandu.service.FeatureService;

@RestController
@RequestMapping("admin/feature")
public class FeatureApiController {
	
	@Autowired
	private FeatureService featureService;
	
	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> saveOrUpdate(@RequestBody FeatureDto featureDto){
		Feature feature = new Feature();
		
		BeanUtils.copyProperties(featureDto, feature);
		
		BeanUtils.copyProperties(featureService.save(feature),featureDto );
		
		return ResponseEntity.ok(featureDto);
		
	}
	
	@GetMapping("edit/{id}")
	public ResponseEntity<?> edit(@PathVariable("id")Long id){
		FeatureDto featureDto = new FeatureDto();
		
		BeanUtils.copyProperties(featureService.findById(id).or(null),featureDto);
		
		return ResponseEntity.ok(featureDto);
		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		featureService.deleteById(id);
		
		return ResponseEntity.ok("Success");
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(featureService.findAll().stream().map(item->{
			FeatureDto featureDto = new FeatureDto();
			
			BeanUtils.copyProperties(item, featureDto);
			
			return featureDto;
		}).toList());
	}
	
	@GetMapping("pagination")
	public ResponseEntity<?> pagination(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
	
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<Feature> list  = featureService.findAll(pageable);
		

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
