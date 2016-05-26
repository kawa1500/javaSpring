package com.spoldzielnia.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.UserService;

public class UserConverter implements Converter<String, User> {

	@Autowired
	UserService userService;
	
	@Override
	public User convert(String source) {
		return userService.getUser(Integer.parseInt(source));
	}
}


