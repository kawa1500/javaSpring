package com.spoldzielnia.app.controller;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.Building;
import com.spoldzielnia.app.model.Flat;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.model.UserRole;
import com.spoldzielnia.app.service.BuildingService;
import com.spoldzielnia.app.service.FlatService;
import com.spoldzielnia.app.service.UserService;
import com.spoldzielnia.app.utils.mail.MailMail;
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
		
		//je¿eli jest ID to znaczy ¿e bêdzie edycja 
		if(userID>0)
		{
			user=userService.getUser(userID);
			user.setPassword("");
			
			System.out.println("ILOSC Ról: "+user.getUserRole().size());	}
		else
		{
			user=new User();
		}
//		for(Flat f:flatService.listFlat())
//		{
//			
//			 for (Iterator<Building> it = f.getBuilding().iterator(); it.hasNext(); ) {
//			        Building b = it.next();
//			        System.out.println("found "+b.getBuildingCity());
//			    }
//			System.out.println("MIeszkanie + "+f.getBuilding().getClass());
//		}
			
			
		map.put("userRoleList",userService.listUserRole());
//		map.put("flatList", flatService.listFlat());
		map.put("user", user);
		

		return "createUser";
	}
	
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model,Map<String,Object> map, BindingResult result, HttpServletRequest request) {
		
		userValidator.validate(user, result);
		String language = request.getLocale().toString();
		if(result.getErrorCount()==0)
		{
			if (user.getIdUser()==0)
			{
				User memory = userService.getUser(user.getLogin());
				if(memory!=null)
				{
					result.rejectValue("login", "error.login.exist");
					map.put("userRoleList",userService.listUserRole());
					return "createUser";
				}
				else
				{
					SendingMail mailSend = new SendingMail(language);
					mailSend.createUser(user);
					System.out.println("JEEEEEEEZYK: "+language);
					userService.addUser(user);
					return "redirect:manageUsers";
				}	
			}
			else
			{
				User userEdit = userService.getUser(user.getIdUser());
				System.out.println("ILOSC Rólaaaaaaaaaaaa: "+userEdit.getUserRole().size());
				if(user.getUserRole()==null)user.setUserRole(userEdit.getUserRole());
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
