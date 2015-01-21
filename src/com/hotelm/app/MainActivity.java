package com.hotelm.app;

import java.util.ArrayList;
import com.hotelm.adapter.EntryAdapter;
import com.hotelm.utils.EntryItem;
import com.hotelm.utils.Item;
import com.hotelm.app.R;
import com.hotelm.utils.SectionItem;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity 
{
	public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;
    int position;
    ArrayList<Item> items = new ArrayList<Item>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//IMPIDE ROTAR PANTALLA
		
		mTitle = getTitle();
	  
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);// Locate DrawerLayout in drawer_main.xml
        mDrawerList = (ListView) findViewById(R.id.left_drawer);// Locate ListView in drawer_main.xml
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        /*
        items.add(new SectionItem("PERFIL"));
        items.add(new EntryItem(101,"INICIAR SESIÓN", R.drawable.ic_drawer_profile));
        */
        items.add(new SectionItem("MENÚ"));
        items.add(new EntryItem(201,"VOLVER A HOME",R.drawable.ic_drawer_home));
        items.add(new EntryItem(202,"ÚLTIMO MINUTO", R.drawable.ic_drawer_um));
        items.add(new EntryItem(203,"BUSCADOR GENERAL", R.drawable.ic_drawer_glass));
        items.add(new EntryItem(204,"VACACIONES Y ESCAPADAS", R.drawable.ic_drawer_sun));

        items.add(new SectionItem("COMPARTIR"));
        items.add(new EntryItem(301,"COMPARTIR CON FACEBOOK", R.drawable.ic_drawer_face));
        items.add(new EntryItem(302,"COMPARTIR CON TWITTER",R.drawable.ic_drawer_tw));
        items.add(new EntryItem(303,"VALORA NUESTRA APP", R.drawable.ic_drawer_star));
        
        items.add(new SectionItem("AYUDA"));
        items.add(new EntryItem(401,"¿CÓMO FUNCIONA?", R.drawable.ic_drawer_tool));
        items.add(new EntryItem(402,"ENVÍANOS COMENTARIOS",R.drawable.ic_drawer_comment));
        items.add(new EntryItem(403,"POLÍTICA DE PRIVACIDAD", R.drawable.ic_drawer_lock));
        items.add(new EntryItem(404,"TÉRMINOS Y CONDICIONES", R.drawable.ic_drawer_paper));
       
        EntryAdapter adapter = new EntryAdapter(this, items);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        getActionBar().setDisplayHomeAsUpEnabled(true); //habilitar el icono de la ActionBar como un botón volver.
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            @Override
			public void onDrawerClosed(View view) {
            	
            	if(position == 201)
                {
                	getActionBar().setTitle(null);
                	getActionBar().setIcon(R.drawable.logo_comp);
                	
                }else
                {
                	getActionBar().setTitle(mTitle);
                	
                }
            	
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
			public void onDrawerOpened(View drawerView) {
            	
            	invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(201,"INICIO",3);
        }
		 
		 
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        
        MenuItem searchItem = menu.findItem(R.id.action_websearch);
        SearchView mSearchView  = (SearchView) searchItem.getActionView();
        int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
        ImageView v = (ImageView) mSearchView.findViewById(searchImgId);
        v.setImageResource(R.drawable.lupa); 
        
        return super.onCreateOptionsMenu(menu);
    }
	
	
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        //menu.findItem(R.id.geo).setVisible(!drawerOpen);
        menu.findItem(R.id.call).setVisible(!drawerOpen);
        
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
         if (mDrawerToggle.onOptionsItemSelected(item)) 
         {
            return true;
         }
        
        switch (item.getItemId()) 
        {
        /*case R.id.action_websearch:
        	Toast.makeText(this, "Search", Toast.LENGTH_LONG).show(); 
            return true;
        case R.id.geo:
        	Toast.makeText(this, "Geo", Toast.LENGTH_LONG).show(); 
            return true;*/
        case R.id.call:
        	Toast.makeText(this, "Call", Toast.LENGTH_LONG).show(); 
            return true;    
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener 
    {// The click listener for ListView in the navigation drawer
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
         
            if (!items.get(position).isSection()) {
                EntryItem item = (EntryItem)items.get(position);
                
                selectItem(item.getId(),item.title,position);
            }

        }
    }

    private void selectItem(int idFragment,String infoTitle,int positionItem) {
    	
    	position = idFragment;
        
    	FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        switch ((int)idFragment) {
        case 101:
        	tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this,"com.hotelm.fragment.FInitSesion"));
        	tx.commit();
        	setTitle(infoTitle);
            break;
        case 201:
        	tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this,"com.hotelm.fragment.FHome"));
    	    tx.commit();
    	    setTitle(infoTitle);
            break;
        case 202:
        	tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this,"com.hotelm.fragment.FFindLast"));
    	    tx.commit();
    	    setTitle(infoTitle);
            break;
        case 203:
        	tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this,"com.hotelm.fragment.FGeneral"));
    	    tx.commit();
    	    setTitle(infoTitle);
            break;
        case 204:
        	tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this,"com.hotelm.fragment.FVacEscapada"));
    	    tx.commit();
    	    setTitle(infoTitle);
            break;
        default:
        	Toast.makeText(getApplicationContext(), "En construcción", Toast.LENGTH_SHORT).show();
        }
        
        /*mDrawerList.setItemChecked(positionItem, true);
        setTitle(infoTitle);*/
        mDrawerLayout.closeDrawer(mDrawerList);
    	
    }

    @Override
    public void setTitle(CharSequence title) 
    {
        mTitle = title;
        
        if(position == 201)
        {
        	getActionBar().setTitle(null);
        	getActionBar().setIcon(R.drawable.logo_comp);
        	
        }else 
        {
        	getActionBar().setIcon(R.drawable.logo);
        	getActionBar().setTitle(mTitle);
        }
        
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) 
    {	
    	super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) 
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
