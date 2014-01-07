package com.lukis.unitedcocalculator;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.ClipboardManager;
import android.text.Html;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private TextView email, input1, input2, input3, output1, output2, output3, ekranError;
	double x;
	RelativeLayout frontBackground;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		frontBackground = (RelativeLayout) findViewById(R.id.mainLayout);
		input1 = (TextView) findViewById(R.id.editText1);
		input2 = (TextView) findViewById(R.id.editText2);
		input3 = (TextView) findViewById(R.id.editText3);
		output1 = (TextView) findViewById(R.id.textView10);
		output2 = (TextView) findViewById(R.id.textView11);
		output3 = (TextView) findViewById(R.id.textView12);
		ekranError = (TextView) findViewById(R.id.TextView000);
		email = (TextView) findViewById(R.id.textView5);
		
		email.setText(Html.fromHtml("امجد سلامة e-mail: <u><font color=blue>aasalame@yahoo.com</font></u>"));
        input1.setOnKeyListener(new OnKeyListener() {
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
				wylicz();
				findViewById(R.id.mainLayout).requestFocus();
	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
				}
				return false;
			}
        });
		
        input2.setOnKeyListener(new OnKeyListener() {
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
				wylicz();
				findViewById(R.id.mainLayout).requestFocus();
	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(input2.getWindowToken(), 0);
				}
				return false;
			}
        });
		
        input3.setOnKeyListener(new OnKeyListener() {
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
				wylicz();
				findViewById(R.id.mainLayout).requestFocus();
	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(input3.getWindowToken(), 0);
				}
				return false;
			}
        });
	}

	
	public void kopiuj(View view){
		 ClipboardManager cm=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
		 cm.setText("سعر الشراء: " + input1.getText().toString() + "د " +
				 "\nالدفعة الأولى: " + input2.getText().toString() + "د " +
				 "\nالقسط الشهري: " + input3.getText().toString() + "د " +
				 "\nسعر البيع: " + output1.getText().toString() + 
				 "\nعدد الاقساط: " + output2.getText().toString() 
			//	 +"الربح الشهري " + output3.getText().toString()
				 ); // " سعر الشراء: 200 د"
	}
	
	public void wylicz() {
	
	   	if (checkEmpty(input1)) {
   		if (checkEmpty(input2)) {
		if (checkEmpty(input3)) {
			double valInput1 = Float.valueOf(input1.getText().toString());
			double valInput2 = Float.valueOf(input2.getText().toString());
			double valInput3 = Float.valueOf(input3.getText().toString());
		    if (valInput1>999999||valInput2>999999||valInput3>999999) {
		    	clear();
		    	
		    	frontBackground.setBackgroundColor(0x50ff0000);
		   		ekranError.setText("out of range");
		    } else {
//		    	double procent;
//		    	if (valInput1<360) procent=0.75; else procent=0.6; 
				double valOutput1 = (double) (valInput1*0.65+valInput1);
				double valOutput2 = (double) ((valOutput1-valInput2)/valInput3);
				double valOutput3 = (double) ((valOutput1-valInput1)/((valOutput1-valInput2)/valInput3));				
				double valScreen = (double) ((valInput1-valInput2)*0.45+valInput1-valInput2)/valInput3; 
				output1.setText( (int) Math.ceil(valOutput1) +"د ");
				output2.setText( (int) Math.ceil(valOutput2) +"شهر " );
				if (Math.floor(valScreen)>13) {frontBackground.setBackgroundColor(0x50ff0000);} else {frontBackground.setBackgroundColor(0x5000a100);}
				output3.setText( (int) Math.round(valOutput3) +"د " );
				ekranError.setText(" ");
		    }		
	   	} else { clear();}
		} else { clear();}
   		} else { clear();}
	
		return;
	}
	
	public void clear(){
   		output1.setText(" ");
   		output2.setText(" ");
   		output3.setText(" ");
   		frontBackground.setBackgroundColor(0x00ff0000);
   		ekranError.setText(" ");
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
                wylicz();
            }

        }
    return ret;
    }   
	
    private boolean checkEmpty(TextView etText)
    {
     if(etText.getText().toString().trim().length() > 0)
        return true;
     else
       return false; 
    }
    
    
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
}
