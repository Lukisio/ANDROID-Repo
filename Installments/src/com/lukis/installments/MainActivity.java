package com.lukis.installments;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

	}

	public void goSum (View view){
	    Intent intent = new Intent(this, SummaryActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//    String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
		
	}

	public void goList (View view){
	    Intent intent = new Intent(this, ListActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	public void goExp (View view){
	    Intent intent = new Intent(this, ExpenseActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	public void goBud (View view){
	    Intent intent = new Intent(this, BudgetActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
