package com.przelicznik.vat.lukis;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class activity2 extends MainActivity {

 //   AdView adView;
  //  public static final  String MY_PUBLISHER_ID = "a150728e2c8c655";
 

	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.about);
	        
		        Button next = (Button) findViewById(R.id.button1);
		        next.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Intent intent = new Intent();
		                setResult(RESULT_OK, intent);
		                finish();
		            }
		        	});
		        
		        //request TEST ads to avoid being disabled for clicking your own ads
		   //     AdRequest adRequest = new AdRequest();
		 
		        //test mode on EMULATOR
		    //    adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
		        
		        //test mode on DEVICE (this example code must be replaced with your device uniquq ID)
		    //    adRequest.addTestDevice("4G74FC73D62D42B62A7F7DA61EF5F776");
		 
		        //create a Banner Ad
		     //   adView = new AdView(this, AdSize.BANNER, MY_PUBLISHER_ID);
		 
		        //call the main layout from xml
		    //    RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.about_layout);
		 
		        //add the Banner Ad to our main layout
		    //    mainLayout.addView(adView);
		 
		        // Initiate a request to load an ad in TEST mode. The test mode will work only on emulators and your specific test device, the users will get real ads.
		    //    adView.loadAd(adRequest);
		 
		         
		    }
		 
		 
	
		   
	
	    
	
	
	
	
}

