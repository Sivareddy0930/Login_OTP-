package com.otp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	//from starter-mail dependency.
	JavaMailSender javaMailSender;
	
	public void sendMail(String SendMailto,String subjectOfMail,String bodyOfMail)
	{
		//the createMimeMessage() method is used to create a new MimeMessage instance.
		//JavaMailSender.createMimeMessage() is used to create a new MimeMessage.
		//The MimeMessageHelper class is then used to set various properties of the message, such as the sender, recipient, subject, and text content. 
		//Additionally, you can use MimeMessageHelper for more advanced features like adding attachments or inline images.
		
		MimeMessage createMimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(createMimeMessage,true);
			mimeMessageHelper.setTo(SendMailto);
			mimeMessageHelper.setSubject(subjectOfMail);
			mimeMessageHelper.setText(bodyOfMail);
			
			javaMailSender.send(createMimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
}
