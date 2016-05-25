package com.spoldzielnia.app.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spoldzielnia.app.dao.BuildingDAO;
import com.spoldzielnia.app.model.Building;
;

public class BuildingValidator implements Validator{

	private static final String POSTCODE_PATTERN = "[0-9]{5}";
	private static final String STREET_PATTERN = "[A-Z]{1}+|[a-zA-Z]+";
	private static final String CITY_PATTERN = "[a-zA-Z]+|[a-zA-Z]+";
	private static final String NUMBER_PATTERN = "[0-9a-dA-D]+|[0-9a-dA-D]+";
	
	@Override
	public boolean supports(Class clazz) {
		return Building.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method 
	}
	
	public void validate(Building building, Errors errors){

		ValidationUtils.rejectIfEmpty(errors, "buildingPostCode", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "buildingStreet", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "buildingCity", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "buildingNumber", "error.empty");
		
		if(errors.getErrorCount() == 0)
		{
		
			if (validStreet(building.getBuildingStreet()) == false)
			{
				errors.rejectValue("buildingStreet", "error.buildingStreet.invalid");
			}
			
			if (validPost(building.getBuildingPostCode()) == false)
			{
				errors.rejectValue("buildingPostCode", "error.buildingPostCode.invalid");
			}
			
			if (validCity(building.getBuildingCity()) == false)
			{
				errors.rejectValue("buildingCity", "error.buildingCity.invalid");
			}
			
			if (validNumber(building.getBuildingNumber()) == false)
			{
				errors.rejectValue("buildingNumber", "error.buildingNumber.invalid");
			}
			
			
			
		}
	}

	private boolean validPost(String buildingPostCode)
	{
		if(	buildingPostCode.matches(POSTCODE_PATTERN))
		{return true;}
		else
		{return false;}
	}
	
	private boolean validStreet(String buildingStreet)
	{
		return true;
	}

	private boolean validCity(String buildingCity)
	{
		return true;
	}

	private boolean validNumber(String buildingNumber)
	{
		if(	buildingNumber.matches(NUMBER_PATTERN) && buildingNumber.length()<8)
		{return true;}
		else
		{return false;}
	}

}