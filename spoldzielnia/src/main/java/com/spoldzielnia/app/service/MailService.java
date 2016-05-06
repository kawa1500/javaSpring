package com.spoldzielnia.app.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailService {
    
	private Properties 			props;
	private final String		EMAIL="as"; 
	final String username = EMAIL;
	final String password = "ass";
	
	
	public MailService() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}
	
	public void sendMail(String subject, String textMail) {
	   
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
	    try {
	    		Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress(EMAIL));
		        message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse(EMAIL));
		        message.setSubject(subject);
		        message.setText(textMail);
		        
		        Transport.send(message);
		        	
	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
	
}