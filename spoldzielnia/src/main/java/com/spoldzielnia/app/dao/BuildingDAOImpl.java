package com.spoldzielnia.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spoldzielnia.app.model.Building;


@Repository
public class BuildingDAOImpl implements BuildingDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addBuilding(Building building) {
		sessionFactory.getCurrentSession().save(building);
	}

	@Override
	public List<Building> listBuilding() {
		String sql="from Building order by id";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public void removeBuilding(int id) {
		Building building = getBuilding(id);
		if(building.getIdBuilding()!=0)
		{
			sessionFactory.getCurrentSession().delete(building);
		}
	}

	@Override
	public Building getBuilding(int id) {
		return (Building)sessionFactory.getCurrentSession().get(Building.class, id);
	}

	@Override
	public void editBuilding(Building building) {
		sessionFactory.getCurrentSession().update(building);
	}

}
