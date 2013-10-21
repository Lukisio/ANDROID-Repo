package com.example.feedercall;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OknoGlowne extends Activity {

	Button skanowanie;
	TextView ekranNumer, ekranProducent, ekranGora, ekranDol;
	
	// url to make request
	private static String url = "http://192.168.1.2/json/generator.php";
	 
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
	JSONArray contacts = null;
	
    String lp;
    String numer;
    String info;
    String zb7036g;
    String zb7036d;
    String zb5130g;
    String zb5130d;
    String zbbmg;
    String zbbmd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_okno_glowne);
	  

		ekranNumer = (TextView)findViewById(R.id.textNumer);

		ekranProducent = (TextView)findViewById(R.id.textProducent);
		ekranGora = (TextView)findViewById(R.id.ZbrojenieGorne);
		ekranDol = (TextView)findViewById(R.id.ZbrojenieDolne);
		
	  skanowanie = (Button) findViewById(R.id.button1);
	  skanowanie.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {		
			//	skanowanie();
        		ekranNumer.setText(numer);
        		ekranProducent.setText(lp);
    			ekranGora.setText(info);
    			ekranDol.setText(zbbmg);
			}
		}); 
	  
	        // Creating JSON Parser instance
	        JSONParser jParser = new JSONParser();
	 
	        // getting JSON string from URL
	        JSONObject json = jParser.getJSONFromUrl(url + "?numer=2147483647");
	 
	        try {
	            // Getting Array of Contacts
	            contacts = json.getJSONArray(TAG_TABELA);
	             
	            // looping through All Contacts
	         //   for(int i = 0; i < contacts.length(); i++){
	            int i=0;
	                JSONObject c = contacts.getJSONObject(i);
	                 
	                // Storing each json item in variable
	                lp = c.getString(TAG_LP);
	                numer = c.getString(TAG_NUMER);
	                info = c.getString(TAG_INFO);
	                zb7036g = c.getString(TAG_7036G);
	                zb7036d = c.getString(TAG_7036D);
	                zb5130g = c.getString(TAG_5130G);
	                zb5130d = c.getString(TAG_5130D);
	                zbbmg = c.getString(TAG_BMG);
	                zbbmd = c.getString(TAG_BMD);

	        		ekranNumer.setText(numer);
	        		ekranProducent.setText(lp);
        			ekranGora.setText(info);
        			ekranDol.setText(zbbmg);
	          //  }
	            
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }

		
		
		
		
}
	
	

	public class JSONParser {
		 
	     InputStream is = null;
	     JSONObject jObj = null;
	     String json = "";
	 
	    // constructor
	    public JSONParser() {
	 
	    }
	 
	    public JSONObject getJSONFromUrl(String url) {
	 
	        // Making HTTP request
	        try {
	            // defaultHttpClient
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost(url);
	 
	            HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            is = httpEntity.getContent();           
	 
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    is, "utf8"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            is.close();
	            json = sb.toString();
	        } catch (Exception e) {
	            Log.e("Buffer Error", "Error converting result " + e.toString());
	        }
	 
	        // try parse the string to a JSON object
	        try {
	            jObj = new JSONObject(json);
	        } catch (JSONException e) {
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }
	 
	        // return JSON String
	        return jObj;
	 
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
		
		
		switch(numerTrzy){
		case 511:
			ekranProducent.setText("Tomra 511");
			ekranGora.setText("OW210 - 140mm - LP350mm - 300mm");
			ekranDol.setText("EV/W10 - 140mm - 350mm - 300mm");
			break;
		case 512:
			ekranProducent.setText("Tomra 512");
			ekranGora.setText("OW200 - (-550mm) - 1225mm");
			ekranDol.setText("EV/W16 - 560mm - 1240mm");
			break;
		case 99:
			ekranProducent.setText("Vestas");
			ekranGora.setText("OW200 - 450mm");
			ekranDol.setText("EV/W16 - 450mm");
			break;
		case 112:
			ekranProducent.setText("Cisco/Tandberg");
			ekranGora.setText("OW200 - 450mm");
			ekranDol.setText("EV/W16 - 450mm");
			break;
		case 110:
			ekranProducent.setText("Cisco/Tandberg");
			ekranGora.setText("OW210 - (-380mm)");
			ekranDol.setText("EV/W10 - 400mm");
			break;
		case 590:
			ekranProducent.setText("Prod 590");
			ekranGora.setText("OW200 - 450mm");
			ekranDol.setText("EV/W16 - 450mm");
			break;
		case 400:
			ekranProducent.setText("Prod 400");
			ekranGora.setText("OW220 - (-1380mm) - (-200mm)");
			ekranDol.setText("EV004 - 1380mm - 200mm");
			break;
		case 357:
			ekranProducent.setText("Neutrogena");
			ekranGora.setText("OW220 - 980mm - 230mm");
			ekranDol.setText("EV002 - 980mm - 230mm");
			break;
		default:
			ekranProducent.setText("Inny producent");
			ekranGora.setText("OW220 - 1180mm - 225mm - 110mm");
			ekranDol.setText("EV004 - 1180mm - 225mm - 110mm");
			break;
		}
			
		
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
