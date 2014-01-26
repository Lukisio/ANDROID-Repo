package com.imprezowa.pizza.lukis;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class GlowneOkno extends Activity implements OnSeekBarChangeListener{

	
	private SeekBar suwak1, suwak2;
	private TextView ekran1, ekran2, ekran3, ekran4, ekran5, tvCena1, tvCena2, ekran1Wartosc, ekran2Wartosc, ekran1Pole, ekran2Pole;
	float rozmiar1=45, rozmiar2=45;
	float cena1, cena2, wartosc1, wartosc2, pole1, pole2;
	Button porownaj, ujmijGora, dodajGora, ujmijDol, dodajDol;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glowne_okno);
        
        suwak1 = (SeekBar)findViewById(R.id.seekBar1); // make seekbar object
        suwak1.setOnSeekBarChangeListener(this);
        
        suwak2 = (SeekBar)findViewById(R.id.seekBar2); // make seekbar object
        suwak2.setOnSeekBarChangeListener(this);
    	
        Typeface czcionka1 = Typeface.createFromAsset(getAssets(), "textapoint.ttf"); 
        Typeface czcionka2 = Typeface.createFromAsset(getAssets(), "playtime.ttf"); 
        ekran1 = (TextView)findViewById(R.id.textView1);
        ekran2 = (TextView)findViewById(R.id.textView5);
        ekran3 = (TextView)findViewById(R.id.textView2);
        ekran4 = (TextView)findViewById(R.id.textView4);
        ekran5 = (TextView)findViewById(R.id.textView7);
        ekran1Wartosc = (TextView)findViewById(R.id.textView1Wartosc);
        ekran2Wartosc = (TextView)findViewById(R.id.textView2Wartosc);
        ekran1Pole = (TextView)findViewById(R.id.textView1Pole);
        ekran2Pole = (TextView)findViewById(R.id.textView2Pole);
        ekran1.setTypeface(czcionka2); 
        ekran2.setTypeface(czcionka2); 
      //  ekran3.setTypeface(czcionka2); 
        ekran4.setTypeface(czcionka1); 
        ekran5.setTypeface(czcionka1);
      //  ekran1Wartosc.setTypeface(czcionka2); 
     //   ekran2Wartosc.setTypeface(czcionka2); 
        
        tvCena1 = (TextView) findViewById(R.id.editText1);
        tvCena2 = (TextView) findViewById(R.id.editText2);
        
        tvCena1.setOnKeyListener(new OnKeyListener() {
			
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
				// if keydown and "enter" is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					odczyt1();
					findViewById(R.id.glowneOkno).requestFocus();
			        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			        imm.hideSoftInputFromWindow(tvCena1.getWindowToken(), 0);
	            }
				return false;
			}
        });
        
        tvCena2.setOnKeyListener(new OnKeyListener() {
			
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
				// if keydown and "enter" is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					odczyt2();
					findViewById(R.id.glowneOkno).requestFocus();
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		            imm.hideSoftInputFromWindow(tvCena2.getWindowToken(), 0);
	            }
				return false;
			}
        });
        
        porownaj = (Button) findViewById(R.id.butWylicz);
        porownaj.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {		

				porownaj();
			}
		}); //Koniec "porownaj"
        

        
        dodajGora = (Button) findViewById(R.id.ButtonPlusG);
        dodajGora.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {	        
				suwak1.incrementProgressBy(1);
			}
		}); //Koniec "dodajGora"
        
        
        
        ujmijGora = (Button) findViewById(R.id.ButtonMinusG);
        ujmijGora.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {	        
				suwak1.incrementProgressBy(-1);
			}
		}); //Koniec "ujmijGora"
        
        
        
        dodajDol = (Button) findViewById(R.id.ButtonPlusD);
        dodajDol.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {	        
				suwak2.incrementProgressBy(1);
			}
		}); //Koniec "dodajDol"
        
        
        
        ujmijDol = (Button) findViewById(R.id.ButtonMinusD);
        ujmijDol.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {	        
				suwak2.incrementProgressBy(-1);        
			}
		}); //Koniec "ujmijDol"        
        
    }	
    
    public void odczyt1() {
		if (checkEmpty(tvCena1)) {
			cena1 = Float.valueOf(tvCena1.getText().toString());
		//	String dc1 = String.format("%.02f", cena1);
		//	String wypisanie = df.format(cena1);
		//	tvCena1.setText(""+wypisanie);
		//	Log.i("Wartość1",wypisanie);
		//	tvCena1.setText(String.valueOf(cena1));
			tvCena1.setText(String.valueOf(cena1));
		} else {
			cena1=0;
		}
		return;
    }
    
    public void odczyt2() {
		if (checkEmpty(tvCena2)) {
		//	DecimalFormat df = new DecimalFormat("0.00");	
			cena2 = Float.valueOf(tvCena2.getText().toString());
		//	String dc2 = String.format("%.02f", cena2);
		//	Log.i("Wartość2",tvCena2.getText().toString()); 
			//ekran3.setText("Wychodzi: "+df.format(cena2));
			//float wypisz = df.format(cena2);
			tvCena2.setText(String.valueOf(cena2));
		} else {
			cena2=0;
		}
		return;
    }
	
    void porownaj () {
		pole1= (float) 3.1416*(rozmiar1/2)*(rozmiar1/2);
		pole2= (float) 3.1416*(rozmiar2/2)*(rozmiar2/2);
		wartosc1= (float)cena1/pole1;
		wartosc2= (float)cena2/pole2;
		if (wartosc1==0 || wartosc2==0) {
			ekran3.setText("Podaj obie ceny!");
			return;
		} else {
			ekran1Pole.setText(""+( (int) (pole1/1)) +" cm^2");
			ekran2Pole.setText(""+( (int) (pole2/1)) +" cm^2");
			ekran1Wartosc.setText(""+( (float) ((int) ((wartosc1*1000000)))/10000 ) +"gr./cm^2" );
			ekran2Wartosc.setText(""+( (float) ((int) ((wartosc2*1000000)))/10000 ) +"gr./cm^2" );
			Log.i("Wartość1",""+pole1); 
			Log.i("Wartość2",""+pole2); 
		}
		
		if (wartosc1>wartosc2) {
			ekran3.setText(""+wartosc1 + " " +wartosc2);
			ekran3.setText("Kupuj Pizzę nr 2");
		} else {
			if (wartosc1<wartosc2) {
				ekran3.setText(""+wartosc1 + " " +wartosc2);
				ekran3.setText("Kupuj Pizzę nr 1");
			} else {
				ekran3.setText(""+wartosc1 + " " +wartosc2);
				ekran3.setText("Taka sama cena");
			}
			}
    }
	
   public void onProgressChanged(SeekBar seekBar, int progress,
    		boolean fromUser) {
    	// TODO Auto-generated method stub

	   switch (seekBar.getId()) {

	    case R.id.seekBar1:
	    	rozmiar1=progress;
	        ekran1.setText("Rozmiar Pizzy: "+progress + " cm");
	        break;

	    case R.id.seekBar2:
	    	rozmiar2=progress;
	        ekran2.setText("Rozmiar Pizzy: "+progress + " cm");
	        break;
	    
	    default:
		   ekran3.setText("Error: "+progress + " cm");
	   		break;
	   }
    	//ekran1.setText("Rozmiar Pizzy: "+progress +zmTekst);
    }
   
   
   
    public void onStartTrackingTouch(SeekBar seekBar) {
    	// TODO Auto-generated method stub
    	//textAction.setText("starting to track touch");

    }
    
    public void onStopTrackingTouch(SeekBar seekBar) {
    	//seekBar.setSecondaryProgress(seekBar.getProgress());
    	//textAction.setText("ended tracking touch");
  
    }

    private boolean checkEmpty(TextView etText)
    {
     if(etText.getText().toString().trim().length() > 0)
        return true;
     else
       return false; 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_glowne_okno, menu);
        return true;
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

            Log.d("Activity", "Touch event "+event.getRawX()+","+event.getRawY()+" "+x+","+y+" rect "+w.getLeft()+","+w.getTop()+","+w.getRight()+","+w.getBottom()+" coords "+scrcoords[0]+","+scrcoords[1]);
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) ) { 

            	findViewById(R.id.glowneOkno).requestFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                odczyt1();
                odczyt2();
            }

        }
    return ret;
    }        
    
	public void about(View v){
    	Intent myIntent = new Intent(GlowneOkno.this, activity2.class);
    	startActivity(myIntent);
	}
    
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 	
		//check selected menu item
		// R.id.exit is @+id/exit
		if(item.getItemId() == R.id.exit){
			//close the Activity
			this.finish();
			return true;
		}
	
		if(item.getItemId() == R.id.about){
			//okienko "O programie"

	        	Intent myIntent = new Intent(GlowneOkno.this, activity2.class);
	        	GlowneOkno.this.startActivity(myIntent);
	        
			return true;
		}
		
		
		return false;
	}
}
