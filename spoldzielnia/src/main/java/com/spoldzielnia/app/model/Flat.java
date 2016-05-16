package com.spoldzielnia.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="flat")
public class Flat {

	@ManyToOne
	private Building building;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idFlat;
	private  String flatNumber;
	private String flatSurface;
	private String tenantNumber;


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

	
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}

}
	
