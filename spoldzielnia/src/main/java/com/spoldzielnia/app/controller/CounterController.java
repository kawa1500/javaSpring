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
	
	@RequestMapping(value = "/user/counters", method = RequestMethod.POST)
	public String addCounter(@ModelAttribute("counter") Counters counter) {
			counter.setIdFlat(getIdUser());
			counterService.addCounter(counter);
			return "redirect:counters";
	}
	
	@RequestMapping(value = "/admin/counters", method = RequestMethod.GET)
	public String viewAdminCounters(Map<String,Object> map,HttpServletRequest request ) {
		
		List<Counters> confirmList = new ArrayList<Counters>();
		List<Counters> ryczaltList = new ArrayList<Counters>();
		
		for(User user: userService.listUser())
		{
			System.out.println("wyszukiwanie licznika dla usera: "+user.getIdUser());
			Counters count = counterService.getActiveCounter(user.getIdUser());
			if(count.getIdCounter()>0)
			{
				if(count.getModDate().getMonth()==new Date().getMonth())
				{
					if(count.getStatus()==1)confirmList.add(count);
				}
				else
				{
					ryczaltList.add(count);
				}
			}
			else
			{
				ryczaltList.add(count);
			}
			System.out.println("Wyszukiwanie dla usera "+user.getIdUser()+" znaleziono licznik z idFlat "+count.getIdFlat());
		}
		
		map.put("confirmList",confirmList);
		map.put("ryczaltList",ryczaltList);
		
		boolean visibleButton = true;
		if(new Date().getDate()<=10)visibleButton=false;
		
		map.put("update",visibleButton);
		
		return "adminCounters";
	}
	
	@RequestMapping(value = "/admin/ryczalt", method = RequestMethod.GET)
	public String ryczalt(Map<String,Object> map,HttpServletRequest request ) {
		int userID = ServletRequestUtils.getIntParameter(request, "idUser", -1);
		int idCounter = ServletRequestUtils.getIntParameter(request, "idCounter", -1);
		ryczaltuj(userID, idCounter);
		return "redirect:counters";
	}
	
	@RequestMapping(value = "/admin/confirm", method = RequestMethod.GET)
	public String confirm(Map<String,Object> map,HttpServletRequest request ) {
		int idCounter = ServletRequestUtils.getIntParameter(request, "idCounter", -1);
		Counters myCount = counterService.get(idCounter);
		myCount.setStatus(2);
		counterService.editCounter(myCount);
		return "redirect:counters";
	}
	
	@RequestMapping(value = "/admin/ryczaltAll", method = RequestMethod.GET)
	public String ryczaltAll(Map<String,Object> map,HttpServletRequest request ) {
		for(User user: userService.listUser())
		{
			System.out.println("wyszukiwanie licznika dla usera: "+user.getIdUser());
			Counters count = counterService.getActiveCounter(user.getIdUser());
			if(count.getIdCounter()>0)
			{
				if(count.getModDate().getMonth()!=new Date().getMonth())
				{
					ryczaltuj(count.getIdFlat(), count.getIdCounter());
				}
			}
			else
			{
				ryczaltuj(count.getIdFlat(), count.getIdCounter());
			}
			
			System.out.println("Wyszukiwanie dla usera "+user.getIdUser()+" znaleziono licznik z idFlat "+count.getIdFlat());
		}

		return "redirect:counters";
	}
	
	@RequestMapping(value = "/admin/confirmAll", method = RequestMethod.GET)
	public String confirmAll(Map<String,Object> map,HttpServletRequest request ) {
		for(User user: userService.listUser())
		{
			System.out.println("wyszukiwanie licznika dla usera: "+user.getIdUser());
			Counters count = counterService.getActiveCounter(user.getIdUser());
			if(count.getIdCounter()>0)
			{
				if(count.getModDate().getMonth()==new Date().getMonth() && count.getStatus()==1)
				{
					count.setStatus(2);
					counterService.editCounter(count);
				}
			}
			//System.out.println("Wyszukiwanie dla usera "+user.getIdUser()+" znaleziono licznik z idFlat "+count.getIdFlat());
		}

		return "redirect:counters";
	}
	
	private int getIdUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      System.out.println("ZALOGOWANYYYYYYYYYYYYYYYYYYYYYYYYYY: "+name);
	      int id = userService.getUser(name).getIdUser();
	      System.out.println("ID usera: "+id);
	      return id;
	}
	
	private void ryczaltuj(int idUser, int idCounter)
	{
		Prices actualPrices= priceService.getActivePrice();
		System.out.println("Dane wej�ciowe: "+idUser+"|"+idCounter);
		if(idCounter<1){
			Counters lastCounter = new Counters();
			lastCounter.setCurrent(lastCounter.getCurrent()+actualPrices.getrCurrent());
			lastCounter.setEnergy(lastCounter.getEnergy()+actualPrices.getrEnergy());
			lastCounter.setGas(lastCounter.getGas()+actualPrices.getrGas());
			lastCounter.setWater(lastCounter.getWater()+actualPrices.getrWater());
			lastCounter.setRyczalt(true);
			lastCounter.setIdFlat(idUser);
			counterService.addCounter(lastCounter);
			System.out.println("TWORZENIE LICZNIK�W");
		}
		else
		{
			Counters lastCounter = counterService.getActiveCounter(idUser);
			lastCounter.setCurrent(lastCounter.getCurrent()+actualPrices.getrCurrent());
			lastCounter.setEnergy(lastCounter.getEnergy()+actualPrices.getrEnergy());
			lastCounter.setGas(lastCounter.getGas()+actualPrices.getrGas());
			lastCounter.setWater(lastCounter.getWater()+actualPrices.getrWater());
			lastCounter.setRyczalt(true);
			counterService.addCounter(lastCounter);
			System.out.println("UPDATE LICZNIK�W");
		}
	}
}
