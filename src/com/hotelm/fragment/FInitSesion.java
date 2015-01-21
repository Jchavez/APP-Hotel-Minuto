package com.hotelm.fragment;

import java.util.ArrayList;
import com.hotelm.app.R;
import com.hotelm.app.RegisterActivity;
import com.hotelm.app.RegisterExpressActivity;
import com.hotelm.adapter.ListVacEscapAdapter;
import com.hotelm.clases.VacEscapadas;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
 
public class FInitSesion extends Fragment 
{
	LinearLayout llRegistrate,llRegistExpress;
	ListView lVacEscap;
	ListVacEscapAdapter adaptador;
	public ListView mDrawerList;
	public DrawerLayout mDrawerLayout;
	ArrayList<VacEscapadas> aVacEscap = new ArrayList<VacEscapadas>();
	
    @Override
	public void onActivityCreated(Bundle savedInstanceState) 
    {
		super.onActivityCreated(savedInstanceState);
		getActivity().getActionBar().setIcon(R.drawable.logo);
		getActivity().getActionBar().setTitle("Iniciar sesión");
		//mDrawerList.setItemChecked(101, true);
		
		llRegistrate  		= (LinearLayout)getActivity().findViewById(R.id.llRegistrate);
		llRegistExpress  	= (LinearLayout)getActivity().findViewById(R.id.llRegistExpress);
		
		llRegistrate.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View v) 
	        {
	        	Intent i = new Intent(getActivity(),RegisterActivity.class);
				startActivity(i);
	        }
	    });
		
		llRegistExpress.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View v) 
	        {
	        	Intent i = new Intent(getActivity(),RegisterExpressActivity.class);
				startActivity(i);
	        }
	    });
		
	}

	public static Fragment newInstance(Context context) {
    	FInitSesion f = new FInitSesion();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.init_session, null);
        return root;
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.last_minute, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) getActivity().findViewById(R.id.left_drawer);
		setHasOptionsMenu(true);
		
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		
		menu.findItem(R.id.call).setVisible(true);
		//menu.findItem(R.id.geo).setVisible(false);
		menu.findItem(R.id.action_websearch).setVisible(true);
		
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        menu.findItem(R.id.call).setVisible(!drawerOpen);
        
        super.onPrepareOptionsMenu(menu);
	}

 
}