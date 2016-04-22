package com.spoldzielnia.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spoldzielnia.app.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class UsersController {
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String viewCreateUser(Model model) {
		return "createUser";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model) {
		System.out.println("tworzenie usera: "+user.getFirstName());
		return "createUser";
	}
	
	@RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
	public String manageUsers(Model model) {
		return "manageUsers";
	}
}
