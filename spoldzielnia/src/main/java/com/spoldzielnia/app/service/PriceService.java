package com.spoldzielnia.app.service;

import java.util.List;

import com.spoldzielnia.app.model.Prices;

public interface PriceService {
	public void addPrice(Prices price);
	public Prices getActivePrice();
	public List<Prices> getAll();
}
