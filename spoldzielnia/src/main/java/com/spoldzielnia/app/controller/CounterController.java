package com.spoldzielnia.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.CipherSpi;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.Prices;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.model.UserRole;
import com.spoldzielnia.app.service.CounterService;
import com.spoldzielnia.app.service.PriceService;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.utils.mail.MailMail;
import com.spoldzielnia.app.utils.mail.SendingMail;
import com.spoldzielnia.app.validators.UserValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CounterController {
	
	@Autowired
	CounterService counterService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PriceService priceService;
	
	@RequestMapping(value = "/user/counters", method = RequestMethod.GET)
	public String viewCounters(Map<String,Object> map,HttpServletRequest request ) {
		
		map.put("counterList",counterService.listMyCounter(getIdUser()));
		map.put("counter", counterService.getActiveCounter(getIdUser()));
		boolean visibleButton = true;
		if(new Date().getDate()>10)visibleButton=false;
		for(Counters counter:counterService.listMyCounter(getIdUser()))
		{
			if(counter.getModDate().getMonth()==new Date().getMonth()
				&& counter.getModDate().getYear()==new Date().getYear()) visibleButton=false;
		}
		
		map.put("update",visibleButton);
		
		return "counters";
	}

	private User getIdUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      return userService.getUser(name);
	}
	
}
