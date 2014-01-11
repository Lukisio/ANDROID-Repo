package com.lukis.installments;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends Activity {

	TextView eCash, eDeposits, eDownpayments, eTotalInstallments, eAllBoughts, eExpenses, eSWithdrawals, eMWithdrawals, eAWithdrawals;
	TextView eCurrentProfit, eNetProfit, eSProfit, eMProfit, eAProfit, eSBalance, eMBalance, eABalance;
	TextView eDate;
	Date todayDate = new Date();
	ProgressBar kreciolek;
	int monthNumber=1;
	int yearNumber=1900;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		kreciolek = (ProgressBar)findViewById(R.id.progressBar1);
		kreciolek.setVisibility(View.VISIBLE);
	}
	
    protected void onResume(){
   	 super.onResume();

		kreciolek.setVisibility(View.VISIBLE);
	   	View v = (View)findViewById(R.id.background);
		eDate = (TextView)findViewById(R.id.textView36);
		KlasaSummary summary = new KlasaSummary();
		eCash = (TextView)findViewById(R.id.textView16);
		eDeposits = (TextView)findViewById(R.id.textView17);
		eDownpayments = (TextView)findViewById(R.id.textView18);
		eTotalInstallments = (TextView)findViewById(R.id.textView19);
		eAllBoughts = (TextView)findViewById(R.id.textView20);
		eExpenses = (TextView)findViewById(R.id.textView21);
		eSWithdrawals = (TextView)findViewById(R.id.textView22);
		eMWithdrawals = (TextView)findViewById(R.id.textView23);
		eAWithdrawals = (TextView)findViewById(R.id.textView24);
		eCurrentProfit = (TextView)findViewById(R.id.textView25);
		eNetProfit = (TextView)findViewById(R.id.textView26);
		eSProfit = (TextView)findViewById(R.id.textView27);
		eMProfit = (TextView)findViewById(R.id.textView28);
		eAProfit = (TextView)findViewById(R.id.textView29);
		eSBalance = (TextView)findViewById(R.id.textView33);
		eMBalance = (TextView)findViewById(R.id.textView34);
		eABalance = (TextView)findViewById(R.id.textView35);
		
		eDeposits.setText("" + summary.obliczDeposits(yearNumber+todayDate.getYear(), monthNumber+todayDate.getMonth()));
		eDownpayments.setText("" + summary.obliczDownPayments(yearNumber+todayDate.getYear(), monthNumber+todayDate.getMonth()));
		eAllBoughts.setText("" + summary.allBoughts);
		eExpenses.setText("" + summary.expenses);
		eAWithdrawals.setText("" + summary.aWithdrawals);
		eMWithdrawals.setText("" + summary.mWithdrawals);
		eSWithdrawals.setText("" + summary.sWithdrawals);
		
		eTotalInstallments.setText("" + summary.obliczTotalInstallments(yearNumber+todayDate.getYear(), monthNumber+todayDate.getMonth())); //tylko do testów, bo ma być inna wartość
		
		eCurrentProfit.setText("" + summary.obliczCurrentProfit(yearNumber+todayDate.getYear(), monthNumber+todayDate.getMonth()));
		double netProfit= summary.currentProfit - summary.expenses;
		netProfit = (Double) Math.ceil(netProfit*100)/100;
		eNetProfit.setText(""+netProfit);
		double netSProfit= Math.ceil(netProfit*30)/100;
		double netMProfit= Math.ceil(netProfit*20)/100;
		double netAProfit= Math.ceil(netProfit*50)/100;
		eSProfit.setText(""+netSProfit);
		eMProfit.setText(""+netMProfit);
		eAProfit.setText(""+netAProfit);
		double netSBalance= Math.ceil((netSProfit-summary.sWithdrawals)*100)/100;
		double netMBalance= Math.ceil((netMProfit-summary.mWithdrawals)*100)/100;
		double netABalance= Math.ceil((netAProfit-summary.aWithdrawals)*100)/100;
		eSBalance.setText(""+netSBalance);
		eMBalance.setText(""+netMBalance);
		eABalance.setText(""+netABalance);

		kreciolek.setVisibility(View.GONE);
//        aWithdrawals=0.0;
//		  mWithdrawals=0.0;
//		  sWithdrawals=0.0;

//		eCash.setText("dzień dobry");
//		eDate.setText("dzień dobry"+dateNumber);
		eDate.setText("Month: " + (monthNumber+todayDate.getMonth()) + "." + (yearNumber+todayDate.getYear()));
		
		v.setOnTouchListener(new OnSwipeTouchListener() {
//		    public void onSwipeTop() {
//		        Toast.makeText(SummaryActivity.this, "top", Toast.LENGTH_SHORT).show();
//		    }
		    public void onSwipeRight() {
		    	monthNumber--;
//		    	eDate.setText("dzień dobry "+dateNumber);
		        Toast.makeText(SummaryActivity.this, "right", Toast.LENGTH_SHORT).show();

				kreciolek.setVisibility(View.VISIBLE);
		        month();
		        
		    }
		    public void onSwipeLeft() {
		    	monthNumber++;
//		    	eDate.setText("dzień dobry "+dateNumber);
		        Toast.makeText(SummaryActivity.this, "left", Toast.LENGTH_SHORT).show();

				kreciolek.setVisibility(View.VISIBLE);
		        month();
		    }
//		    public void onSwipeBottom() {
//		        Toast.makeText(SummaryActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//		    }
		});
	}
	
    public void month()
    {
    	if (monthNumber>12) {
    		yearNumber++;
    		monthNumber=1;
    	}
    	if (monthNumber<1) {
    		yearNumber--;
    		monthNumber=12;
    	}
    	eDate.setText("Refreshing data");
    	Intent intent = new Intent(this, SummaryActivity.class);
    	startActivity(intent);
//    	finish();
    }
    
    public void onBackPressed() //wracasz do poprzedniego activity
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    	finish();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}

}
