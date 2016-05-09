package com.spoldzielnia.app.utils;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.spoldzielnia.app.model.PasswordUser;
import com.spoldzielnia.app.model.User;

public class MailMail
{
	private MailSender mailSender;
	private static String fromMail = "spoldzielniaKB@gmail.com";
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	private void sendMail(String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(fromMail);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
	
	public void changePassword(User userProfile, PasswordUser user)
	{
		String msg = "Zmieni³eœ has³o na swoim profilu w aplikacji do zarz¹dzania spó³dzielni¹ mieszkaniow¹\n"+
						"Dane twojego profilu:\nLogin:"+
						user.getLogin()+"\nHas³o:"+
						user.getNewPassword();
			sendMail(userProfile.getEmail(), "Zmiana has³a", msg);
	}
}