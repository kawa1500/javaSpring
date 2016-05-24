package com.spoldzielnia.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.User;

@Repository
public class CounterDAOImpl implements CounterDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addCounter(Counters counter) {
		sessionFactory.getCurrentSession().save(counter);
	}

	@SuppressWarnings("unchecked")
	public List<Counters> listMyCounter(User user) {
		String sql="from Counters where user_iduser=? order by modDate desc";
		return sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, user.getIdUser()).list();
	}

	@SuppressWarnings("unchecked")
	public Counters getActiveCounter(User user) {
		List<Counters> counters = new ArrayList<Counters>();
		  counters = sessionFactory.getCurrentSession()
			.createQuery("from Counters where status>0 and user_iduser=? order by modDate desc")
			.setParameter(0, user.getIdUser())
			.list();

		if (counters.size() > 0) {
			return counters.get(0);
		} else {
			Counters count = new Counters();
			count.setUser(user);
			return count;
		}
	}

	@Override
	public void editCounter(Counters user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public Counters get(int idCounter) {
		return (Counters)sessionFactory.getCurrentSession().get(Counters.class, idCounter);
	}

	@Override
	public void delete(Counters counter) {
		sessionFactory.getCurrentSession().delete(counter);
	}

}
