package com.spoldzielnia.app.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="flat")
public class Flat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idFlat;
	private  String flatNumber;
	private String flatSurface;
	private String tenantNumber;
	
//	@OneToOne
//	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Building> building = new HashSet<Building>(0);
	
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
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "flat_building", joinColumns = {@JoinColumn(name = "idFlat", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "idBuilding", nullable = false, updatable = false)})
	
	public Set<Building> getBuilding() {
		return building;
	}
	public void setBuilding(Set<Building> building) {
		this.building = building;
	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	

}
	
