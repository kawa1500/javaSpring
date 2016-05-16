package com.spoldzielnia.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.Counters;
import com.spoldzielnia.app.model.Prices;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.BillsService;
import com.spoldzielnia.app.service.CounterService;
import com.spoldzielnia.app.service.PriceService;
import com.spoldzielnia.app.service.UserService;

public class CronController {

	@Autowired
	CounterService counterService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PriceService priceService;
	
	@Autowired
	BillsService billService;
	
//	@Scheduled(cron="0 12 16 16 * ?")
//    public void rememberServiceMethod()
//    {
//		//wpisa� metod� do wysy�ania powiadomie� do u�ytkownik�w o wype�nieniu licznik�w
//    }
	
	@Scheduled(cron="0 15 16 16 * ?")
    public void demoServiceMethod()
    {
		for(User user: userService.listUser())
		{
			System.out.println("wyszukiwanie licznika dla usera: "+user.getIdUser());
			Counters count = counterService.getActiveCounter(user.getIdUser());
			if(count.getIdCounter()>0)
			{
				if(count.getModDate().getMonth()!=new Date().getMonth())
				{
					ryczaltuj(count.getIdFlat(), count.getIdCounter());
				}
			}
			else
			{
				ryczaltuj(count.getIdFlat(), count.getIdCounter());
			}
			
			Counters countF = counterService.getActiveCounter(user.getIdUser());
			if(countF.getIdCounter()>0)
			{
				if(countF.getModDate().getMonth()==new Date().getMonth() && countF.getStatus()==1)
				{
					countF.setStatus(2);
					counterService.editCounter(countF);
					generateBill(countF);
				}
			}
			
			System.out.println("Wyszukiwanie dla usera "+user.getIdUser()+" znaleziono licznik z idFlat "+count.getIdFlat());
		}
    }
	
	private void ryczaltuj(int idUser, int idCounter)
	{
		Prices actualPrices= priceService.getActivePrice();
		System.out.println("Dane wej�ciowe: "+idUser+"|"+idCounter);
		if(idCounter<1){
			Counters lastCounter = new Counters();
			lastCounter.setCurrent(lastCounter.getCurrent()+actualPrices.getrCurrent());
			lastCounter.setEnergy(lastCounter.getEnergy()+actualPrices.getrEnergy());
			lastCounter.setGas(lastCounter.getGas()+actualPrices.getrGas());
			lastCounter.setWater(lastCounter.getWater()+actualPrices.getrWater());
			lastCounter.setRyczalt(true);
			lastCounter.setIdFlat(idUser);
			counterService.addCounter(lastCounter);
			System.out.println("TWORZENIE LICZNIK�W");
		}
		else
		{
			Counters lastCounter = counterService.getActiveCounter(idUser);
			lastCounter.setCurrent(lastCounter.getCurrent()+actualPrices.getrCurrent());
			lastCounter.setEnergy(lastCounter.getEnergy()+actualPrices.getrEnergy());
			lastCounter.setGas(lastCounter.getGas()+actualPrices.getrGas());
			lastCounter.setWater(lastCounter.getWater()+actualPrices.getrWater());
			lastCounter.setRyczalt(true);
			counterService.addCounter(lastCounter);
			System.out.println("UPDATE LICZNIK�W");
		}
	}
	
	private void generateBill(Counters counter)
	{
		Bills myBill = new Bills();
		Double costTotal = 0.0;
		Double actualCost = 0.0;
		Counters lastCounterValue = counterService.getLastCounters(counter);
		Prices actualPrice = priceService.getActivePrice();
		myBill.setOsoby(1);
		myBill.setStatus(1);
		myBill.setCurrentValue(counter.getCurrent()-lastCounterValue.getCurrent());
		myBill.setWaterValue(counter.getWater()-lastCounterValue.getWater());
		myBill.setGasValue(counter.getGas()-lastCounterValue.getGas());
		myBill.setEnergyValue(counter.getEnergy()-lastCounterValue.getEnergy());
		myBill.setWater(zaokraglij(actualPrice.getWater()*myBill.getWaterValue()));
		myBill.setCurrent(zaokraglij(actualPrice.getCurrent()*myBill.getCurrentValue()));
		myBill.setGas(zaokraglij(actualPrice.getGas()*myBill.getGasValue()));
		myBill.setEnergy(zaokraglij(actualPrice.getEnergy()*myBill.getEnergyValue()));
		myBill.setIntercom(zaokraglij(myBill.getOsoby()*actualPrice.getIntercom()));
		myBill.setTrash(zaokraglij(actualPrice.getTrash()*myBill.getOsoby()));
		myBill.setSewage(zaokraglij(actualPrice.getSewage()*myBill.getWaterValue()));
		myBill.setOther(zaokraglij(actualPrice.getOther()*myBill.getOsoby()));
		myBill.setCost(zaokraglij(myBill.getCurrent()+myBill.getEnergy()+myBill.getGas()+myBill.getIntercom()+myBill.getOther()+myBill.getSewage()+myBill.getTrash()+myBill.getWater()));
		myBill.setIdFlat(counter.getIdFlat());
		myBill.setModDate(new Date());
		billService.add(myBill);
	}
	
	private Double zaokraglij(Double value)
	{
		Double wynik = value;
		value*=100;
		value=(double) Math.round(value);
		value/=100;
		return wynik;
	}
}
