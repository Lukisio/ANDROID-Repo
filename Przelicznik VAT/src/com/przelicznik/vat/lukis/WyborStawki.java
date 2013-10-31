package com.przelicznik.vat.lukis;


import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.GridView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class WyborStawki extends MainActivity {

//	private TextView info, info2;
	
	GridView tabelka;
	TextView info;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wybor_stawki);
        
    	info = (TextView)findViewById(R.id.textView1);
//   	info2 = (TextView)findViewById(R.id.textView2);
        tabelka = (GridView) findViewById(R.id.gridView1);
        final AlertDialog adb = new AlertDialog.Builder(this).create();
        
        
        tabelka.setAdapter(new ImageAdapter(this));
        
        
        tabelka.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		   public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
    			   int argInt = (int) arg;
    			   final String wybor= Long.toString(arg);
          //    	in afo.setText("ID: "+wybor);
          //    	info2.setText("Pos: " +position);

              	
          //    	info.setText("ID: "+wybor);

    			adb.setTitle(R.string.wybrana); 
   			    adb.setButton2(getString(R.string.zmiana), new DialogInterface.OnClickListener() {
   			        public void onClick(DialogInterface dialog, int which) {
   			        	return;
   			    } });
                Intent intent = new Intent();
                intent.putExtra("result", wybor);
                setResult(RESULT_OK,intent);
    			adb.setButton("OK", new DialogInterface.OnClickListener() {
   			        public void onClick(DialogInterface dialog, int which) {
   		                finish();
   			    } });
    			String nazwaKraju;
    			
    		//	Log.i("nazwa Kraju", " " +Locale.getDefault().getLanguage().equals("pl"));
    			if(Locale.getDefault().getLanguage().equals("pl")) {
    				nazwaKraju = kraj[argInt][0];
    			} else {
    				nazwaKraju = krajEN[argInt][0];
    			}
    			
    			
    			
    			
    			
    			   if (VAT[argInt][0]==4){
	    			    adb.setMessage(Html.fromHtml("<b>" + getString(R.string.oknoWybor) +": </b><br>" + getString(R.string.oknoKraj) + ": " + nazwaKraju + " [" + kraj[argInt][1] + "] <br><br>" + getString(R.string.oknoStawkaPodst) + ": " + VAT[argInt][4] + "%<br>" + getString(R.string.oknoStawkaUlg) + " 1: " + VAT[argInt][3] + "%<br>" + getString(R.string.oknoStawkaUlg) + " 2: " + VAT[argInt][2] + "%<br>" + getString(R.string.oknoStawkaUlg) +" 3: " + VAT[argInt][1] + "%"));
	    			    adb.show();
	              	}
	              	
	              	if (VAT[argInt][0]==3){
	    			    adb.setMessage(Html.fromHtml("<b>" + getString(R.string.oknoWybor) +": </b><br>" + getString(R.string.oknoKraj) + ": " + nazwaKraju + " [" + kraj[argInt][1] + "] <br><br>" + getString(R.string.oknoStawkaPodst) + ": " + VAT[argInt][4] + "%<br>" + getString(R.string.oknoStawkaUlg) + " 1: " + VAT[argInt][3] + "%<br>" + getString(R.string.oknoStawkaUlg) + " 2: " + VAT[argInt][2] + "%"));
	    			    adb.show();
	              	}
	              	
	              	if (VAT[argInt][0]==2){
	    			    adb.setMessage(Html.fromHtml("<b>" + getString(R.string.oknoWybor) +": </b><br>" + getString(R.string.oknoKraj) + ": " + nazwaKraju + " [" + kraj[argInt][1] + "] <br><br>" + getString(R.string.oknoStawkaPodst) + ": " + VAT[argInt][4] + "%<br>" + getString(R.string.oknoStawkaUlg) + ": " + VAT[argInt][3] + "%"));
	    			    adb.show();
	              	}
	              	
	              	if (VAT[argInt][0]==1){
	    			    adb.setMessage(Html.fromHtml("<b>" + getString(R.string.oknoWybor) +": </b><br>" + getString(R.string.oknoKraj) + ": " + nazwaKraju + " [" + kraj[argInt][1] + "] <br><br>" + getString(R.string.oknoStawkaPodst) + ": " + VAT[argInt][4] + "%"));
	    			    adb.show();
	              	}
              	}
    		    
    		});


    

        
        
        
        
    }


    
}
