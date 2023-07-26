package com.vandu.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	private Long id;

	private String categoryCode;

	private String name;

	@JsonIgnore
	private MultipartFile image;
	
	private List<BrandDto> brands;
}
