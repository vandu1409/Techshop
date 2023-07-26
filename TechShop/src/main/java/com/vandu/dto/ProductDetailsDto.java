package com.vandu.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDto {
	private Long productDetailsId;

	private Long quantity;

	private Double importPrice;

	private Double price;
	
	private String image;
	
	@JsonIgnore
	private MultipartFile fileImgae;

	private Date createDate;

	private Date updateDate;

	private boolean available;

	
	private Long productVersionId;

	private Long colorId;
}
