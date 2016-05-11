package com.spoldzielnia.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.PricesDAO;
import com.spoldzielnia.app.model.Prices;

@Service
@Transactional
public class PriceServiceImpl implements PriceService{

	@Autowired
	PricesDAO pricesDAO;
	
	@Override
	public void addPrice(Prices price) {
		Prices last = getActivePrice();
		last.setStatus(0);
		if(last!=null)pricesDAO.updatePrice(last);
		price.setStatus(1);
		price.setCreateDate(new Date());
		pricesDAO.addPrice(price);
	}

	@Override
	public Prices getActivePrice() {
		return pricesDAO.getActivePrice();
	}

	@Override
	public List<Prices> getAll() {
		return pricesDAO.getAllPrices();
	}
	
}
