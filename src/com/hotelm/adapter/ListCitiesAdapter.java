package com.hotelm.adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.hotelm.app.R;
import com.hotelm.clases.Cities;


public class ListCitiesAdapter extends ArrayAdapter<Cities> 
{
	
	Activity context;
	private ArrayList<Cities> datos;
	
	public ListCitiesAdapter(Activity context,int textViewResourceId, ArrayList<Cities> datosLista) 
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
	public Cities getItem(int position) {
        return datos.get(position);
    }

    
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View item = convertView;
		ViewHolder holder;
		
		Cities cities = getItem(position);
		
		if(item == null)
		{
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.list_item_cities, null);
			
			holder = new ViewHolder();
			holder.cities = (TextView)item.findViewById(R.id.lblListItemCities);
			
			
			item.setTag(holder);
		}
		else // convertView llega informado
		{
			holder = (ViewHolder)item.getTag();
		}
		
		holder.cities.setText(cities.getCities());
		
		
		return(item);
	}
}

	 class ViewHolder 
	{
		TextView cities;
		
	}
			

