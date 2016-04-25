package com.spoldzielnia.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spoldzielnia.app.model.PasswordUser;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.validators.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	UserValidator userValidator = new UserValidator();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewUser() {
		return "user";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(Map<String,Object> map)
	{
		map.put("user", new PasswordUser());
		return "changePassword";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String updatePassword(@ModelAttribute("user") PasswordUser user, BindingResult result)
	{
		User myUser = userService.getUser(user.getLogin());
		if(myUser!=null)
		{
			
			user.setOldPasswordHash(myUser.getPassword());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(user.getOldPassword(), user.getOldPasswordHash()))
			{
				myUser.setPassword(user.getNewPassword());
				userService.editUser(myUser);
				return "user";
			}
			else
			{
				System.out.println("B³êdne has³o");
				return "changePassword";
			}
			
		}
		else 
		{
			System.out.println("B³êdny login");
			return "changePassword";
		}
		
	}
}
