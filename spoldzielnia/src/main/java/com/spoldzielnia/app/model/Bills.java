package com.spoldzielnia.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bills")
public class Bills {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBills;
	
	private double water;
	private double gas;
	private double current;
	private double energy;
	private double waterValue;
	private double gasValue;
	private double currentValue;
	private double energyValue;
	private double intercom;
	private double trash;
	private double sewage;
	private double other;
	private int osoby;
	private double cost;
	
	private int status;	
	private int idFlat;
	
	@Temporal(TemporalType.DATE)
	private Date modDate;

	public int getIdBills() {
		return idBills;
	}

	public void setIdBills(int idBills) {
		this.idBills = idBills;
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

	public double getWaterValue() {
		return waterValue;
	}

	public void setWaterValue(double waterValue) {
		this.waterValue = waterValue;
	}

	public double getGasValue() {
		return gasValue;
	}

	public void setGasValue(double gasValue) {
		this.gasValue = gasValue;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public double getEnergyValue() {
		return energyValue;
	}

	public void setEnergyValue(double energyValue) {
		this.energyValue = energyValue;
	}

	public double getIntercom() {
		return intercom;
	}

	public void setIntercom(double intercom) {
		this.intercom = intercom;
	}

	public double getTrash() {
		return trash;
	}

	public void setTrash(double trash) {
		this.trash = trash;
	}

	public double getSewage() {
		return sewage;
	}

	public void setSewage(double sewage) {
		this.sewage = sewage;
	}

	public double getOther() {
		return other;
	}

	public void setOther(double other) {
		this.other = other;
	}

	public int getOsoby() {
		return osoby;
	}

	public void setOsoby(int osoby) {
		this.osoby = osoby;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdFlat() {
		return idFlat;
	}

	public void setIdFlat(int idFlat) {
		this.idFlat = idFlat;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	
}
