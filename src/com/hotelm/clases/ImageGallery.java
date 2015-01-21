package com.hotelm.clases;

import android.graphics.drawable.Drawable;

public class ImageGallery 
{
    
	protected Drawable photo;
    
    
 
    public ImageGallery(Drawable photo) {
        super();
        this.photo = photo;
        
    }
 
   
    
    public Drawable getPhoto() {
		return photo;
	}

	public void setPhoto(Drawable photo) {
		this.photo = photo;
	}

	


	
}
