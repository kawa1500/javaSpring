package com.spoldzielnia.app.service;

import java.util.List;

import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.User;

public interface CounterService {
	public Counters get(int idCounter);
	public void addCounter(Counters counter);
	public List<Counters> listMyCounter(User user);
	public Counters getActiveCounter(User user);
	public void editCounter(Counters user);
	public Counters getLastCounters(Counters nowCounter);
}
