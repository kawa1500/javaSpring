package com.spoldzielnia.app.utils;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.spoldzielnia.app.model.Building;
import com.spoldzielnia.app.service.BuildingService;

public class BuildingConverter implements Converter<String, Set<Building>> {

	@Autowired
	BuildingService buildingService;
	
	@Override
	public Set<Building> convert(String source) {
		
		Set<Building> buildingList = new HashSet<Building>(0);
		
			buildingList.add(buildingService.getBuilding(Integer.parseInt(source)));
		
		return buildingList;
	}

}
