package com.spoldzielnia.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class FlatController {
	
	@RequestMapping(value = "/createFlat", method = RequestMethod.GET)
	public String createFlat(Model model) {
		return "createFlat";
	}
	
	@RequestMapping(value = "/createBuilding", method = RequestMethod.GET)
	public String createBuilding(Model model) {
		return "createBuilding";
	}
}
