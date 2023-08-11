package com.vandu.controller.site;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.vandu.dto.MessageDTO;
import com.vandu.exception.ErrorDetails;
import com.vandu.exception.NotLoggedInException;
import com.vandu.helper.UserHelper;
import com.vandu.model.Conversations;
import com.vandu.model.Message;
import com.vandu.model.User;
import com.vandu.service.ConversationService;
import com.vandu.service.MessageService;
import com.vandu.service.UserService;
import com.vandu.service.Impl.ConversationsServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import net.minidev.json.writer.BeansMapper.Bean;

@Controller
public class MessageSiteController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Autowired
	private ConversationService conversationService;

	@MessageMapping("/send")
	@SendTo("/topic/messages")
	public MessageDTO sendMessage(MessageDTO messageDto) throws NotLoggedInException {
		System.out.println("Đã vào web socket");
		User user = userService
				.findByUsernameAndAuthenticationType(messageDto.getUsername(), messageDto.getAuthenticationType())
				.orElse(null);

//		User user = UserHelper.getCurrentUser(request, userService);
		if (user == null) {
			throw new NotLoggedInException();

		}

		Conversations conversations = messageService.findConversationsByUser(user.getUserId()).orElse(null);

		if (conversations == null) {
			conversations = new Conversations();
			conversations.setStartDate(new Date());

			conversations = conversationService.save(conversations);
		}

		Message message = new Message();

		BeanUtils.copyProperties(messageDto, message);
		message.setUser(user);
		message.setConversations(conversations);
		message.setCreateAt(new Date());

		messageService.save(message);

		return messageDto;
	}
	
	@MessageMapping("/admin/sendMessage")
	@SendTo("/admin/message")
	public ResponseEntity<?> sendAdminMessage(MessageDTO messageDto) {
		User user = userService
				.findByUsernameAndAuthenticationType(messageDto.getUsername(), messageDto.getAuthenticationType())
				.orElse(null);


		if (user == null) {
			return ResponseEntity.ok(new ErrorDetails("Vui lòng đăng nhập để thực hiện chức năng!",400));

		}

		Conversations conversations = conversationService.findById(messageDto.getConversationId()).orElse(null);
		
		if(conversations==null) {
			return ResponseEntity.ok(new ErrorDetails("Không tìm thấy cuộc trò chuyện! Vui lòng kiểm tra lại!",400));

		}
		
		Message message = new Message();

		BeanUtils.copyProperties(messageDto, message);
		message.setUser(user);
		message.setConversations(conversations);
		message.setCreateAt(new Date());

		messageService.save(message);
		
		return ResponseEntity.ok(messageDto);
	}
	
	
}
