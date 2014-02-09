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

	TextView eDate, eExpense, eDescription;
	Date todayDate = new Date();
	String lp, name;
	
	public static String urlExp = "http://united.webege.com/zapisz.php?table=expense";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_expense);

		Bundle z = getIntent().getExtras();
		

	    if (z.getString("LP") == null) lp = "";
	    else lp = z.getString("LP");
	    
	    if (z.getString("NAME") == null) name = "";
	    else name = z.getString("NAME");
	    
	    String date;
	    if (z.getString("DATE") == null) date = "";
	    else date = z.getString("DATE");

	    String value;
	    if (z.getString("VALUE") == null) value = "";
	    else value = z.getString("VALUE");
	    
	    String description;
	    if (z.getString("DESCRIPTION") == null) description = "";
	    else description = z.getString("DESCRIPTION");

		
		eDate = (TextView)findViewById(R.id.editText1);
		eExpense = (TextView)findViewById(R.id.editText2);
		eDescription = (TextView)findViewById(R.id.editText3);
		eDate.setText("" + date);
		eExpense.setText("" + value);
		eDescription.setText("" + description);
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
	alert.setTitle("Add expense");
	alert.setMessage("Do you want to save your expense?");
	alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		    String data = eDate.getText().toString();
	    	if (data.matches("")) data = ""+ ((1900+todayDate.getYear()) + "-"
	                + (todayDate.getMonth()+1) + "-" + todayDate.getDate());
	    	
		    String value = eExpense.getText().toString();
	    	if (value.matches("")) value="0";
	    	
		    String description = eDescription.getText().toString();
		    new DodajKoszt().execute(lp, name, data, value, description);	
	
			Intent intent = new Intent(NewExpenseActivity.this, ExpenseActivity.class);
	    	finish();
	    	startActivity(intent);
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
				 KlasaUser.nowyKoszt(arg0[0], arg0[1], arg0[2], arg0[3], arg0[4]);
				 Log.i("arg0[0]: ", arg0[0]);
				 Log.i("arg0[1]: ", arg0[1]);
				 Log.i("arg0[2]: ", arg0[2]);
				 Log.i("arg0[3]: ", arg0[3]);
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
