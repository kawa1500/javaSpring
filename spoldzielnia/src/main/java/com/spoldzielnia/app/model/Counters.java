package com.spoldzielnia.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="counters")
public class Counters {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCounter;
	
	private double water;
	private double gas;
	private double current;
	private double energy;
	
	private int status;	
	
	private boolean ryczalt;
	
	@Temporal(TemporalType.DATE)
	private Date modDate;
	
	@ManyToOne
	private User user;

	@OneToOne(mappedBy="counters")
	private Bills bills;
	
	public int getIdCounter() {
		return idCounter;
	}

	public void setIdCounter(int idCounter) {
		this.idCounter = idCounter;
	}

	public double getWater() {
		return water;
	}

	public void setWater(double water) {
		this.water = water;
	}

	public double getGas() {
		return gas;
	}

	public void setGas(double gas) {
		this.gas = gas;
	}

	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public boolean isRyczalt() {
		return ryczalt;
	}

	public void setRyczalt(boolean ryczalt) {
		this.ryczalt = ryczalt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bills getBills() {
		return bills;
	}

	public void setBills(Bills bills) {
		this.bills = bills;
	}


}
