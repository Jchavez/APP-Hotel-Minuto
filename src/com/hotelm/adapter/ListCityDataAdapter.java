package com.hotelm.adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hotelm.app.R;
import com.hotelm.clases.DataHotel;


public class ListCityDataAdapter extends ArrayAdapter<DataHotel> 
{
	
	Activity context;
	private ArrayList<DataHotel> datos;
	
	public ListCityDataAdapter(Activity context,int textViewResourceId, ArrayList<DataHotel> datosLista) 
	{
		super(context, textViewResourceId, datosLista);
		this.context = context;
		this.datos = datosLista;
			
	}
	
	@Override
    public int getCount() {
    	return datos.size();

    }

    @Override
	public DataHotel getItem(int position) {
        return datos.get(position);
    }

    
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View item = convertView;
		ViewH holder;
		
		DataHotel cities = getItem(position);
		
		if(item == null)
		{
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.list_item_data_hotel, null);
			
			holder = new ViewH();
			holder.nameZona = (TextView)item.findViewById(R.id.textZone);
			holder.category = (TextView)item.findViewById(R.id.textCategory);
			
			holder.txtDay = (TextView)item.findViewById(R.id.txtToday);
			holder.moneyDay = (TextView)item.findViewById(R.id.moneyToday);
			
			holder.txtTomorrow = (TextView)item.findViewById(R.id.txtTomorrow);
			holder.moneyTomorrow = (TextView)item.findViewById(R.id.moneyTomorrow);
			
			holder.txtNextDay = (TextView)item.findViewById(R.id.txtNextDay);
			holder.moneyNextDay = (TextView)item.findViewById(R.id.moneyNextToday);
			
			holder.lToday = (LinearLayout)item.findViewById(R.id.todayH);
			holder.lTomorrow = (LinearLayout)item.findViewById(R.id.tomorrowH);
			holder.lNextDay = (LinearLayout)item.findViewById(R.id.todayNextH);
			
			item.setTag(holder);
		}
		else // convertView llega informado
		{
			holder = (ViewH)item.getTag();
		}
		
		
		holder.category.setText("Hotel "+cities.getCategory());
		holder.nameZona.setText("- "+cities.getNameZona());
		
		holder.txtDay.setText(cities.getToday());
		holder.moneyDay.setText(""+cities.getMoneyToday());
			
		holder.txtTomorrow.setText(cities.getTomorrow());
		holder.moneyTomorrow.setText(""+cities.getMoneyTomorrow());
			
		holder.txtNextDay.setText(cities.getNextToday());
		holder.moneyNextDay.setText(""+cities.getMoneyNextMoney());
			
		return(item);
	}
}

	 class ViewH
	{
		TextView nameZona;
		TextView category;
		
		TextView txtDay;
		TextView moneyDay;
		
		TextView txtTomorrow;
		TextView moneyTomorrow;
		
		TextView txtNextDay;
		TextView moneyNextDay;
		
		LinearLayout lToday;
		LinearLayout lTomorrow;
		LinearLayout lNextDay;
		
	}
			

