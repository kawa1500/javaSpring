package com.spoldzielnia.app.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spoldzielnia.app.model.Flat;


@Repository
public class FlatDAOImpl implements FlatDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addFlat(Flat flat) {
		sessionFactory.getCurrentSession().save(flat);
	}

	@Override
	public List<Flat> listFlat() {
		String sql="from Flat order by id";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public void removeFlat(int id) {
		Flat flat= getFlat(id);
		if(flat.getIdFlat()!=0)
		{
			sessionFactory.getCurrentSession().delete(flat);
		}
	}

	@Override
	public Flat getFlat(int id) {
		return (Flat)sessionFactory.getCurrentSession().get(Flat.class, id);
	}

	@Override
	public void editFlat(Flat flat) {
		sessionFactory.getCurrentSession().update(flat);
	}

}

