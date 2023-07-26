package com.vandu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionResponseDto {
	private Long id;

	private String title;

	private String description;

	private String image;
}
