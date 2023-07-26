package com.vandu.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

	private Long id;

	private String categoryCode;

	private String name;

	private String image;
	
	private List<BrandResponseDto> brands;
}
