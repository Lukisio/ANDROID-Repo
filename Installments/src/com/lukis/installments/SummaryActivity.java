package com.lukis.installments;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SummaryActivity extends Activity {

	TextView eCash, eDeposits, eDownpayments, eTotalInstallments, eAllBoughts, eExpenses, eSWithdrawals, eMWithdrawals, eAWithdrawals;
	TextView eCurrentProfit, eNetProfit, eSProfit, eMProfit, eAProfit, eSBalance, eMBalance, eABalance;
	ProgressBar kreciolek;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		kreciolek = (ProgressBar)findViewById(R.id.progressBar1);
		kreciolek.setVisibility(View.VISIBLE);
	}
	
    protected void onResume(){
   	 super.onResume();
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
		
		eDeposits.setText("" + summary.obliczDeposits());
		eDownpayments.setText("" + summary.obliczDownPayments());
		eAllBoughts.setText("" + summary.allBoughts);
		eExpenses.setText("" + summary.expenses);
		eAWithdrawals.setText("" + summary.aWithdrawals);
		eMWithdrawals.setText("" + summary.mWithdrawals);
		eSWithdrawals.setText("" + summary.sWithdrawals);
		
		eTotalInstallments.setText("" + summary.obliczTotalInstallments()); //tylko do testów, bo ma być inna wartość
		
		eCurrentProfit.setText("" + summary.obliczCurrentProfit());
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
