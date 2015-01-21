package com.hotelm.app;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.hotelm.adapter.GalleryDeatailsLastMinuteAdapter;
import com.hotelm.adapter.ListOpinionesAdapter;
import com.hotelm.clases.ImageGallery;
import com.hotelm.clases.Opiniones;
import com.hotelm.utils.GallView;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDetailsGeneralActivity extends Activity implements LocationListener
{
	Button btnMaps,btnExit,btnOpiniones,btnPhotos;
	LinearLayout llPhotos,llContentOpiniones,llContentMapas,llContentFotos,contentBotonera,contentMap,contentMapBotonera,contentMapOpiniones,llExit,llBtnMaps,llbotones,llcntReadMore,llcntNoReadMore,llBtnSetDate;
	LinearLayout.LayoutParams paramsllBtnMaps,paramsllBtnExit,paramsllBtnPhotos;
	TextView txtReadMore,textReadNoMore,txtPrice;
	ImageView imgReadMore,imgNoReadMore;
	ListView lOpiniones;
	ListOpinionesAdapter adaptador;
	ArrayList<Opiniones> aOpiniones = new ArrayList<Opiniones>();
	String strMonth,type,info,srtMonthExit,strNumHabs,strNumAdultos,strChildren,strAgeOne,strAgeTwo,strDestino;
	GallView gallery;
    ImageGallery imageGallery;
	ArrayList<ImageGallery> aGallery = new ArrayList<ImageGallery>();
	Bundle bundle;
	ActionBar actionBar;
	int dayStart,monthStart,yearStart,dayEnd,monthEnd,yearEnd;
    double longitude,latitude;
	Criteria criteria;
    LocationManager locationManager;
    Location location;
    Geocoder geocoder;
    boolean isGPSEnabled,isNetworkEnabled = true;
    List<Address> addresses;
    private GoogleMap googleMap;
    private static final String LOGTAG = "LogsMapsHM";
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters // The minimum distance to change Updates in meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute // The minimum time between updates in milliseconds
	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_details_general);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//IMPIDE ROTAR PANTALLA
		setActionbarBack();
		
		getValueResult();
		
		TextView txtVDestino = (TextView)findViewById(R.id.txtCiudadBusq);
	    TextView txtVDates 	 = (TextView)findViewById(R.id.txtDates);
	    TextView txtVHabs 	 = (TextView)findViewById(R.id.txtResulHab);
	    TextView txtVAdultos = (TextView)findViewById(R.id.txtResulAdultos);
	   
	    txtVDestino.setText(strDestino);
	    txtVDates.setText("Del "+dayStart+"."+strMonth+"."+yearStart+" al "+dayEnd+"."+srtMonthExit+"."+yearEnd);
	    txtVHabs.setText("// "+strNumHabs+" HABITACIONES");
	    txtVAdultos.setText("// "+strNumAdultos+" ADULTOS");
		
		contentBotonera     = (LinearLayout)findViewById(R.id.contentBotonera);
		contentMapBotonera  = (LinearLayout)findViewById(R.id.contentMapBotonera);
		contentMapOpiniones = (LinearLayout)findViewById(R.id.contentMapOpiniones);
		llBtnMaps 			= (LinearLayout)findViewById(R.id.llBtnMaps);
		llExit 			= (LinearLayout)findViewById(R.id.llExit);
		llPhotos 		= (LinearLayout)findViewById(R.id.llBtnFotos);
		
		llbotones        	= (LinearLayout)findViewById(R.id.llbotones);
		llcntReadMore  		= (LinearLayout)findViewById(R.id.cntReadMore);
		llcntNoReadMore  	= (LinearLayout)findViewById(R.id.cntNoReadMore);
		
		paramsllBtnMaps 	= (LinearLayout.LayoutParams)llBtnMaps.getLayoutParams();
		paramsllBtnExit 	= (LinearLayout.LayoutParams)llExit.getLayoutParams();
		paramsllBtnPhotos 	= (LinearLayout.LayoutParams)llPhotos.getLayoutParams();
		
		txtReadMore        	= (TextView)findViewById(R.id.txtReadMore);
		imgReadMore        	= (ImageView)findViewById(R.id.ic_read_more);
		textReadNoMore      = (TextView)findViewById(R.id.txtNoReadMore);
		imgNoReadMore       = (ImageView)findViewById(R.id.img_no_read_more);
		
		btnOpiniones 		= (Button)findViewById(R.id.btnOpiniones);
		btnMaps 			= (Button)findViewById(R.id.btnMaps);
		btnPhotos 			= (Button)findViewById(R.id.btnFotos);
		btnExit 			= (Button)findViewById(R.id.btnExit);
		lOpiniones			= (ListView)findViewById(R.id.lViewOpiniones);
		gallery 			= (GallView)findViewById(R.id.gallery);
		
		llContentMapas  = (LinearLayout)findViewById(R.id.llContentMapas);
		llContentOpiniones = (LinearLayout)findViewById(R.id.llContentOpiniones);
		llContentFotos = (LinearLayout)findViewById(R.id.llContentFotos);
		llBtnSetDate = (LinearLayout)findViewById(R.id.llBtnSetDate);
		
		insertDataImgGallery(5);
		GalleryDeatailsLastMinuteAdapter adapter = new GalleryDeatailsLastMinuteAdapter(ViewDetailsGeneralActivity.this, aGallery);
		gallery.setAdapter(adapter);
		
		try 
        {
	    	getLocation();
		    initilizeMap(); 
            UiSettingsMaps();
            
         } catch (Exception e) 
         {
            e.printStackTrace();
            
        }
		
		setListOpiniones();
		adaptador = new ListOpinionesAdapter(ViewDetailsGeneralActivity.this, R.layout.list_item_opiniones, aOpiniones);
		lOpiniones.setAdapter(adaptador);
		
		
		btnMaps.setOnClickListener(new OnClickListener() 
        {
        	@Override
	    	public void onClick(View v) 
	        {
        		contentBotonera.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		contentBotonera.setVisibility(View.INVISIBLE);
        		
        		contentMapOpiniones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		contentMapBotonera.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,12));
        		
        		llContentOpiniones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		llContentOpiniones.setVisibility(View.INVISIBLE);
        		llContentFotos.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		llContentFotos.setVisibility(View.INVISIBLE);
        		llContentMapas.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		llContentMapas.setVisibility(View.VISIBLE);
        		
        		btnMaps.setVisibility(View.INVISIBLE);
        		btnOpiniones.setVisibility(View.INVISIBLE);
        		btnPhotos.setVisibility(View.INVISIBLE);
        		
        		paramsllBtnMaps.setMargins(0, 0, 0, 0);
        		llBtnMaps.setLayoutParams(paramsllBtnMaps);
        		
        		paramsllBtnPhotos.setMargins(0, 0, 0, 0);
        		llPhotos.setLayoutParams(paramsllBtnPhotos);
        		
        		paramsllBtnExit.setMargins(0, 0, 0, 0);
        		llExit.setLayoutParams(paramsllBtnMaps);
        		llExit.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		
        		
        		
        	}
    	});
		
		btnOpiniones.setVisibility(View.VISIBLE);
		btnOpiniones.setOnClickListener(new OnClickListener() 
        {
        	@Override
	    	public void onClick(View v) 
	        {
        		contentBotonera.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		contentBotonera.setVisibility(View.INVISIBLE);
        		
        		contentMapOpiniones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		contentMapBotonera.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,12));
        		
        		llContentFotos.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		llContentFotos.setVisibility(View.INVISIBLE);
        		llContentMapas.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		llContentMapas.setVisibility(View.INVISIBLE);
        		llContentOpiniones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		llContentOpiniones.setVisibility(View.VISIBLE);
        		
        		btnMaps.setVisibility(View.INVISIBLE);
        		btnOpiniones.setVisibility(View.INVISIBLE);
        		btnPhotos.setVisibility(View.INVISIBLE);
        		
        		paramsllBtnMaps.setMargins(0, 0, 0, 0);
        		llBtnMaps.setLayoutParams(paramsllBtnMaps);
        		
        		paramsllBtnPhotos.setMargins(0, 0, 0, 0);
        		llPhotos.setLayoutParams(paramsllBtnPhotos);
        		
        		llExit.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		
        		llContentMapas.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		llContentMapas.setVisibility(View.INVISIBLE);
        		llContentOpiniones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        	}
    	});
		
		
		btnPhotos.setVisibility(View.VISIBLE);
		btnPhotos.setOnClickListener(new OnClickListener() 
        {
        	@Override
	    	public void onClick(View v) 
	        {
        		contentBotonera.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		contentBotonera.setVisibility(View.INVISIBLE);
        		
        		contentMapOpiniones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,(float) 1));
        		
        		contentMapBotonera.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,12));
        		
        		llContentMapas.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		llContentMapas.setVisibility(View.INVISIBLE);
        		llContentOpiniones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
        		llContentOpiniones.setVisibility(View.INVISIBLE);
        		llContentFotos.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		llContentFotos.setVisibility(View.VISIBLE);
        		
        		btnMaps.setVisibility(View.INVISIBLE);
        		btnOpiniones.setVisibility(View.INVISIBLE);
        		btnPhotos.setVisibility(View.INVISIBLE);
        		
        		paramsllBtnMaps.setMargins(0, 0, 0, 0);
        		llBtnMaps.setLayoutParams(paramsllBtnMaps);
        		
        		paramsllBtnPhotos.setMargins(0, 0, 0, 0);
        		llPhotos.setLayoutParams(paramsllBtnPhotos);
        		
        		llExit.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		
        	}
    	});
		
		btnExit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) 
            {
            	contentMapBotonera.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,(float) 2.5));
        		contentMapOpiniones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,(float) 3));
        		
        		contentBotonera.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
        		contentBotonera.setVisibility(View.VISIBLE);
        		btnMaps.setVisibility(View.VISIBLE);
        		btnOpiniones.setVisibility(View.VISIBLE);
        		btnPhotos.setVisibility(View.VISIBLE);
        		
        		paramsllBtnMaps.setMargins(0, 0, 10, 0);
        		llBtnMaps.setLayoutParams(paramsllBtnMaps);
        		
        		paramsllBtnPhotos.setMargins(0, 0, 10, 0);
        		llPhotos.setLayoutParams(paramsllBtnPhotos);
        		
        		llExit.setLayoutParams(new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        	}
        });
		
		llcntReadMore.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View v) 
	        {
	        	txtReadMore.setText("More descripción del hotel Miguel Virumbrales");
	        	txtReadMore.setTextSize(12);
	        	txtReadMore.setTextColor(getResources().getColor(R.color.black));
	        	txtReadMore.setTypeface(Typeface.DEFAULT);
	        	imgReadMore.setVisibility(View.INVISIBLE);
	        	llcntNoReadMore.setVisibility(View.VISIBLE);
	        	llbotones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,500));
	        	llcntReadMore.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
	        	llcntNoReadMore.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,15));
	        	
	        }
	    });
	    
	    llcntNoReadMore.setOnClickListener(new View.OnClickListener() 
	    {
	        @Override
	        public void onClick(View v) 
	        {	
	        	
	        	llcntNoReadMore.setVisibility(View.INVISIBLE);
	        	llbotones.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,1));
	        	llcntReadMore.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,15));
	        	llcntNoReadMore.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT,60));
	        	txtReadMore.setText("Leer más");
	        	txtReadMore.setTextSize(13);
	        	txtReadMore.setTypeface(Typeface.DEFAULT_BOLD);
	        	txtReadMore.setTextColor(getResources().getColor(R.color.blue_hm));
	        	imgReadMore.setVisibility(View.VISIBLE);
	        }
	    });
	    
	    Button btnGo = (Button)findViewById(R.id.btnGo);
	    btnGo.setOnClickListener(new OnClickListener() 
	    {
        	@Override
        	public void onClick(View v) 
        	{
        		Intent i = new Intent(ViewDetailsGeneralActivity.this,InitSessionActivity.class);
				startActivity(i);
        	}
        });
	    
	   /* llBtnSetDate.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) 
	        {
	        	
	        	
	        }
	    });*/
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
	
	public void insertDataImgGallery(int longitud) 
	{
		for(int i=0;i<=longitud;i++)
		{
			imageGallery = new ImageGallery(getResources().getDrawable(R.drawable.imgcity));
		    aGallery.add(imageGallery);
		}
	}
	
	private void setListOpiniones() 
	{
		aOpiniones.add(new Opiniones("Excelente todo", "9 FEB 2014", "El hotel es genial, es un tres estrellas, pero podria sin lugar a dudas tener 4. Al ubicación genial, al lado de la Catedral, y en el mismo centro de Logroño, en unas de las calles también famosas por sus tascas, Calle San Juan. Lo recomiendo, el servicio fenomenal, el desayuno genial, la limpieza de la habitación, era una habitación...", 3));
		aOpiniones.add(new Opiniones("Genial", "8 FEB 2014", "Situado en el centro de Logroño, hotel ideal para pasar un fin de semana. Bonito detalle el de invitar a una copa de vino a tu llegada. sin dudar repetiria, lo unico negativo, por poner algo, es que se oyen bastante las campanadas!", 5));
		aOpiniones.add(new Opiniones("Estupendo", "5 FEB 2014", "Hotel centrico y totalmente rehabilitado. Situado muy cerca de la calle Laurel con gran cantidad de bares y restaurantes para elegir. La habitación pequeña asi como el baño. El personal de recepcion muy amable. Recomendable para pasar una noche o por lo menos en la habitacion que nosotros nos alojamos ya que igual disponen de otras mas grandes para mayor...", 2));
		aOpiniones.add(new Opiniones("Buena elección", "3 FEB 2014", "Nos alojamos el pasado miércoles y no puedo decir otra cosa q GENIAL. Solicitamos una habitación con cama de matrimonio...la chica de recepción fue encantadora y nos la dio sin ningún problema xq había disponibilidad. Nos encanto TODO, la estancia, el servicio...MUY RECOMENDABLE. Gracias x todo, un saludo", 1));
		aOpiniones.add(new Opiniones("Hotel con encanto perfecto", "1 FEB 2014","Lo bueno: salon cafeteria con sofas y chimenea muy acogedor, cercania calle laurel, y muy buen trato (invitacion copa vino al llegar). Lo malo (y por eso la puntación): las habitaciones economicas son muy ruidosas, procedente de cañerias, otras habitaciones , como del exterior. En si la habitacion esta correcta y limpia, pero no vale los 65€ que cuesta.", 4));
	}
	
	
	public void setActionbarBack()
	{
		 actionBar = getActionBar();
	     actionBar.setHomeButtonEnabled(true);
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.setTitle("DETALLE BÚSQ.GENERAL");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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

	@Override
    protected void onResume() 
    {
        super.onResume();
        initilizeMap();
    }
    
    @Override
    protected void onPause() 
    {
       super.onPause();
       if(locationManager != null)
       {
    	   locationManager.removeUpdates(this); // Stop updates to save power while app paused
       }
       
    }
    
    @SuppressLint("NewApi")
	private void initilizeMap() // function to load map. If map is not created it will create it for you
    { 
        if (googleMap == null) 
        {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
 
            if (googleMap == null) // check if map is created successfully or not
            { 
                Toast.makeText(getApplicationContext(),"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    private void UiSettingsMaps()
    {
    	googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);// Changing map type
     	googleMap.setMyLocationEnabled(true);// Showing / hiding your current location
		googleMap.getUiSettings().setZoomControlsEnabled(true);// Enable / Disable zooming controls
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);// Enable / Disable my location button
		googleMap.getUiSettings().setCompassEnabled(true);// Enable / Disable Compass icon
		googleMap.getUiSettings().setRotateGesturesEnabled(true);// Enable / Disable Rotate gesture
		googleMap.getUiSettings().setZoomGesturesEnabled(true);// Enable / Disable zooming functionality
		CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude,longitude)).zoom(16).build();
		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    
    public Location getLocation() 
    {
        try {
        	
			locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);// getting GPS status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER); // getting network status

            if (!isGPSEnabled && !isNetworkEnabled) 
            { // no network provider is enabled
               
            } else {
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES, this);Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
               
                if (isGPSEnabled) { // if GPS Enabled get lat/long using GPS Services
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES, this); Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

	@Override
	public void onLocationChanged(Location location) 
    {
		dumpLocation(location);
    }
	
	private void dumpLocation(Location location) 
    {
       if (location == null)
          Log.i(LOGTAG,"\nLocation[unknown]");
       else
    	  Log.i(LOGTAG,"\n" + location.toString());
    }
    
    @Override
	public void onProviderDisabled(String provider) 
	{
		Log.i(LOGTAG,"\nProvider disabled: " + provider);
	}

	@Override
	public void onProviderEnabled(String provider) 
	{
		Log.i(LOGTAG,"\nProvider enabled: " + provider);
	}

    @Override
	public void onStatusChanged(String provider, int status,Bundle extras) 
    {
    	Log.i(LOGTAG,"\nProvider status changed: " + provider);
    }
}
