package com.spoldzielnia.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.spoldzielnia.app.model.Building;
import com.spoldzielnia.app.service.BuildingService;

public class BuildingConverter implements Converter<String, Building> {

	@Autowired
	BuildingService buildingService;
	
	@Override
	public Building convert(String source) {
		return buildingService.getBuilding(Integer.parseInt(source));
	}
}


