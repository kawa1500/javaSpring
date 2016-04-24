package com.spoldzielnia.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flat")
public class Flat {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idFlat;
	
	private Integer flatNumber;
	
	
	public Integer getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(Integer flatNumber) {
		this.flatNumber = flatNumber;
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
		String result_f = "Number of flat: "+flatNumber; 
		return result_f;
	}
	
}