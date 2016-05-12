package com.spoldzielnia.app.controller;

import java.util.Date;
import java.util.Map;

import javax.crypto.CipherSpi;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
@RequestMapping("/user")
public class CounterController {
	
	@Autowired
	CounterService counterService;
	
	@RequestMapping(value = "/counters", method = RequestMethod.GET)
	public String viewCreateUser(Map<String,Object> map,HttpServletRequest request ) {
		map.put("counterList",counterService.listMyCounter(1));
		map.put("counter", counterService.getActiveCounter(1));
		boolean visibleButton = true;
		if(new Date().getDate()>10)visibleButton=false;
		for(Counters counter:counterService.listMyCounter(1))
		{
			if(counter.getModDate().getMonth()==new Date().getMonth()
				&& counter.getModDate().getYear()==new Date().getYear()) visibleButton=false;
		}
		
		map.put("update",visibleButton);
		
		return "counters";
	}
	
	
	@RequestMapping(value = "/counters", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("counter") Counters counter) {
			counter.setIdFlat(1);
			counterService.addCounter(counter);
			return "redirect:counters";
	}
}
