package com.hotelm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hotelm.app.R;

public class MenuListAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	String[] mTitle;
	String[] mSubTitle;
	int[] mIcon;
	LayoutInflater inflater;

	public MenuListAdapter(Context context, String[] title, String[] subtitle,
	        int[] icon) {
	    this.context = context;
	    this.mTitle = title;
	    this.mSubTitle = subtitle;
	    this.mIcon = icon;

	    inflater = (LayoutInflater) context
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getViewTypeCount() {
	    return super.getViewTypeCount();
	}

	@Override
	public int getItemViewType(int position) {
	    return super.getItemViewType(position);
	}

	@Override
	public int getCount() {
	    return mTitle.length;
	}

	@Override
	public Object getItem(int position) {
	    return mTitle[position];
	}

	@Override
	public long getItemId(int position) {
	    return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	    // Declare Variables
	    TextView txtTitle;
	    ImageView imgIcon;

	    View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);

	    txtTitle = (TextView) itemView.findViewById(R.id.title);// Locate the TextViews in drawer_list_item.xml
	    imgIcon = (ImageView) itemView.findViewById(R.id.icon); // Locate the ImageView in drawer_list_item.xml

	    txtTitle.setText(mTitle[position]);// Set the results into TextViews
	    imgIcon.setImageResource(mIcon[position]);// Set the results into ImageView

	    return itemView;
	}

	
	
}
