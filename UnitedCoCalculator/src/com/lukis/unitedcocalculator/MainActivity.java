package com.lukis.unitedcocalculator;


import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	private TextView ekran1, ekran2, ekran3, ekranError;
	double input1, output1, output2, x;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ekran1 = (TextView) findViewById(R.id.editText1);
		ekran2 = (TextView) findViewById(R.id.textView02);
		ekran3 = (TextView) findViewById(R.id.textView03);
		ekranError = (TextView) findViewById(R.id.TextView000);
		
        ekran1.setOnKeyListener(new OnKeyListener() {
			
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
				// if keydown and "enter" is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {

				wylicz();
				findViewById(R.id.mainLayout).requestFocus();
	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(ekran1.getWindowToken(), 0);
				}
				return false;
			}
        });
	}

	
	public void wylicz() {
	
		
		
	   	if (checkEmpty(ekran1)) {
			x = Float.valueOf(ekran1.getText().toString());
		    if (x>999999) {
		   		ekran2.setText(" ");
		   		ekran3.setText(" ");
		   		ekranError.setText("قيمة السلعة out of range");
		    } else {
				double valOutput1 = (double) (x+0.6*x)/12;
				double valOutput2 = (double) x * 12;
				ekran2.setText(" "+ (int) Math.ceil(valOutput1));
				ekran3.setText(" "+ valOutput2);
				ekranError.setText(" ");
		    }		
	   	} else {
	   		ekran2.setText(" ");
	   		ekran3.setText(" ");
	   		ekranError.setText(" ");
	   	}
	
	//	Output1= roundup[ (x+0.6*x)/12 , 0]
	//	Output2= Output1*12
		return;
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

         //   Log.d("Activity", "Touch event "+event.getRawX()+","+event.getRawY()+" "+x+","+y+" rect "+w.getLeft()+","+w.getTop()+","+w.getRight()+","+w.getBottom()+" coords "+scrcoords[0]+","+scrcoords[1]);
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
