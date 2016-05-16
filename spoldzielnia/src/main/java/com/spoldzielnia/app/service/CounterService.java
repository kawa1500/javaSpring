package com.spoldzielnia.app.service;

import java.util.List;

import com.spoldzielnia.app.model.Counters;

public interface CounterService {
	public Counters get(int idCounter);
	public void addCounter(Counters counter);
	public List<Counters> listMyCounter(int ifFlat);
	public Counters getActiveCounter(int idFlat);
	public void editCounter(Counters user);
	public Counters getLastCounters(Counters nowCounter);
}
