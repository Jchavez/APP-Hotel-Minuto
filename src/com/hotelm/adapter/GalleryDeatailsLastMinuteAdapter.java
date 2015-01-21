package com.hotelm.adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.hotelm.app.R;
import com.hotelm.clases.ImageGallery;


public class GalleryDeatailsLastMinuteAdapter extends BaseAdapter {

	protected ArrayList<ImageGallery> items;
	private Activity activity;

  
   
    
    public GalleryDeatailsLastMinuteAdapter(Activity activity, ArrayList<ImageGallery> items) 
    {
		 this.activity = activity;
         this.items = items;
     }

    @Override
	public int getCount() {
        return items.size();
    }

    @Override
	public Object getItem(int position) {
        return items.get(position);
    }

    @Override
	public long getItemId(int position) {
		return 0;
	}
    
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	
    	View v = convertView;
 
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.cell_gallery_details_last_minute, null);
        }
        
        ImageGallery movie = items.get(position);
        
        ImageView photo = (ImageView) v.findViewById(R.id.imageCellHomeSerie);
        photo.setImageDrawable(movie.getPhoto());
        
        
        
        return v;
    	
    }

	
    	
    

}
