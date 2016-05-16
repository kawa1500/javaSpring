package com.spoldzielnia.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spoldzielnia.app.model.Prices;
import com.spoldzielnia.app.model.User;

@Repository
public class PricesDAOImpl implements PricesDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addPrice(Prices price) {
		sessionFactory.getCurrentSession().save(price);
	}

	@SuppressWarnings("unchecked")
	public Prices getActivePrice() {
		List<Prices> prices = new ArrayList<Prices>();
		  int status = 0;
		prices = sessionFactory.getCurrentSession()
			.createQuery("from Prices where status>?")
			.setParameter(0, status)
			.list();

		if (prices.size() > 0) {
			return prices.get(0);
		} else {
			return new Prices();
		}
	}

	@Override
	public List<Prices> getAllPrices() {
		String sql="from Prices where status<1";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public void updatePrice(Prices price) {
		sessionFactory.getCurrentSession().update(price);
	}

}
