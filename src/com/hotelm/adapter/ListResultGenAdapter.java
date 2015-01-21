package com.hotelm.adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.hotelm.app.R;
import com.hotelm.clases.ResultGen;


public class ListResultGenAdapter extends ArrayAdapter<ResultGen> 
{
	
	Activity context;
	ArrayList<ResultGen> datos;
	
	public ListResultGenAdapter(Activity context,int textViewResourceId, ArrayList<ResultGen> datosLista) 
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
	public ResultGen getItem(int position) {
        return datos.get(position);
    }

    
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View item = convertView;
		ViewHolderResult holder;
		
		ResultGen resultGen = getItem(position);
		
		if(item == null)
		{
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.list_item_result_gen, null);
			
			holder = new ViewHolderResult();
			holder.nameHotel = (TextView)item.findViewById(R.id.txtResultNameHotel);
			holder.nameCat = (TextView)item.findViewById(R.id.txtResultCat);
			holder.address		= (TextView)item.findViewById(R.id.txtAddress);
			
			holder.price		= (TextView)item.findViewById(R.id.price);
			
			
			item.setTag(holder);
		}
		else // convertView llega informado
		{
			holder = (ViewHolderResult)item.getTag();
		}
		
		holder.nameHotel.setText(resultGen.getNameHotel());
		holder.nameCat.setText(resultGen.getCategory());
		holder.address.setText(resultGen.getAddress());
		
		holder.price.setText(resultGen.getPrice());
		
		
		return(item);
	}
}

	 class ViewHolderResult 
	{
		TextView nameHotel;
		TextView nameCat;
		TextView address;
		
		TextView price;
		
	}
			

