package com.lukis.installments;



import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;



public class DetailsActivity extends Activity {


		
	public KlasaUser det= new KlasaUser();
	
	TextView info, eDate, eName, eAddress, eItem, eBuyPrice, eSellPrice, eProfit, eDownpay, eMonthpay, ePayed, eToPay, eNextDate, eNextPayment;

	
	 static final String TAG_TABELA = "installments";
	private static final String TAG_LP = "lp";
	private static final String TAG_DATE = "date";
	private static final String TAG_NAME = "name";
	private static final String TAG_ADDRES = "address";
	private static final String TAG_ITEM = "item";
	private static final String TAG_NOTE = "note";
	private static final String TAG_BUYPRICE = "buyprice";
	private static final String TAG_SELLPRICE = "sellprice";
	private static final String TAG_DOWNPAY = "downpay";
	private static final String TAG_MONTHPAY = "monthpay";
	private static final String TAG_PAYED = "numberPayed";
	
	public int profit, toPay, nextPayment;
	String nextDate;
	ProgressBar kreciolek;
	Button historyButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		kreciolek = (ProgressBar)findViewById(R.id.progressBar1);
//		getIntent().getExtra("MyClass");

	//	Bundle extras = getIntent().getExtras();


		
		//refresh();
		
	    historyButton = (Button) findViewById(R.id.button3);
	    historyButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {		 //HISTORY
		    	Intent intent = new Intent(DetailsActivity.this, HistoryActivity.class);
			    intent.putExtra("LP", det.lp);
			    intent.putExtra("PAYED", det.numberPayed);
			    intent.putExtra("PAYED_TOTAL", det.payedTotal);
			    Log.i("LP: ", det.lp);
			    finish();
		    	startActivity(intent);
			}
		}); //Koniec "porownaj"
	}
	
    protected void onResume(){
    	 super.onResume();
    	 
		Bundle z = getIntent().getExtras();


	    det.buyPrice = "0";
	    det.sellPrice = "0";
	    det.downpay = "0";
	    det.monthpay = "0";
	    det.numberPayed = "0";
	    
	    det.lp = z.getString("LP");
	    det.date = z.getString("DATE");
	    det.name = z.getString("NAME");
	    det.address = z.getString("ADDRESS");
	    det.item = z.getString("ITEM");
	    det.buyPrice = z.getString("BUY_PRICE");
	    det.sellPrice = z.getString("SELL_PRICE");
	    det.downpay = z.getString("DOWNPAY");
	    det.monthpay = z.getString("MONTHPAY");
	    det.numberPayed = z.getString("PAYED");
		det.payedTotal = z.getString("PAYED_TOTAL");
	    det.toPay = Integer.valueOf(z.getString("TOPAY"));
	    

	    z.clear();
	//    String lp="13";
	//    det=KlasaUser.wyszukaj(lp);
		Log.i("imię: ", det.name);
		
		profit=0;
		
		nextPayment=0;
		
	//	profit= Float.valueOf(det.sellPrice)-Float.valueOf(det.buyPrice);
	//	toPay= Float.valueOf(det.sellPrice)-Float.valueOf(det.payed);
		
		info = (TextView)findViewById(R.id.textView1);
		eDate = (TextView)findViewById(R.id.textView2);
		eName = (TextView)findViewById(R.id.textView3);
		eAddress = (TextView)findViewById(R.id.textView4);
		eItem = (TextView)findViewById(R.id.textView5);
		eBuyPrice = (TextView)findViewById(R.id.textView6);
		eSellPrice = (TextView)findViewById(R.id.textView7);
		eProfit = (TextView)findViewById(R.id.textView8);
		eDownpay = (TextView)findViewById(R.id.textView9);
		eMonthpay = (TextView)findViewById(R.id.textView10);
		ePayed = (TextView)findViewById(R.id.textView11);
		eToPay = (TextView)findViewById(R.id.textView12);
		eNextDate = (TextView)findViewById(R.id.textView13);
		eNextPayment = (TextView)findViewById(R.id.textView14);
    	refresh();
    }
    
	void refresh(){
		info.setText("Wybrany numer: "+det.lp);
		eDate.setText("Date: "+det.date);
		eName.setText("Name: "+det.name);
		eAddress.setText("Phone: "+det.address);
		eItem.setText("Item: "+det.item);
		eBuyPrice.setText("Buy: "+det.buyPrice);
		eSellPrice.setText("Sell: "+det.sellPrice);
		if(det.buyPrice.trim().length() <= 0) det.buyPrice="0";
		if(det.sellPrice.trim().length() <= 0) det.sellPrice="0";
		profit = Integer.valueOf(det.sellPrice) - Integer.valueOf(det.buyPrice);
		eProfit.setText("Profit: "+profit); //nie z klasy
		eDownpay.setText("Downpaid: "+det.downpay);
		eMonthpay.setText("Monthpayment: "+det.monthpay);
		ePayed.setText("Paid number: "+det.numberPayed);
		eToPay.setText("To pay: "+det.toPay);
		eNextPayment.setText("Next Payment: "+det.monthpay); //nie z klasy
		eNextDate.setText(" ");
	}
	
	public void edit(View view) {
    	Intent intent = new Intent(DetailsActivity.this, EditActivity.class);
	    intent.putExtra("LP", det.lp);
	    intent.putExtra("DATE", det.date);
	    intent.putExtra("NAME", det.name);
	    intent.putExtra("ADDRESS", det.address);
	    intent.putExtra("ITEM", det.item);
	    intent.putExtra("BUY_PRICE", det.buyPrice);
	    intent.putExtra("SELL_PRICE", det.sellPrice);
	    intent.putExtra("DOWNPAY", det.downpay);
	    intent.putExtra("MONTHPAY", det.monthpay);
	    intent.putExtra("PAYED", det.numberPayed);
	    intent.putExtra("REMAIN", det.remain);
	    intent.putExtra("TOPAY", String.valueOf(det.toPay));
	    finish();
    	startActivity(intent);
	}
	

	
	public void delete(View view) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Delete entry");
		alert.setMessage("Are you sure you want to delete this entry?");

		alert.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		 //	det.item = input.getText().toString();
			new UsunDetal().execute(det.lp);
	    	Intent intent = new Intent(DetailsActivity.this, ListActivity.class);
	    	finish();
	    	startActivity(intent);
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
			  return;
		  }
		});
		alert.show();
	}
	
	

	   public class EdytujDetal extends AsyncTask<KlasaUser, Integer, String>{
	    	 
	  @Override
	  protected String doInBackground(KlasaUser... arg0) {
	   // TODO Auto-generated method stub
//	    publishProgress(i);
	//   wyszukaj(arg0[0]);
		 DetailsActivity.zapisz(arg0[0]);
	   return null;
	  } 
	   
	@Override
	  protected void onPostExecute(String result) {
		  kreciolek.setVisibility(View.GONE);
		refresh();
	  }
	  
	  @Override
	  protected void onPreExecute() {
		  kreciolek.setVisibility(View.VISIBLE);
	  }
	  }
	   
	   
	   
	   public class UsunDetal extends AsyncTask<String, Integer, String>{
	  @Override
	  protected String doInBackground(String... arg0) {
	   // TODO Auto-generated method stub
//	    publishProgress(i);
	//   wyszukaj(arg0[0]);
		 DetailsActivity.usun(arg0[0]);
	   return null;
	  }
	@Override
	  protected void onPostExecute(String result) {
		  kreciolek.setVisibility(View.GONE);
		refresh();
	  }
	  @Override
	  protected void onPreExecute() {
		  kreciolek.setVisibility(View.VISIBLE);
	  }
	  }
	
		
	public static void zapisz(KlasaUser detal) {
		InputStream isZ = null;
		String pelnyAdres=ListActivity.urlZapis;

		try {
			pelnyAdres+="?lp=" + detal.lp + "&date=" + detal.date + "&name=" + URLEncoder.encode(detal.name, "UTF-8") + "&address=" + detal.address + 
					"&item=" + URLEncoder.encode(detal.item, "UTF-8") + "&buyprice=" + detal.buyPrice + "&sellprice=" + detal.sellPrice + "&downpay=" + detal.downpay
					+ "&monthpay=" + detal.monthpay + "&numberpayed=" + detal.numberPayed;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Log.i("Pelny adres: ", pelnyAdres);
        try {
    		URI uriPelnyAdres = new URI(pelnyAdres.replace(" ", "%20"));
    		pelnyAdres=uriPelnyAdres.toString();
    		Log.i("Pelny adres bez białych znaków: ", pelnyAdres);
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(pelnyAdres);
 
            httpClient.execute(httpPost);
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}
		
		
		public static void usun(String lp) {
			String pelnyAdres=ListActivity.urlZapis;

			pelnyAdres+="?lp=" + lp + "&usun=1";

			Log.i("Pelny adres: ", pelnyAdres);
	        try {
	    		URI uriPelnyAdres = new URI(pelnyAdres.replace(" ", "%20"));
	    		pelnyAdres=uriPelnyAdres.toString();
	    		Log.i("Pelny adres bez białych znaków: ", pelnyAdres);
	            // defaultHttpClient
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost(pelnyAdres);
	 
	            httpClient.execute(httpPost);
	            
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        } catch (URISyntaxException e) {
				e.printStackTrace();
			}

		}
	    
    public void onBackPressed() //wracasz do poprzedniego activity
    {
//    	String name = ((TextView) v.findViewById(R.id.attrib_name)).getText().toString();
    	Intent intent = new Intent(DetailsActivity.this, ListActivity.class);
	    intent.putExtra("NAME", det.name);
    	finish();
    	startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
