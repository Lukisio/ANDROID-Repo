package com.lukis.skup.butelek;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.os.Build;

public class AddBottle extends OknoGlowne {

	TextView eCode, eDate, eName, eProducer;
	CheckBox isReturnable;
	private Beer beer = new Beer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bottle);
		
		eCode = (TextView) findViewById(R.id.editText1);
		eDate = (TextView) findViewById(R.id.editText2);
		eName = (TextView) findViewById(R.id.editText3);
		eProducer = (TextView) findViewById(R.id.editText4);
		isReturnable = (CheckBox) findViewById(R.id.checkBox1);

	}

	
	public void add(View view){
		Log.i("add", "add");
		beer.setCode("" + eCode.getText().toString());
		beer.setDate("" + eDate.getText().toString());
		beer.setName("" + eName.getText().toString());
		beer.setProducer("" + eProducer.getText().toString());
		beer.setReturnable("" + isReturnable.isChecked());
		beer.saveToLocalDB(AddBottle.this);
	}
	
	
	public void cancel(View view){
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_bottle, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
