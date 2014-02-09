package com.lukis.installments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SWithdrawActivity extends Activity {
	
	static final String TAG_TABELA = "installments";
	private static final String TAG_LP = "lp";
	private static final String TAG_NAME = "name";
	private static final String TAG_DATE = "date";
	private static final String TAG_VALUE = "value";
	private static final String TAG_DESCRIPTION = "description";

	public static String urlExp = "http://united.webege.com/generator.php?table=expense";
	
	TableLayout table;
	JSONArray zbrojenia = null;
	public Boolean czekaj = false;
    public String[][] lista;
    int dlugosc;
    String lp;
    String NAME="Withdraw_S";
    int suma=0;
    TextView eSuma;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swithdraw);
		eSuma = (TextView)findViewById(R.id.textView2);
	}
    
    protected void onResume(){
    	 super.onResume();
		new WypelnijTabele().execute("");
		while(czekaj){}
		
		
		table = (TableLayout)SWithdrawActivity.this.findViewById(R.id.tableLayout);
		table.removeAllViews();
        if (dlugosc>0){
		for(int i=0;i<dlugosc;i++)
		{		
			
		    // Inflate your row "template" and fill out the fields.
		    TableRow row = (TableRow)LayoutInflater.from(SWithdrawActivity.this).inflate(R.layout.expense_row, null);
		    ((TextView)row.findViewById(R.id.attrib_lp)).setText(lista[i][0]);
//		    ((TextView)row.findViewById(R.id.attrib_name)).setText(lista[i][1]);
		    ((TextView)row.findViewById(R.id.attrib_date)).setText(lista[i][2]);
		    ((TextView)row.findViewById(R.id.attrib_value)).setText(lista[i][3]);
		    ((TextView)row.findViewById(R.id.attrib_description)).setText(lista[i][4]);
		    suma+=Integer.valueOf(lista[i][3]);
		    row.setClickable(true);
		    row.setFocusable(true);
		 // row.setFocusableInTouchMode(true);
		    row.setOnClickListener(editExp);

		    table.addView(row);
		}
		eSuma.setText("Total: " + suma);
		table.requestLayout();
        }
    }
	
    private class WypelnijTabele extends AsyncTask<String, Integer, String>{
     	 //wypelnia listę kilkoma kolumnami z calej tabeli
   @Override
   protected String doInBackground(String... arg0) {
       ObslugaJSON jParser = new ObslugaJSON();
       try {
           JSONObject json = jParser.getJSONFromUrl(urlExp + "&name=" + NAME);
       	Log.i("tabela: ", TAG_TABELA);
           zbrojenia = json.getJSONArray(TAG_TABELA);    
           dlugosc=zbrojenia.length();
           lista = new String[dlugosc][5];
           // looping through All Contacts
			for(int i = 0; i < zbrojenia.length(); i++){
				JSONObject z = zbrojenia.getJSONObject(i);
				
			    // Storing each json item in variable
			    lista[i][0] = z.getString(TAG_LP);
			    lista[i][1] = z.getString(TAG_NAME);
			    lista[i][2] = z.getString(TAG_DATE);
			    lista[i][3] = z.getString(TAG_VALUE);
			    lista[i][4] = z.getString(TAG_DESCRIPTION);

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
 //	  kreciolek.setVisibility(View.GONE);
 	//  info.setText("name: "+detal.name);
   }
   
   @Override
   protected void onPreExecute() {
	  czekaj=true;
 //	  kreciolek.setVisibility(View.VISIBLE);
   }
   
   @Override
   protected void onProgressUpdate(Integer... progress) {
  //  info.setText(""+progress[0]);
   }
     }


    public void addExp(View view){
    	
    	Intent intent = new Intent(SWithdrawActivity.this, NewExpenseActivity.class);
    	intent.putExtra("LP", lp);
    	intent.putExtra("NAME", NAME);
    	finish();
    	startActivity(intent);
    }

	private OnClickListener editExp = new OnClickListener() {
        public void onClick(View v) {
        	
        	lp = ((TextView) v.findViewById(R.id.attrib_lp)).getText().toString();
        	String date = ((TextView) v.findViewById(R.id.attrib_date)).getText().toString();
        	String value = ((TextView) v.findViewById(R.id.attrib_value)).getText().toString();
        	String description = ((TextView) v.findViewById(R.id.attrib_description)).getText().toString();
        	
        	Intent intent = new Intent(SWithdrawActivity.this, NewExpenseActivity.class);
        	intent.putExtra("LP", lp);
        	intent.putExtra("NAME", NAME);
        	intent.putExtra("DATE", date);
        	intent.putExtra("VALUE", value);
        	intent.putExtra("DESCRIPTION", description);
        	
        	finish();
        	startActivity(intent);
        }
    };
    
    public void onBackPressed() //wracasz do poprzedniego activity
    {
    	Intent intent = new Intent(this, ExpenseActivity.class);
    	finish();
    	startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.swithdraw, menu);
		return true;
	}

}
