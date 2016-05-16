package com.spoldzielnia.app.utils.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.spoldzielnia.app.model.Bills;


public class MailMail
{
	private static String fromMail = "spoldzielniaKB@gmail.com";
	
	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(fromMail);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
	
	public void sendMailWithPdf(String pathFile, String to, String subject, String msg)
	{
			   MimeMessage message = mailSender.createMimeMessage();
				
			   try{
				   MimeMessageHelper helper = new MimeMessageHelper(message, true);
					
				   helper.setFrom(fromMail);
				   helper.setTo(to);
				   helper.setSubject(subject);
				   helper.setText(msg);
					
				
				   FileSystemResource file = new FileSystemResource(pathFile);
				   helper.addAttachment(file.getFilename(), file);

			     }catch (MessagingException e) {
			    	 throw new MailParseException(e);
			     }
			     mailSender.send(message);
		         
	}

}