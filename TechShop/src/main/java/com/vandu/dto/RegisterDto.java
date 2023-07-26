package com.vandu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
	
	private String username;
	
	private String email;
	
	private String fullname;

	private String password;
	
	private String repassword;
}
