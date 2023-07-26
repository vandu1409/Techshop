package com.vandu.dto.response;

import java.util.Date;

import com.vandu.enums.AuthenticationType;
import com.vandu.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	private Long userId;

	private String username;

	private String email;

	private String fullname;

	private String phone;

	private Role role;
	
	private AuthenticationType authenticationType;

	private String avatar;

	private Date createDate;

	private Date updateDate;

	private boolean isActive;

	private boolean isDeleted;
}
