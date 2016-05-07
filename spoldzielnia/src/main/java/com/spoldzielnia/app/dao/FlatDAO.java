package com.spoldzielnia.app.dao;

import java.util.List;
import com.spoldzielnia.app.model.Flat;
// interfejst tutaj piszemy operacje jakie chcemy wykonaæ na bazie
public interface FlatDAO {
	public void addFlat(Flat flat);
	public List<Flat> listFlat();
	public void removeFlat(int id);
	public Flat getFlat(int id);
	public void editFlat(Flat flat);
}


