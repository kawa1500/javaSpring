package com.spoldzielnia.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.BuildingDAO;
import com.spoldzielnia.app.dao.FlatDAO;
import com.spoldzielnia.app.model.Building;
import com.spoldzielnia.app.model.Flat;

@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired
	BuildingDAO buildingDAO;
	
	@Autowired
	FlatDAO flatDAO;
	
	@Autowired 
	FlatService flatService;
	
	@Transactional
	public void addBuilding(Building building) {
		buildingDAO.addBuilding(building);
	}

	@Transactional
	public List<Building> listBuilding() {
		return buildingDAO.listBuilding();
	}

	@Transactional
	public void removeBuilding(int id) {
		for(Flat f : flatDAO.listFlat())
		{
			if(f.getBuilding().getIdBuilding()==id) flatService.removeFlat(f); 
		}
		buildingDAO.removeBuilding(id);
	}

	@Transactional
	public Building getBuilding(int id) {
		return buildingDAO.getBuilding(id);
	}

	@Transactional
	public void editBuilding(Building building) {
		buildingDAO.editBuilding(building);
	}



}
