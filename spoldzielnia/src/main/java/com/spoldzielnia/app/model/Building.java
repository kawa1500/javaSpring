package com.spoldzielnia.app.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="building")
public class Building {
	
	
	@OneToMany(mappedBy = "building")
	private List<Flat> flatList;
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int idBuilding;
	private String  buildingNumber;
	private String buildingStreet;
	private String buildingCity;
	private String buildingPostCode;
	
	public String getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	public int getIdBuilding() {
		return idBuilding;
	}
	public void setIdBuilding(int idBuilding) {
		this.idBuilding = idBuilding;
	}
	public String getBuildingPostCode() {
		return buildingPostCode;
	}
	public void setBuildingPostCode(String buildingPostCode) {
		this.buildingPostCode = buildingPostCode;
	}
	public String getBuildingCity() {
		return buildingCity;
	}
	public void setBuildingCity(String buildingCity) {
		this.buildingCity = buildingCity;
	}
	public String getBuildingStreet() {
		return buildingStreet;
	}
	public void setBuildingStreet(String buildingStreet) {
		this.buildingStreet = buildingStreet;
	}
	public List<Flat> getFlatList() {
		return flatList;
	}
	public void setFlatList(List<Flat> flatList) {
		this.flatList = flatList;
	}

}