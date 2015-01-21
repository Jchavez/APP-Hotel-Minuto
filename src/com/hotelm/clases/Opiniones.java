package com.hotelm.clases;

public class Opiniones 
{
	String  title;
	String  date;
	String  comentario;
	int		ratioBar;
	
	public Opiniones(String title,String date,String comentario,int ratioBar)
	{
	   this.title  		= title;
	   this.date  		= date;
	   this.comentario  = comentario;
	   this.ratioBar  	= ratioBar;
	  
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public int getRatioBar() {
		return ratioBar;
	}


	public void setRatioBar(int ratioBar) {
		this.ratioBar = ratioBar;
	}


	
	
	

}
