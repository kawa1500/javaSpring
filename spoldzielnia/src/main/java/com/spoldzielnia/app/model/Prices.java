package com.spoldzielnia.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="prices")
public class Prices {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPrices;
	
	private double water;
	private double hotWater;
	private double gas;
	private double current;
	private double energy;
	private double intercom;
	private double trash;
	private double sewage;
	private double other;
	private int status;
	private double rWater;
	private double rHotWater;
	private double rGas;
	private double rCurrent;
	private double rEnergy;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	public int getIdPrices() {
		return idPrices;
	}
	public void setIdPrices(int idPrices) {
		this.idPrices = idPrices;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public double getrWater() {
		return rWater;
	}
	public void setrWater(double rWater) {
		this.rWater = rWater;
	}
	public double getrGas() {
		return rGas;
	}
	public void setrGas(double rGas) {
		this.rGas = rGas;
	}
	public double getrCurrent() {
		return rCurrent;
	}
	public void setrCurrent(double rCurrent) {
		this.rCurrent = rCurrent;
	}
	public double getrEnergy() {
		return rEnergy;
	}
	public void setrEnergy(double rEnergy) {
		this.rEnergy = rEnergy;
	}
	public double getHotWater() {
		return hotWater;
	}
	public void setHotWater(double hotWater) {
		this.hotWater = hotWater;
	}
	public double getrHotWater() {
		return rHotWater;
	}
	public void setrHotWater(double rHotWater) {
		this.rHotWater = rHotWater;
	}
}
