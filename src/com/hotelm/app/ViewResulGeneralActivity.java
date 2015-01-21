package com.hotelm.app;

import java.util.ArrayList;
import com.hotelm.adapter.ListResultGenAdapter;
import com.hotelm.clases.ResultGen;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ActionBar;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
public class ViewResulGeneralActivity extends Activity 
{
	
	ActionBar actionBar;
	ListView lResultGen;
    ArrayList<ResultGen> aResultGn = new ArrayList<ResultGen>();
    ListResultGenAdapter adaptador;
    Bundle bundle;
    String strMonth,type,info,srtMonthExit,strNumHabs,strNumAdultos,strChildren,strAgeOne,strAgeTwo,strDestino;
    int dayStart,monthStart,yearStart;
	int dayEnd,monthEnd,yearEnd;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_result_find_general);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//IMPIDE ROTAR PANTALLA
		
		setActionbarBack();
		dataResultGn(10);
		adaptador = new ListResultGenAdapter(ViewResulGeneralActivity.this,R.layout.list_item_result_gen,aResultGn);
		lResultGen = (ListView)findViewById(R.id.lViewResultGen);
		lResultGen.setAdapter(adaptador);
		
		getValueResult();
		
	    TextView txtVDestino = (TextView)findViewById(R.id.txtCiudadBusq);
	    TextView txtVDates 	 = (TextView)findViewById(R.id.txtDates);
	    TextView txtVHabs 	 = (TextView)findViewById(R.id.txtResulHab);
	    TextView txtVAdultos = (TextView)findViewById(R.id.txtResulAdultos);
	   
	    txtVDestino.setText(strDestino);
	    txtVDates.setText("Del "+dayStart+"."+strMonth+"."+yearStart+" al "+dayEnd+"."+srtMonthExit+"."+yearEnd);
	    txtVHabs.setText("// "+strNumHabs+" HABITACIONES");
	    txtVAdultos.setText("// "+strNumAdultos+" ADULTOS");
		
		lResultGen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		       
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				Intent i = new Intent(ViewResulGeneralActivity.this,ViewDetailsGeneralActivity.class);
				i.putExtra("destino", strDestino);
	    		i.putExtra("nHabs", strNumHabs);
	    		i.putExtra("nAdultos", strNumAdultos);
	    		i.putExtra("nChildren", strChildren);
	    		i.putExtra("ageNinoOne", strAgeOne);
	    		i.putExtra("ageNinoTwo", strAgeTwo);
	    		i.putExtra("dayStart", dayStart);
	    		i.putExtra("monthStart", strMonth);
	    		i.putExtra("yearStart", yearStart);
	    		i.putExtra("dayEnd", dayEnd);
	    		i.putExtra("monthEnd", srtMonthExit);
	    		i.putExtra("yearEnd", yearEnd);
				startActivity(i);
			}
	    });
		
		
	}

	private void getValueResult() 
	{
		bundle 		    = getIntent().getExtras();
	    strDestino	    = bundle.getString("destino");
	    strNumHabs	    = bundle.getString("nHabs");
	    strNumAdultos	= bundle.getString("nAdultos");
	    strChildren		= bundle.getString("nChildren");
	    strAgeOne		= bundle.getString("ageNinoOne");
	    strAgeTwo		= bundle.getString("ageNinoTwo");
	    dayStart		= bundle.getInt("dayStart");
	    strMonth		= bundle.getString("monthStart");
	    yearStart		= bundle.getInt("yearStart");
	    dayEnd			= bundle.getInt("dayEnd");
	    srtMonthExit	= bundle.getString("monthEnd");
	    yearEnd			= bundle.getInt("yearEnd");
	}

	public void setActionbarBack()
	{
		 actionBar = getActionBar();
	     actionBar.setHomeButtonEnabled(true);
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.setTitle("RESULTADOS BÚ	SQUEDA GENERAL");
	    
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
	
	public void dataResultGn(int count)
	{
		for(int i=1 ; i<=count;i++)
		{
			
			aResultGn.add(new ResultGen("Hotel Miguel Ángel", "4*", "Direccion:"+""+i, "Servicios", "35€"));
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	

}
