package com.spoldzielnia.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spoldzielnia.app.model.PasswordUser;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.utils.mail.SendingMail;
import com.spoldzielnia.app.validators.PasswordUserValidator;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	PasswordUserValidator validator = new PasswordUserValidator();

	
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
		validator.validate(user, result);

		User myUser = userService.getUser(user.getLogin());
		if(myUser!=null)
		{
			
			user.setOldPasswordHash(myUser.getPassword());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(user.getOldPassword(), user.getOldPasswordHash()))
			{	
				System.out.println("Nowe has³o:"+user.getNewPassword());
				myUser.setPassword(user.getNewPassword());
				myUser.setPassword(userService.hashPassword(myUser.getPassword()));
				userService.editUser(myUser);
				SendingMail mailSend = new SendingMail("en");
				mailSend.changePassword(user, myUser.getEmail());
				return "user";
			}
			else
			{
				result.rejectValue("oldPassword", "error.password.incorect");
				System.out.println("B³êdne has³o");
				return "changePassword";
			}
			
		}
		else 
		{
			result.rejectValue("login", "error.login.incorect");
			System.out.println("B³êdny login");
			return "changePassword";
		}
		
	}
}
