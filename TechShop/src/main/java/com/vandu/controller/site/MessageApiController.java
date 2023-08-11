package com.vandu.controller.site;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.vandu.dto.response.MessageResponseDto;
import com.vandu.enums.Role;
import com.vandu.helper.UserHelper;
import com.vandu.model.Conversations;
import com.vandu.model.Message;
import com.vandu.model.User;
import com.vandu.service.ConversationService;
import com.vandu.service.MessageService;
import com.vandu.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MessageApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired 
	private MessageService messageService;
	
	@Autowired
	private ConversationService conversationService;
			
	
	@GetMapping("getAllMessages")
	public ResponseEntity<?> getAllMessage(){
		User user = UserHelper.getCurrentUser(request, userService);
		
		if(user==null) {
			return ResponseEntity.ok(null);
		}
		
		if(user.getSentMessages()== null || user.getSentMessages().size()==0 || !user.getRole().equals(Role.ROLE_USER)) {
			return ResponseEntity.ok(null);
		}
		List<MessageResponseDto> lisMessageResponseDtos = new ArrayList<>();
		
		Conversations conversations = messageService.findConversationsByUser(user.getUserId()).orElse(null);
		
		if(conversations!=null) {
			List<Message> list = conversations.getMessages();
			lisMessageResponseDtos = list.stream().map(item->{
				MessageResponseDto messageResponseDto = new MessageResponseDto();
				BeanUtils.copyProperties(item, messageResponseDto);
				messageResponseDto.setAdmin(item.getUser().getUserId() != user.getUserId());
				return messageResponseDto;
			}).toList();
		}
		
		return ResponseEntity.ok(lisMessageResponseDtos);
	}
}
