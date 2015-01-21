package com.hotelm.clases;

public class DataHotel 
{
	String  nameZona;
	String  category;
	String  today;
	String  tomorrow;
	String  nextToday;
	String    moneyToday;
	String    moneyTomorrow;
	String    moneyNextMoney;
	
	public DataHotel(String category,String nameZona,String today,String tomorrow,String nextToday,String moneyToday,String moneyTomorrow,String moneyNextMoney)
	{
	   this.nameZona  		= nameZona;
	   this.category  		= category;
	   this.today     		= today;
	   this.tomorrow  		= tomorrow;
	   this.nextToday 		= nextToday;
	   this.moneyToday      = moneyToday;
	   this.moneyTomorrow   = moneyTomorrow;
	   this.moneyNextMoney  = moneyNextMoney;
	  
	}

	public String getNameZona() {
		return nameZona;
	}

	public void setNameZona(String nameZona) {
		this.nameZona = nameZona;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getTomorrow() {
		return tomorrow;
	}

	public void setTomorrow(String tomorrow) {
		this.tomorrow = tomorrow;
	}

	public String getNextToday() {
		return nextToday;
	}

	public void setNextToday(String nextToday) {
		this.nextToday = nextToday;
	}


	public String getMoneyToday() {
		return moneyToday;
	}

	public void setMoneyToday(String moneyToday) {
		this.moneyToday = moneyToday;
	}

	public String getMoneyTomorrow() {
		return moneyTomorrow;
	}

	public void setMoneyTomorrow(String moneyTomorrow) {
		this.moneyTomorrow = moneyTomorrow;
	}

	public String getMoneyNextMoney() {
		return moneyNextMoney;
	}

	public void setMoneyNextMoney(String moneyNextMoney) {
		this.moneyNextMoney = moneyNextMoney;
	}
	
	
	

}
