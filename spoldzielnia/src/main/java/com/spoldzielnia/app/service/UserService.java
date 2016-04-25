package com.spoldzielnia.app.service;

import java.util.List;

import com.spoldzielnia.app.model.User;
// services s³u¿a do ³¹czenia widoków z baz¹
public interface UserService {
	public void addUser(User user);
	public List<User> listUser();
	public void removeUser(int id);
	public User getUser(int id);
	public void editUser(User user);
}
