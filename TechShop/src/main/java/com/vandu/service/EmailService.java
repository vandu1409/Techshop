package com.vandu.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;

import com.vandu.dto.EmailDto;
import com.vandu.model.Order;
import com.vandu.model.OrderItem;
import com.vandu.model.Payments;
import com.vandu.model.Token;

public interface EmailService {

	String sendMailWithAttachment(EmailDto email);

	String sendSimpleMail(EmailDto email);

//	void sendOrderConfirmationEmail(EmailDto email, Order order, Payments payments, List<OrderItem> lisOrderItems);

	void sendMailRegister(EmailDto emailDto, Token token);

	void sendMailForgetPassword(EmailDto emailDto, Token token);



	

}
