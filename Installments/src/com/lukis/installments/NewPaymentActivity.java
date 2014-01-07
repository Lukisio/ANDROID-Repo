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
import android.widget.ProgressBar;
import android.widget.TextView;

public class NewPaymentActivity extends Activity {
	
	public KlasaUser det = new KlasaUser();
	Date todayDate = new Date();
	TextView eDate, ePayment;
	ProgressBar kreciolek;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_payment);
		kreciolek = (ProgressBar)findViewById(R.id.progressBar1);
		
		Bundle z = getIntent().getExtras();
		
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
	//    det.payments[][] = z.getString("PAYMENTS");
	    //TODO trzeba przerobić na parcellable lub serializable
		
		eDate = (TextView)findViewById(R.id.editText1);
		ePayment = (TextView)findViewById(R.id.editText2);
		ePayment.setText("0");
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
			    String lp= det.lp;
				String nrRaty = det.numberPayed;
			    det.numberPayed = (""+ nrRaty);
			    String data = eDate.getText().toString();
			    String rata = ePayment.getText().toString();
			    new DodajRate().execute(lp, data, rata, nrRaty);	
		/*	    intent.putExtra("BUY_PRICE", det.buyPrice);
			    intent.putExtra("SELL_PRICE", det.sellPrice);
			    intent.putExtra("DOWNPAY", det.downpay);
			    intent.putExtra("MONTHPAY", det.monthpay);
			    intent.putExtra("PAYED", det.numberPayed);
			    intent.putExtra("REMAIN", det.remain);
*/
			//	DetailsActivity.zapisz(det);
		//		new EdytujDetal().execute(det);	
				Intent intent = new Intent(NewPaymentActivity.this, ListActivity.class);
		 /*   	Intent intent = new Intent(NewPaymentActivity.this, DetailsActivity.class);
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
		*/    	startActivity(intent);
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

		public void cancel(View view){
	    	Intent intent = new Intent(NewPaymentActivity.this, ListActivity.class);
	    	startActivity(intent);
	    	finish();
		}
		
	   
		
	   private class DodajRate extends AsyncTask<String, Integer, String>{
	    	 
			  @Override
			  protected String doInBackground(String... arg0) {
				 KlasaUser.nowaWplata(arg0[0], arg0[1], arg0[2], arg0[3]);
				 Log.i("arg0[0]: ", arg0[0]);
				 Log.i("arg1[1]: ", arg0[1]);
				 Log.i("arg2[2]: ", arg0[2]);
				 Log.i("arg3[3]: ", arg0[3]);
			   return null;
			  } 
			   
			@Override
			  protected void onPostExecute(String result) {
				  kreciolek.setVisibility(View.GONE);
			//	refresh();
			  }
			  
			  @Override
			  protected void onPreExecute() {
				  kreciolek.setVisibility(View.VISIBLE);
			  }
		}

		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_payment, menu);
		return true;
	}

}
