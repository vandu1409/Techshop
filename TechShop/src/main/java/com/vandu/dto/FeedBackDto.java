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
public class FeedBackDto {
	private int rating;

	private String content;
	
	@JsonIgnore
	private List<MultipartFile> images;
	
	private Long orderItemId;
	
	
}
