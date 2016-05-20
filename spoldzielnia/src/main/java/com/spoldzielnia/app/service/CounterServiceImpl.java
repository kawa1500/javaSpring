package com.spoldzielnia.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.CounterDAO;
import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.User;

@Service
@Transactional
public class CounterServiceImpl implements CounterService {

	@Autowired
	CounterDAO counterDAO;
	
	@Override
	public void addCounter(Counters counter) {
		Counters last = getActiveCounter(counter.getUser());
		last.setStatus(0);
		if(last!=null && last.getIdCounter()>0)counterDAO.editCounter(last);
		counter.setStatus(1);
		counter.setModDate(new Date());
		counterDAO.addCounter(counter);
	}

	@Override
	public List<Counters> listMyCounter(User user) {
		return counterDAO.listMyCounter(user);
	}

	@Override
	public Counters getActiveCounter(User user) {
		Counters active=counterDAO.getActiveCounter(user);
		if(active.getIdCounter()<=0)
		{
			List<Counters> countersy = listMyCounter(user);
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

	@Override
	public Counters get(int idCounter) {
		return counterDAO.get(idCounter);
	}

	@Override
	public Counters getLastCounters(Counters nowCounter) {
		List<Counters> listMyUser = counterDAO.listMyCounter(nowCounter.getUser());
		if(listMyUser.size()>1)
		{
			return listMyUser.get(1);
		}
		else
		{
			return new Counters();
		}
	}

}
