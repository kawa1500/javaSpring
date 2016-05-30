package com.spoldzielnia.app.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.Pay;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.BillsService;
import com.spoldzielnia.app.service.PriceService;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.utils.document.PdfCreator;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;


/**
 * Handles requests for the application home page.
 */
@Controller
public class BillsController {
	
	@Autowired
	BillsService billsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PriceService priceService;
	
	@Autowired
	ReCaptchaImpl reCaptcha;
	
	@RequestMapping(value = "/user/bills", method = RequestMethod.GET)
	public String viewCounters(Map<String,Object> map,HttpServletRequest request ) {
		
		map.put("billsList",billsService.listForUser(getIdUser()));
		
		return "bills";
	}
	
	@RequestMapping(value = "/admin/bills", method = RequestMethod.GET)
	public String viewBills(Map<String,Object> map,HttpServletRequest request ) {
		
		map.put("billsList",billsService.listBills());
		
		return "adminBills";
	}

	@RequestMapping(value = "/user/billsView", method = RequestMethod.GET)
	public void payBills(Map<String,Object> map,HttpServletRequest request, HttpServletResponse response ) throws IOException {
		int idBills = ServletRequestUtils.getIntParameter(request, "idBill", -1);
		Bills bb = billsService.get(idBills);
		
		String path = PdfCreator.Generate(bb,priceService.getActivePrice());
		System.out.println("STWORZY£EM RACHUNEK:"+path);
		
        File file = new File(path);
         
        String mimeType= "application/pdf";

        response.setContentType(mimeType);
         
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
 
        response.setContentLength((int)file.length());
 
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
 
        FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping (value = "/user/pay", method = RequestMethod.GET)
	public String placenie(Map<String,Object> map, HttpServletRequest request){
		return "pay";
	}
	
	@RequestMapping (value = "/user/pay", method = RequestMethod.POST)
	public String placenieRachunku(HttpServletRequest request, HttpServletResponse response){
		String reCaptchaChallenge = request.getParameter("recaptcha_challenge_field");
        String reCaptchaResponse = request.getParameter("recaptcha_response_field");
        String remoteAddress = request.getRemoteAddr();
		ReCaptchaResponse myRe = reCaptcha.checkAnswer(remoteAddress, reCaptchaChallenge, reCaptchaResponse);
 
		if(myRe.isValid()){
			System.out.println("Zweryfikowano prawid³owo!!!!");
			int idBills = ServletRequestUtils.getIntParameter(request, "idBill", -1);
			Bills bb = billsService.get(idBills);
			bb.setStatus(2);
			billsService.edit(bb);
			return "redirect:bills";
		} else {
			System.out.println("B³êdnie zweryfikowano!!!!");	
			return "pay";
		}		
		
		
	}
	private User getIdUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      System.out.println("ZALOGOWANYYYYYYYYYYYYYYYYYYYYYYYYYY: "+name);
	      return userService.getUser(name);
	}
}
