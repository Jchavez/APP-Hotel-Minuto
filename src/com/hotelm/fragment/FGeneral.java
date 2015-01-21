package com.hotelm.fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.hotelm.app.R;
import com.hotelm.app.ViewResulGeneralActivity;
import com.hotelm.utils.DateFragPicker;
import com.hotelm.utils.UserFunctions;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
 
public class FGeneral extends Fragment implements OnClickListener
{
	public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
	Button btnFind,btnIncAgeOne,btnDecAgeOne,btnIncAgeTwo,btnDecAgeTwo;
	TextView txtAgeOne,txtAgeTwo;
	LinearLayout llFindMinuto,llFindGen,llVacPac,llCalendStart,llCalendExit;
	TextView txtCalendarStart,txtCalendarExit;
	EditText edtDestino;
	UserFunctions userFunction;
	String strMonth,type,info,srtMonthExit,strNumHabs,strNumAdultos,strChildren,strAgeOne,strAgeTwo,strDestino; 
	Calendar calender,calenderExit;
	Boolean cierto;
	Spinner spChildren,spAgeNinoOne,spAgeNinoTwo,spHabs,spAdulto;
	SimpleDateFormat sdf;
	
	int day,month,year;
	int dayStart,monthStart,yearStart;
	int dayEnd,monthEnd,yearEnd;
	
