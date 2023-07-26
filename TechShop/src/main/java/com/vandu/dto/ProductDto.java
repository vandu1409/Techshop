package com.vandu.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
	private Long productId;

	private String name;

	private Double price;

	private Date createDate;

	private Date updateDate;

	@JsonIgnore
	private MultipartFile fileImage;

	private String image;

	private boolean isAvailable;

	private String productCode;

	private String description;

	private boolean edit;

	private Long brandId;

	private Long productSeriesId;
	
	private Long featureId;

//	private Long categoryId;

	@JsonIgnore
	private List<MultipartFile> listImages;

	private List<AttributesDto> listAttributes;

	private List<ProductVersionDto> versions;

	private List<DescriptionDto> descriptions;
}
