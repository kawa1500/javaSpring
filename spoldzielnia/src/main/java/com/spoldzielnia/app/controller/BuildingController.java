package com.spoldzielnia.app.controller;

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

import com.spoldzielnia.app.model.Building;

import com.spoldzielnia.app.service.BuildingService;




@Controller
@RequestMapping("/admin")
public class BuildingController {
	
	@Autowired
	BuildingService buildingService;
	
	@RequestMapping(value = "/createBuilding", method = RequestMethod.GET)
	public String viewCreateBuilding(Map<String,Object> map,HttpServletRequest request ) {
		int buildingID = ServletRequestUtils.getIntParameter(request, "idBuilding", -1);
		Building building;
		
		//je¿eli jest ID to znaczy ¿e bêdzie edycja 
		if(buildingID>0)
		{
			building=buildingService.getBuilding(buildingID);
		}
		else
		{
			building=new Building();
		}
		
		map.put("building", building);

		return "createBuilding";
	}
	
	
	@RequestMapping(value = "/createBuilding", method = RequestMethod.POST)
	public String addBuilding(@ModelAttribute("building") Building building, Model model, BindingResult result) {
		
		
		// je¿eli nie ma b³êdów to idzie dalej w ifie, a jak s¹ to zwraca createUser
		if(result.getErrorCount()==0)
		{
			if (building.getIdBuilding()==0)
			{
				buildingService.addBuilding(building);
			}
			else
			{
				buildingService.editBuilding(building);
			}
			return "redirect:manageBuilding";
		}
		else
		{
			return "createBuilding";
		}
		
	}
	
	@RequestMapping(value = "/manageBuilding", method = RequestMethod.GET)
	public ModelAndView manageBuilding() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("building", buildingService.listBuilding());
		modelAndView.setViewName("manageBuilding");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteBuilding", method = RequestMethod.GET)
	public String DeleteBuilding(HttpServletRequest request) {
		int buildingID = ServletRequestUtils.getIntParameter(request, "idBuilding", -1);
		buildingService.removeBuilding(buildingID);
		return "redirect:manageBuilding";
	}
}
