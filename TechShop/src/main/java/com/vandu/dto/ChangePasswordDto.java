package com.vandu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
	private String token;
	
	private String currentPassword;
	
	private String password;
	
	private String repassword;
}
