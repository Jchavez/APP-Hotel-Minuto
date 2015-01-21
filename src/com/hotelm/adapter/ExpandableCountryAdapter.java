package com.hotelm.adapter;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hotelm.app.R;

public class ExpandableCountryAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;
	public int _selectChild;
	
	public int imageResourceId;

	public ExpandableCountryAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<String>> listChildData,int _selectChild) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
		this._selectChild = _selectChild;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_child_country, null);
		}

		TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
		
		if(getChildId(groupPosition, childPosition) == _selectChild  )
		{
			txtListChild.setTypeface(null,Typeface.BOLD);
			
		}else
		{
			txtListChild.setTypeface(null,Typeface.NORMAL);
		}
		
		txtListChild.setText(childText);
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) 
	{
		ImageView image = null;
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_country, null);
		}

		TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);
		
		image = (ImageView) convertView.findViewById(R.id.expandableIcon);
		
		/*
		 * if this is not the first group (future travel) show the arrow image
		 * and change state if necessary
		 */
		if((getChildrenCount(groupPosition)) == 0){
			
			image.setVisibility(View.INVISIBLE);
			
		} else {
			
				
			imageResourceId = isExpanded ? R.drawable.ic_action_collapse_country : R.drawable.ic_action_expand_country;
			image.setImageResource(imageResourceId);
			image.setVisibility(View.VISIBLE);
			
		}
		
		return convertView;
		
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
