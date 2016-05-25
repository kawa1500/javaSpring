package com.spoldzielnia.app.service;

import java.util.List;
import com.spoldzielnia.app.model.Flat;


public interface FlatService {
	public void addFlat(Flat flat);
	public List<Flat> listFlat();
	public void removeFlat(Flat flat);
	public Flat getFlat(int id);
	public void editFlat(Flat flat);
}
