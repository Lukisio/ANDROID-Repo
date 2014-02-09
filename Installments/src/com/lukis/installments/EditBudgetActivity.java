package com.lukis.installments;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

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

public class EditBudgetActivity extends Activity {

	TextView eDate, eWire1, eWire2, eWire3, eMaterial, eIncome, eDividends, eFuel, eRepairs, eInsurance, eCar;
	KlasaBudget budget = new KlasaBudget();
	int monthNumber=1;
	int yearNumber=1900;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_budget);

		eDate = (TextView)findViewById(R.id.textView26);
		eWire1 = (TextView)findViewById(R.id.textView16);
		eWire2 = (TextView)findViewById(R.id.textView17);
		eWire3 = (TextView)findViewById(R.id.textView18);
		eMaterial = (TextView)findViewById(R.id.textView19);
		eIncome = (TextView)findViewById(R.id.textView20);
		eDividends = (TextView)findViewById(R.id.textView21);
		eFuel = (TextView)findViewById(R.id.textView22);
		eRepairs = (TextView)findViewById(R.id.textView23);
		eInsurance = (TextView)findViewById(R.id.textView24);
		eCar = (TextView)findViewById(R.id.textView25);
		
		Bundle z = getIntent().getExtras();
	    budget.date = z.getString("DATE");
	    budget.lp = z.getString("LP");
	    budget.wire1 = Double.valueOf(z.getString("WIRE1"));
	    budget.wire2 = Double.valueOf(z.getString("WIRE2"));
	    budget.wire3 = Double.valueOf(z.getString("WIRE3"));
	    budget.material = Double.valueOf(z.getString("MATERIAL"));
	    budget.income = Double.valueOf(z.getString("INCOME"));
	    budget.dividends = Double.valueOf(z.getString("DIVIDENDS"));
	    budget.fuel = Double.valueOf(z.getString("FUEL"));
	    budget.repairs = Double.valueOf(z.getString("REPAIRS"));
	    budget.insurance = Double.valueOf(z.getString("INSURANCE"));
	    budget.car = Double.valueOf(z.getString("CAR"));
		
		eDate.setText("" + budget.date);		
		eWire1.setText("" + budget.wire1);
		eWire2.setText("" + budget.wire2);
		eWire3.setText("" + budget.wire3);
		eMaterial.setText("" + budget.material);
		eIncome.setText("" + budget.income);
		eDividends.setText("" + budget.dividends);
		eFuel.setText("" + budget.fuel);
		eRepairs.setText("" + budget.repairs);
		eInsurance.setText("" + budget.insurance);
		eCar.setText("" + budget.car);
		


	}
	
	
	public void save(View view){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Edit Budget");
		alert.setMessage("Do you want to save your new budget?");
		alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
//			    lp="7"; //wywalić jak już zacznie definiować lp
			    new DodajBudget().execute(""+budget.lp, budget.date, 
			    		eWire1.getText().toString(), eWire2.getText().toString(),
			    		eWire3.getText().toString(), eMaterial.getText().toString(),
			    		eIncome.getText().toString(), eDividends.getText().toString(),
			    		eFuel.getText().toString(), eRepairs.getText().toString(),
			    		eInsurance.getText().toString(), eCar.getText().toString() );	
		
				Intent intent = new Intent(EditBudgetActivity.this, BudgetActivity.class);
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
		
   private class DodajBudget extends AsyncTask<String, Integer, String>{
    	 
		  @Override
		  protected String doInBackground(String... arg0) {
			 KlasaBudget.nowyBudget(arg0[0], arg0[1], arg0[2], arg0[3], arg0[4], arg0[5],
					 arg0[6], arg0[7], arg0[8], arg0[9], arg0[10], arg0[11]);
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

   public void onBackPressed() //wracasz do poprzedniego activity
   {
   	Intent intent = new Intent(EditBudgetActivity.this, BudgetActivity.class);
	finish();
   	startActivity(intent);
   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_budget, menu);
		return true;
	}

}
