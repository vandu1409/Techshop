package com.vandu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributesDto {
	private Long id;

	private Long productId;

	private Long attributesId;

	private String value;
}
