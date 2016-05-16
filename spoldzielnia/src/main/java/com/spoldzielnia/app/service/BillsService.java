package com.spoldzielnia.app.service;

import java.util.List;

import com.spoldzielnia.app.model.Bills;

public interface BillsService {
	public void add(Bills bill);
	public void edit(Bills bill);
	public List<Bills> listForUser(int idUser);
	public List<Bills> listBills(int status);
	public Bills get(int idBills);
}
