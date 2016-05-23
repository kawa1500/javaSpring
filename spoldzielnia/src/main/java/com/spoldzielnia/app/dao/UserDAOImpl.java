package com.spoldzielnia.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.model.UserRole;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser() {
		String sql="from User order by id";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public void removeUser(int id) {
		User user = getUser(id);
		if(user.getIdUser()!=0)
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

	@Override
	public void addRole(UserRole userRole) {
		sessionFactory.getCurrentSession().save(userRole);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> listUserRole() {
		return sessionFactory.getCurrentSession().createQuery("from UserRole order by id").list();
	}

	@Override
	public void removeUserRole(int id) {
		UserRole userRole = (UserRole)sessionFactory.getCurrentSession().load(UserRole.class, id);
		if(userRole!=null)
		{
			sessionFactory.getCurrentSession().delete(userRole);
		}
	}

	@Override
	public UserRole getUserRole(int id) {
		return (UserRole)sessionFactory.getCurrentSession().get(UserRole.class, id);
	}

	@SuppressWarnings("unchecked")
	public UserRole findRoleByName(String role) {
		List<UserRole> userRole = new ArrayList<UserRole>();
		userRole=sessionFactory.getCurrentSession().createQuery("from UserRole where role=?").setParameter(0, role).list();
		if(userRole.size()>0)
		{
			return userRole.get(0);
		}
		else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public User findByLogin(String login) {
		List<User> users = new ArrayList<User>();
		  
		users = sessionFactory.getCurrentSession()
			.createQuery("from User where login=?")
			.setParameter(0, login)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserRole listUserRoleForUser(int id) {
		List<UserRole> userRole = new ArrayList<UserRole>();;
		String sql="select * from UserRole as r join user_userrole as m on r.id=m.userrole_id where m.user_iduser="+id;
		userRole=sessionFactory.getCurrentSession().createQuery(sql).list();
		return userRole.get(0);
	}

}
