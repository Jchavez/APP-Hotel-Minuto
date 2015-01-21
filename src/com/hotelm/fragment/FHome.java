package com.hotelm.fragment;

import com.hotelm.app.R;
import com.hotelm.video.PlayVideo;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.VideoView;
 
public class FHome extends Fragment 
{
	VideoView video;
	LinearLayout llFindMinuto,llFindGen,llVacPac;
	public ListView mDrawerList;
	public DrawerLayout mDrawerLayout;
	Button btnPLay;
	
    public static Fragment newInstance(Context context) {
    	FHome f = new FHome();
    	return f;
    }
    
    @Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) getActivity().findViewById(R.id.left_drawer);
		
	}
    
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);
        return root;
    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		super.onActivityCreated(savedInstanceState);
		//mDrawerList.setItemChecked(1, true);
		
		llFindMinuto = (LinearLayout)getActivity().findViewById(R.id.findMinuto);
		llFindMinuto.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	    	public void onClick(View v) 
	        {	
	    		
	    		FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
	    	    tx.replace(R.id.content_frame, Fragment.instantiate(getActivity().getApplicationContext(),"com.hotelm.fragment.FFindLast"));
	    	    tx.commit();
	    		
	    		//Intent i = new Intent(getActivity().getApplicationContext(),FragmentLastMinute.class);
				//startActivity(i);
	    	    
	    	    getActivity().getActionBar().setTitle("ULTIMO MINUTO");
	    			
	    	}
	    	
		});
		
		llFindGen = (LinearLayout)getActivity().findViewById(R.id.findGeneral);
		llFindGen.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	    	public void onClick(View v) 
	        {	
	    		
	    		FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
	    	    tx.replace(R.id.content_frame, Fragment.instantiate(getActivity().getApplicationContext(),"com.hotelm.fragment.FGeneral"));
	    	    tx.commit();
	    		
	    		//Intent i = new Intent(getActivity().getApplicationContext(),FragmentLastMinute.class);
				//startActivity(i);
	    	    
	    	    //From a Fragment:
	    	    	
	    	    /*Intent intent = new Intent(getActivity(), FGeneral.class);
	    	    startActivity(intent);*/
	    	    
	    	    //From an Activity

	    	    //Intent intent = new Intent(this, mFragmentFavorite.class);
	    	    //startActivity(intent);*/
	    	    
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
		
		btnPLay = (Button)getActivity().findViewById(R.id.bt_resfolder);
        btnPLay.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				
				PlayVideo playVideo = new PlayVideo(getActivity().getApplicationContext(), getActivity());
				playVideo.play();
			}
		});
		
        
		
	}
	
	
    
    
 
}