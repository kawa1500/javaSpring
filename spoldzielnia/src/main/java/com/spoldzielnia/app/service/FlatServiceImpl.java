package com.spoldzielnia.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spoldzielnia.app.dao.FlatDAO;
import com.spoldzielnia.app.dao.UserDAO;
import com.spoldzielnia.app.model.Flat;

@Service
public class FlatServiceImpl implements FlatService{

	@Autowired
	FlatDAO flatDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired 
	UserService userService;
	
	@Transactional
	public void addFlat(Flat flat) {
		flatDAO.addFlat(flat);
	}

	@Transactional
	public List<Flat> listFlat() {
		return flatDAO.listFlat();
	}

	@Transactional
	public void removeFlat(Flat flat) {
		userService.removeUserByFlat(flat.getIdFlat());
		flatDAO.removeFlat(flat);
	}

	@Transactional
	public Flat getFlat(int id) {
		return flatDAO.getFlat(id);
	}

	@Transactional
	public void editFlat(Flat flat) {
		flatDAO.editFlat(flat);
	}

}
