package com.spoldzielnia.app.service;

import java.util.List;

import com.spoldzielnia.app.model.Building;


public interface BuildingService {
	public void addBuilding(Building building);
	public List<Building> listBuilding();
	public void removeBuilding(int id);
	public Building getBuilding(int id);
	public void editBuilding(Building building);
}


