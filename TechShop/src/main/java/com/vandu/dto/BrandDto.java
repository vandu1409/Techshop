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
public class BrandDto {
	private Long brandId;

	private String name;

	private String brandCode;

//	private Long categoryId;

	private String brandName;

	@JsonIgnore
	private MultipartFile image;
	
	private List<ProductSeriesDto> series;

}
