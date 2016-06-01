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
import com.spoldzielnia.app.utils.document.PdfCreator;
import com.spoldzielnia.app.utils.mail.SendingMail;

public class CronController {

	@Autowired
	CounterService counterService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PriceService priceService;
	
	@Autowired
	BillsService billService;
	
	@Scheduled(cron="0 0 0 11 * ?")
    public void demoServiceMethod()
    {
		for(User user: userService.listUser())
		{
			System.out.println("wyszukiwanie licznika dla usera: "+user.getIdUser());
			Counters count = counterService.getActiveCounter(user);
			if(count.getStatus()==0 && count.getIdCounter()>0)
			{
				//uzytkownik doda³ stan
				System.out.println("UZYTKOWNIK DODA£ WARTOŒC LICZNIKA");
				Prices actualPrices= priceService.getActivePrice();
				count.setEnergy(counterService.getLastCounters(count).getEnergy()+actualPrices.getrEnergy());
				count.setStatus(1);
				counterService.editCounter(count);
			}
			else
			{
				//ryczalt pierwszy
				System.out.println("TRZEBA ZROBIÆ RYCZA£T");
				ryczaltuj(count);
			}
			
			generateBill(user);
		}
    }
	
	private void ryczaltuj(Counters lastCounter)
	{
		Prices actualPrices= priceService.getActivePrice();
		Counters counter = new Counters();
		counter.setUser(lastCounter.getUser());
		counter.setStatus(1);
		counter.setRyczalt(true);
		counter.setCurrent(lastCounter.getCurrent()+actualPrices.getrCurrent());
		counter.setEnergy(lastCounter.getEnergy()+actualPrices.getrEnergy());
		counter.setGas(lastCounter.getGas()+actualPrices.getrGas());
		counter.setWater(lastCounter.getWater()+actualPrices.getrWater());
		counter.setHotWater(lastCounter.getHotWater()+actualPrices.getrHotWater());
		
		counterService.addCounter(counter);
	}
	
	private void generateBill(User user)
	{
		Bills myBill = new Bills();
		Counters counter = counterService.getActiveCounter(user);
		Counters lastCounterValue = counterService.getLastCounters(counter);
		Prices actualPrice = priceService.getActivePrice();
		myBill.setOsoby(Integer.parseInt(counter.getUser().getFlat().getTenantNumber()));
		myBill.setStatus(1);
		myBill.setCurrentValue(counter.getCurrent()-lastCounterValue.getCurrent());
		myBill.setWaterValue(counter.getWater()-lastCounterValue.getWater());
		myBill.setHotWaterValue(counter.getHotWater()-lastCounterValue.getHotWater());
		myBill.setGasValue(counter.getGas()-lastCounterValue.getGas());
		myBill.setEnergyValue(counter.getEnergy()-lastCounterValue.getEnergy());
		myBill.setWater(zaokraglij(actualPrice.getWater()*myBill.getWaterValue()));
		myBill.setHotWater(zaokraglij(actualPrice.getHotWater()*myBill.getHotWaterValue()));
		myBill.setCurrent(zaokraglij(actualPrice.getCurrent()*myBill.getCurrentValue()));
		myBill.setGas(zaokraglij(actualPrice.getGas()*myBill.getGasValue()));
		myBill.setEnergy(zaokraglij(actualPrice.getEnergy()*myBill.getEnergyValue()));
		myBill.setIntercom(zaokraglij(myBill.getOsoby()*actualPrice.getIntercom()));
		myBill.setTrash(zaokraglij(actualPrice.getTrash()*myBill.getOsoby()));
		myBill.setSewage(zaokraglij(actualPrice.getSewage()*myBill.getWaterValue()));
		myBill.setOther(zaokraglij(actualPrice.getOther()*myBill.getOsoby()));
		myBill.setCost(zaokraglij(myBill.getCurrent()+myBill.getEnergy()+myBill.getGas()+myBill.getIntercom()+myBill.getOther()+myBill.getSewage()+myBill.getTrash()+myBill.getWater()));
		myBill.setCounters(counter);
		myBill.setModDate(new Date());
		billService.add(myBill);
		SendingMail mailSend = new SendingMail();
		mailSend.createBill(myBill, PdfCreator.Generate(myBill,actualPrice), counter.getUser().getEmail());
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
