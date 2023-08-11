package com.vandu.controller.site;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.FeatureDto;
import com.vandu.service.FeatureService;

@RestController
@RequestMapping("feature")
public class FeatureSiteController {
	
	@Autowired
	private FeatureService featureService;
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(featureService.findAll().stream().map(item->{
			FeatureDto featureDto = new FeatureDto();
			
			BeanUtils.copyProperties(item, featureDto);
			
			return featureDto;
		}).toList());
	}

}
