package com.hotelm.app;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class InitSessionActivity extends Activity 
{
	ActionBar actionBar;
	LinearLayout llRegistrate,llRegistExpress;
	TextView txtSub;
	private PopupWindow pwindo;
	Button btnAceptar,btnExit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.init_session);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//IMPIDE ROTAR PANTALLA
		setActionbarBack();
		
		llRegistrate  		= (LinearLayout)findViewById(R.id.llRegistrate);
		llRegistExpress  	= (LinearLayout)findViewById(R.id.llRegistExpress);
		txtSub 				= (TextView)findViewById(R.id.txtOlvide);
		
		llRegistrate.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View v) 
	        {
	        	Intent i = new Intent(InitSessionActivity.this,RegisterActivity.class);
				startActivity(i);
	        }
	    });
		
		llRegistExpress.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View v) 
	        {
	        	Intent i = new Intent(InitSessionActivity.this,RegisterExpressActivity.class);
				startActivity(i);
	        }
	    });
		
		txtSub.setOnClickListener(new OnClickListener()
		{
			 @Override
			 public void onClick(View arg0) 
			 {
				 initiatePopupWindow();   
			 }
	    });
		
	}
	
	private void initiatePopupWindow() 
	{
		try 
		{
			LayoutInflater inflater = (LayoutInflater) InitSessionActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.onpopup,(ViewGroup) findViewById(R.id.popup_element));
			pwindo = new PopupWindow(layout, 600, 350, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 90);
	
			btnExit = (Button) layout.findViewById(R.id.btnExit);
			btnExit.setOnClickListener(cancel_button_click_listener);
			
			btnAceptar = (Button) layout.findViewById(R.id.btnAceptar);
			btnAceptar.setOnClickListener(cancel_button_click_listener);

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private OnClickListener cancel_button_click_listener = new OnClickListener() 
	{
		@Override
		public void onClick(View v) 
		{
			pwindo.dismiss();
		}
	};
	
	public void setActionbarBack()
	{
		 actionBar = getActionBar();
	     actionBar.setHomeButtonEnabled(true);
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.setTitle("INICIAR SESIÓN");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
