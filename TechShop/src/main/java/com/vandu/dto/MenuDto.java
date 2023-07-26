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
public class MenuDto {
	private Long id;
	
	private String name;
	
	private String image;
	
	@JsonIgnore
	private MultipartFile urlImage;
	
	private String url;
	
	private List<SubMenuDto> subMenus;
}
