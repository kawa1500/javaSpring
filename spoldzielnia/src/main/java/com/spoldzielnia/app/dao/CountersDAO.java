package com.spoldzielnia.app.dao;

import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.Flat;

public interface CountersDAO {
	
	public void addCounters(Counters counter);
	public void editCounters(Counters counter);
	public Counters getCounters(Flat flat);
}
