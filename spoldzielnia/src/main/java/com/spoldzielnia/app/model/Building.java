package com.spoldzielnia.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="building")
public class Building {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBuilding;
	
	private Integer buildingNumber;
	private String buildingStreet;
	private String buildingCity;
	private Integer buildingPostCode;
	
	public Integer getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	public int getIdBuilding() {
		return idBuilding;
	}
	public void setIdBuilding(int idBuilding) {
		this.idBuilding = idBuilding;
	}
	public Integer getBuildingPostCode() {
		return buildingPostCode;
	}
	public void setBuildingPostCode(Integer buildingPostCode) {
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
	


}