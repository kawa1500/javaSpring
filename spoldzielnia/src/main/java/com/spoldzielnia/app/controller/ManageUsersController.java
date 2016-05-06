package com.spoldzielnia.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.model.UserRole;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.validators.UserValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class ManageUsersController {
	
	@Autowired
	UserService userService;
	
	UserValidator userValidator = new UserValidator();
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String viewCreateUser(Map<String,Object> map,HttpServletRequest request ) {
		int userID = ServletRequestUtils.getIntParameter(request, "idUser", -1);
		User user;
		if(userID>0)
		{
			user=userService.getUser(userID);
			user.setPassword("");
		}
		else
		{
			user=new User();
		}
		
		//map.put("userRoleList",userService.listUserRole());
		map.put("user", user);
		

		return "createUser";
	}
	
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model, BindingResult result) {
		
		userValidator.validate(user, result);

		if(result.getErrorCount()==0)
		{
			if (user.getIdUser()==0)
			{
				userService.addUser(user);
			}
			else
			{
				User userEdit = userService.getUser(user.getIdUser());
				System.out.println("ILOSC Rólaaaaaaaaaaaa: "+userEdit.getUserRole().size());
				user.setUserRole(userEdit.getUserRole());
				if(user.getPassword().isEmpty())
				{
					user.setPassword(userEdit.getPassword());
				}
				else
				{
					user.setPassword(userService.hashPassword(user.getPassword()));
				}
				userService.editUser(user);
			}
			return "redirect:manageUsers";
		}
		else
		{
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
}
