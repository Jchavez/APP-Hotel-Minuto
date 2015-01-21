package com.hotelm.fragment;

import java.util.ArrayList;

import com.hotelm.app.R;
import com.hotelm.app.ViewDetailsVacActivity;
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
import android.widget.AdapterView;
import android.widget.ListView;
 
public class FVacEscapada extends Fragment 
{
	
	ListView lVacEscap;
	ListVacEscapAdapter adaptador;
	public ListView mDrawerList;
	public DrawerLayout mDrawerLayout;
	ArrayList<VacEscapadas> aVacEscap = new ArrayList<VacEscapadas>();
	String strPrice,strHotel,strCategory,strAdultos,strNights,strZone;
	
    @Override
	public void onActivityCreated(Bundle savedInstanceState) 
    {
		super.onActivityCreated(savedInstanceState);
		getActivity().getActionBar().setIcon(R.drawable.logo);
		getActivity().getActionBar().setTitle("VACACIONES Y ESCAPADAS");
		
		loadDataVacEscapadas(3);
		
		lVacEscap = (ListView)getActivity().findViewById(R.id.lViewResultVacEscapadas);
		adaptador = new ListVacEscapAdapter(getActivity(), R.layout.list_item_vac_escap, aVacEscap);
		lVacEscap.setAdapter(adaptador);
		
		lVacEscap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		       
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				Intent i 	= new Intent(getActivity(),ViewDetailsVacActivity.class);
				strPrice 	= ((VacEscapadas)parent.getAdapter().getItem(position)).getPrice();
				strHotel 	= ((VacEscapadas)parent.getAdapter().getItem(position)).getNameHotel();
				strCategory	= ((VacEscapadas)parent.getAdapter().getItem(position)).getCategory();
				strAdultos	= ((VacEscapadas)parent.getAdapter().getItem(position)).getNumberAdulto();
				strNights	= ((VacEscapadas)parent.getAdapter().getItem(position)).getNumberNights();
				strZone		= ((VacEscapadas)parent.getAdapter().getItem(position)).getZone();
				i.putExtra("price", strPrice);
				i.putExtra("hotel", strHotel);
				i.putExtra("category", strCategory);
				i.putExtra("adultos", strAdultos);
				i.putExtra("nights", strNights);
				i.putExtra("zone", strZone);
				startActivity(i);
			}
	    });
	}

	public static Fragment newInstance(Context context) {
    	FVacEscapada f = new FVacEscapada();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_vac_escapadas, null);
        return root;
    }
    
    public void loadDataVacEscapadas(int count)
	{
		for(int i=1 ; i<=count;i++)
		{
			aVacEscap.add(new VacEscapadas(getResources().getDrawable(R.drawable.imgcity), "Hotel Virumbrales", "3*", "ALFAZ DEL PI", "Lorem ipsum dolor sit amet, consectetur adipiscing alit.Nam rhoncus et nist eu ultricies.Ut lacus ante,facilisis molis aliquet,scelerisque ac felis integer.", "45€", "2 Personas", "1 Noche",true));
			aVacEscap.add(new VacEscapadas(getResources().getDrawable(R.drawable.imgcity), "Hotel Museo", "4*", "PASEO DE LA CASTELLANA", "Lorem ipsum dolor sit amet, consectetur adipiscing alit.Nam rhoncus et nist eu ultricies.Ut lacus ante,facilisis molis aliquet,scelerisque ac felis integer.", "90€", "3 Personas", "2 Noches",false));
			aVacEscap.add(new VacEscapadas(getResources().getDrawable(R.drawable.imgcity), "Hotel Estepona", "5*", "Estepona - Ciudad", "Lorem ipsum dolor sit amet, consectetur adipiscing alit.Nam rhoncus et nist eu ultricies.Ut lacus ante,facilisis molis aliquet,scelerisque ac felis integer.", "135€", "4 Personas", "3 Noches",true));
		}
		
		
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
		//menu.findItem(R.id.action_websearch).setVisible(true);
		
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
       // menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        menu.findItem(R.id.call).setVisible(!drawerOpen);
        
        super.onPrepareOptionsMenu(menu);
	}

 
}