package com.spoldzielnia.app.dao;

import java.util.List;

import com.spoldzielnia.app.model.Prices;

public interface PricesDAO {
	public void addPrice(Prices price);
	public Prices getActivePrice();
	public List<Prices> getAllPrices();
	public void updatePrice(Prices price);
}
