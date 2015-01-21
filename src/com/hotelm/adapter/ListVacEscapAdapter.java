package com.hotelm.adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hotelm.app.R;
import com.hotelm.clases.VacEscapadas;;


public class ListVacEscapAdapter extends ArrayAdapter<VacEscapadas> 
{
	
	Activity context;
	ArrayList<VacEscapadas> datos;
	
	public ListVacEscapAdapter(Activity context,int textViewResourceId, ArrayList<VacEscapadas> datosLista) 
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
	public VacEscapadas getItem(int position) {
        return datos.get(position);
    }

    
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View item = convertView;
		ViewHolderVacEscap holder;
		
		VacEscapadas resultVacEscap = getItem(position);
		
		if(item == null)
		{
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.list_item_vac_escap, null);
			
			holder = new ViewHolderVacEscap();
			
			holder.nameHotelCat     = (TextView)item.findViewById(R.id.txtNameHotel);
			holder.zone			    = (TextView)item.findViewById(R.id.txtZoneVacEscapadas);
			holder.description	    = (TextView)item.findViewById(R.id.txtDescrVacEscap);
			holder.price		    = (TextView)item.findViewById(R.id.priceVacEscap);
			holder.numberAdultos    = (TextView)item.findViewById(R.id.txtNumberAdulto);
			holder.numberNights		= (TextView)item.findViewById(R.id.txtNumberNight);
			holder.imgVacEscapadas  = (ImageView)item.findViewById(R.id.imgVacEscap);
			holder.imgOk  			= (ImageView)item.findViewById(R.id.imgOk);
			
			
			item.setTag(holder);
		}
		else // convertView llega informado
		{
			holder = (ViewHolderVacEscap)item.getTag();
		}
		
		holder.nameHotelCat.setText(resultVacEscap.getNameHotel()+" "+resultVacEscap.getCategory());
		holder.zone.setText(resultVacEscap.getZone());
		holder.description.setText(resultVacEscap.getDescription());
		holder.price.setText(resultVacEscap.getPrice());
		holder.numberAdultos.setText(resultVacEscap.getNumberAdulto());
		holder.numberNights.setText(resultVacEscap.getNumberNights());
		holder.imgVacEscapadas.setImageDrawable(resultVacEscap.getPhoto());
		
		if (resultVacEscap.isImgOk() == true)
			holder.imgOk.setVisibility(View.VISIBLE);
		
		return(item);
	}
}

	 class ViewHolderVacEscap 
	{
		TextView  nameHotelCat;
		TextView  zone;
		TextView  description;
		TextView  price;
		TextView  numberNights;
		TextView  numberAdultos;
		ImageView imgVacEscapadas;
		ImageView imgOk;
		
	}
			

