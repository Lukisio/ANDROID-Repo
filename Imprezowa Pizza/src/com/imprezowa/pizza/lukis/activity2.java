package com.imprezowa.pizza.lukis;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.InterstitialAd;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class activity2 extends GlowneOkno implements AdListener{
	
	 private InterstitialAd interstitial;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
     // Create the interstitial
        interstitial = new InterstitialAd(this, "ca-app-pub-3383509206758569/5501689232");

        // Create ad request
        AdRequest adRequest = new AdRequest();

        // Begin loading your interstitial
        interstitial.loadAd(adRequest);

        // Set Ad Listener to use the callbacks below
        interstitial.setAdListener(this);
        

	        Button next = (Button) findViewById(R.id.button1);
	        next.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                Intent intent = new Intent();
	                setResult(RESULT_OK, intent);
	                finish();
	            }
	        	});
	}

	
	@Override
	public void onReceiveAd(Ad ad) {
	  Log.d("OK", "Received ad");
	  if (ad == interstitial) {
	    interstitial.show();
	  }
	}
	  
	@Override
	public void onDismissScreen(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeaveApplication(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPresentScreen(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

    public void onBackPressed() //wracasz do poprzedniego activity
    {
    	Intent intent = new Intent(activity2.this, GlowneOkno.class);
    	finish();
    	startActivity(intent);
    }
}
