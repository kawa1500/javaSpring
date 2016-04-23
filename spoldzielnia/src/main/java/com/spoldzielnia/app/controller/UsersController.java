package com.spoldzielnia.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class UsersController {
	
//	@Autowired
//	UserService userService;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public ModelAndView viewCreateUser() {
		return new ModelAndView("createUser", "command", new User());
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model) {
//		if (user.getIdUser()==0)
//			   userService.addUser(user);
//	       else
//			   userService.editUser(user);
		System.out.println("Tworzono uzytkownika : "+user.getFirstName());
		return "manageUsers";
	}
	
	@RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
	public String manageUsers(Model model) {
		return "manageUsers";
	}
}
