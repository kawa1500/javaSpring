package com.spoldzielnia.app.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spoldzielnia.app.model.PasswordUser;
import com.spoldzielnia.app.model.User;

public class PasswordUserValidator implements Validator{
	
	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub	
	}
	
	public void validate(PasswordUser user, Errors errors){
		ValidationUtils.rejectIfEmpty(errors, "login", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "oldPassword", "error.empty");
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "error.empty");
		
		if(errors.getErrorCount() == 0)
		{
			if (validPassword(user.getNewPassword()) == false)
			{
				errors.rejectValue("newPassword", "error.password.invalid");
			}
		}
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
