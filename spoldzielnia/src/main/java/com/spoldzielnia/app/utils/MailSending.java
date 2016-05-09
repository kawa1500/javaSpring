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

	private static final String HOST = "smtp.gmail.com";
	 private static final int PORT = 465;
	 // Adres email osby która wysy³a maila
	 private static final String FROM = "spoldzielniakb@gmail.com";
	 // Has³o do konta osoby która wysy³a maila
	 private static final String PASSWORD = "spoldzielnia";
	 // Adres email osoby do której wysy³any jest mail
	 private static final String TO = "spoldzielniakb@gmail.com";
	 // Temat wiadomoœci
	 private static final String SUBJECT = "Hello World";
	 // Treœæ wiadomoœci
	 private static final String CONTENT = "To mój pierwszy mail wys³any za pomoc¹ JavaMailAPI.";

	 public void send() throws MessagingException {

	  Properties props = new Properties();
	  props.put("mail.transport.protocol", "smtps");
	  props.put("mail.smtps.auth", "true");

	  // Inicjalizacja sesji
	  Session mailSession = Session.getDefaultInstance(props);

	  // ustawienie debagowania, jeœli nie chcesz ogl¹daæ logów to usuñ
	  // linijkê poni¿ej lub zmieñ wartoœæ na false
	  mailSession.setDebug(true);

	  // Tworzenie wiadomoœci email
	  MimeMessage message = new MimeMessage(mailSession);
	  message.setSubject(SUBJECT);
	  message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
	  message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

	  Transport transport = mailSession.getTransport();
	  transport.connect(HOST, PORT, FROM, PASSWORD);

	  // wys³anie wiadomoœci
	  transport.sendMessage(message, message
	    .getRecipients(Message.RecipientType.TO));
	  transport.close();
	 }
   
}
