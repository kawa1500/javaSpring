package com.spoldzielnia.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author KAWA
 *
 */
@Entity
@Table(name="counters")
public class Counters {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCounters;
	
	private int water;
	private int gas;
	private int current;
	private int energy;
	
	@ManyToOne
	private Flat flat;

	public int getIdCounters() {
		return idCounters;
	}

	public void setIdCounters(int idCounters) {
		this.idCounters = idCounters;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getGas() {
		return gas;
	}

	public void setGas(int gas) {
		this.gas = gas;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}
}
