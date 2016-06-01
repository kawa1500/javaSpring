package com.spoldzielnia.app.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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

import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.Prices;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.BillsService;
import com.spoldzielnia.app.service.CounterService;
import com.spoldzielnia.app.service.PriceService;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.utils.document.PdfCreator;
import com.spoldzielnia.app.utils.mail.SendingMail;

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
	BillsService billService;
	
	@Autowired
	PriceService priceService;
	
	@SuppressWarnings("deprecation")
	private List<User> getUserList()
	{
		List<User> result = new LinkedList<User>();
		for(User u : userService.listUser())
		{
			boolean weCanAdd=true;
			for(Counters c : counterService.listMyCounter(u))
			{
				if((c.getModDate().getYear()==new Date().getYear())&&(c.getStatus()==2)){
					weCanAdd=false;
					break;
				}
			}
			if(weCanAdd)result.add(u);
		}
		return result;
	}
	
	@RequestMapping(value = "/admin/countersVerify", method = RequestMethod.GET)
	public String viewCountersAdmin(Map<String,Object> map,HttpServletRequest request ) {
		map.put("userList", getUserList());
		map.put("counter", new Counters());
		if(getUserList().size()>0)map.put("update",true);
		else map.put("update",false);
		return "countersVerify";
	}
	@RequestMapping(value = "/admin/countersVerify", method = RequestMethod.POST)
	public String addCountersAdmin(@ModelAttribute("counter") Counters counter, Map<String,Object> map,HttpServletRequest request ) {
		counter.setStatus(2);
		counterService.addCounter(counter);
		generateBill(counter.getUser());
		return "redirect:countersVerify";
	}
	
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
		if(counter.getHotWater()<counterService.getActiveCounter(getIdUser()).getHotWater())
		{
			result.rejectValue("hotWater", "error.counters.small");
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

	private void generateBill(User user)
	{
		Bills myBill = new Bills();
		Counters counter = counterService.getActiveCounter(user);
		Counters lastCounterValue = counterService.getLastCounters(counter);
		Prices actualPrice = priceService.getActivePrice();
		myBill.setOsoby(Integer.parseInt(counter.getUser().getFlat().getTenantNumber()));
		myBill.setStatus(1);
		myBill.setCurrentValue(counter.getCurrent()-lastCounterValue.getCurrent());
		myBill.setWaterValue(counter.getWater()-lastCounterValue.getWater());
		myBill.setHotWaterValue(counter.getHotWater()-lastCounterValue.getHotWater());
		myBill.setGasValue(counter.getGas()-lastCounterValue.getGas());
		myBill.setEnergyValue(counter.getEnergy()-lastCounterValue.getEnergy());
		myBill.setWater(zaokraglij(actualPrice.getWater()*myBill.getWaterValue()));
		myBill.setHotWater(zaokraglij(actualPrice.getHotWater()*myBill.getHotWaterValue()));
		myBill.setCurrent(zaokraglij(actualPrice.getCurrent()*myBill.getCurrentValue()));
		myBill.setGas(zaokraglij(actualPrice.getGas()*myBill.getGasValue()));
		myBill.setEnergy(zaokraglij(actualPrice.getEnergy()*myBill.getEnergyValue()));
		myBill.setIntercom(zaokraglij(myBill.getOsoby()*actualPrice.getIntercom()));
		myBill.setTrash(zaokraglij(actualPrice.getTrash()*myBill.getOsoby()));
		myBill.setSewage(zaokraglij(actualPrice.getSewage()*myBill.getWaterValue()));
		myBill.setOther(zaokraglij(actualPrice.getOther()*myBill.getOsoby()));
		myBill.setCost(zaokraglij(myBill.getCurrent()+myBill.getEnergy()+myBill.getGas()+myBill.getIntercom()+myBill.getOther()+myBill.getSewage()+myBill.getTrash()+myBill.getWater()));
		myBill.setCounters(counter);
		myBill.setModDate(new Date());
		billService.add(myBill);
		SendingMail mailSend = new SendingMail();
		mailSend.createBill(myBill, PdfCreator.Generate(myBill,actualPrice), counter.getUser().getEmail());
	}
	
	private User getIdUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      return userService.getUser(name);
	}
	
	private Double zaokraglij(Double value)
	{
		Double wynik = value;
		value*=100;
		value=(double) Math.round(value);
		value/=100;
		return wynik;
	}
}
