package com.vandu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionAttributesDto {
	private Long id;
	
	private String value;
	
	private Long attributesId;
	
	private Long versionId;
}
