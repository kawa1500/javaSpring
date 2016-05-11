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
		String msg="Stworzono uzytkownika w aplikacji do zarz�dzania mieszkaniem\n\n"+
					"Twoje dane:\n"+
					"Imi�:"+user.getFirstName()+"\n"+
					"Nazwisko:"+user.getLastName()+"\n"+
					"Numer telefonu:"+user.getPhone()+"\n"+
					"\nDane logowania do portalu:\n"+
					"Login:"+user.getLogin()+"\n"+
					"Imi�:"+user.getPassword()+"\n\n"+
					"Je�li wyst�puj� tu jakie� b��dy odpisz";
		myMail.sendMail(user.getEmail(), "Rejestracja u�ytkownika", msg);
	}
}
