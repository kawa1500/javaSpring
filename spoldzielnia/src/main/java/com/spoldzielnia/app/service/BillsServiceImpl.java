package com.spoldzielnia.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.BillsDAO;
import com.spoldzielnia.app.model.Bills;

@Service
@Transactional
public class BillsServiceImpl implements BillsService{
	
	@Autowired
	BillsDAO billsDAO;

	@Override
	public void add(Bills bill) {
		billsDAO.add(bill);
	}

	@Override
	public void edit(Bills bill) {
		billsDAO.edit(bill);
	}

	@Override
	public List<Bills> listForUser(int idUser) {
		// TODO Auto-generated method stub
		return billsDAO.listForUser(idUser);
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
