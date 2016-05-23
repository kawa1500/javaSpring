package com.spoldzielnia.app.service;

import java.util.List;

import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.User;

public interface BillsService {
	public void add(Bills bill);
	public void edit(Bills bill);
	public List<Bills> listForUser(User user);
	public List<Bills> listBills(int status);
	public Bills get(int idBills);
}
