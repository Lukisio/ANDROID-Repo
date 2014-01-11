package com.lukis.installments;



import java.util.Date;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EditActivity extends Activity {


	public KlasaUser det = new KlasaUser();
	Date todayDate = new Date();

	TextView info, eDate, eName, eAddress, eItem, eBuyPrice, eSellPrice, eProfit, eDownpay, eMonthpay, ePayed, eToPay, eNextDate, eNextPayment;
	ProgressBar kreciolek;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
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
	    det.toPay = Double.valueOf(z.getString("TOPAY"));
	    
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
	//	ePayed = (TextView)findViewById(R.id.textView);
		eToPay = (TextView)findViewById(R.id.textView12);
		if(det.buyPrice.trim().length() <= 0) det.buyPrice="0"; //jeśli nie wpisano tekstu, ustal na 0
		if(det.sellPrice.trim().length() <= 0) det.sellPrice="0";
		if(det.downpay.trim().length() <= 0) det.downpay="0";
		if(det.monthpay.trim().length() <= 0) det.monthpay="0";
		refresh();
		
		
	    eDate.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {		
					rolkaDaty(v);
				}
			}); //Koniec "porownaj"
		}	
		
		public void rolkaDaty(View view){
			DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
			 
			            @Override
			            public void onDateSet(DatePicker view, int year,
			                    int monthOfYear, int dayOfMonth) {
			                eDate.setText(dayOfMonth + "."
			                        + (monthOfYear + 1) + "." + year);
			            }
			        }, (1900+todayDate.getYear()), (todayDate.getMonth()), todayDate.getDate());
			dpd.show();
		}
	
	void refresh(){
		info.setText(""+det.lp);
		eDate.setText(""+det.date);
		eName.setText(""+det.name);
		eAddress.setText(""+det.address);
		eItem.setText(""+det.item);
		eBuyPrice.setText(""+det.buyPrice);
		eSellPrice.setText(""+det.sellPrice);
//		eProfit.setText("Profit: "+profit); //nie z klasy
		eDownpay.setText(""+det.downpay);
		eMonthpay.setText(""+det.monthpay);
//		ePayed.setText(""+det.payed);
	}
	
	public void save(View view){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Edit entry");
		alert.setMessage("Do you want to save your new entry?");

		// Set an EditText view to get user input 
/*		final EditText input = new EditText(this);
		input.setSingleLine(true);
		input.setText(det.item);
		alert.setView(input);
*/
		alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		    
		    det.date= eDate.getText().toString();
		    det.name = eName.getText().toString();
		    det.address = eAddress.getText().toString();
		    det.item = eItem.getText().toString();
		    det.buyPrice = eBuyPrice.getText().toString();
		    det.sellPrice = eSellPrice.getText().toString();
		    det.downpay = eDownpay.getText().toString();
		    det.monthpay = eMonthpay.getText().toString();
		//	DetailsActivity.zapisz(det);
			new EdytujDetal().execute(det);	

	    	Intent intent = new Intent(EditActivity.this, DetailsActivity.class);
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

	public void cancel(View view){
    	Intent intent = new Intent(EditActivity.this, DetailsActivity.class);
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
    	startActivity(intent);
    	finish();
	}
		
		   private class EdytujDetal extends AsyncTask<KlasaUser, Integer, String>{
		    	 
				  @Override
				  protected String doInBackground(KlasaUser... arg0) {
				   // TODO Auto-generated method stub
//				    publishProgress(i);
				//   wyszukaj(arg0[0]);
					 DetailsActivity.zapisz(arg0[0]);
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
		   
    @Override // chowanie klawiatury po wciśnięciu w ekran
    public boolean dispatchTouchEvent(MotionEvent event) {

        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) ) { 
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                
            }
        }
    return ret;
    }
    
    public void onBackPressed() //wracasz do poprzedniego activity
    {
    	Intent intent = new Intent(EditActivity.this, ListActivity.class);
    	startActivity(intent);
    	finish();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
