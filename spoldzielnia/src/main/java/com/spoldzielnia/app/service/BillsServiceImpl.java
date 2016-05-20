package com.spoldzielnia.app.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.BillsDAO;
import com.spoldzielnia.app.dao.CounterDAO;
import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.User;

@Service
@Transactional
public class BillsServiceImpl implements BillsService{
	
	@Autowired
	BillsDAO billsDAO;
	
	@Autowired 
	CounterDAO counterDAO;

	@Override
	public void add(Bills bill) {
		billsDAO.add(bill);
	}

	@Override
	public void edit(Bills bill) {
		billsDAO.edit(bill);
	}

	@Override
	public List<Bills> listForUser(User user) {
		// TODO Auto-generated method stub
		List<Bills> wynik = new LinkedList<Bills>();
		for(Counters counter: counterDAO.listMyCounter(user))
		{
			wynik.add(billsDAO.listForUser(counter));
		}
		return wynik;
	}

	@Override
	public List<Bills> listBills(int status) {
		// TODO Auto-generated method stub
		return billsDAO.listBills(status);
	}

	@Override
	public Bills get(int idBills) {
		return billsDAO.get(idBills);
	}
	
}
