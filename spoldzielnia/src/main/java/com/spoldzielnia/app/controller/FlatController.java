package com.spoldzielnia.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.Flat;

@Controller
@RequestMapping("/admin")
public class FlatController 
{
	   @RequestMapping("/createFlat")
	   public ModelAndView createFlat() {
	        
	       return new ModelAndView("createFlat", "flat", new Flat());
	   }
}



