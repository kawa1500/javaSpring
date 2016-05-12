package com.spoldzielnia.app.dao;

import java.util.List;

import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.User;

public interface CounterDAO {
	public void addCounter(Counters counter);
	public List<Counters> listMyCounter(int ifFlat);
	public Counters getActiveCounter(int idFlat);
	public void editCounter(Counters user);
}
