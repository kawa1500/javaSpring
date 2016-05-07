package com.spoldzielnia.app.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.spoldzielnia.app.model.Flat;


public class FlatValidator implements Validator{

	private static final String FLATNUMBER_PATTERN = "[0-9a-dA-D]+|[0-9a-dA-D]+";
	private static final String FLATSURFACE_PATTERN = "[0-9]+";
	private static final String FLATTENANT_PATTERN = "[0-9]+";
	
	@Override
	public boolean supports(Class clazz) {
		return Flat.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method 
	}
	
	public void validate(Flat flat, Errors errors){

		ValidationUtils.rejectIfEmpty(errors, "flatNumber", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "tenantNumber", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "flatSurface", "error.empty");
		
		if(errors.getErrorCount() == 0)
		{
			if (validFlat(flat.getFlatNumber()) == false)
			{
				errors.rejectValue("flatNumber", "error.flatNumber.invalid");
			}
			
			if (validSurface(flat.getFlatSurface()) == false)
			{
				errors.rejectValue("flatSurface", "error.flatSurface.invalid");
			}
			
			if (validTenant(flat.getTenantNumber()) == false)
			{
				errors.rejectValue("tenantNumber", "error.tenantNumber.invalid");
			}
		
		}
	}


	private boolean validTenant(String tenantNumber)
	{
		if(	tenantNumber.matches(FLATTENANT_PATTERN))
		{return true;}
		else
		{return false;}
	}
	
	private boolean validSurface(String flatSurface)
	{
		if(	flatSurface.matches(FLATSURFACE_PATTERN ))
		{return true;}
		else
		{return false;}
	}
	private boolean validFlat(String flatNumber)
	{
		if(flatNumber.matches(FLATNUMBER_PATTERN) && flatNumber.length()<5)
		{return true;}
		else
		{return false;}
	}	
}