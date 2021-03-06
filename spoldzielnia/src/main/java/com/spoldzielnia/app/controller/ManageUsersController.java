package com.spoldzielnia.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.Flat;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.model.UserRole;
import com.spoldzielnia.app.service.FlatService;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.utils.mail.SendingMail;
import com.spoldzielnia.app.validators.UserValidator;



/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class ManageUsersController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	FlatService flatService;
	
	UserValidator userValidator = new UserValidator();
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String viewCreateUser(Map<String,Object> map,HttpServletRequest request ) {
		int userID = ServletRequestUtils.getIntParameter(request, "idUser", -1);
		User user;
		List<Flat> flatList=getFlatforUSer();
		//je�eli jest ID to znaczy �e b�dzie edycja 
		if(userID>0)
		{
			user=userService.getUser(userID);
			user.setPassword("");
			flatList.add(user.getFlat());
			System.out.println("ILOSC R�l: "+user.getUserRole().size());	}
		else
		{
			user=new User();
		}			
			
		map.put("userRoleList",userService.listUserRole());
		map.put("flatList", flatList);
		map.put("user", user);
		
		return "createUser";
	}
	
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model,Map<String,Object> map, BindingResult result, HttpServletRequest request) {
		
		userValidator.validate(user, result);
		if(result.getErrorCount()==0)
		{
			if (user.getIdUser()==0)
			{
				User memory = userService.getUser(user.getLogin());
				if(memory!=null)
				{
					result.rejectValue("login", "error.login.exist");
					map.put("userRoleList",userService.listUserRole());
					map.put("flatList", getFlatforUSer());
					return "createUser";
				}
				else
				{
					SendingMail mailSend = new SendingMail();
					mailSend.createUser(user);
					userService.addUser(user);
					return "redirect:manageUsers";
				}	
			}
			else
			{
				User userEdit = userService.getUser(user.getIdUser());
				System.out.println("ILOSC R�laaaaaaaaaaaa: "+userEdit.getUserRole().size());
				if(user.getUserRole()==null)user.setUserRole(userEdit.getUserRole());
				if(user.getFlat()==null)user.setFlat(userEdit.getFlat());
				if(user.getPassword().isEmpty())
				{
					user.setPassword(userEdit.getPassword());
				}
				else
				{
					user.setPassword(userService.hashPassword(user.getPassword()));
				}
				userService.editUser(user);
				return "redirect:manageUsers";
			}
			
		}
		else
		{
			map.put("userRoleList",userService.listUserRole());
			map.put("flatList", flatService.listFlat());
			return "createUser";
		}
		
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
	
	@RequestMapping(value = "/createRole", method = RequestMethod.GET)
	public ModelAndView showUserRole(){
		return new ModelAndView("createRole","userRole",new UserRole());
	}
	
	@RequestMapping(value = "/createRole", method = RequestMethod.POST)
	public String addUserRole(@ModelAttribute("userRole") UserRole userRole)
	{
		userService.addUserRole(userRole);
		return "redirect:manageUsers";
	}
	
	private List<Flat> getFlatforUSer()
	{
		List<Flat> myList = new ArrayList<Flat>();
		for(Flat f : flatService.listFlat())
		{
			boolean weCanAdd = true;
			for(User u : userService.listUser())
			{
				if(u.getFlat().getIdFlat()==f.getIdFlat())  weCanAdd=false;
			}
			if(weCanAdd)myList.add(f);
		}
		return myList;
	}
}
