package com.lukis.skup.butelek;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class OknoGlowne extends Activity {

	private TextView ekranPodpis, ekranProducent, ekranZwrot;
	ProgressBar kreciolek;
	String samNumer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_okno_glowne);
		Typeface czcionka1 = Typeface.createFromAsset(getAssets(), "ariendezze.ttf"); 
		ekranPodpis = (TextView)findViewById(R.id.textViewMarka);
		ekranPodpis.setTypeface(czcionka1);
		ekranProducent = (TextView)findViewById(R.id.textViewProducent);
		ekranProducent.setTypeface(czcionka1);
		ekranZwrot = (TextView)findViewById(R.id.textViewZwrot);
		ekranZwrot.setTypeface(czcionka1);
		kreciolek = (ProgressBar) findViewById(R.id.progressBar1);
//		IntentIntegrator integrator = new IntentIntegrator(this);
	}

	public enum kody {
		kod5902995000988,	//Lubuskie Jasne
	    kod5902995000254,	//Lubuskie Jagodowe
	    kod5902995001039,	//Lubuskie Lubusz
	    kod5902995000483,	//Lubuskie Naturalne
	    kod5902995001145,	//Lubuskie Celtyckie
	    kod5902995000476,	//Lubuskie Malinowe
	    kod5902995000506,	//Lubuskie Czekoladowe
	    kod5902995000131,	//Lubuskie Jabłkowe
	    kod5902995001213,	//Lubuskie Miodowe Jasne
	    kod5902995001206,	//Lubuskie Miodowe Ciemne
	    kod5902995001190,	//Lubuskie Zielone
	    kod5902995001169,	//Lubuskie Cytrynowe
	    kod5902995000773,	//Lubuskie Wiśniowe
	    kod5902995000995,	//Lubuske Pszeniczne
	    kod5902995000537,	//Lubuskie Black Boss
	    kod5902995000162,	//Lubuskie Porter
	    kod5902995000261,	//Lubuskie Podpiwek
	    kod5902995000285,	//Lubuskie Kwas Chlebowy
	    kod5902995000179,	//Lubuskie Śliwkowe
	    kod5902709615064, //Fortuna Czarne
	    kod5903082101014, //Kasztelan Niepasteryzowane
	    kod5901335000428, //Mazowieckie Niepasteryzowane
	    kod5901335000381,	//Dawne Niepasteryzowane
	    kod5901335000374,	//Dawne Niepasteryzowane
	    kod5901335000664,	//Starodawne
	    kod5901335000657,	//Starodawne
	    kod5901335000633,	//Żytnie
	    kod5901335000626,	//Pszeniczne
	    kod5901335000039,	//Konstancin Jasny Pełny
	    kod5901335000671,	//Czarny Dąb
	    kod5901335000305,	//Mazowieckie
	    kod5901335000312,	//Mazowieckie
	    kod5901335000084,	//Warszawiak Mocny
	    kod5901335000138,	//Warszawiak Mocny
	    kod5901359014784,	//Książęce Czerwony Lager
	    kod5901359072012, 	//Książęce Pszeniczne
	    kod5902709615286, 	//Miłosław Marcowe
	    kod20422776, 		//Argus Naturalnie Mętny
	    kod20409883,		//Argus Miodowy
	    kod20416935,		//Argus  El Bravos
	    kod20346034,		//Argus Maestic
	    kod20409906,		//Argus Niepasteryzowany
	    kod20232122,		//Perlenbacher
	    kod5901559830009,	//Tatra Jasne Pełne
	    kod5900014002180,	//Harnaś
	    
	    kod5900014002432,	//Okocim Radler
	    kod5900014002173,	//Carlsberg
	    kod5900940000168,	//Książ
	    kod5901359014968,	//Książęce Złote Pszeniczne
	    kod5904003000225, 	//Wojak
	    kod5901359014401,	//Tyskie Klasyczne
	    kod5901359000367,	//Tyskie
	    kod5900490000427,	//Lech Premium
	    kod5901359642017,	//Gingers
	    kod8712000010249,	//Heineken 650ml
	    kod5901559951162,	//Heineken 500ml
	    kod5900877000248,	//Żubr
	    kod5901559130000,	//Żywiec Premium
	    kod5902746641453,	//Warka Radler
	    kod5900699000099,	//Special Jasny Pełny
	    kod5902093000040,	//Królewskie
	    kod5907786301015,	//Brackie
	    kod5907040100019,	//Kujawiak
	    kod5905927001619,	//Perła Chmielowa
	    kod5905927001695,	//Perła Export
	    kod5905927051546,	//Perła Summer
	    kod5905927007192,	//Perła Niepasteryzowana
	    kod5905927047754,	//Perła Vetter
	    kod5905912650259,	//Ciechan Miodowe
	    kod5905912650310,	//Ciechan Wyborne
	    kod5905912650341,	//Ciechan Mocne
	    kod5905912650273,	//Ciechan Porter
	    kod5905912651485,	//Ciechan Porter 22
	    kod5905912651478,	//Ciechan Pszeniczne
	    kod5905912650136,	//Ciechan Krzepiak
	    kod5905912650020,	//Ciechan Lagerowe
	    kod5905912650303,	//Ciechan Stout
	    kod5905912650235,	//Ciechan Marcowe
	    kod5901687910048,	//Miłosław Niefiltrowane
	    kod5902709615323,	//Miłosław Pilzner
	    kod5900014002449,	//Świętojańskie piwo sezonowe
	    kod5908254154003,	//Lwówek Książęcy
	    kod5908254154010,	//Lwówek Ratuszowy
	    kod5908254154027,	//Lwówek Belg
	    kod5908254154034,	//Lwówek Wrocławskie
	    kod5905912650440,	//Lwówek Koźlik
	    kod5905912650457,	//Lwówek Malinowe
	    kod5908254154126,	//Lwówek Wiedeński
	    kod5908254154133,	//Lwówek Darłowiak
	    kod5905689303655,	//Sulimar Bohemia Pilsner
	    kod5905689303686,	//Sulimar Lager Miodowy
	    kod5905689303662,	//Sulimar Vienna Lager
	    kod5903538901946,	//Fasberg Gold
	    kod5905927037328,	//Trybunalskie Miodowe
	    kod5905927061118,	//Trybunalskie Miodowe zwrotne
	    kod5903538902219,	//Łomża Lemonowe
	    kod19013729,		//Piwo ERROR
	    //Kody piw
	}
	

	
