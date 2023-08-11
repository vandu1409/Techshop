package com.vandu.dto;

import java.time.LocalDate;

import com.vandu.enums.AuthenticationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

//	private Long id;

	private String message;

//	private LocalDate createAt;

	private String username;
	
	private AuthenticationType authenticationType;

	private Long conversationId;
}
