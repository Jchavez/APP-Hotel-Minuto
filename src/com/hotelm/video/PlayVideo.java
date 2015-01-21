package com.hotelm.video;

import com.hotelm.app.R;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideo 
{
	
	Context mCtx;
	Activity activity;
	String vSource;
 
    public PlayVideo(Context context,Activity activity) {
        super();
        this.mCtx = context;
        this.activity = activity;
    }
 
    public void play()  
    {
    	LinearLayout l1 = (LinearLayout)activity.findViewById(R.id.contentBtnVideo);
    	l1.setVisibility(View.VISIBLE);
		l1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,1));
		
		ViewGroup lContentVideo = (ViewGroup) activity.findViewById(R.id.contentVideo);
		View vPlay = lContentVideo.findViewById(R.id.contentBtnPlay);
		lContentVideo.removeView(vPlay);
		
		VideoView myVideoView = (VideoView)activity.findViewById(R.id.video);
		
		DisplayMetrics metrics = new DisplayMetrics(); 
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params2 = (android.widget.LinearLayout.LayoutParams) myVideoView.getLayoutParams();
        params2.width =  metrics.widthPixels;
        params2.height = metrics.heightPixels;
        params2.leftMargin = 0;
        myVideoView.setLayoutParams(params2);
		
		myVideoView.requestFocus();
        vSource ="android.resource://" + activity.getPackageName() + "/" + R.raw.movie;
    	myVideoView.setVideoURI(Uri.parse(vSource));
   	 	myVideoView.setMediaController(new MediaController(activity));
        myVideoView.start();
    }
}