/*	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("kod", "requestCode: "+requestCode);
			kreciolek.setVisibility(View.GONE);
			
		if (resultCode != RESULT_CANCELED) {
		if (data.getStringExtra("SCAN_RESULT") != null) {
		String upc = data.getStringExtra("SCAN_RESULT");
		samNumer = upc.replaceAll( "[^\\d]", "" );
		ekranPodpis.setText(upc);
		
		
		try {
		//	samNumer = "kod" + samNumer;
			kody tenKod = kody.valueOf("kod" +samNumer);
			
		switch(tenKod){
		case kod5902995000988:
			ekranPodpis.setText("Lubuskie Jasne");
			producent("BW");
			zwrotna(true);
			break;
		case kod5902995000254:
			ekranPodpis.setText("Lubuskie Jagodowe");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995001039:
			ekranPodpis.setText("Lubusz");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000483:
			ekranPodpis.setText("Lubuskie Naturalne");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995001145:
			ekranPodpis.setText("Celtyckie");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000476:
			ekranPodpis.setText("Lubuskie Malinowe");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000506:
			ekranPodpis.setText("Lubuskie Czekoladowe");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000131:
			ekranPodpis.setText("Lubuskie Jabłkowe");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995001213:
			ekranPodpis.setText("Miodowe Jasne");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995001206:
			ekranPodpis.setText("Miodowe Ciemne");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995001190:
			ekranPodpis.setText("Lubuskie Zielone");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995001169:
			ekranPodpis.setText("Lubuskie Cytrynowe");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000773:
			ekranPodpis.setText("Lubuskie Wiśniowe");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000995:
			ekranPodpis.setText("Lubuskie Pszeniczne");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000537:
			ekranPodpis.setText("Black Boss");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000162:
			ekranPodpis.setText("Porter");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000261:
			ekranPodpis.setText("Podpiwek");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000285:
			ekranPodpis.setText("Kwas Chlebowy");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902995000179:
			ekranPodpis.setText("Lubuskie Śliwkowe");
			producent("BW");
			zwrotna(false);
			break;
		case kod5902709615064:
			ekranPodpis.setText("Fortuna Czarne");
			producent("BF");
			zwrotna(true);
			break;
		case kod5903082101014:
			ekranPodpis.setText("Kasztelan Niepasteryzowane");
			producent("CP");
			zwrotna(true);
			break;
		case kod5901335000428:
			ekranPodpis.setText("Mazowieckie Niepasteryzowane");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000381:
			ekranPodpis.setText("Dawne Niepasteryzowane");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000374:
			ekranPodpis.setText("Dawne Niepasteryzowane");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000664:
			ekranPodpis.setText("Starodawne");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000657:
			ekranPodpis.setText("Starodawne");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000633:
			ekranPodpis.setText("Żytnie");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000626:
			ekranPodpis.setText("Pszeniczne");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000039:
			ekranPodpis.setText("Konstancin Jasny Pełny");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000671:
			ekranPodpis.setText("Czarny Dąb");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000305:
			ekranPodpis.setText("Mazowieckie");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000312:
			ekranPodpis.setText("Mazowieckie");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000084:
			ekranPodpis.setText("Warszawiak Mocny");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901335000138:
			ekranPodpis.setText("Warszawiak Mocny");
			producent("BK");
			zwrotna(true);
			break;
		case kod5901359014784:
			ekranPodpis.setText("Książęce Czerwony Lager");
			producent("KP");
			zwrotna(true);
			break;
		case kod5901359072012:
			ekranPodpis.setText("Książęce Pszeniczne");
			producent("KP");
			zwrotna(true);
			break;
		case kod5902709615286:
			ekranPodpis.setText("Miłosław Marcowe");
			producent("BF");
			zwrotna(true);
			break;
		case kod20422776:
			ekranPodpis.setText("Argus Naturalnie Mętny");
			producent("LP");
			zwrotna(false);
			break;
		case kod20409883:
			ekranPodpis.setText("Argus Miodowy");
			producent("LP");
			zwrotna(false);
			break;
		case kod20416935:
			ekranPodpis.setText("Argus El Bravos");
			producent("LP");
			zwrotna(false);
			break;
		case kod20346034:
			ekranPodpis.setText("Argus Maestic");
			producent("LP");
			zwrotna(false);
			break;
		case kod20409906:
			ekranPodpis.setText("Argus Niepasteryzowany");
			producent("LP");
			zwrotna(false);
			break;
		case kod20232122:
			ekranPodpis.setText("Perlenbacher");
			producent("LP");
			zwrotna(false);
			break;
		case kod5901559830009:
			ekranPodpis.setText("Tatra Jasne Pełne");
			producent("GZ");
			zwrotna(true);
			break;
		case kod5900014002180:
			ekranPodpis.setText("Harnaś");
			producent("CP");
			zwrotna(true);
			break;
		case kod5900014002432:
			ekranPodpis.setText("Okocim Radler");
			producent("CP");
			zwrotna(true);
			break;
		case kod5900014002173:
			ekranPodpis.setText("Carlsberg");
			producent("CP");
			zwrotna(false);
			break;
		case kod5900940000168:
			ekranPodpis.setText("Książ");
			producent("CP");
			zwrotna(true);
			break;
		case kod5901359014968:
			ekranPodpis.setText("Książęce Złote Pszeniczne");
			producent("KP");
			zwrotna(true);
			break;
		case kod5904003000225:
			ekranPodpis.setText("Wojak Jasne Pełne");
			producent("KP");
			zwrotna(true);
			break;
		case kod5901359014401:
			ekranPodpis.setText("Tyskie Klasyczne");
			producent("KP");
			zwrotna(true);
			break;
		case kod5901359000367:
			ekranPodpis.setText("Tyskie");
			producent("KP");
			zwrotna(true);
			break;
		case kod5900490000427:
			ekranPodpis.setText("Lech Premium");
			producent("KP");
			zwrotna(true);
			break;
		case kod5901359642017:
			ekranPodpis.setText("Gingers");
			producent("KP");
			zwrotna(true);
			break;
		case kod8712000010249:
			ekranPodpis.setText("Heineken 650ml");
			producent("GZ");
			zwrotna(false);
			break;
		case kod5901559951162:
			ekranPodpis.setText("Heineken 500ml");
			producent("GZ");
			zwrotna(false);
			break;
		case kod5900877000248:
			ekranPodpis.setText("Żubr");
			producent("KP");
			zwrotna(true);
			break;
		case kod5901559130000:
			ekranPodpis.setText("Żywiec Premium");
			producent("GZ");
			zwrotna(true);
			break;
		case kod5902746641453:
			ekranPodpis.setText("Warka Radler");
			producent("GZ");
			zwrotna(true);
			break;
		case kod5900699000099:
			ekranPodpis.setText("Specjal Jasny Pełny");
			producent("GZ");
			zwrotna(true);
			break;
		case kod5902093000040:
			ekranPodpis.setText("Królewskie");
			producent("GZ");
			zwrotna(true);
			break;
		case kod5907786301015:
			ekranPodpis.setText("Brackie");
			producent("GZ");
			zwrotna(true);
			break;
		case kod5907040100019:
			ekranPodpis.setText("Kujawiak");
			producent("GZ");
			zwrotna(true);
			break;
		case kod5905927001619:
			ekranPodpis.setText("Perła Chmielowa");
			producent("BP");
			zwrotna(false);
			break;
		case kod5905927001695:
			ekranPodpis.setText("Perła Export");
			producent("BP");
			zwrotna(false);
			break;
		case kod5905927051546:
			ekranPodpis.setText("Perła Summer");
			producent("BP");
			zwrotna(false);
			break;
		case kod5905927007192:
			ekranPodpis.setText("Perła Niepasteryzowana");
			producent("BP");
			zwrotna(false);
			break;
		case kod5905927047754:
			ekranPodpis.setText("Perła Vetter");
			producent("BP");
			zwrotna(false);
			break;
		case kod5905912650259:
			ekranPodpis.setText("Ciechan Miodowe");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912650310:
			ekranPodpis.setText("Ciechan Wyborne");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912650341:
			ekranPodpis.setText("Ciechan Mocne");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912650273:
			ekranPodpis.setText("Ciechan Porter");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912651485:
			ekranPodpis.setText("Ciechan Porter 22");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912651478:
			ekranPodpis.setText("Ciechan Pszeniczne");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912650136:
			ekranPodpis.setText("Ciechan Krzepiak");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912650020:
			ekranPodpis.setText("Ciechan Lagerowe");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912650303:
			ekranPodpis.setText("Ciechan Stout");
			producent("BC");
			zwrotna(true);
			break;
		case kod5905912650235:
			ekranPodpis.setText("Ciechan Marcowe");
			producent("BC");
			zwrotna(true);
			break;
		case kod5901687910048:
			ekranPodpis.setText("Miłosław Niefiltrowane");
			producent("BF");
			zwrotna(true);
			break;
		case kod5902709615323:
			ekranPodpis.setText("Miłosław Pilzner");
			producent("BF");
			zwrotna(true);
			break;
		case kod5900014002449:
			ekranPodpis.setText("Świętojańskie Piwo Sezonowe");
			producent("CP");
			zwrotna(true);
			break;
		case kod5908254154003:
			ekranPodpis.setText("Lwówek Książęcy");
			producent("BL");
			zwrotna(true);
			break;
		case kod5908254154010:
			ekranPodpis.setText("Lwówek Ratuszowy");
			producent("BL");
			zwrotna(true);
			break;
		case kod5908254154027:
			ekranPodpis.setText("Belg");
			producent("BL");
			zwrotna(true);
			break;
		case kod5908254154034:
			ekranPodpis.setText("Wrocławskie");
			producent("BL");
			zwrotna(true);
			break;
		case kod5905912650440:
			ekranPodpis.setText("Koźlik");
			producent("BL");
			zwrotna(true);
			break;
		case kod5905912650457:
			ekranPodpis.setText("Lwówek Malinowe");
			producent("BL");
			zwrotna(true);
			break;
		case kod5908254154126:
			ekranPodpis.setText("Lwówek Wiedeński");
			producent("BL");
			zwrotna(true);
			break;
		case kod5908254154133:
			ekranPodpis.setText("Darłowiak");
			producent("BL");
			zwrotna(true);
			break;
		case kod5905689303655:
			ekranPodpis.setText("Bohemia Pilsner");
			producent("BS");
			zwrotna(false);
			break;
		case kod5905689303686:
			ekranPodpis.setText("Lager Miodowy");
			producent("BS");
			zwrotna(false);
			break;
		case kod5903538901946:
			ekranPodpis.setText("Fasberg Gold");
			producent("LO");
			zwrotna(false);
			break;
		case kod5905689303662:
			ekranPodpis.setText("Vienna Lager");
			producent("BS");
			zwrotna(false);
			break;
		case kod5905927037328:
			ekranPodpis.setText("Trybunalskie Miodowe");
			producent("BP");
			zwrotna(false);
			break;
		case kod5905927061118:
			ekranPodpis.setText("Trybunalskie Miodowe");
			producent("BP");
			zwrotna(true);
			break;
		case kod5903538902219:
			ekranPodpis.setText("Łomża Lemonowe");
			producent("LO");
			zwrotna(false);
			break;
		case kod19013729:
			ekranPodpis.setText("To piwo nie istnieje!");
			ekranProducent.setText("Zeskanuj ponownie");
			zwrotna(false);
			break;
			
			//Dane piwa
		default: //default i tak nie działa, bo trzeba zawrzeć każdy kod w 'enum kody'
//			ekranPodpis.setText("Inny producent");
//			ekranGora.setText("OW220 - 1180mm - 225mm - 110mm");
//			ekranDol.setText("EV004 - 1180mm - 225mm - 110mm");
			break;
		}
			
	    } catch (Exception e) {
	    	ekranPodpis.setText("Tego piwa jeszcze nie piłem!");
	    	ekranProducent.setText(" ");
	    	ekranZwrot.setText(" ");
		    	AlertDialog alertDodaj = new AlertDialog.Builder(this).create();
		    	alertDodaj.setTitle("Tego piwa jeszcze nie piłem!"); 
		    	alertDodaj.setButton("Wyślij e-mail", new DialogInterface.OnClickListener() {
		 			        public void onClick(DialogInterface dialog, int which) {
		 			        	kreciolek.setVisibility(View.VISIBLE);
		 				    	wiadomosc(samNumer);
		 			    } });
		    	alertDodaj.setButton2("Skanuj ponownie", new DialogInterface.OnClickListener() {
		 		        public void onClick(DialogInterface dialog, int which) {
		 		        	return;
		 		    } });
		    	alertDodaj.setMessage(Html.fromHtml("Odczytano kod:<br/>  "+samNumer+"<br/><br/>Niestety nie znam takiego piwa.<br/>Zeskanuj kod ponownie, jeśli został błędnie odczytany lub wyślij nam info o nowym piwie!"));
		    	alertDodaj.show();

          //  ekranPodpis.setText("Error: " + e.getMessage());
	    }
		

		
		
		
		}
		}
		}
*/
	
	public void add(View view) {
    	Intent myIntent = new Intent(OknoGlowne.this, AddBottle.class);
    	OknoGlowne.this.startActivity(myIntent);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		   if (requestCode == 0) {
		      if (resultCode == RESULT_OK) {
		         String contents = intent.getStringExtra("SCAN_RESULT");
		         String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
		         // Handle successful scan
		         ekranZwrot.setText("Odczytano: "+ contents);
		         
		      } else if (resultCode == RESULT_CANCELED) {
		         // Handle cancel
		    	  ekranZwrot.setText("Dupa Dupa!");
		      }
		   }
		}
	
	public void producent(String kodProducenta){
		if("BW"==kodProducenta){
			ekranProducent.setText("Browar Witnica");
		} else if("KP"==kodProducenta){
			ekranProducent.setText("Kompania Piwowarska");
		} else if("GZ"==kodProducenta){
			ekranProducent.setText("Grupa Żywiec");
		} else if("CP"==kodProducenta){
			ekranProducent.setText("Carlsberg Polska");
		} else if("BF"==kodProducenta){
			ekranProducent.setText("Browar Fortuna");
		} else if("LP"==kodProducenta){
			ekranProducent.setText("Lidl Polska");
		} else if("BK"==kodProducenta){
			ekranProducent.setText("Browar Konstancin");
		} else if("BA"==kodProducenta){
			ekranProducent.setText("Browar Amber");
		} else if("BC"==kodProducenta){
			ekranProducent.setText("Browar Ciechan");
		} else if("BL"==kodProducenta){
			ekranProducent.setText("Browar Lwówek");
		} else if("LO"==kodProducenta){
			ekranProducent.setText("Browar Łomża");
		} else if("BS"==kodProducenta){
			ekranProducent.setText("Browar Sulimar");
		} else if("BP"==kodProducenta){
			ekranProducent.setText("Browar Perła");
		}
			
	}
	public void zwrotna(boolean zwrot){
		if(zwrot){
			ekranZwrot.setText("Butelka zwrotna");
		} else ekranZwrot.setText("Butelka bezzwrotna");
	}
	
	public void butelka(View view) {
		ekranPodpis.setText("Butelka kliknięta!\nUruchamiam skaner");
		kreciolek.setVisibility(View.VISIBLE);
		Button przyciskButelka;
		przyciskButelka = (Button) findViewById(R.id.button1);
		przyciskButelka.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
		skanowanie(view);
	}
	
