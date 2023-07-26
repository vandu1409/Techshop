package com.vandu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSeriesResponseDto {
	private Long productSeriesId;

	private String name;

	private String seriesCode;

	private String image;
}
