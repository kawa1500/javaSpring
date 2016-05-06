package com.spoldzielnia.app.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.UserDAO;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.model.UserRole;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Transactional
	public void addUser(User user) {
		user.getUserRole().add(userDAO.findRoleByName("ROLE_USER"));
		user.setPassword(hashPassword(user.getPassword()));
		userDAO.addUser(user);
		userDAO.editUser(user);
	}

	@Transactional
	public List<User> listUser() {
		return userDAO.listUser();
	}

	@Transactional
	public void removeUser(int id) {
		userDAO.removeUser(id);
	}

	@Transactional
	public User getUser(int id) {
		return userDAO.getUser(id);
	}

	@Transactional
	public void editUser(User user) {
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


}
