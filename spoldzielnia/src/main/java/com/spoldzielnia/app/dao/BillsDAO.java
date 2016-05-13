package com.spoldzielnia.app.dao;

import java.util.List;

import com.spoldzielnia.app.model.Bills;

public interface BillsDAO {
	public void add(Bills bill);
	public void edit(Bills bill);
	public List<Bills> listForUser(int idUser);
	public List<Bills> listBills(int status);
	public Bills get(int idBills);
}
