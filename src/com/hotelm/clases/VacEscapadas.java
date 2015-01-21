package com.hotelm.clases;

import android.graphics.drawable.Drawable;

public class VacEscapadas 
{
	Drawable photo;
	String nameHotel;
	String category;
	String zone;
	String description;
	String price;
	String numberAdulto;
	String numberNights;
	boolean imgOk;
	
	public VacEscapadas(Drawable photo,String nameHotel,String category,String zone,String description,String price,String numberAdulto,String numberNights,boolean imgOk)
	{
	   this.nameHotel 	 = nameHotel;
	   this.category 	 =  category;
	   this.zone 		 = zone;
	   this.description  = description;
	   this.price 		 = price;
	   this.photo 		 = photo;
	   this.numberAdulto = numberAdulto;
	   this.numberNights = numberNights;
	   this.imgOk 		 = imgOk;
	  
	}

	public Drawable getPhoto() {
		return photo;
	}

	public String getNameHotel() {
		return nameHotel;
	}

	public String getCategory() {
		return category;
	}

	public String getZone() {
		return zone;
	}

	public String getDescription() {
		return description;
	}

	public String getPrice() {
		return price;
	}

	public String getNumberAdulto() {
		return numberAdulto;
	}

	public String getNumberNights() {
		return numberNights;
	}
	
	public boolean isImgOk() {
		return imgOk;
	}

	public void setImgOk(boolean imgOk) {
		this.imgOk = imgOk;
	}
    
}
