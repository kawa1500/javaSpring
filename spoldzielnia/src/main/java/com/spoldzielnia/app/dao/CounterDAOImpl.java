package com.spoldzielnia.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spoldzielnia.app.model.Counters;

@Repository
public class CounterDAOImpl implements CounterDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addCounter(Counters counter) {
		sessionFactory.getCurrentSession().save(counter);
	}

	@SuppressWarnings("unchecked")
	public List<Counters> listMyCounter(int ifFlat) {
		String sql="from Counters where status<1";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	public Counters getActiveCounter(int idFlat) {
		List<Counters> counters = new ArrayList<Counters>();
		  int status = 0;
		  counters = sessionFactory.getCurrentSession()
			.createQuery("from Counters where status>?")
			.setParameter(0, status)
			.list();

		if (counters.size() > 0) {
			return counters.get(0);
		} else {
			return new Counters();
		}
	}

	@Override
	public void editCounter(Counters user) {
		sessionFactory.getCurrentSession().update(user);
	}

}
