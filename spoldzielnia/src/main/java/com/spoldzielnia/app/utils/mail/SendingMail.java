package com.spoldzielnia.app.utils.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spoldzielnia.app.model.User;

public class SendingMail {

	private MailMail myMail;
	private String language = "en";
	
	public SendingMail(String language)
	{
		this.language = language;
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
    	myMail = (MailMail) context.getBean("mailMail");
	}
	
	public void createUser(User user)
	{
		myMail.sendMail(user.getEmail(), "Create account", language);
	}
}
