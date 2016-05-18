package com.spoldzielnia.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spoldzielnia.app.model.Bills;


@Repository
public class BillsDAOImpl implements BillsDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Bills> listForUser(int idUser) {
		String sql="from Bills where idFlat=? order by modDate desc";
		return sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, idUser).list();
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