//	void wiadomosc(String kod, String producent, boolean zwrotna) {
	void wiadomosc(String kod) {
    	Intent i = new Intent(Intent.ACTION_SEND);
    	i.setType("message/rfc822");
    	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"lukisio@siedemjeden.net"});
    	i.putExtra(Intent.EXTRA_SUBJECT, "Nowe piwo do kolekcji");
    	i.putExtra(Intent.EXTRA_TEXT   , "Uzupełnij dane butelki:\n  kod: "+kod+"\n  marka: "+"\n  zwrotna: ");
    	try {
    	    startActivity(Intent.createChooser(i, "Wybierz program pocztowy"));
    	} catch (android.content.ActivityNotFoundException ex) {
    	    Toast.makeText(OknoGlowne.this, "Nie zainstalowano żadnego programu pocztowego.", Toast.LENGTH_SHORT).show();
    	}
    	kreciolek.setVisibility(View.GONE);
	}
	
	public void skanowanie(View view) {
//		IntentIntegrator integrator = new IntentIntegrator(this);
//		integrator.initiateScan();
	//Dwie linijki do skanowania przez zewnętrzną apkę. Poniżej skanowanie przez library:
		
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
		startActivityForResult(intent, 0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_okno_glowne, menu);
		return true;
	}

}
