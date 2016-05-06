package com.spoldzielnia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spoldzielnia.app.service.MailService;
import com.spoldzielnia.app.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {


	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {	
		/*
		 Has³o : 12345678
		 $2a$10$vZfPujX4Ohgl6eJqsRgn6ur8wFQNGNDwYNc.7YOyyOoxggTR6Umje
		 */
		MailService m = new MailService();
		m.sendMail("as", "asd");

		return "home";
	}
	
}
