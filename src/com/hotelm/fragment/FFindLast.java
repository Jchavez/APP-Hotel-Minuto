package com.hotelm.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.hotelm.app.R;
import com.hotelm.app.ViewLastMinuteActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.ExpandableListView.OnChildClickListener;
import com.hotelm.adapter.ExpandableCityAdapter;
import com.hotelm.adapter.ExpandableCountryAdapter;
import com.hotelm.utils.UserFunctions;
 
public class FFindLast extends Fragment 
{
	
	ExpandableCountryAdapter expAdapterCountry;
	ExpandableCityAdapter expAdapterCity;
	ExpandableListView expListCountry,expListCity;
	LinearLayout llFindMinuto,llFindGen,llVacPac;
	
	List<String> listDataHeaderCountry;
	HashMap<String, List<String>> listDataChildCountry;
	
	List<String> listDataHeaderCity;
	HashMap<String, List<String>> listDataChildCity;
	
	public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    
    String strCountry,strCity,strOneZoneCity = null;
    ArrayList<String > arrayZonesCity;
    
    int numberGruops = 0;
    UserFunctions userFunction = new UserFunctions();
    
    String[] aStrCity_A   = userFunction.getCityA();
	String[] aStrCity_B   = userFunction.getCityB();
	String[] aStrCity_C   = userFunction.getCityC();
	String[] aStrCity_FR  = userFunction.getCityFR();
	String[] aStrCity_ALE = userFunction.getCityALE();

	Bundle parametros;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
		//mDrawerList.setItemChecked(202, true);
		getActivity().getActionBar().setIcon(R.drawable.logo);
		getActivity().getActionBar().setTitle("ÚLTIMO MINUTO");
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//IMPIDE ROTAR PANTALLA
		
