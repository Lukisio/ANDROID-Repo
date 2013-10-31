package com.example.feedercall;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OknoGlowne extends Activity {

	Button skanowanie, butBM, but5130, but7036;
	TextView ekranNumer, ekranProducent, ekranGora, ekranDol;
	

	
	// url to make request
	public static String url = "http://192.168.1.2/json/generator.php";
	public static String urlZapis = "http://192.168.1.2/json/zapisz.php";	
		
	// JSON Node names
	private static final String TAG_TABELA = "tabela_json";
	private static final String TAG_LP = "lp";
	private static final String TAG_NUMER = "numer";
	private static final String TAG_INFO = "info";
	private static final String TAG_7036G = "7036g";
	private static final String TAG_7036D = "7036d";
	private static final String TAG_5130G = "5130g";
	private static final String TAG_5130D = "5130d";
	private static final String TAG_BMG = "bmg";
	private static final String TAG_BMD = "bmd";
	JSONArray zbrojenia = null;
	
	public class KlasaDetal {
    String lp;
    String numer;
    String info;
    String zb7036g;
    String zb7036d;
    String zb5130g;
    String zb5130d;
    String zbbmg;
    String zbbmd;
	public void zapisz() {
		ObslugaJSON.zapisz(this);
	}
	}
	
	KlasaDetal detal= new KlasaDetal();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_okno_glowne);
	  
		butBM = (Button) findViewById(R.id.button2);
		but5130= (Button) findViewById(R.id.button3);
		but7036= (Button) findViewById(R.id.button4);
		
		ekranNumer = (TextView)findViewById(R.id.textNumer);

		ekranProducent = (TextView)findViewById(R.id.textProducent);
		ekranGora = (TextView)findViewById(R.id.ZbrojenieGorne);
		ekranDol = (TextView)findViewById(R.id.ZbrojenieDolne);
		

		
	  skanowanie = (Button) findViewById(R.id.button1);
	  skanowanie.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {		
				skanowanie();
		//		wyszukaj("5907813508097");
        /*		ekranNumer.setText(numer);
        		ekranProducent.setText(lp);
    			ekranGora.setText(info);
    			ekranDol.setText(zbbmg);
		*/	}
		}); 

		
	  butBM.setOnClickListener(new View.OnClickListener() {
		  @Override
		  public void onClick(View view) {
		  // TODO Auto-generated method stub
			//  ekranGora.setText(info);
			//  ekranDol.setText(zbbmd);
			  alert("BendMaster", detal.zbbmg, detal.zbbmd);

		  }
		  });
		
      but5130.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
			//  ekranGora.setText(info);
			//  ekranDol.setText(zb5130d);
			  alert("TB-5130", detal.zb5130g, detal.zb5130d);
		  }
		  });
      
      but7036.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
			//  ekranGora.setText(info);
			//  ekranDol.setText(zb7036d);
			  alert("TB-7036", detal.zb7036g, detal.zb7036d);
          }
      	});
      
}
	
	public void alert(String maszyna, String gora, String dol) {
	       final AlertDialog adb = new AlertDialog.Builder(this).create();
			adb.setTitle(""+maszyna); 
			adb.setButton("Edytuj", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	Intent myIntent = new Intent(OknoGlowne.this, EdytorZbrojenia.class);
			        	OknoGlowne.this.startActivity(myIntent);
			        	//detal.zapisz();
		                return;
			    } });

		    adb.setButton2("Zamknij", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	return;
			    } });
		    adb.setMessage(Html.fromHtml("<b>Zbrojenie górne</b><br/> " + gora + "<b><br/><br/>Zbrojenie dolne</b><br/> " + dol));
		    adb.show();
	}
	

	public void wyszukaj(String szukanyNumer) {
		  
        // Creating JSON Parser instance
        ObslugaJSON jParser = new ObslugaJSON();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url + "?numer=" + szukanyNumer);
 
        try {
            // Getting Array of Contacts
            zbrojenia = json.getJSONArray(TAG_TABELA);
             
            // looping through All Contacts
		//	for(int i = 0; i < zbrojenia.length(); i++){
				int i=0; //numer linijki w obiekcie JSONObject.
			    JSONObject z = zbrojenia.getJSONObject(i);
			     
			    // Storing each json item in variable
			    detal.lp = z.getString(TAG_LP);
			    detal.numer = z.getString(TAG_NUMER);
			    detal.info = z.getString(TAG_INFO);
			    detal.zb7036g = z.getString(TAG_7036G);
			    detal.zb7036d = z.getString(TAG_7036D);
			    detal.zb5130g = z.getString(TAG_5130G);
			    detal.zb5130d = z.getString(TAG_5130D);
			    detal.zbbmg = z.getString(TAG_BMG);
			    detal.zbbmd = z.getString(TAG_BMD);
			
				ekranNumer.setText(detal.numer);
				ekranProducent.setText(detal.lp);
				ekranGora.setText(detal.info);
				//ekranDol.setText(zb7036d);
		//	}
            
        } catch (JSONException e) {
            e.printStackTrace();
        }

	}


	
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
			case IntentIntegrator.REQUEST_CODE: {
				if (resultCode != RESULT_CANCELED) {
					IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
					if (scanResult != null) {
					String upc = scanResult.getContents();
					String samNumer = upc.replaceAll( "[^\\d]", "" );
					long numer = Long.parseLong(samNumer);
					//put whatever you want to do with the code here
					int numerTrzy = Integer.parseInt(samNumer.substring(0, 3));
					
					ekranNumer.setText(upc);
					
					wyszukaj(samNumer);
					
					}
				}
			break;
			}
		}
	}
	
	void skanowanie() {
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}

        
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_okno_glowne, menu);

		 
		return true;
	}

}
