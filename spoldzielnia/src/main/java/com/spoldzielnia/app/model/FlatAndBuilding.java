package com.spoldzielnia.app.model;

import java.util.Iterator;

public class FlatAndBuilding {
	private String  buildingNumber;
	private String buildingStreet;
	private String buildingCity;
	private String buildingPostCode;
	private int idFlat;
	private  String flatNumber;
	private String flatSurface;
	private String tenantNumber;
	
	public FlatAndBuilding(Flat flat)
	{
		Building b = null;
		for (Iterator<Building> it = flat.getBuilding().iterator(); it.hasNext(); ) {
			b = it.next();
	        System.out.println("found "+b.getBuildingCity());
	    }
		this.buildingNumber = b.getBuildingNumber();
		this.buildingStreet = b.getBuildingStreet();
		this.buildingCity = b.getBuildingCity();
		this.buildingPostCode = b.getBuildingPostCode();
		this.idFlat = flat.getIdFlat();
		this.flatNumber = flat.getFlatNumber();
		this.flatSurface = flat.getFlatSurface();
		this.tenantNumber = flat.getTenantNumber();
	}
	
	public String getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	public String getBuildingStreet() {
		return buildingStreet;
	}
	public void setBuildingStreet(String buildingStreet) {
		this.buildingStreet = buildingStreet;
	}
	public String getBuildingCity() {
		return buildingCity;
	}
	public void setBuildingCity(String buildingCity) {
		this.buildingCity = buildingCity;
	}
	public String getBuildingPostCode() {
		return buildingPostCode;
	}
	public void setBuildingPostCode(String buildingPostCode) {
		this.buildingPostCode = buildingPostCode;
	}
	public int getIdFlat() {
		return idFlat;
	}
	public void setIdFlat(int idFlat) {
		this.idFlat = idFlat;
	}
	public String getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getFlatSurface() {
		return flatSurface;
	}
	public void setFlatSurface(String flatSurface) {
		this.flatSurface = flatSurface;
	}
	public String getTenantNumber() {
		return tenantNumber;
	}
	public void setTenantNumber(String tenantNumber) {
		this.tenantNumber = tenantNumber;
	}
}