		llFindGen = (LinearLayout)getActivity().findViewById(R.id.findGeneral);
		llFindGen.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	    	public void onClick(View v) 
	        {	
	    		
	    		FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
	    	    tx.replace(R.id.content_frame, Fragment.instantiate(getActivity().getApplicationContext(),"com.hotelm.fragment.FGeneral"));
	    	    tx.commit();
	    		
	    		getActivity().getActionBar().setTitle("BUSCADOR GENERAL");
	    			
	    	}
	    	
		});
		
		llVacPac = (LinearLayout)getActivity().findViewById(R.id.findPVacacionales);
		llVacPac.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	    	public void onClick(View v) 
	        {	
	    		
	    		FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
	    	    tx.replace(R.id.content_frame, Fragment.instantiate(getActivity().getApplicationContext(),"com.hotelm.fragment.FVacEscapada"));
	    	    tx.commit();
	    		
	    		getActivity().getActionBar().setTitle("VACACIONES Y ESCAPADAS");
	    			
	    	}
	    	
		});
		
		expListCountry = (ExpandableListView) getActivity().findViewById(R.id.lvExpCountry);// get the listview
		expListCountry.setGroupIndicator(null);
		
		setListDataCountry("ESPAÑA");// preparing list data
		
		expAdapterCountry = new ExpandableCountryAdapter(getActivity(), listDataHeaderCountry, listDataChildCountry,0);
		expListCountry.setAdapter(expAdapterCountry);// setting list adapter
		
		expListCountry.setOnChildClickListener(new OnChildClickListener() // Listview on child click listener
		{ 
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
				
				strCountry = listDataChildCountry.get(listDataHeaderCountry.get(groupPosition)).get(childPosition);
				
				setListDataCountry(strCountry);
				setListDataCity(strCountry);
				
				expAdapterCity = new ExpandableCityAdapter(getActivity(), listDataHeaderCity, listDataChildCity);
				expListCity.setAdapter(expAdapterCity);
				
				expAdapterCountry = new ExpandableCountryAdapter(getActivity(), listDataHeaderCountry, listDataChildCountry,childPosition);
				expListCountry.setAdapter(expAdapterCountry);
				
				expListCountry.collapseGroup(0);
				
				return false;
			}
		});
		
		setListDataCity("ESPAÑA");
		
		expListCity = (ExpandableListView) getActivity().findViewById(R.id.lvExpCity);// get the listview
		expListCity.setGroupIndicator(null);
		
		expAdapterCity = new ExpandableCityAdapter(getActivity(), listDataHeaderCity, listDataChildCity);
		expListCity.setAdapter(expAdapterCity);
		
		expListCity.setOnGroupClickListener(new OnGroupClickListener() 
		{
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,int groupPosition, long id) 
			{	
				
				boolean aEmpty= listDataChildCity.get(listDataHeaderCity.get(groupPosition)).isEmpty();
				strCity = listDataHeaderCity.get(groupPosition);
				arrayZonesCity = new ArrayList<String>();
				
				if(aEmpty) //Gruop has not elements
				{
				    parametros = new Bundle();
			        parametros.putBoolean("aEmpty", aEmpty);
			        parametros.putString("strCity", strCity);
			        parametros.putStringArrayList("arrayZonesCity", arrayZonesCity);
			        Intent i = new Intent(getActivity().getBaseContext(),ViewLastMinuteActivity.class);
			        i.putExtras(parametros);
					startActivity(i);
				}
				return false;
			}
		});
		
		expListCity.setOnChildClickListener(new OnChildClickListener() // Listview on child click listener
		{ 
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
				
				arrayZonesCity = new ArrayList<String>();
				strOneZoneCity = listDataChildCity.get(listDataHeaderCity.get(groupPosition)).get(childPosition);
				strCity = listDataHeaderCity.get(groupPosition);
				boolean aEmpty= listDataChildCity.get(listDataHeaderCity.get(groupPosition)).isEmpty();
				List<String> listZoneCity = listDataChildCity.get(listDataHeaderCity.get(groupPosition));
				
				for(int i = 0;i<listZoneCity.size();i++)
				{
					String names[]=listZoneCity.toArray(new String[listZoneCity.size()]);
					arrayZonesCity.add(names[i]);
				}
				
				parametros = new Bundle();
				parametros.putInt("childPosition", childPosition);
				parametros.putBoolean("aEmpty", aEmpty);
				parametros.putString("strCity", strCity);
				parametros.putString("strOneZoneCity", strOneZoneCity);
				parametros.putStringArrayList("arrayZonesCity", arrayZonesCity);
				Intent i = new Intent(getActivity().getBaseContext(),ViewLastMinuteActivity.class);
				i.putExtras(parametros);
				startActivity(i);
				
				
				
				return false;
			}
		});
		
	}
	
	private void setListDataCountry(String country) { 
		 
		listDataHeaderCountry = new ArrayList<String>();
		listDataChildCountry = new HashMap<String, List<String>>();

		List<String> aCountry = new ArrayList<String>();
		aCountry.add("ESPAÑA");
		aCountry.add("ALEMANIA");
		aCountry.add("FRANCIA");
		
		listDataHeaderCountry.add(country);
		listDataChildCountry.put(listDataHeaderCountry.get(0), aCountry);
		
	}
	
	private void setListDataCity(String country) { 
		 
		listDataHeaderCity = new ArrayList<String>();
		listDataChildCity = new HashMap<String, List<String>>();
		
		if(country.equals("ESPAÑA")){
			setListCityESP();
		}else if (country.equals("ALEMANIA")){
			setListCityALE();
		}else if (country.equals("FRANCIA")){
			setListCityFR();
		}
		numberGruops = 0;
	}
	
	private void setListCityESP()
	{
		for(int nGruop_A = 0; nGruop_A < aStrCity_A.length;nGruop_A++)
		{	
			List<String> aVille_A = new ArrayList<String>();
			String gen = aStrCity_A[nGruop_A];
			listDataHeaderCity.add(gen);
			listDataChildCity.put(listDataHeaderCity.get(nGruop_A), aVille_A);
			numberGruops ++;
		}
		
		List<String> aVille_BAR = new ArrayList<String>();
		aVille_BAR.add("BARCELONA - AEROPUERTO");
		aVille_BAR.add("BARCELONA - DIAGONAL MAR");
		aVille_BAR.add("BARCELONA - PLAZA CATALUNYA");
		aVille_BAR.add("BARCELONA - RAMBLAS");
		listDataHeaderCity.add("BARCELONA");
		listDataChildCity.put(listDataHeaderCity.get(numberGruops), aVille_BAR);
		
		numberGruops++;
		
		for(int nGruop_B = 0; nGruop_B < aStrCity_B.length;nGruop_B++)
		{	
			List<String> aVille_B = new ArrayList<String>();
			String gen = aStrCity_B[nGruop_B];
			listDataHeaderCity.add(gen);
			listDataChildCity.put(listDataHeaderCity.get(numberGruops), aVille_B);
			numberGruops++;
		}

		List<String> aVille_MAD = new ArrayList<String>();
		aVille_MAD.add("MADRID - AEROPUERTO");
		aVille_MAD.add("MADRID - ARTURO SORIA");
		aVille_MAD.add("MADRID - ATOCHA");
		aVille_MAD.add("MADRID - BARRIO DE SALAMANCA");
		aVille_MAD.add("MADRID - GRAN VÍA");
		aVille_MAD.add("MADRID - SOL");
		listDataHeaderCity.add("MADRID");
		listDataChildCity.put(listDataHeaderCity.get(numberGruops), aVille_MAD);
		
		numberGruops++;
		
		List<String> aVille_MAL = new ArrayList<String>();
		aVille_MAL.add("MÁLAGA - AEROPUERTO");
		aVille_MAL.add("MÁLAGA - ZONA_A");
		aVille_MAL.add("MÁLAGA - ZONA_B");
		aVille_MAL.add("MÁLAGA - ZONA_C");
		listDataHeaderCity.add("MÁLAGA");
		listDataChildCity.put(listDataHeaderCity.get(numberGruops), aVille_MAL);
		
		numberGruops++;
		
		for(int nGruop_C = 0; nGruop_C < aStrCity_C.length;nGruop_C++)
		{	
			List<String> aVille_C = new ArrayList<String>();
			String gen = aStrCity_C[nGruop_C];
			listDataHeaderCity.add(gen);
			listDataChildCity.put(listDataHeaderCity.get(numberGruops), aVille_C);
			numberGruops++;
		}
	}
	
	private void setListCityALE()
	{
		for(int nGruop_ALE = 0; nGruop_ALE < aStrCity_ALE.length;nGruop_ALE++)
		{	
			List<String> aVille_ALE = new ArrayList<String>();
			String gen = aStrCity_ALE[nGruop_ALE];
			listDataHeaderCity.add(gen);
			listDataChildCity.put(listDataHeaderCity.get(nGruop_ALE), aVille_ALE);
			numberGruops ++;
		}
	}
	
	private void setListCityFR()
	{
		for(int nGruop_FR = 0; nGruop_FR < aStrCity_FR.length;nGruop_FR++)
		{	
			List<String> aVille_FR = new ArrayList<String>();
			String gen = aStrCity_FR[nGruop_FR];
			listDataHeaderCity.add(gen);
			listDataChildCity.put(listDataHeaderCity.get(nGruop_FR), aVille_FR);
			numberGruops ++;
		}
		
	}
	
	public static Fragment newInstance(Context context) {
    	FFindLast f = new FFindLast();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_laste_minute, null);
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
		//menu.findItem(R.id.action_websearch).setVisible(true);
		
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        menu.findItem(R.id.call).setVisible(!drawerOpen);
        
        super.onPrepareOptionsMenu(menu);
	}
	
	
}