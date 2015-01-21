package com.hotelm.clases;

public class ResultGen 
{
	String category;
	String address;
	String description;
	String price;
	
	public ResultGen(String nameHotel,String category,String address,String description,String price)
	{
	   this.nameHotel = nameHotel;
	   this.category =  category;
	   this.address = address;
	   this.description = description;
	   this.price = price;
	  
	}

	String nameHotel;
    public String getNameHotel() {
		return nameHotel;
	}

	public void setNameHotel(String nameHotel) {
		this.nameHotel = nameHotel;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
	

}
