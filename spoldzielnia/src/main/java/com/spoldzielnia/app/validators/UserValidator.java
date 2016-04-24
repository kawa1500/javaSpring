package com.spoldzielnia.app.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spoldzielnia.app.model.User;

public class UserValidator implements Validator{

	private static final String PESEL_PATTERN = "[0-9]{2}[0-1]{1}[0-9]{1}[0-3]{1}[0-9]{1}[0-9]{5}";
	
	EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub	
	}
	
	public void validate(User user, Errors errors){
		ValidationUtils.rejectIfEmpty(errors, "firstName", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "PESEL", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "phone", "error.empty");
		
		if(errors.getErrorCount() == 0)
		{
			if (StringUtils.hasText(user.getEmail()) && emailValidator.isValid(user.getEmail()) == false)
			{
				errors.rejectValue("email", "error.email.invalid");
			}
			if (validatePESEL(user.getPESEL()) == false)
			{
				errors.rejectValue("PESEL", "error.pesel.invalid");
			}
			if (validPhone(user.getPhone()) == false)
			{
				errors.rejectValue("phone", "error.phone.invalid");
			}
			if (validPassword(user.getPassword()) == false)
			{
				errors.rejectValue("password", "error.password.invalid");
			}
		}
	}

	private boolean validatePESEL(String pesel)
	{
		if(pesel.matches(PESEL_PATTERN ))
		{
			int a = Integer.parseInt(pesel.substring(0, 1));
			int b = Integer.parseInt(pesel.substring(1, 2));
			int c = Integer.parseInt(pesel.substring(2, 3));
			int d = Integer.parseInt(pesel.substring(3, 4));
			int e = Integer.parseInt(pesel.substring(4, 5));
			int f = Integer.parseInt(pesel.substring(5, 6));
			int g = Integer.parseInt(pesel.substring(6, 7));
			int h = Integer.parseInt(pesel.substring(7, 8));
			int i = Integer.parseInt(pesel.substring(8, 9));
			int j = Integer.parseInt(pesel.substring(9, 10));
			int k = Integer.parseInt(pesel.substring(10, 11));
			
			int suma = a+(3*b)+(7*c)+(9*d)+e+(3*f)+(7*g)+(9*h)+i+(3*j);
			int mod = suma%10;
			if(mod==0)
			{
				if(k==0)return true;
				else return false;
			}
			else
			{
				if((10-mod)==k)return true;
				else return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	private boolean validPhone(String phone)
	{
		if(phone.length()==12 && phone.startsWith("+"))
		{
			return true;
		}
		else return false;
	}
	
	private boolean validPassword(String password)
	{
		if(password.length()>7)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
