package com.attendance.service.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService{
	@Autowired
    private JavaMailSender mailSender;
	
	
	public void sendMail(String receiver, String subject, String body) throws MailException, MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		
		helper.setText(body, true); // Use this or above line.
		helper.setTo(receiver);
		helper.setSubject(subject);
		helper.setFrom("tomadsin123@gmail.com");
		mailSender.send(mimeMessage);
		
//		message.setFrom("tomadsin123@gmail.com");
//		message.setTo(receiver);
//		message.setText(body);
//		message.setSubject(subject);
//		mailSender.send(message);
		
		
		System.out.println("Mail Send...");
	}

	
}

