package com.vandu.dto;

import java.util.Date;
import java.util.Set;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long userId;

	private String username;

	private String password;

	private String email;

	private String fullname;
	
	private String avatar;

	private String phone;

	private Date createDate;

	private Date updateDate;

	private boolean active;

	private boolean delete;
	
	private int role;
}
