package com.lukis.installments;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends Activity {

	
	TextView eDate, eWire1, eWire2, eWire3, eMaterial, eIncome, eDividends, eFuel, eRepairs, eInsurance, eCar;
	KlasaBudget budget = new KlasaBudget();
	int monthNumber=1;
	int yearNumber=1900;
	ProgressBar kreciolek;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);
	}
		
    protected void onResume(){
      	 super.onResume();
      	 
		View v = (View)findViewById(R.id.background);


		kreciolek = (ProgressBar)findViewById(R.id.progressBar1);

		
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
		

		

		
		v.setOnTouchListener(new OnSwipeTouchListener() {
//		    public void onSwipeTop() {
//		        Toast.makeText(SummaryActivity.this, "top", Toast.LENGTH_SHORT).show();
//		    }
		    public void onSwipeRight() {
	        	monthNumber--;
		    	kreciolek.setVisibility(View.VISIBLE);
		        Toast.makeText(BudgetActivity.this, "Previous month", Toast.LENGTH_SHORT).show();
	        	month();
		        	
//				refresh();

		    }
		    public void onSwipeLeft() {
	        	monthNumber++;
		    	kreciolek.setVisibility(View.VISIBLE);
		        Toast.makeText(BudgetActivity.this, "Next month", Toast.LENGTH_SHORT).show();
	        	month();
		        
//				refresh();
		    }
//		    public void onSwipeBottom() {
//		        Toast.makeText(SummaryActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//		    }
		});
		
		refresh();
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
    	refresh();

    }
	
	
	public void refresh(){
		eDate.setText("Refreshing data");
		Date todayDate = new Date();
		int month = monthNumber+todayDate.getMonth();
		String strMonth;
		if (month<10){
			strMonth="0"+month;
		} else {
			strMonth=""+month;
		}
		budget.wypelnijBudget(yearNumber+todayDate.getYear(), monthNumber+todayDate.getMonth());
		eDate.setText("Month: " + (monthNumber+todayDate.getMonth()) + "." + (yearNumber+todayDate.getYear()));
		budget.date= ((yearNumber+todayDate.getYear()) + "-" + strMonth + "-");
		Log.i("date inne", "" + budget.date);
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
		kreciolek.setVisibility(View.GONE);
	}
	
	
	
	public void goEdBud(View view){
	    Intent intent = new Intent(this, EditBudgetActivity.class);
	    intent.putExtra("LP", ""+budget.lp);
	    intent.putExtra("DATE", ""+budget.date);
	    intent.putExtra("WIRE1", ""+budget.wire1);
	    intent.putExtra("WIRE2", ""+budget.wire2);
	    intent.putExtra("WIRE3", ""+budget.wire3);
	    intent.putExtra("MATERIAL", ""+budget.material);
	    intent.putExtra("INCOME", ""+budget.income);
	    intent.putExtra("DIVIDENDS", ""+budget.dividends);
	    intent.putExtra("FUEL", ""+budget.fuel);
	    intent.putExtra("REPAIRS", ""+budget.repairs);
	    intent.putExtra("INSURANCE", ""+budget.insurance);
	    intent.putExtra("CAR", ""+budget.car);
    	finish();
	    startActivity(intent);
	}
	
    public void onBackPressed() //wracasz do poprzedniego activity
    {
    	Intent intent = new Intent(BudgetActivity.this, MainActivity.class);
    	finish();
    	startActivity(intent);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.budget, menu);
		return true;
	}

}
