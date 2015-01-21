package com.hotelm.utils;

import com.hotelm.utils.Item;

public class EntryItem implements Item{

    public final String title;
    private int icono;
    private int id;

    public EntryItem(int id,String title, int icono) 
    {
        this.title = title;
        this.icono = icono;
        this.id = id;
    }

    @Override
    public boolean isSection() {
        return false;
    }
    
    public int getIcono() {
		return icono;
	}
	public void setIcono(int icono) {
		this.icono = icono;
	} 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}