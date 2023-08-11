package com.vandu.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponseDto {
	private Long id;
	
	private String title;
	
	private String username;
	
	private List<MessageResponseDto> listMessages;
}
