package com.spoldzielnia.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.BillsDAO;
import com.spoldzielnia.app.dao.CounterDAO;
import com.spoldzielnia.app.dao.UserDAO;
import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.Flat;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.model.UserRole;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	CounterDAO counterDAO;
	
	@Autowired
	BillsDAO billsDAO;
	
	@Transactional
	public void addUser(User user) {
		user.setPassword(hashPassword(user.getPassword()));
		userDAO.addUser(user);
		//userDAO.editUser(user);
	}

	@Transactional
	public List<User> listUser() {
		return userDAO.listUser();
	}

	@Transactional
	public void removeUser(int id) {
		for(Counters c:counterDAO.listMyCounter(getUser(id)))
		{
			billsDAO.delete(billsDAO.listForUser(c));
			counterDAO.delete(c);
		}
		userDAO.removeUser(id);
	}

	@Transactional
	public User getUser(int id) {
		return userDAO.getUser(id);
	}

	@Transactional
	public void editUser(User user) {
		//user.setPassword(hashPassword(user.getPassword()));
		//User user1=userDAO.getUser(user.getIdUser());
		System.out.println("SUMA Ró1111qw : "+user.getUserRole().size());

			System.out.println("ITERACJA");
			

		//System.out.println("SUMA Ról : "+user1.getUserRole().size());
		userDAO.editUser(user);
	}

	@Transactional
	public String hashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Transactional
	public void addUserRole(UserRole userRole) {
		userDAO.addRole(userRole);
	}

	@Transactional
	public List<UserRole> listUserRole() {
		return userDAO.listUserRole();
	}

	@Transactional
	public void removeUserRole(int id) {
		userDAO.removeUser(id);
	}

	@Transactional
	public UserRole getUserRole(int id) {
		return userDAO.getUserRole(id);
	}

	@Override
	public User getUser(String login) {
		return userDAO.findByLogin(login);
	}

	@Override
	public void removeUserByFlat(int idFlat) {
		for(User us : listUser())
		{
			if(us.getFlat().getIdFlat()==idFlat)
			{
				removeUser(us.getIdUser());
			}
		}
	}


}
