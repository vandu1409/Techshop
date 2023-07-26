package com.vandu.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponseDto {
	private Long brandId;

	private String name;

	private String brandCode;

	private String image;
	
	private List<ProductSeriesResponseDto> series;
}
