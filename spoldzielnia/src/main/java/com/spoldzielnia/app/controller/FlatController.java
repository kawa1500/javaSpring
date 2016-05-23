package com.spoldzielnia.app.controller;

import java.util.Map;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.spoldzielnia.app.model.Building;
import com.spoldzielnia.app.model.Flat;
import com.spoldzielnia.app.service.BuildingService;
import com.spoldzielnia.app.service.FlatService;
import com.spoldzielnia.app.validators.FlatValidator;


@Controller
@SessionAttributes
@RequestMapping("/admin")
public class FlatController {
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	FlatService flatService;
	FlatValidator flatValidator = new FlatValidator();
	@RequestMapping(value = "/createFlat", method = RequestMethod.GET)
	public String viewCreateFlat(Map<String,Object> map,HttpServletRequest request ) {
		int flatID = ServletRequestUtils.getIntParameter(request, "idFlat", -1);
		Flat flat;
		
		//je¿eli jest ID to znaczy ¿e bêdzie edycja 
		if(flatID>0)
		{
			flat=flatService.getFlat(flatID);
		}
		else
		{flat=new Flat();}
		
		map.put("flat", flat);
		map.put("buildingList", buildingService.listBuilding());
		return "createFlat";
	}
	
	
	@RequestMapping(value = "/createFlat", method = RequestMethod.POST)
	public String addFlat(@ModelAttribute("flat") Flat flat, Model model, BindingResult result) {
		
		flatValidator.validate(flat, result);
		System.out.println("TWORZENIE FLAT: "+flat.getBuilding().getBuildingCity());
		// je¿eli nie ma b³êdów to idzie dalej w ifie, a jak s¹ to zwraca createUser
		if(result.getErrorCount()==0)
		{
			if (flat.getIdFlat()==0)
			{
				flatService.addFlat(flat);
			}
			else
			{
				flatService.editFlat(flat);
			}
			return "redirect:manageFlat";
		}
		else
		{
			return "createFlat";
		}
		
	}
	
	@RequestMapping(value = "/manageFlat", method = RequestMethod.GET)
	public ModelAndView manageFlat() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("flat", flatService.listFlat());
		modelAndView.setViewName("manageFlat");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteFlat", method = RequestMethod.GET)
	public String DeleteFlat(HttpServletRequest request) {
		int flatID = ServletRequestUtils.getIntParameter(request, "idFlat", -1);
		flatService.removeFlat(flatID);
		return "redirect:manageFlat";
	}
}
