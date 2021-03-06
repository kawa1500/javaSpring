package com.spoldzielnia.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spoldzielnia.app.model.Prices;
import com.spoldzielnia.app.service.PriceService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class PricesController {
	
	@Autowired
	PriceService priceService;
	
	@RequestMapping(value = "/prices", method = RequestMethod.GET)
	public String viewCreateUser(Map<String,Object> map,HttpServletRequest request ) {
		map.put("priceList",priceService.getAll());
		map.put("price", priceService.getActivePrice());
		
		return "prices";
	}
	
	
	@RequestMapping(value = "/prices", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("price") Prices price) {
			priceService.addPrice(price);
			return "redirect:prices";
	}
}
