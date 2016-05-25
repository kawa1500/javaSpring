package com.spoldzielnia.app.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.CounterService;
import com.spoldzielnia.app.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CounterController {
	
	@Autowired
	CounterService counterService;
	
	@Autowired
	UserService userService;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/user/counters", method = RequestMethod.GET)
	public String viewCounters(Map<String,Object> map,HttpServletRequest request ) {
		
		map.put("counterList",counterService.listMyCounter(getIdUser()));
		map.put("counter", counterService.getActiveCounter(getIdUser()));
		boolean visibleButton = true;
		if((counterService.getActiveCounter(getIdUser()).getStatus()==0 &&
				counterService.getActiveCounter(getIdUser()).getIdCounter()>0)||(new Date().getDate()>10))visibleButton=false;//

		map.put("update",visibleButton);
		
		return "counters";
	}
	
	@RequestMapping(value = "/user/counters", method = RequestMethod.POST)
	public String addCounters(Map<String,Object> map, @ModelAttribute("counter") Counters counter, BindingResult result ) {
		counter.setUser(getIdUser());
		boolean isOK=true;
		if(counter.getCurrent()<counterService.getActiveCounter(getIdUser()).getCurrent())
		{
			result.rejectValue("current", "error.counters.small");
			isOK=false;
		}
		if(counter.getGas()<counterService.getActiveCounter(getIdUser()).getGas())
		{
			result.rejectValue("gas", "error.counters.small");
			isOK=false;
		}
		if(counter.getWater()<counterService.getActiveCounter(getIdUser()).getWater())
		{
			result.rejectValue("water", "error.counters.small");
			isOK=false;
		}
		
		if(isOK)
		{
			counterService.addCounter(counter);	
			return "redirect:counters";
		}
		else
		{
			map.put("counterList",counterService.listMyCounter(getIdUser()));
			map.put("update",true);
			return	"counters";
		}
		
	}

	private User getIdUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      return userService.getUser(name);
	}
	
}
