package com.lukis.installments;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListActivity extends Activity {


	public static String url = "http://united.webege.com/generator.php";
	public static String urlZapis = "http://united.webege.com/zapisz.php";
	public static String urlRata = "http://united.webege.com/raty.php";
	
		
	// JSON Node names
	 static final String TAG_TABELA = "installments";
	private static final String TAG_DATELIST = "datelist";
	private static final String TAG_PAYLIST = "paylist";
	private static final String TAG_LP = "lp";
	private static final String TAG_DATE = "date";
	private static final String TAG_NAME = "name";
	private static final String TAG_ADDRES = "address";
	private static final String TAG_ITEM = "item";
	private static final String TAG_BUYPRICE = "buyprice";
	private static final String TAG_SELLPRICE = "sellprice";
	private static final String TAG_DOWNPAY = "downpay";
	private static final String TAG_MONTHPAY = "monthpay";
	private static final String TAG_PAYED = "numberpayed";
	JSONArray zbrojenia = null;
	public Boolean czekaj = false;
	

	
	TextView info;
    int lp, dlugosc;
    public String[][] lista;
	public String date, name, address, item;
	public double buyPrice, sellPrice, downpay, monthpay, payed;	
	public KlasaUser detal= new KlasaUser();
	
	ProgressBar kreciolek;
	TableLayout table;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		info = (TextView)findViewById(R.id.textView1);
		kreciolek = (ProgressBar)findViewById(R.id.progressBar1);
		
		
		
/*		// Dynamiczne dodawanie linii do tabeli
		TableLayout table = (TableLayout)ListActivity.this.findViewById(R.id.tableLayout);
		for(int i=0;i<imiona.length;i++)
		{		
		    lp=i;
			date="12.09.2013";
			name=imiona[i];
			address="Pod mostem";
			item=rzeczy[i];
			buyPrice=kwota[i];
			sellPrice=kwotaSprzedazy[i];
			downpay=50.0;
			monthpay=15.0;
			payed=23.5;	
			
		    // Inflate your row "template" and fill out the fields.
		    TableRow row = (TableRow)LayoutInflater.from(ListActivity.this).inflate(R.layout.one_row, null);
		    ((TextView)row.findViewById(R.id.attrib_name)).setText(imiona[i]);
		    ((TextView)row.findViewById(R.id.attrib_lp)).setText(String.valueOf(lp));
		    ((TextView)row.findViewById(R.id.attrib_item)).setText(rzeczy[i]);
		    ((TextView)row.findViewById(R.id.attrib_value)).setText(String.valueOf(buyPrice));
		    row.setClickable(true);
		    row.setFocusable(true);
		 // row.setFocusableInTouchMode(true);
		    row.setOnClickListener(rowOnClickListener);

		    
		    table.addView(row);
		}
		table.requestLayout();  
*/
/*		new WypelnijTabele().execute("");
		while(czekaj){}
		
		
		TableLayout table = (TableLayout)ListActivity.this.findViewById(R.id.tableLayout);
		for(int i=0;i<dlugosc;i++)
		{		
			
		    // Inflate your row "template" and fill out the fields.
		    TableRow row = (TableRow)LayoutInflater.from(ListActivity.this).inflate(R.layout.one_row, null);
		    ((TextView)row.findViewById(R.id.attrib_lp)).setText(lista[i][0]);
		    ((TextView)row.findViewById(R.id.attrib_name)).setText(lista[i][1]);
		    ((TextView)row.findViewById(R.id.attrib_item)).setText(lista[i][2]);
		    ((TextView)row.findViewById(R.id.attrib_value)).setText(lista[i][3]);
		    row.setClickable(true);
		    row.setFocusable(true);
		 // row.setFocusableInTouchMode(true);
		    row.setOnClickListener(rowOnClickListener);

		    table.addView(row);
		}
		table.requestLayout();  
*/


	}

    
    
    protected void onResume(){
    	 super.onResume();
		new WypelnijTabele().execute("");
		while(czekaj){}
		
		
		table = (TableLayout)ListActivity.this.findViewById(R.id.tableLayout);
		table.removeAllViews();
		for(int i=0;i<dlugosc;i++)
		{		
			
		    // Inflate your row "template" and fill out the fields.
		    TableRow row = (TableRow)LayoutInflater.from(ListActivity.this).inflate(R.layout.one_row, null);
		    ((TextView)row.findViewById(R.id.attrib_lp)).setText(lista[i][0]);
		    ((TextView)row.findViewById(R.id.attrib_name)).setText(lista[i][1]);
		    ((TextView)row.findViewById(R.id.attrib_item)).setText(lista[i][2]);
		    ((TextView)row.findViewById(R.id.attrib_value)).setText(lista[i][3]);
		    row.setClickable(true);
		    row.setFocusable(true);
		 // row.setFocusableInTouchMode(true);
		    row.setOnClickListener(rowOnClickListener);

		    table.addView(row);
		}
		table.requestLayout();
		info.setText("Table reloaded!");
    }


	private OnClickListener rowOnClickListener = new OnClickListener() {
        public void onClick(View v) {
            //GET TEXT HERE
        	
        	String lp = ((TextView) v.findViewById(R.id.attrib_lp)).getText().toString();
    		new JedenDetal().execute(lp);
    		while(czekaj){}
    		
        	goDetails();
        }
    };
    
	public void goDetails(){ //wysyła dane konkretnego wpisu do kolejnej Activity
    	Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
	    intent.putExtra("LP", detal.lp);
	    intent.putExtra("DATE", detal.date);
	    intent.putExtra("NAME", detal.name);
	    intent.putExtra("ADDRESS", detal.address);
	    intent.putExtra("ITEM", detal.item);
	    intent.putExtra("BUY_PRICE", detal.buyPrice);
	    intent.putExtra("SELL_PRICE", detal.sellPrice);
	    intent.putExtra("DOWNPAY", detal.downpay);
	    intent.putExtra("MONTHPAY", detal.monthpay);
	    intent.putExtra("PAYED", detal.numberPayed);
	    intent.putExtra("REMAIN", detal.remain);
	    intent.putExtra("TOPAY", String.valueOf(detal.toPay));
    	startActivity(intent);
	}

	public void newEntry(View view) {
		//TODO
    	Intent intent = new Intent(ListActivity.this, CreateActivity.class);
    	startActivity(intent);
    	finish();
	}
	
	
    private class WypelnijTabele extends AsyncTask<String, Integer, String>{
     	 //wypelnia listę kilkoma kolumnami z calej tabeli
   @Override
   protected String doInBackground(String... arg0) {
    // TODO Auto-generated method stub
       ObslugaJSON jParser = new ObslugaJSON();
  
       try {
           JSONObject json = jParser.getJSONFromUrl(url);
       	Log.i("tabela: ", TAG_TABELA);
           zbrojenia = json.getJSONArray(TAG_TABELA);    
           dlugosc=zbrojenia.length();
           lista = new String[dlugosc][4];
           // looping through All Contacts
			for(int i = 0; i < zbrojenia.length(); i++){
				JSONObject z = zbrojenia.getJSONObject(i);
				
			    // Storing each json item in variable
			    lista[i][0] = z.getString(TAG_LP);
			    lista[i][1] = z.getString(TAG_NAME);
			    lista[i][2] = z.getString(TAG_ITEM);
			    lista[i][3] = z.getString(TAG_SELLPRICE);

			 //   publishProgress(i);
			}
       } catch (JSONException e) {
           e.printStackTrace();
       }
  	  czekaj=false;

    return null;
   }
   
   @Override
   protected void onPostExecute(String result) {
  //  setProgressBar(STOP_PROGRESS);
  //  button.setEnabled(true);
 	  kreciolek.setVisibility(View.GONE);
 	//  info.setText("name: "+detal.name);
   }
   
   @Override
   protected void onPreExecute() {
	  czekaj=true;
 	  kreciolek.setVisibility(View.VISIBLE);
   }
   
   @Override
   protected void onProgressUpdate(Integer... progress) {
  //  info.setText(""+progress[0]);
   }
     }
      
	
	public void wyszukajButton(View view) {
	//	wyszukaj("3");
	//	new JedenDetal().execute("2");
		new WypelnijTabele().execute("");

	}

	
   private class JedenDetal extends AsyncTask<String, Integer, String>{
    	 
  @Override
  protected String doInBackground(String... arg0) {
   // TODO Auto-generated method stub
//    publishProgress(i);
   wyszukaj(arg0[0]);
   return null;
  } 
   
  @Override
  protected void onPostExecute(String result) {
 //  setProgressBar(STOP_PROGRESS);
 //  button.setEnabled(true);
	  kreciolek.setVisibility(View.GONE);
	  info.setText("name: "+detal.name);
	//  czekaj=false;
	//  goDetails();
  }
  
  @Override
  protected void onPreExecute() {
   //"wyzerujemy" progress bar
 //  setProgressBar(START_PROGRESS);  
   //zablokujmy przycisk na czas dzialania watku
 //  button.setEnabled(false);
	  kreciolek.setVisibility(View.VISIBLE);
	  czekaj=true;
  }
  
  @Override
  protected void onProgressUpdate(Integer... progress) {
 //  info.setText(""+progress[0]);
  }
    }
     

	public void wyszukaj(String szukanyNumer) { //wyszukuje konkretną linię z bazy i zapisuje do klasy "detal"
        // Creating JSON Parser instance
        ObslugaJSON jParser = new ObslugaJSON();
 
        try {
            // getting JSON string from URL
            JSONObject json = jParser.getJSONFromUrl(url + "?lp=" + szukanyNumer);
            // Getting Array of Contacts

        	Log.i("tabela: ", TAG_TABELA);
            zbrojenia = json.getJSONArray(TAG_TABELA);

            // looping through All Contacts
		//	for(int i = 0; i < zbrojenia.length(); i++){
				int i=0; //numer linijki w obiekcie JSONObject.
				JSONObject z = zbrojenia.getJSONObject(i);
			     
			    // Storing each json item in variable
			    detal.lp = z.getString(TAG_LP);
			    detal.date = z.getString(TAG_DATE);
			    detal.name = z.getString(TAG_NAME);
			    detal.address = z.getString(TAG_ADDRES);
			    detal.item = z.getString(TAG_ITEM);
			    detal.buyPrice = z.getString(TAG_BUYPRICE);
			    detal.sellPrice = z.getString(TAG_SELLPRICE);
			    detal.downpay = z.getString(TAG_DOWNPAY);
			    detal.monthpay = z.getString(TAG_MONTHPAY);
			    detal.numberPayed = z.getString(TAG_PAYED);
			    
			    Log.i("numberPayed: ", detal.numberPayed);

				detal.payments = new String[2][Integer.valueOf(detal.numberPayed)+1];	
				detal.toPay=Double.valueOf(detal.sellPrice)-Double.valueOf(detal.downpay);
				
				for(int j = 1 ; j <= Integer.valueOf(detal.numberPayed); j++){
					Log.i("paylist: ", z.getString(TAG_PAYLIST+j));
				    detal.payments[0][j]=z.getString(TAG_DATELIST+j);
				    detal.payments[1][j]=z.getString(TAG_PAYLIST+j);
				    detal.toPay-=Double.valueOf(detal.payments[1][j]);
				}
				
				

        } catch (JSONException e) {
            e.printStackTrace();
        }
        czekaj=false;
	}

    public void onBackPressed() //wracasz do poprzedniego activity
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
