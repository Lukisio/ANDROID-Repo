package com.lukis.installments;

import java.util.Date;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class NewExpenseActivity extends Activity {

	TextView eDate, eExpense;
	Date todayDate = new Date();
	String lp, name;
	
	public static String urlExp = "http://united.webege.com/zapisz.php?table=expense";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_expense);

		Bundle z = getIntent().getExtras();
		
	    lp = z.getString("LP");
	    name = z.getString("NAME");
		
		eDate = (TextView)findViewById(R.id.editText1);
		eExpense = (TextView)findViewById(R.id.editText2);
		eExpense.setText("0");
	    eDate.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {		
					rolkaDaty(v);
				}
			}); 
	}

	
	
public void rolkaDaty(View view){
	DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
	 
	            @Override
	            public void onDateSet(DatePicker view, int year,
	                    int monthOfYear, int dayOfMonth) {
	                eDate.setText(year + "."
	                        + (monthOfYear + 1) + "." + dayOfMonth);
	            }
	        }, (1900+todayDate.getYear()), (todayDate.getMonth()), todayDate.getDate());
	dpd.show();
}


public void save(View view){
	AlertDialog.Builder alert = new AlertDialog.Builder(this);
	alert.setTitle("Add payment");
	alert.setMessage("Do you want to save your new payment?");
	alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		    String data = eDate.getText().toString();
		    String value = eExpense.getText().toString();
		    lp="7"; //wywalić jak już zacznie definiować lp
		    new DodajKoszt().execute(name, data, value);	
	
			Intent intent = new Intent(NewExpenseActivity.this, ExpenseActivity.class);
	    	startActivity(intent);
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
	
	   private class DodajKoszt extends AsyncTask<String, Integer, String>{
	    	 
			  @Override
			  protected String doInBackground(String... arg0) {
				 KlasaUser.nowyKoszt(arg0[0], arg0[1], arg0[2]);
				 Log.i("arg0[0]: ", arg0[0]);
				 Log.i("arg1[1]: ", arg0[1]);
				 Log.i("arg2[2]: ", arg0[2]);
			   return null;
			  } 
			   
		@Override
		  protected void onPostExecute(String result) {
		//	  kreciolek.setVisibility(View.GONE);
		//	refresh();
		  }
		  
		  @Override
		  protected void onPreExecute() {
		//	  kreciolek.setVisibility(View.VISIBLE);
		  }
	}

	    
	public void cancel(View view){
		finish();
	}
	   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_expense, menu);
		return true;
	}

}
