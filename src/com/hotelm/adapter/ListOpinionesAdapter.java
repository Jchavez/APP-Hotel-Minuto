package com.hotelm.adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import com.hotelm.app.R;
import com.hotelm.clases.Opiniones;

public class ListOpinionesAdapter extends ArrayAdapter<Opiniones> 
{
	
	Activity context;
	private ArrayList<Opiniones> datos;
	
	public ListOpinionesAdapter(Activity context,int textViewResourceId, ArrayList<Opiniones> datosLista) 
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
	public Opiniones getItem(int position) {
        return datos.get(position);
    }

    
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View item = convertView;
		ViewOpiniones holder;
		
		Opiniones opiniones = getItem(position);
		
		if(item == null)
		{
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.list_item_opiniones, null);
			
			holder = new ViewOpiniones();
			holder.title = (TextView)item.findViewById(R.id.txtTitle);
			holder.date = (TextView)item.findViewById(R.id.txtDate);
			holder.comments = (TextView)item.findViewById(R.id.txtComentarios);
			
			holder.valoration = (RatingBar)item.findViewById(R.id.ratingBar);
			
			LayerDrawable stars = (LayerDrawable) holder.valoration.getProgressDrawable();
			//stars.getDrawable(2).setColorFilter(Color.rgb(34, 164, 55), Mode.SRC_ATOP);//GREEN_HM
			stars.getDrawable(2).setColorFilter(Color.rgb(55, 97, 173), Mode.SRC_ATOP);//BLUE_HM
			
			item.setTag(holder);
		}
		else // convertView llega informado
		{
			holder = (ViewOpiniones)item.getTag();
		}
		
		
		holder.title.setText("\""+opiniones.getTitle()+"\"");
		holder.date.setText(opiniones.getDate());
		holder.comments.setText(opiniones.getComentario());
		holder.valoration.setRating(opiniones.getRatioBar());
		
		return(item);
	}
}

	 class ViewOpiniones
	{
		TextView title;
		TextView date;
		TextView comments;
		
		RatingBar valoration;
		
		
		
	}
			

