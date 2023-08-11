package com.vandu.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.response.ConversationResponseDto;
import com.vandu.model.Conversations;
import com.vandu.service.ConversationService;


@RestController
@RequestMapping("admin/message")
public class MessageAdminController {
	
	@Autowired
	private ConversationService conversationService;
	
	@GetMapping("getAllConversation")
	public ResponseEntity<?> getAllConversation(){
		
//		Sort sort = Sort.by("createAt").descending();
		
		List<Conversations> list = conversationService.findAll();
		
		List<ConversationResponseDto> listDto = list.stream().map(item->{
			return conversationService.converConversationResponseDto(item);
		}).toList();
		
		return ResponseEntity.ok(listDto);
	}
	
	@GetMapping("findConversation/{id}")
	public ResponseEntity<?> findConversation(@PathVariable("id") Long id){
		System.out.println("Đã dô");
		Conversations conversations = conversationService.findById(id).orElse(null);
		ConversationResponseDto conversationResponseDto = new ConversationResponseDto();
		
		if(conversations!=null) {
			conversationResponseDto = conversationService.converConversationResponseDto(conversations);
		}
		
		return ResponseEntity.ok(conversationResponseDto);
	}

}
