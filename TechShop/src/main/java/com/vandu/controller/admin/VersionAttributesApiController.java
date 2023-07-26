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

import com.vandu.dto.CategoryAttributesDto;
import com.vandu.dto.VersionAttributesDto;
import com.vandu.model.Attributes;
import com.vandu.model.CategoryAttributes;
import com.vandu.model.ProductVersion;
import com.vandu.model.VersionAttributes;
import com.vandu.service.VersionAttributesService;

@RestController
@RequestMapping("admin/version-atrributes")
public class VersionAttributesApiController {

	@Autowired
	private VersionAttributesService versionAttributesService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> saveOrUpdate(@RequestBody VersionAttributesDto versionAttributesDto) {
		VersionAttributes versionAttributes = new VersionAttributes();

		BeanUtils.copyProperties(versionAttributesDto, versionAttributes);

		Attributes attributes = new Attributes();
		attributes.setId(versionAttributesDto.getAttributesId());

		ProductVersion productVersion = new ProductVersion();
		productVersion.setProductVersionId(versionAttributesDto.getVersionId());

		versionAttributes.setAttributes(attributes);
		versionAttributes.setProductVersion(productVersion);

		BeanUtils.copyProperties(versionAttributesService.save(versionAttributes), versionAttributesDto);

		return ResponseEntity.ok(versionAttributesDto);
	}

	@GetMapping("edit/{id}")
	public ResponseEntity<?> edit(@PathVariable("id") Long id) {
		VersionAttributes versionAttributes = versionAttributesService.findById(id).orElse(null);
		VersionAttributesDto versionAttributesDto = new VersionAttributesDto();
		
		if(versionAttributes!=null) {
			BeanUtils.copyProperties(versionAttributes, versionAttributesDto);
			versionAttributesDto.setAttributesId(versionAttributes.getAttributes().getId());
		}

		return ResponseEntity.ok(versionAttributesDto);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		versionAttributesService.deleteById(id);

		return ResponseEntity.ok("success");
	}
	
	@GetMapping("getAll/{versionId}")
	public ResponseEntity<Page<VersionAttributes>> getAll(@PathVariable("versionId") Optional<Long> versionId,@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		Long currentVersionId = versionId.orElse(null);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<VersionAttributes> resultPage = versionAttributesService.getAllByVersion(currentVersionId,pageable);
		
		

		return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}

}
