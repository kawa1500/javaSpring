package com.spoldzielnia.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.spoldzielnia.app.model.Flat;
import com.spoldzielnia.app.service.FlatService;



public class FlatConverter implements Converter<String, Flat> {

	@Autowired
	FlatService flatService;
	
	@Override
	public Flat convert(String source) {
		return flatService.getFlat(Integer.parseInt(source));
	}
}


