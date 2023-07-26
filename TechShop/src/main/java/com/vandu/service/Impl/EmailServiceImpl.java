package com.vandu.service.Impl;

import java.io.File;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vandu.dto.EmailDto;
import com.vandu.model.Order;
import com.vandu.model.OrderItem;
import com.vandu.model.Payments;
import com.vandu.model.Token;
import com.vandu.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private HttpServletRequest httpRequest;

	@Value("${spring.mail.username}")
	private String sender;
	
	 @Value("${spring.mail.port}")
	    private int port;

	    @Value("${spring.mail.username}")
	    private String username;

	    @Value("${spring.mail.password}")
	    private String password;

	@Override
	@Scheduled(fixedDelay = 5000) // chờ 5 giây
	public String sendSimpleMail(EmailDto email) {
		MimeMessage message = javaMailSender.createMimeMessage();
		// Try block to check for exceptions
		try {

			 MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			    // Set the necessary details
			    helper.setFrom(sender);
			    helper.setTo(email.getRecipient());
			    helper.setSubject(email.getSubject());
			    helper.setText(email.getMsgBody(), true); // Set the HTML content

			System.err.println("ddddmmmmm"+email.getRecipient());
			// Sending the mail
			javaMailSender.send(message);

			System.err.println("ddddmmmmm"+email.getRecipient());
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

	// Method 2
	// gừi email có file
	@Override
	public String sendMailWithAttachment(EmailDto email) {
		// Creating a mime message
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(email.getRecipient());
			mimeMessageHelper.setText(email.getMsgBody());
			mimeMessageHelper.setSubject(email.getSubject());

			// Adding the attachment
			FileSystemResource file = new FileSystemResource(new File(email.getAttachment()));

			mimeMessageHelper.addAttachment(file.getFilename(), file);

			// Sending the mail
			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		}

		// Catch block to handle MessagingException
		catch (MessagingException e) {

			// Display message when exception occurred
			return "Error while sending mail!!!";
		}
	}
	
		
		@Override
		public void sendMailRegister(EmailDto emailDto,Token token) {
			String htmlContent = "<html><body>" + "<h3>Please click on the button below to account verification:</h3>"
					+ "<a href=\"http://localhost:8080" + httpRequest.getContextPath()
					+ "/confirm/" + token.getToken() + "\">"
					+ "<button style=\"background-color: #4CAF50; color: white; border: none; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer;\">Confirm Account</button>"
					+ "</a><br><p><b>Thank you!</b></p></body></html>";
			emailDto.setMsgBody(htmlContent);
			
			sendSimpleMail(emailDto);
		}

		@Override
		public void sendMailForgetPassword(EmailDto emailDto,Token token) {
			String htmlContent = "<html><body>" + "<h3>Please click on the button below to  change password:</h3>"
					+ "<a href=\"http://localhost:8080" + httpRequest.getContextPath()
					+ "/change-password/" + token.getToken() + "\">"
					+ "<button style=\"background-color: #4CAF50; color: white; border: none; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer;\">Confirm Account</button>"
					+ "</a><br><p><b>Thank you!</b></p></body></html>";
			emailDto.setMsgBody(htmlContent);
			
			sendSimpleMail(emailDto);
		}
		
//	public String sendMailRegister(EmailDto,String token) {
//		
//	}

}
