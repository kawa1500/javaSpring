package com.spoldzielnia.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flat")
public class Build {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idFlat;
	
	private Integer buildNumber;
	private String buildAddress;
	
	public Integer getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(Integer buildNumber) {
		this.buildNumber = buildNumber;
	}
	
	public String getBuildAddress() {
		return buildAddress;
	}
	public void setBuildAddress(String buildAddress) {
		this.buildAddress = buildAddress;
	}
	
	public int getidFlat() {
		return idFlat;
	}
	public void setIdUser(int idFlat) {
		this.idFlat = idFlat;
	}
	
	@Override
	public String toString()
	{
		String result_b = "Address of Build: "+buildAddress+" "+buildNumber; 
		return result_b;
	}
	
}