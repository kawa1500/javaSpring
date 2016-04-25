package com.spoldzielnia.app.service;

import java.util.List;

import com.spoldzielnia.app.model.User;
// services s�u�a do ��czenia widok�w z baz�
public interface UserService {
	public void addUser(User user);
	public List<User> listUser();
	public void removeUser(int id);
	public User getUser(int id);
	public void editUser(User user);
}