	public static Fragment newInstance(Context context) 
    {
    	FGeneral f = new FGeneral();
    	return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_general, null);
        return root;
    }
    
    @Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		super.onActivityCreated(savedInstanceState);
		getActivity().getActionBar().setIcon(R.drawable.logo);
		getActivity().getActionBar().setTitle("BÚSQUEDA GENERAL");
		
		userFunction 		= new UserFunctions();
		
		llCalendStart 		= (LinearLayout) getActivity().findViewById(R.id.calendStart);
		llCalendExit 		= (LinearLayout) getActivity().findViewById(R.id.calendExit);
		
		txtCalendarStart 	= (TextView)getActivity().findViewById(R.id.txtCalendarStart);
		txtCalendarExit  	= (TextView)getActivity().findViewById(R.id.txtCalendarExit);
		
		edtDestino 			= (EditText) getActivity().findViewById(R.id.edtDestino);
		
		
		llCalendStart.setOnClickListener(this);
		llCalendExit.setOnClickListener(this);
		
		initDateStartExit();
		
		llFindMinuto = (LinearLayout)getActivity().findViewById(R.id.findMinuto);
		llFindMinuto.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	    	public void onClick(View v) 
	        {	
	    		
	    		FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
	    	    tx.replace(R.id.content_frame, Fragment.instantiate(getActivity().getApplicationContext(),"com.hotelm.fragment.FFindLast"));
	    	    tx.commit();
	    		
	    		getActivity().getActionBar().setTitle("ULTIMO MINUTO");
	    			
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
		
		btnFind = (Button)getActivity().findViewById(R.id.btnFindGen);
		btnFind.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	    	public void onClick(View v) 
	        {	
	    		strDestino			= edtDestino.getText().toString();
	    		Intent i = new Intent(getActivity().getBaseContext(),ViewResulGeneralActivity.class);
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
	    		
	    		
	    		String[] checkDate 		= userFunction.checkDate(dayStart, monthStart, yearStart, dayEnd, monthEnd, yearEnd);
	    		String resultCheckDate  = checkDate[0];
	    		String infoCheckDate    = checkDate[1];
	    		
	    		if(strDestino.length() > 0)
	    		{
		    		if(userFunction.validateFindGeneral(strNumHabs, strNumAdultos, strChildren, strAgeOne, strAgeTwo))
		    		{
			    		if(resultCheckDate.equals("TRUE"))
			    		{
			    			startActivity(i);
			    			
			    		}else
			    		{
			    			Toast.makeText(getActivity(),infoCheckDate, Toast.LENGTH_SHORT).show();
			    		}
			    		
		    		}else
		    		{
		    			Toast.makeText(getActivity(),"Algún valor no seleccionado", Toast.LENGTH_SHORT).show();
		    		}
		    		
	    		}else
	    		{
	    			Toast.makeText(getActivity(),"Introduzca destino", Toast.LENGTH_SHORT).show();
	    		}
	    		
	    	}
	    	
		});
		
		spAgeNinoOne = (Spinner) getActivity().findViewById(R.id.spAgeNinoOne);
		ArrayAdapter<CharSequence> adapterSpAgeNinoOne = new ArrayAdapter<CharSequence>(getActivity(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.AgeNinoOne)) 
		{

        	public View getView(int position, View convertView,ViewGroup parent) 
        	{
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setPadding(10, 0, 0, 0);
                ((TextView) v).setTextColor(getResources().getColorStateList(R.color.gray));
                return v;
            }
        	
		};
		
        adapterSpAgeNinoOne.setDropDownViewResource(R.layout.textview_spinner);
        spAgeNinoOne.setAdapter(adapterSpAgeNinoOne);
        
        spAgeNinoTwo = (Spinner) getActivity().findViewById(R.id.spAgeNinoTwo);
		ArrayAdapter<CharSequence> adapterSpAgeNinoTwo = new ArrayAdapter<CharSequence>(getActivity(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.AgeNinoTwo)) 
		{

        	public View getView(int position, View convertView,ViewGroup parent) 
        	{
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setPadding(10, 0, 0, 0);
                ((TextView) v).setTextColor(getResources().getColorStateList(R.color.gray));
                return v;
            }
        	
		};
		
        adapterSpAgeNinoTwo.setDropDownViewResource(R.layout.textview_spinner);
        spAgeNinoTwo.setAdapter(adapterSpAgeNinoTwo);
		
		spHabs = (Spinner) getActivity().findViewById(R.id.spHabs);
		ArrayAdapter<CharSequence> adapterSpHabs = new ArrayAdapter<CharSequence>(getActivity(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.NumeberHabs)) 
		{

        	public View getView(int position, View convertView,ViewGroup parent) 
        	{
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setPadding(10, 0, 0, 0);
                ((TextView) v).setTextColor(getResources().getColorStateList(R.color.gray));
                return v;
            }
        	
		};
		
		adapterSpHabs.setDropDownViewResource(R.layout.textview_spinner);
		spHabs.setAdapter(adapterSpHabs);
		
		spAdulto = (Spinner) getActivity().findViewById(R.id.spAdulto);
		ArrayAdapter<CharSequence> adapterSpAdulto = new ArrayAdapter<CharSequence>(getActivity(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.NumberAdulto)) 
		{

        	public View getView(int position, View convertView,ViewGroup parent) 
        	{
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setPadding(10, 0, 0, 0);
                ((TextView) v).setTextColor(getResources().getColorStateList(R.color.gray));
                return v;
            }
        	
		};
		
        adapterSpAdulto.setDropDownViewResource(R.layout.textview_spinner);
		spAdulto.setAdapter(adapterSpAdulto);
		
		spChildren = (Spinner) getActivity().findViewById(R.id.spChildren);
		ArrayAdapter<CharSequence> adapterSpChildren = new ArrayAdapter<CharSequence>(getActivity(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Children)) 
		{

        	public View getView(int position, View convertView,ViewGroup parent) 
        	{
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setPadding(10, 0, 0, 0);
                ((TextView) v).setTextColor(getResources().getColorStateList(R.color.gray));
                return v;
            }
        	
		};
		
        adapterSpChildren.setDropDownViewResource(R.layout.textview_spinner);
        spChildren.setAdapter(adapterSpChildren);
        
        strChildren = spChildren.getItemAtPosition(0).toString();
        
        spAdulto.setOnItemSelectedListener(onItemSpinner);
        spAgeNinoOne.setOnItemSelectedListener(onItemSpinner);
        spAgeNinoTwo.setOnItemSelectedListener(onItemSpinner);
        spChildren.setOnItemSelectedListener(onItemSpinner);
        spHabs.setOnItemSelectedListener(onItemSpinner);
        
     }
    
    OnItemSelectedListener onItemSpinner=new OnItemSelectedListener() 
    {
    	@Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,long id) 
        {
             switch (parent.getId()) 
             {
                case R.id.spHabs:
                	strNumHabs = parent.getItemAtPosition(position).toString();
                    break;
                case R.id.spAdulto:
                	strNumAdultos = parent.getItemAtPosition(position).toString();
                    break;
                case R.id.spChildren:
                	strChildren = parent.getItemAtPosition(position).toString();
                	
                	if(strChildren.equals("0") || strChildren.equals("Niños"))
                	{
                		spAgeNinoOne.setClickable(false);spAgeNinoTwo.setClickable(false);
                		
                	}else if(strChildren.equals("1")) 
                	{
                		spAgeNinoOne.setClickable(true);
                		
                	}else if(strChildren.equals("2"))
                	{
                		spAgeNinoOne.setClickable(true);spAgeNinoTwo.setClickable(true);
                	}	
                	
                	break;
                case R.id.spAgeNinoOne:
                	strAgeOne = parent.getItemAtPosition(position).toString();         
                    break;
                case R.id.spAgeNinoTwo:
                	strAgeTwo = parent.getItemAtPosition(position).toString();
                    break;    
             }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) 
        {
        	return;
        	
        }
    };
    
    public void initDateStartExit() 
    {	
    	calender 			= Calendar.getInstance();
    	calenderExit		= Calendar.getInstance();
    	
    	dayStart		= calender.get(Calendar.DAY_OF_MONTH);
		monthStart 		= calender.get(Calendar.MONTH) +1;
		yearStart 		= calender.get(Calendar.YEAR);
		
		strMonth = userFunction.getStringMonth(monthStart);
		
		calenderExit.add(Calendar.DATE, 1);
		dayEnd = calenderExit.get(Calendar.DAY_OF_MONTH);
		monthEnd = calenderExit.get(Calendar.MONDAY)+1;
		yearEnd = calenderExit.get(Calendar.YEAR);
		
		srtMonthExit = userFunction.getStringMonth(monthEnd);
		
    	txtCalendarStart.setText(new StringBuilder().append(dayStart).append(".").append(strMonth).append(".").append(yearStart).append(" "));
		txtCalendarExit.setText(new StringBuilder().append(dayEnd).append(".").append(srtMonthExit).append(".").append(yearEnd).append(" "));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) getActivity().findViewById(R.id.left_drawer);
		setHasOptionsMenu(true);
		
	}
    
    public void onClick(View v) {
        
    	if (v == llCalendStart){
            
        	showDatePicker("S");
        }
        
		if (v == llCalendExit){
		            
		    showDatePicker("E");
		}
    }
    
    
    private void showDatePicker(String typePicker) 
    {
    	DateFragPicker date = new DateFragPicker();
    	  
    	Bundle args = new Bundle();
		args.putInt("year", calender.get(Calendar.YEAR));
		args.putInt("month", calender.get(Calendar.MONTH));
		args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		date.setArguments(args);
		
		type = typePicker;
    	  
    	date.setCallBack(ondate);
    	date.show(getFragmentManager(), "Date Picker");
    }

	OnDateSetListener ondate = new OnDateSetListener() 
	{
    	@Override
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth,int selectedDay) 
		{
    	   day = selectedDay;
		   month = selectedMonth;
		   year = selectedYear;
		   
		   if(type.equals("S"))
		   {	
			   dayStart	  = day;
			   monthStart = month+1;
			   yearStart  = year;		
			   
			   strMonth = userFunction.getStringMonth(monthStart);
			   
			   txtCalendarStart.setText(new StringBuilder().append(dayStart).append(".").append(strMonth).append(".").append(yearStart).append(" "));
				   
		   }else 
		   {	
			   dayEnd	= day;
			   monthEnd = month+1;
			   yearEnd 	= year;	
			   
			   srtMonthExit = userFunction.getStringMonth(monthEnd);
			   
			   txtCalendarExit.setText(new StringBuilder().append(dayEnd).append(".").append(strMonth).append(".").append(yearEnd).append(" "));
				   
		   }
		   
		}
    	
    };
	
	
	
 
}