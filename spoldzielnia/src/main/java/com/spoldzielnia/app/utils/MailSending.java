package com.spoldzielnia.app.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSending {

	private static final String HOST = "smtp.poczta.onet.pl";
	 private static final int PORT = 465;
	 // Adres email osby kt�ra wysy�a maila
	 private static final String FROM = "spoldzielnia.kb@onet.pl";
	 // Has�o do konta osoby kt�ra wysy�a maila
	 private static final String PASSWORD = "Spoldzielnia1";
	 // Adres email osoby do kt�rej wysy�any jest mail
	 private static final String TO = "spoldzielniakb@gmail.com";
	 // Temat wiadomo�ci
	 private static final String SUBJECT = "Hello World";
	 // Tre�� wiadomo�ci
	 private static final String CONTENT = "To m�j pierwszy mail wys�any za pomoc� JavaMailAPI.";

	 public void send() throws MessagingException {

	  Properties props = new Properties();
	  props.put("mail.transport.protocol", "smtps");
	  props.put("mail.smtps.auth", "true");

	  // Inicjalizacja sesji
	  Session mailSession = Session.getDefaultInstance(props);

	  // ustawienie debagowania, je�li nie chcesz ogl�da� log�w to usu�
	  // linijk� poni�ej lub zmie� warto�� na false
	  mailSession.setDebug(true);

	  // Tworzenie wiadomo�ci email
	  MimeMessage message = new MimeMessage(mailSession);
	  message.setSubject(SUBJECT);
	  message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
	  message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

	  Transport transport = mailSession.getTransport();
	  transport.connect(HOST, PORT, FROM, PASSWORD);

	  // wys�anie wiadomo�ci
	  transport.sendMessage(message, message
	    .getRecipients(Message.RecipientType.TO));
	  transport.close();
	 }
   
}
