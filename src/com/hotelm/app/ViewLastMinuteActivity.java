package com.hotelm.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import com.hotelm.adapter.ExpandableCountryAdapter;
import com.hotelm.adapter.ListCityDataAdapter;
import com.hotelm.clases.DataHotel;


public class ViewLastMinuteActivity extends Activity 
{
	ActionBar actionBar;
	ExpandableCountryAdapter expAdapterCity;
	List<String> listDataHeaderCountry;
	HashMap<String, List<String>> listDataChildCountry;
	
	ExpandableListView expListCity;
	ArrayList<DataHotel> aCity = new ArrayList<DataHotel>();
	
	ListCityDataAdapter adaptador;
	ListView lCity;
	
	String zone,category = null;
	String strCity,strOneZoneCity=null;
	Boolean aEmpty;
	int childPosition;
	ArrayList<String> arrayZonesCity = new ArrayList<String>();
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_last_minute_city);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//IMPIDE ROTAR PANTALLA
		
		setActionbarBack();
		
		Bundle parametros = getIntent().getExtras();
		childPosition 	  = parametros.getInt("childPosition");
        aEmpty 			  = parametros.getBoolean("aEmpty"); 
        strCity 		  = parametros.getString("strCity");
        strOneZoneCity    = parametros.getString("strOneZoneCity");
        arrayZonesCity 	  = parametros.getStringArrayList("arrayZonesCity");
        
		expListCity = (ExpandableListView) findViewById(R.id.lvExpCity);// get the listview
		expListCity.setGroupIndicator(null);
		
		setListCity();// preparing list data
		
		expAdapterCity = new ExpandableCountryAdapter(ViewLastMinuteActivity.this, listDataHeaderCountry, listDataChildCountry,childPosition);
		expListCity.setAdapter(expAdapterCity);// setting list adapter
		
		expListCity.setOnChildClickListener(new OnChildClickListener() // Listview on child click listener
		{ 
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
				
				aCity = new ArrayList<DataHotel>();
				strOneZoneCity = listDataChildCountry.get(listDataHeaderCountry.get(groupPosition)).get(childPosition);
				
				setListZoneCity();
				
				adaptador = new ListCityDataAdapter(ViewLastMinuteActivity.this,0,aCity);
				lCity.setAdapter(adaptador);
				
				expAdapterCity = new ExpandableCountryAdapter(ViewLastMinuteActivity.this, listDataHeaderCountry, listDataChildCountry,childPosition);
				expListCity.setAdapter(expAdapterCity);// setting list adapter
				
				expListCity.collapseGroup(0);
				
				return false;
			}
		});
		
		setListZoneCity();
		
		lCity = (ListView)findViewById(R.id.lView);
		adaptador = new ListCityDataAdapter(ViewLastMinuteActivity.this,0,aCity);
		lCity.setAdapter(adaptador);
		
		lCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	       
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				category = ((DataHotel)parent.getAdapter().getItem(position)).getCategory();
				zone	=  ((DataHotel)parent.getAdapter().getItem(position)).getNameZona();
				Intent i = new Intent(ViewLastMinuteActivity.this,ViewDetailsLastActivity.class);
				i.putExtra("category", category);
		    	i.putExtra("zone", zone);
				startActivity(i);
			}
	    });
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		expListCity.expandGroup(0);
	}

	private void setListCity() { /*Preparing the list data*/
		 
		listDataHeaderCountry = new ArrayList<String>();
		listDataChildCountry = new HashMap<String, List<String>>();

		
		if(aEmpty) // has not ELEMENTS
		{
			// Adding child data
			listDataHeaderCountry.add(strCity);
			// Adding child data
			List<String> aZoneEmpty = new ArrayList<String>();
			listDataChildCountry.put(listDataHeaderCountry.get(0), aZoneEmpty); // Header, Child data
			
		}else
		{
			// Adding child data
			listDataHeaderCountry.add(strCity);
			// Adding child data
			listDataChildCountry.put(listDataHeaderCountry.get(0), arrayZonesCity); // Header, Child data
		}
		
		
		
	}

	private void setListZoneCity() 
	{
		aCity.add(new DataHotel("4*", "Zona Barrio Prosperidad", "Hoy", "Mañana", "Miércoles","32", "25€", "35€"));
		aCity.add(new DataHotel("3*", "Zona Av. América - Barajas", "Hoy", "Mañana", "Jueves", "30€", "45€", "31€"));
		aCity.add(new DataHotel("2*", "Zona Centro Palacio Real", "Hoy", "Mañana", "Martes", "33€", "39€", "41€"));
		aCity.add(new DataHotel("5*", "Zona Latina", "Hoy", "Mañana", "Viernes", "35€", "23€", "43€"));
		aCity.add(new DataHotel("3*", "Zona R.Dario", "Hoy", "Mañana", "Lunes", "14€", "28€", "67€"));
		aCity.add(new DataHotel("4*", "Zona Aluche", "Hoy", "Mañana", "Sábado", "42€", "35€", "45€"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setActionbarBack()
	{
		 actionBar = getActionBar();
	     actionBar.setHomeButtonEnabled(true);
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.setTitle("RESULTADOS ÚLTIMO MINUTO");
	    
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	onBackPressed();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	
}
