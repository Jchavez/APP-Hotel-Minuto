package com.hotelm.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;

public class RegisterExpressActivity extends Activity 
{
	ActionBar actionBar;
	LinearLayout llRegistrate,llRegistExpress,llInitSession;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_express);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//IMPIDE ROTAR PANTALLA
		setActionbarBack();
		
		/*llInitSession  		= (LinearLayout)findViewById(R.id.llInitSession);
		
		llInitSession.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View v) 
	        {
	        	Intent i = new Intent(RegisterExpressActivity.this,InitSessionActivity.class);
				startActivity(i);
	        }
	    });*/
		
	}
	
	public void setActionbarBack()
	{
		 actionBar = getActionBar();
	     actionBar.setHomeButtonEnabled(true);
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.setTitle("RESERVA EXPRESS");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
