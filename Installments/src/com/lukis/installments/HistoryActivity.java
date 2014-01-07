package com.lukis.installments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HistoryActivity extends Activity {

	ProgressBar kreciolek;
	TableLayout table;
	TextView info;
	JSONArray zbrojenia = null;
	public Boolean czekaj = false;
	int dlugosc;
 //   public String[][] lista;
	//String lp;
    
	public static String url = "http://united.webege.com/generator.php";
	public static String urlZapis = "http://united.webege.com/zapisz.php";
    
	private static final String TAG_TABELA = "installments";
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
    
	public KlasaUser detal= new KlasaUser();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		info = (TextView)findViewById(R.id.textView1);
		kreciolek = (ProgressBar)findViewById(R.id.progressBar1);
		Bundle z = getIntent().getExtras();
		detal.lp = z.getString("LP");
		detal.numberPayed = z.getString("PAYED");
	    
	}

    
    protected void onResume(){
    	 super.onResume(); 	 
  		detal.payments = new String[2][Integer.valueOf(detal.numberPayed)+1]; 
    	 new JedenDetal().execute(detal.lp);
		while(czekaj){}

		refresh();

    }
    
	private OnClickListener rowOnClickListener = new OnClickListener() {
        public void onClick(View v) {
            //GET TEXT HERE

			AlertDialog.Builder alert = new AlertDialog.Builder(HistoryActivity.this);
			alert.setTitle("Delete payment");
			alert.setMessage("Are you sure you want to delete payment?");
			alert.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

		    	return;
			  }
			});

			alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
				  return;
			  }
			});
			alert.show();
		}
    };
    
    public void refresh(){

		table = (TableLayout)HistoryActivity.this.findViewById(R.id.tableLayout);
		table.removeAllViews();
		for(int i=0;i<=Integer.valueOf(detal.numberPayed);i++)	{		    	
		    // Inflate your row "template" and fill out the fields.
		    TableRow row = (TableRow)LayoutInflater.from(HistoryActivity.this).inflate(R.layout.history_row, null);
		    ((TextView)row.findViewById(R.id.attrib_date)).setText(detal.payments[0][i]);
		    ((TextView)row.findViewById(R.id.attrib_amount)).setText(detal.payments[1][i]);
		    Log.i("lista0 ", detal.payments[0][i] );
		    row.setClickable(true);
		    row.setFocusable(true);
		//    row.setOnClickListener(rowOnClickListener);
		    row.setOnClickListener(rowOnClickListener);
		    table.addView(row);
		}
		table.requestLayout();
		info.setText("Table reloaded!");
    }

    
    private class JedenDetal extends AsyncTask<String, Integer, String>{
   	 
    	  @Override
    	  protected String doInBackground(String... arg0) {
    	   // TODO Auto-generated method stub
//    	    publishProgress(i);
    	   wyszukaj(arg0[0]);
    	   return null;
    	  } 
    	   
    	  @Override
    	  protected void onPostExecute(String result) {
    	 //  setProgressBar(STOP_PROGRESS);
    	 //  button.setEnabled(true);
    		  kreciolek.setVisibility(View.GONE);
    		//  info.setText("name: "+detal.name);
    	      czekaj=false;
    	  }
    	  
    	  @Override
    	  protected void onPreExecute() {
    	   //"wyzerujemy" progress bar
    	 //  setProgressBar(START_PROGRESS);  
    	   //zablokujmy przycisk na czas dzialania watku
    	 //  button.setEnabled(false);
    		  czekaj=true;
    		  kreciolek.setVisibility(View.VISIBLE);
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


				JSONObject z = zbrojenia.getJSONObject(0); //0, bo tylko jedną linijkę importuje
			    
				
			    // Storing each json item in variable
			    detal.lp = z.getString(TAG_LP);
			    detal.address = z.getString(TAG_ADDRES);
			    detal.buyPrice = z.getString(TAG_BUYPRICE);
			    detal.sellPrice = z.getString(TAG_SELLPRICE);
			    detal.downpay = z.getString(TAG_DOWNPAY);
			    Log.i("downpay(wyszukaj): ", detal.downpay);
			    detal.monthpay = z.getString(TAG_MONTHPAY);
			    detal.numberPayed = z.getString(TAG_PAYED);
				for(int i = 0; i <= Integer.valueOf(detal.numberPayed); i++){
					Log.i("datelist: ", TAG_DATELIST+i);
			    detal.payments[0][i]=z.getString(TAG_DATELIST+i);
			    detal.payments[1][i]=z.getString(TAG_PAYLIST+i);		
				}
		//		info.setText(""+detal.lp);

            
        } catch (JSONException e) {
            e.printStackTrace();
        }
	      czekaj=false;
	}

	
	public void newEntry(View view){
    	Intent intent = new Intent(HistoryActivity.this, NewPaymentActivity.class);
	    intent.putExtra("LP", detal.lp);
	    intent.putExtra("DATE", detal.date);
	    intent.putExtra("MONTHPAY", detal.monthpay);
	    intent.putExtra("PAYED", detal.numberPayed);
	    intent.putExtra("REMAIN", detal.remain);
    	startActivity(intent);
    	finish();
	}
	
    public void onBackPressed() //wracasz do poprzedniego activity
    {
    	Intent intent = new Intent(this, ListActivity.class);
    	startActivity(intent);
    	finish();
    }
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

}
