package com.lukis.installments;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class BudgetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);
	}
	
	public void goEdBud(View view){
	    Intent intent = new Intent(this, EditBudgetActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.budget, menu);
		return true;
	}

}
