package com.lukis.installments;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ExpenseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense);
	}
	
	public void goAWith (View view){
	    Intent intent = new Intent(this, AWithdrawActivity.class);
    	finish();
	    startActivity(intent);
	}
	
	public void goMWith (View view){
	    Intent intent = new Intent(this, MWithdrawActivity.class);
    	finish();
	    startActivity(intent);
	}
	
	public void goSWith (View view){
	    Intent intent = new Intent(this, SWithdrawActivity.class);
    	finish();
	    startActivity(intent);
	}
	
	public void goADep (View view){
	    Intent intent = new Intent(this, ADepositActivity.class);
    	finish();
	    startActivity(intent);
	}
	
	public void goGeneral (View view){
	    Intent intent = new Intent(this, GenExpensesActivity.class);
    	finish();
	    startActivity(intent);
	}

    
    public void onBackPressed() //wracasz do poprzedniego activity
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	finish();
    	startActivity(intent);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense, menu);
		return true;
	}

}
