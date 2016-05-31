package com.spoldzielnia.app.model;

public class Pay {
	private int idBill;

	public Pay(int idBill)
	{
		this.idBill=idBill;
	}
	
	public int getIdBill() {
		return idBill;
	}

	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}
}
