package com.przelicznik.vat.lukis;

import java.text.DecimalFormat;
import java.util.Locale;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements OnSeekBarChangeListener {

	//int pozycjaSuwak;
	private SeekBar suwak;
	private TextView ekran, ekran2, ekran3, ekran4, ekran5, ekran6, ekran7, ekran8, cenaN, cenaB;
	double stawka=23.0;
	double cenaNetto=1, cenaBrutto, kwotaVat;
	double cenaNetto2, cenaBrutto2, kwotaVat2;
	double a=0.0, b=5.0, c=8.0, d=23.0;
	int uruchomiono, klik, optKraj, raz=0;
	String waluta="PLN";
    SharedPreferences dane;
    String plik = "plik opcji";
	double [][] VAT = {
			  {3, 0, 3.0, 7.0, 22.0},	//PL do 2010
			  {3, 0, 5.0, 8.0, 23.0},	//PL od 2011
			  {3, 0, 10.0, 12.0, 20.0},	//Austria
			  {3, 0, 6.0, 12.0, 21.0},	//Belgia
			  {2, 0, 0, 9.0, 20.0},		//Bułgaria
			  
			  {3, 0, 5, 8, 15},	 		//Cypr
			  {2, 0, 0, 14,	20},	 	//Czechy
			  {1, 0, 0, 0, 25},			//Dania
			  {2, 0, 0, 9, 20},	 		//Estonia
			  {3, 0, 9,	13,	23},		//Finlandia
			  {4, 2.1, 5.5,	7, 19.6},	//Francja
			  
			  {3, 0, 6.5, 13, 23},		//Grecja
			  {3, 0, 4,	8, 18},			//Hiszpania
			  {2, 0, 0, 6, 19},	 		//Holandia
			  {4, 4.8, 9, 13.5, 23},	//Irlandia
			  {3, 0, 5,	9, 21},			//Litwa
			  
			  {4, 3, 6, 12, 15},		//Luksemburg
			  {2, 0, 0, 12,	22},	 	//Łotwa
			  {3, 0, 5,	7, 18},			//Malta
			  {2, 0, 0, 7, 19},	 		//Niemcy
			  {3, 0, 6,	13,	23},	 	//Portugalia
			  
			  {3, 0, 5,	9, 24},	 		//Rumunia
			  {2, 0, 0, 10,	20},	 	//Słowacja
			  {2, 0, 0, 8.5, 20},	 	//Słowenia
			  {3, 0, 6,	12,	25},	 	//Szwecja
			  
			  {3, 0, 5,	18,	27},	 	//Węgry
			  {2, 0, 0, 5, 20},	 		//Wielka Brytania
			  {3, 0, 4,	10,	21}	 		//Włochy
			};
	
	String [][] kraj = {
			{"Polska do 2010", "PLN"}, {"Polska od 2011", "PLN"},
			{"Austria", "EUR"},
			{"Belgia", "EUR"},
			{"Bulgaria", "BGN"},
			{"Cypr", "EUR"},
			{"Czechy", "CZK"},
			{"Dania", "DKK"},
			{"Estonia", "EUR"},
			{"Finlandia", "EUR"},
			{"Francja", "EUR"},
			{"Grecja", "EUR"},
			{"Hiszpania", "EUR"},
			{"Holandia", "EUR"},
			{"Irlandia", "EUR"},
			{"Litwa", "LTL"},
			{"Luksemburg", "EUR"},
			{"Lotwa", "LVL"},
			{"Malta", "EUR"},
			{"Niemcy", "EUR"},
			{"Portugalia", "EUR"},
			{"Rumunia", "RON"},
			{"Slowacja", "EUR"},
			{"Slowenia", "EUR"},
			{"Szwecja", "SEK"},
			{"Wegry", "HUF"},
			{"Wielka Brytania", "GBP"},
			{"Wlochy", "EUR"}
	};
	
	String [][] krajEN = {
			{"Poland before 2011", "PLN"}, {"Poland since 2011", "PLN"},
			{"Austria", "EUR"},
			{"Belgium", "EUR"},
			{"Bulgaria", "BGN"},
			{"Cyprus", "EUR"},
			{"Czech Republic", "CZK"},
			{"Denmark", "DKK"},
			{"Estonia", "EUR"},
			{"Finland", "EUR"},
			{"France", "EUR"},
			{"Greece", "EUR"},
			{"Spain", "EUR"},
			{"Netherlands", "EUR"},
			{"Ireland", "EUR"},
			{"Lithuania", "LTL"},
			{"Luxembourg", "EUR"},
			{"Latvia", "LVL"},
			{"Malta", "EUR"},
			{"Germany", "EUR"},
			{"Portugal", "EUR"},
			{"Romania", "RON"},
			{"Slovakia", "EUR"},
			{"Slovenia", "EUR"},
			{"Sweden", "SEK"},
			{"Hungary", "HUF"},
			{"United Kingdom", "GBP"},
			{"Italy", "EUR"}
	};
	
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        Button butStawka = (Button) findViewById(R.id.butZmianaStawki);

        suwak = (SeekBar)findViewById(R.id.seekBar1); // make seekbar object
        suwak.setOnSeekBarChangeListener(this); // set seekbar listener.
        
        Typeface czcionka0 = Typeface.createFromAsset(getAssets(), "textapoint.ttf"); 
        Typeface czcionka1 = Typeface.createFromAsset(getAssets(), "helsinki.ttf"); 
        

     	ekran = (TextView)findViewById(R.id.textView3);
     
		if(Locale.getDefault().getLanguage().equals("sk")) {
	        ekran.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
		} else {

		}
        ekran.setTypeface(czcionka0); 
        
        ekran2 = (TextView)findViewById(R.id.textView2);
        ekran2.setTypeface(czcionka1); 
        
        ekran3 = (TextView)findViewById(R.id.textView5);
        ekran3.setTypeface(czcionka1); 
     
        ekran4 = (TextView)findViewById(R.id.textView1);
        ekran4.setTypeface(czcionka1); 

        ekran5 = (TextView)findViewById(R.id.TextView01);
        ekran5.setTypeface(czcionka1); 
        
        ekran6 = (TextView)findViewById(R.id.TextView02);
        ekran6.setTypeface(czcionka1); 
     
        ekran7 = (TextView)findViewById(R.id.TextView03);
        ekran7.setTypeface(czcionka1);
        
        ekran8 = (TextView)findViewById(R.id.textView4);
        ekran8.setTypeface(czcionka1);
        
        cenaN = (TextView) findViewById(R.id.oknoCeny);
        cenaB = (TextView) findViewById(R.id.oknoCeny2);
        
        cenaN.setTypeface(czcionka1); 
        cenaB.setTypeface(czcionka1); 
        
        cenaN.setOnKeyListener(new OnKeyListener() {
			
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
				// if keydown and "enter" is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
				wylicz();
				//wyliczNet();
				findViewById(R.id.mainLayout).requestFocus();
	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(cenaN.getWindowToken(), 0);
				}
				return false;
			}
        });

        cenaB.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// if keydown and "enter" is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
				//wylicz();
				wyliczNet();
				findViewById(R.id.mainLayout).requestFocus();
	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(cenaN.getWindowToken(), 0);
				}
				return false;
			}
        });
        

        
        butStawka.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	wyborStawek();
            }
        	});
        
    //    SharedPreferences data;
    //    String plik = "plik opcji";
        dane = getSharedPreferences(plik, 0);
        
        uruchomiono = dane.getInt("uruchomienia", 0);
        optKraj = dane.getInt("kraj", 0);
        
        SharedPreferences.Editor opcje = dane.edit();
        
        if (0==raz){
        	if(Locale.getDefault().getLanguage().equals("pl")){
        		waluta=kraj[optKraj][1];
        	} else {
        		waluta=krajEN[optKraj][1];
        	}

    		a=VAT[optKraj][1];
    		b=VAT[optKraj][2];
    		c=VAT[optKraj][3];
    		d=VAT[optKraj][4];
      		ustawStawke();
        	

            ++raz;
        }
        if (uruchomiono >= 5) {
           	uruchomiono=0;
        }
        opcje.putInt("uruchomienia", ++uruchomiono);  
        opcje.commit();
 /*   	
  */		

        
  /*      licz = (Button) findViewById(R.id.butLicz1);
        licz.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {		
				wylicz();
				wyliczNet();
			}
		});
*/
        
    }
    
    
   void zacheta() {
       final AlertDialog adb = new AlertDialog.Builder(this).create();
       adb.setTitle(getString(R.string.bannerClick)); 
		    adb.setButton(getString(R.string.klikne), new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	Intent myIntent = new Intent(MainActivity.this, activity2.class);
			        	MainActivity.this.startActivity(myIntent);
			        	
			    } });
		    adb.setButton2(getString(R.string.exit), new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		        	return;
		    } });
		    adb.setMessage(Html.fromHtml(getString(R.string.szklankaMleka)));
		    adb.show();
   }
   
   public void wylicz() {
	   	
		
	   	if (checkEmpty(cenaN)) {
			cenaNetto = Float.valueOf(cenaN.getText().toString());
		    if (cenaNetto>999999) {
		    	ekran2.setText(getString(R.string.zbytWysoka));
		    	ekran3.setText(getString(R.string.kwotaVat) + " ");
		    } else {
			//	String zmTekst=" PLN";
				DecimalFormat df = new DecimalFormat("0.00");
				double cenaBrutto = (double) stawka/100;
				double kwotaVat = (double) cenaNetto * cenaBrutto++;
				ekran3.setText(getString(R.string.kwotaVat) +" "+df.format(kwotaVat) + " " + waluta);
				cenaBrutto *= cenaNetto;
				ekran2.setText(getString(R.string.kwotaBrutto2) +": "+df.format(cenaBrutto) + " " + waluta);
				
		        if (uruchomiono >= 5) {
		           	uruchomiono=0;
		           	zacheta();

		        }
		    }		
	   	} else {
	   		ekran2.setText(getString(R.string.kwotaBrutto));
	   		ekran3.setText(getString(R.string.kwotaVat) +" ");
	   	}
   }
    
   public void wyliczNet() {
	   	
		Log.i("informacja", "jezyk: "+(Locale.getDefault().getLanguage().equals("en")));
		
	   	if (checkEmpty(cenaB)) {
			cenaBrutto2 = Float.valueOf(cenaB.getText().toString());
		    if (cenaBrutto2>999999) {
		    	ekran6.setText(getString(R.string.zbytWysoka));
		    	ekran7.setText(getString(R.string.kwotaVat) + " ");
		    } else {
			//	String zmTekst=" PLN";
				DecimalFormat df = new DecimalFormat("0.00");
				cenaNetto2 = (double) stawka/100;
				cenaNetto2 = (double) cenaBrutto2 / ++cenaNetto2;
				ekran6.setText(getString(R.string.przelKwota) + " "+df.format(cenaNetto2) + " " + waluta);
				kwotaVat2 = cenaBrutto2 - cenaNetto2;
				ekran7.setText(getString(R.string.kwotaVat) + " "+df.format(kwotaVat2) + " " + waluta);
				
		        if (uruchomiono >= 5) {
		           	uruchomiono=0;
		           	zacheta();

		        }
		    }		
	   	} else {
	   		ekran6.setText(getString(R.string.kwotaNetto2));
	   		ekran7.setText(getString(R.string.kwotaVat) +" ");
	   	}
  }
   
   
    public void onProgressChanged(SeekBar seekBar, int progress,
    		boolean fromUser) {

    	
    	if (progress<=5) {
    		stawka = a;
       		seekBar.setProgress(0);
    	}
    	
    	if (progress>5 && progress<=15) {
    		stawka = b;
    		seekBar.setProgress(10);
    	}
    	if (progress>15 && progress<=25) {
    		stawka = c;
    		seekBar.setProgress(20);
    	}
    	if (progress>25) {
    		stawka = d;
    		seekBar.setProgress(30);
    	}

		DecimalFormat dff = new DecimalFormat("0.##");
    	ekran.setText(getString(R.string.stawka) +" " +dff.format(stawka) + "%");
    	// change action text label to changing
    	//textAction.setText("changing");
    }

   /*    public void zmianaStawek(View view) {
        // Is the toggle on?
     boolean on = ((ToggleButton) view).isChecked();
        
        if (on) {
        	//ekran2.setText("CZAD! Mamy stary rok!");
        	a=0; b=3; c=7; d=22;
        } else {
        	//ekran2.setText("Super! Mamy nowy rok!");
        	a=0; b=5; c=8; d=23;
        }
  	stawka = d;
    	wylicz();
    	wyliczNet();
        suwak.setProgress(30);
       	String zmTekst="%";
    	ekran.setText("Stawka: "+stawka +zmTekst);
    }
  */    
    public void wyborStawek() {
    	Intent myIntent = new Intent(MainActivity.this, WyborStawki.class);
    //	MainActivity.this.startActivity(myIntent);
    	startActivityForResult(myIntent, 1);
    }
    
    public void ustawStawke() {
    	stawka = d;
    	wylicz();
    	wyliczNet();
        suwak.setProgress(30);
		DecimalFormat dff = new DecimalFormat("0.##");
    	ekran.setText(getString(R.string.stawka) +" " + dff.format(stawka) + "%");
    	
    	String nazwaKraju;
    	if(Locale.getDefault().getLanguage().equals("pl")){
    		nazwaKraju=kraj[optKraj][0];
    	} else {
    		nazwaKraju=krajEN[optKraj][0];
    	}
    	ekran8.setText("" + nazwaKraju);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	
    	if (requestCode == 1) {
	    	if(resultCode == RESULT_OK){
	        	String wynikStr=data.getStringExtra("result");
	        	int wynik = Integer.valueOf(wynikStr);

	        	if(Locale.getDefault().getLanguage().equals("pl")){
	        		waluta=kraj[wynik][1];
	        	} else {
	        		waluta=krajEN[wynik][1];
	        	}
	            dane = getSharedPreferences(plik, 0);
	        	SharedPreferences.Editor opcje = dane.edit();
	            opcje.putInt("kraj", wynik);
	            optKraj=wynik;
	            opcje.commit();
        		a=VAT[wynik][1];
        		b=VAT[wynik][2];
        		c=VAT[wynik][3];
        		d=VAT[wynik][4];
	      		ustawStawke();
	      		
	    	}	
    	}

    	        if (resultCode == RESULT_CANCELED) {
	   
        	//	ekran.setText("Dupa, dupa! Anulowano");
        	}
    	        
    	}//onAcrivityResult    
  
    
    
    public void onStartTrackingTouch(SeekBar seekBar) {
    	// TODO Auto-generated method stub
    	//textAction.setText("starting to track touch");

    }

    private boolean checkEmpty(TextView etText)
    {
     if(etText.getText().toString().trim().length() > 0)
        return true;
     else
       return false; 
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
       	wylicz();
    	wyliczNet();
    	//seekBar.setSecondaryProgress(seekBar.getProgress());
    	//textAction.setText("ended tracking touch");
  
    }
    

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
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

         //   Log.d("Activity", "Touch event "+event.getRawX()+","+event.getRawY()+" "+x+","+y+" rect "+w.getLeft()+","+w.getTop()+","+w.getRight()+","+w.getBottom()+" coords "+scrcoords[0]+","+scrcoords[1]);
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) ) { 

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                wylicz();
                wyliczNet(); 
            }

        }
    return ret;
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

	        	Intent myIntent = new Intent(MainActivity.this, activity2.class);
	        	MainActivity.this.startActivity(myIntent);
	        
			return true;
		}

		if(item.getItemId() == R.id.wybor){
			//okienko "O programie" 

	   //     	Intent myIntent = new Intent(MainActivity.this, WyborStawki.class);
	   //    	MainActivity.this.startActivity(myIntent);
	        	wyborStawek();
	        	
			return true;
		}		
		
		
		
		return false;
	}
	
}