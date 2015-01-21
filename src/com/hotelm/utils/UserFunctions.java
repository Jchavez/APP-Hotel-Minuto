package com.hotelm.utils;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class  UserFunctions 
{
	
	String[] aStrCity_A = { "A CORUÑA", "ALICANTE","ALMERÍA","ÁVILA","BADAJOZ"};
	String[] aStrCity_B = { "BILBAO", "BURGOS","CÁCERES","CÁDIZ","CASTELLÓN","CIUDAD REAL","GIJÓN","GRANADA","IBIZA",
							"LAS PALMAS DE GRAN CANARIA","LEÓN","LLEIDA","LOGROÑO"};
	String[] aStrCity_C = { "MARBELLA", "MURCIA","OVIEDO","PALMA DE MALLORCA","PAMPLONA","SALAMANCA","SAN SEBASTIÁN","SANTANDER","SANTIAGO",
							"SEGOVIA","SEVILLA","TARRAGONA","TENERIFE","TOLEDO","VALLADOLID","VIGO","VITORIA","ZAMORA","ZARAGOZA"};
	
	String[] aStrCity_FR = { "BURDEOS", "CANNES","GRENOBLE","LILLE","LYON","MARSELLA","NANTES","NIZA","PARÍS",
			"STRASBOURG","TOULOUSE"};
	
	String[] aStrCity_ALE = { "BERLÍN", "BONN","BREMEN","COLONIA","DORTMUND","DRESDEN","DUSSELDORF","ESSEN","FRANKFURT",
			"HAMBURGO","HANNOVER","LEIPZIG","MAIN","MUNICH","NUREMBERG","STUTTGART"};
	
	@SuppressLint("SimpleDateFormat")
	public String[] checkDate(int dayStart,int monthStart,int yearStart,int dayEnd,int monthEnd,int yearEnd)
	{
		StringBuilder buildStart= new StringBuilder().append(dayStart).append("-").append(monthStart).append("-").append(yearStart).append(" ");
		StringBuilder buildEnd= new StringBuilder().append(dayEnd).append("-").append(monthEnd).append("-").append(yearEnd).append(" ");
		   
		   String strStart = buildStart.toString();
		   String strEnd = buildEnd.toString();
		   
		   SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-mm-yyyy");
		   String[] result = new String[2];;
		   
		   try 
		   {
			   Date startDate1=(Date) sdf.parse(strStart);
			   Date startDate2=(Date) sdf.parse(strEnd );
			   
			   if(startDate1.after(startDate2)) // start date is after current date
			   {  
				   result[0] = "FALSE";
				   result[1] = "La fecha de inicio es posterior a la fecha actual";
			   }  
			   else if(startDate2.after(startDate1)) // start date is before current date
			   {  
				   result[0] = "TRUE";
				   result[1] = "La fecha de inicio es antes de la fecha actual";
				   
			   }  
			   else if(startDate1.equals(startDate2)) // both are equal
			   {  
				   result[0] = "FALSE";
				   result[1] = "La fecha de inicio es igual a la fecha actual";
				   
			   }  
			   
			   
		   } catch (ParseException e) 
		   {
			   e.printStackTrace();
		   }
		
		return result;
	}

	
	public String getStringMonth(int month)
    {
		String monthString;
        
		switch (month)
        {
            case 1:  monthString = "ENE";
                     break;
            case 2:  monthString = "FEB";
                     break;
            case 3:  monthString = "MAR";
                     break;
            case 4:  monthString = "ABR";
                     break;
            case 5:  monthString = "MAY";
                     break;
            case 6:  monthString = "JUN";
                     break;
            case 7:  monthString = "JUL";
                     break;
            case 8:  monthString = "AUG";
                     break;
            case 9:  monthString = "SEP";
                     break;
            case 10: monthString = "OCT";
                     break;
            case 11: monthString = "NOV";
                     break;
            case 12: monthString = "DEC";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        
		
		return monthString;
    }

	public Boolean validateFindGeneral(String strNumHabs,String strNumAdultos,String strChildren,String strAgeOne,String strAgeTwo)
	{
		Boolean cierto = true;
		
		
		
		if((strNumHabs.equals("Nº Habs") || strNumAdultos.equals("Nº Adultos") || strChildren.equals("Niños"))) 
		{
			cierto = false;
		}	
		if ((!strChildren.equals("0")) && (strAgeOne.equals("Edad Niño 1") || strAgeTwo.equals("Edad Niño 2")))
		{
			cierto = false;
			
		}
		
		return cierto;
	}
	
	
	
	public String[] getCityA()
	{
		return aStrCity_A;
	}
	
	public String[] getCityB()
	{
		return aStrCity_B;
	}
	
	public String[] getCityC()
	{
		return aStrCity_C;
	}
	
	public String[] getCityFR()
	{
		return aStrCity_FR;
	}
	
	public String[] getCityALE()
	{
		return aStrCity_ALE;
	}
	
}