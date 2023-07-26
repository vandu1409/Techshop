package com.vandu.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
public class ProductSeriesDto {
	private Long productSeriesId;

	private String name;

	private String seriesCode;
	
//	private String image;
	@JsonIgnore
	private MultipartFile image;
	
	private Long brandId;
}
