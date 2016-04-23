package com.spoldzielnia.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spoldzielnia.app.model.User;

public class UserDAOImpl implements UserDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public List<User> listUser() {
		String sql="from User order by id";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public void removeUser(int id) {
		User user = getUser(id);
		if(user.getIdUser()!=null)
		{
			sessionFactory.getCurrentSession().delete(user);
		}
	}

	@Override
	public User getUser(int id) {
		return (User)sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public void editUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

}
