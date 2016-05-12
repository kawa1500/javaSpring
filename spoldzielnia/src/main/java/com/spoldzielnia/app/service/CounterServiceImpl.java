package com.spoldzielnia.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.CounterDAO;
import com.spoldzielnia.app.model.Counters;

@Service
@Transactional
public class CounterServiceImpl implements CounterService {

	@Autowired
	CounterDAO counterDAO;
	
	@Override
	public void addCounter(Counters counter) {
		Counters last = getActiveCounter(counter.getIdFlat());
		last.setStatus(0);
		if(last!=null && last.getIdCounter()>0)counterDAO.editCounter(last);
		counter.setStatus(1);
		counter.setModDate(new Date());
		counterDAO.addCounter(counter);
	}

	@Override
	public List<Counters> listMyCounter(int ifFlat) {
		return counterDAO.listMyCounter(ifFlat);
	}

	@Override
	public Counters getActiveCounter(int idFlat) {
		Counters active=counterDAO.getActiveCounter(idFlat);
		if(active.getIdCounter()<=0)
		{
			List<Counters> countersy = listMyCounter(idFlat);
			if(countersy.size()>0)
			{
				active = countersy.get(0);
			}
		}
		return active;
	}

	@Override
	public void editCounter(Counters user) {
		counterDAO.editCounter(user);
	}

}
