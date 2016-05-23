package com.spoldzielnia.app.dao;

import java.util.List;

import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.Counters;

public interface BillsDAO {
	public void add(Bills bill);
	public void edit(Bills bill);
	public Bills listForUser(Counters counter);
	public List<Bills> listBills();
	public Bills get(int idBills);
}
