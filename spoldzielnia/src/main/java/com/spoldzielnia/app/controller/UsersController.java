package com.spoldzielnia.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class UsersController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String viewCreateUser(Map<String,Object> map,HttpServletRequest request ) {
		int userID = ServletRequestUtils.getIntParameter(request, "idUser", -1);
		User user;
		if(userID>0)
		{
			user=userService.getUser(userID);
		}
		else
		{
			user=new User();
		}
		System.out.println("wczytywanie usera: "+user.getIdUser());
		map.put("user", user);

		return "createUser";
	}
	
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model) {
		System.out.println(user.toString());
		if (user.getIdUser()==0)
		{
			userService.addUser(user);
		}
		else
		{
			userService.editUser(user);
		}
	   
		return "redirect:manageUsers";
	}
	
	@RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
	public ModelAndView manageUsers() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("users", userService.listUser());
		modelAndView.setViewName("manageUsers");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String DeleteUser(HttpServletRequest request) {
		int userID = ServletRequestUtils.getIntParameter(request, "idUser", -1);
		userService.removeUser(userID);
		return "redirect:manageUsers";
	}
}
