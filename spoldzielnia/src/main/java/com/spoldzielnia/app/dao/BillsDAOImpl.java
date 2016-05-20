package com.spoldzielnia.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.Counters;

@Repository
public class BillsDAOImpl implements BillsDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	
	public Bills listForUser(Counters counter) {
		String sql="from Bills where counters_idcounter=?";
		return (Bills)sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, counter.getIdCounter()).list().get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Bills> listBills(int status) {
		String sql="from Bills where status=? order by modDate desc";
		return sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, status).list();
	}

	@Override
	public void add(Bills bill) {
		sessionFactory.getCurrentSession().save(bill);
	}

	@Override
	public void edit(Bills bill) {
		sessionFactory.getCurrentSession().save(bill);
	}

	@Override
	public Bills get(int idBills) {
		return (Bills)sessionFactory.getCurrentSession().get(Bills.class, idBills);
	}
	
	
}
