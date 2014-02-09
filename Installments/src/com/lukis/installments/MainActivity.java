package com.lukis.installments;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


	
	TextView email;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		email = (TextView) findViewById(R.id.textView5);
		
		email.setText(Html.fromHtml("امجد سلامة e-mail:<br/><u><font color=blue>aasalame@yahoo.com</font></u>"));

	}

	public void goSum (View view){
	    Intent intent = new Intent(this, SummaryActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//    String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
    	finish();
	    startActivity(intent);
		
	}

	public void goList (View view){
	    Intent intent = new Intent(this, ListActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
    	finish();
	    startActivity(intent);
	}
	
	public void goExp (View view){
	    Intent intent = new Intent(this, ExpenseActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
    	finish();
	    startActivity(intent);
	}
	
	public void goCalc (View view){
	    Intent intent = new Intent(this, CalculatorActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
    	finish();
	    startActivity(intent);
	}
	
	public void goUsers (View view){
	    Intent intent = new Intent(this, UsersActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
    	finish();
	    startActivity(intent);
	}
	
	public void goUnpaid (View view){
	    Intent intent = new Intent(this, UnpaidActivity.class);
	//    EditText editText = (EditText) findViewById(R.id.edit_message);
	//   String message = editText.getText().toString();
	//    intent.putExtra(EXTRA_MESSAGE, message);
    	finish();
	    startActivity(intent);
	}
	
	
	public void email(View view) {
    	Intent i = new Intent(Intent.ACTION_SEND);
    	i.setType("message/rfc822");
    	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"aasalame@yahoo.com"});
    	i.putExtra(Intent.EXTRA_SUBJECT, "Message");
    	try {
    	    startActivity(Intent.createChooser(i, "Select your e-mail program"));
    	} catch (android.content.ActivityNotFoundException ex) {
    	    Toast.makeText(MainActivity.this, "You don't have an e-mail program", Toast.LENGTH_SHORT).show();
    	}
	}
	
    public void onBackPressed() 
    {
    	finish();
    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
