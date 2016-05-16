package com.spoldzielnia.app.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.CipherSpi;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.Prices;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.model.UserRole;
import com.spoldzielnia.app.service.BillsService;
import com.spoldzielnia.app.service.CounterService;
import com.spoldzielnia.app.service.PriceService;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.utils.document.PdfCreator;
import com.spoldzielnia.app.utils.mail.MailMail;
import com.spoldzielnia.app.utils.mail.SendingMail;
import com.spoldzielnia.app.validators.UserValidator;

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
	
	
	@RequestMapping(value = "/user/bills", method = RequestMethod.GET)
	public String viewCounters(Map<String,Object> map,HttpServletRequest request ) {
		
		map.put("billsList",billsService.listForUser(getIdUser()));
		
		return "bills";
	}
	
	@RequestMapping(value = "/admin/bills", method = RequestMethod.GET)
	public String viewBills(Map<String,Object> map,HttpServletRequest request ) {
		
		map.put("billsList",billsService.listBills(1));
		
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
	
//	@RequestMapping (value = "/user/pay", method = RequestMethod.GET)
//	public ResponseEntity<Object> redirectToExternalUrl(Map<String,Object> map,HttpServletRequest request) throws URISyntaxException{
//		int idBills = ServletRequestUtils.getIntParameter(request, "idBill", -1);
//		Bills bb = billsService.get(idBills);
//		String path = PdfCreator.Generate(bb);
//		path=path.replaceAll(":\\", ":/");
//		path=path.replaceAll("\\", "/");
//		String redirectUrl = "file:///"+path;
//		URI uri = new URI(redirectUrl);
//	    HttpHeaders httpHeaders = new HttpHeaders();
//	    httpHeaders.setLocation(uri);
//	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//	}
	
	private int getIdUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      System.out.println("ZALOGOWANYYYYYYYYYYYYYYYYYYYYYYYYYY: "+name);
	      int id = userService.getUser(name).getIdUser();
	      System.out.println("ID usera: "+id);
	      return id;
	}
}
