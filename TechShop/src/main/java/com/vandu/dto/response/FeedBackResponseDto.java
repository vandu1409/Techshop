package com.vandu.dto.response;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackResponseDto {
	
	private Long id;
	
	private int rating;
	
	private String content;
	
	private Date feedBackDate;
	
	private String username;
	
	private String productBought;
	
	private List<String> images;
	

}
