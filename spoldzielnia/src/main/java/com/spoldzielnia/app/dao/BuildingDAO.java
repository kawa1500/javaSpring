package com.spoldzielnia.app.dao;

import java.util.List;

import com.spoldzielnia.app.model.Building;
// interfejst tutaj piszemy operacje jakie chcemy wykonaæ na bazie
public interface BuildingDAO {
	public void addBuilding(Building building);
	public List<Building> listBuilding();
	public void removeBuilding(int id);
	public Building getBuilding(int id);
	public void editBuilding(Building user);
}
