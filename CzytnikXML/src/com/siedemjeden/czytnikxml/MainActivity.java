package com.siedemjeden.czytnikxml;


import java.net.URL;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.siedemjeden.czytnikxml.R;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Handler mHandler;
	TextView ekran, ekran2, ekranBM1, ekranBM2, ekran5130, ekran70361, ekran70362, ekranCell, ekranNity;
	String poprzedni;
	int i=0;
	private static final int NOTIFY_ME_ID=1987;
	private int count=0;
	private NotificationManager mgr=null;
	Button But5130, ButBM1, ButBM2, But70361, But70362, ButCell, ButNit;
	int stanBM1=0, stanBM2=0, stan5130=0, stan70361=0, stan70362=0, stanCell=0, stanNity=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	 
	//	final String MY_DEBUG_TAG = "Parser XML";
		ekran = (TextView)findViewById(R.id.textView1);
		ekran2 = (TextView)findViewById(R.id.textView2);
		ekranBM1 = (TextView)findViewById(R.id.textView3);
		ekranBM2 = (TextView)findViewById(R.id.textView4);
		ekran5130 = (TextView)findViewById(R.id.textView5);
		ekran70361= (TextView)findViewById(R.id.textView6);
		ekran70362 = (TextView)findViewById(R.id.textView7);
		ekranCell = (TextView)findViewById(R.id.textView8);
		ekranNity = (TextView)findViewById(R.id.textView9);
		
        mHandler = new Handler();
        mHandler.post(mUpdate);
		
        ButBM1 = (Button) findViewById(R.id.button1);
        ButBM1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		}); //Koniec "ButBM1"
        
        ButBM2 = (Button) findViewById(R.id.button2);
        ButBM2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		}); //Koniec "ButBM2"
        
        But5130 = (Button) findViewById(R.id.button3);
        But5130.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		}); //Koniec "But5130"  
        
        But70361 = (Button) findViewById(R.id.button4);
        But70361.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		}); //Koniec "But70361"
        
        But70362 = (Button) findViewById(R.id.button5);
        But70362.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		}); //Koniec "But70362"
        
        ButCell = (Button) findViewById(R.id.button6);
        ButCell.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		}); //Koniec "ButCell"
        
        ButNit = (Button) findViewById(R.id.button7);
        ButNit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		}); //Koniec "ButNit"
    
    //* Display the TextView. *
	//	this.setContentView(ekran);
	//	ekran.setText("Dzień dobry!");
	
	}
	
	private Runnable mUpdate = new Runnable() {
		   public void run() {

			   
			   
			 /*  
				 //Define Notification Manager
			   NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

			   //Define sound URI
			   Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

			   long[] vibraPattern = {0, 300, 200, 200, 80, 200, 80, 200, 80, 300 };
			   NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
			           .setVibrate(vibraPattern)
			           .setContentTitle("title")
			           .setContentText("message")
			           .setSound(soundUri); //This sets the sound to play
*/
			  
			   //Display notification
		//	   notificationManager.notify(0, mBuilder.build());
		   
			   try {
		            //* Create a URL we want to load some xml-data from. 
		            URL url = new URL("http://192.168.1.5/android/plik2.xml");
		            
		            
		            //* Get a SAXParser from the SAXPArserFactory. 
		            SAXParserFactory spf = SAXParserFactory.newInstance();
		            SAXParser sp = spf.newSAXParser();

		            //* Get the XMLReader of the SAXParser we created. *
		            XMLReader xr = sp.getXMLReader();

		            //* Create a new ContentHandler and apply it to the XML-Reader*
		            ExampleHandler myExampleHandler = new ExampleHandler();
		            xr.setContentHandler(myExampleHandler);

		            //* Parse the xml-data from our URL. 
		            xr.parse(new InputSource(url.openStream()));
		            
		            //* Parsing has finished. 

		            //* Our ExampleHandler now provides the parsed data to us. 
		            ParsedExampleDataSet parsedExampleDataSet = myExampleHandler.getParsedData();
		            i++;
		            //* Set the result to be displayed in our GUI. 
		            ekran.setText("liczba cykli i="+i);
		        //   ekran.setText(""+ parsedExampleDataSet.toStringBM1() + " i="+i);
		            if (parsedExampleDataSet.toStringBM1().equals(poprzedni)){
		            	ekran2.setText("hmm");
		            } else {
		            	ekran2.setText("NOWY TEKST!");
		            	poprzedni= new String(parsedExampleDataSet.toStringBM1());
		            }
		            //	notificationManager.notify(0, mBuilder.build());
		          //  	if (i>2){
		            		if(parsedExampleDataSet.getExtractedIntBM1()!=stanBM1){
		            			przywolaj(1);
		            			stanBM1=parsedExampleDataSet.getExtractedIntBM1();
		            			ekranBM1.setText(""+parsedExampleDataSet.toStringBM1());
		            		} else if (parsedExampleDataSet.getExtractedIntBM1()==0){
		            			ButBM1.getBackground().setColorFilter(null);
		            			ekranBM1.setText("");
		            		}
		            		if(parsedExampleDataSet.getExtractedIntBM2()!=stanBM2){
		            			przywolaj(2);
		            			stanBM2=parsedExampleDataSet.getExtractedIntBM2();
		            			ekranBM2.setText(""+parsedExampleDataSet.toStringBM2());
		            		} else if (parsedExampleDataSet.getExtractedIntBM2()==0){
		            			ekranBM2.setText("");
		            			ButBM2.getBackground().setColorFilter(null);
		            		}
		            		if(parsedExampleDataSet.getExtractedInt5130()!=stan5130){
		            			przywolaj(3);
		            			stan5130=parsedExampleDataSet.getExtractedInt5130();
		            			ekran5130.setText(""+parsedExampleDataSet.toString5130());
		            		} else if (parsedExampleDataSet.getExtractedInt5130()==0){
		            			ekran5130.setText("");
		            			But5130.getBackground().setColorFilter(null);
		            		}
		            		if(parsedExampleDataSet.getExtractedInt70361()!=stan70361){
		            			przywolaj(4);
		            			stan70361=parsedExampleDataSet.getExtractedInt70361();
		            			ekran70361.setText(""+parsedExampleDataSet.toString70361());
		            		} else if (parsedExampleDataSet.getExtractedInt70361()==0){
		            			ekran70361.setText("");
		            			But70361.getBackground().setColorFilter(null);
		            		}
		            		if(parsedExampleDataSet.getExtractedInt70362()!=stan70362){
		            			przywolaj(5);
		            			stan70362=parsedExampleDataSet.getExtractedInt70362();
		            			ekran70362.setText(""+parsedExampleDataSet.toString70362());
		            		} else if (parsedExampleDataSet.getExtractedInt70362()==0){
		            			ekran70362.setText("");
		            			But70362.getBackground().setColorFilter(null);
		            		}
		            		if(parsedExampleDataSet.getExtractedIntCell()!=stanCell){
		            			przywolaj(6);
		            			stanCell=parsedExampleDataSet.getExtractedIntCell();
		            			ekranCell.setText(""+parsedExampleDataSet.toStringCell());
		            		} else if (parsedExampleDataSet.getExtractedIntCell()==0){
		            			ekranCell.setText("");
		            			ButCell.getBackground().setColorFilter(null);
		            		}
		            		if(parsedExampleDataSet.getExtractedIntNity()!=stanNity){
		            			przywolaj(7);
		            			stanNity=parsedExampleDataSet.getExtractedIntNity();
		            			ekranNity.setText(""+parsedExampleDataSet.toStringNity());
		            		} else if (parsedExampleDataSet.getExtractedIntNity()==0){
		            			ekranNity.setText("");
		            			ButNit.getBackground().setColorFilter(null);
		            		}
		            		
		            		
		            //		ekranBM1.setText(""+parsedExampleDataSet.getExtractedIntBM1());
		            //		ekranBM2.setText(""+parsedExampleDataSet.getExtractedIntBM2());
		            //		ekran5130.setText(""+parsedExampleDataSet.getExtractedInt5130());
		            //		ekran70361.setText(""+parsedExampleDataSet.getExtractedInt70361());
		            //		ekran70362.setText(""+parsedExampleDataSet.getExtractedInt70362());
		            //		ekranCell.setText(""+parsedExampleDataSet.getExtractedIntCell());
		            //		ekranNity.setText(""+parsedExampleDataSet.getExtractedIntNity());
		            	
		            

		        //    Log.i("Teraz:", parsedExampleDataSet.toStringBM1());
		        //    Log.i("Był:", poprzedni);

		    } catch (Exception e) {
		            // Display any Error to the GUI. 
		            ekran.setText("Error: " + e.getMessage());
		         //   Log.e(MY_DEBUG_TAG, "WeatherQueryError", e);
		    }

		       mHandler.postDelayed(this, 3000);
		    }
		};

	    private void przywolaj(int numer){
	        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	         
	        int icon = R.drawable.icon;
	        CharSequence text;
	        CharSequence contentText;
	        switch (numer) {
	        case 1:{
		        text = "Podejdź na BM1";
		        contentText = "Podejdź na BM1!!!!";
		        ButBM1.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
	          break;}
	        case 2:{
		        text = "Podejdź na BM2";
		        contentText = "Podejdź na BM2!!!!";
		        ButBM2.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
		      break;}
	        case 3:{
		        text = "Podejdź na 5130";
		        contentText = "Podejdź na 5130!!!!";
		        But5130.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
	          break;}
	        case 4:{
		        text = "Podejdź na 7036-1";
		        contentText = "Podejdź na 7036-1!!!!";
		        But70361.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
	          break;}
	        case 5:{
		        text = "Podejdź na 7036-2";
		        contentText = "Podejdź na 7036-2!!!!";
		        But70362.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
	          break;}
	        case 6:{
		        text = "Podejdź na Cell";
		        contentText = "Podejdź na Cell!!!!";
		        ButCell.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
		      break;}
	        case 7:{
		        text = "Podejdź na Nitownice";
		        contentText = "Podejdź na Nitownice!!!!";
		        ButNit.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
		      break;}
	        default:{
		        text = "Błędna maszyna";
		        contentText = "Tej maszyny jeszcze nie mamy";
		      break;}
	      }

	        CharSequence contentTitle = "Przywołanie";
	        
	        long when = System.currentTimeMillis();
	         
	        Intent intent = new Intent(this, MainActivity.class);
	        intent.setAction("android.intent.action.MAIN");
	        intent.addCategory("android.intent.category.LAUNCHER");
	        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
	        Notification notification = new Notification(icon,text,when);
	         
	        long[] vibrate = {0,100,200,300};
	        notification.vibrate = vibrate;
	         
	        notification.ledARGB = Color.RED;
	        notification.ledOffMS = 300;
	        notification.ledOnMS = 300;
	         
	        notification.defaults |= Notification.DEFAULT_LIGHTS;
	        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
	        notification.flags|=Notification.FLAG_AUTO_CANCEL;

	        notification.setLatestEventInfo(this, contentTitle, contentText, contentIntent);
	     //   But5130.getBackground().setColorFilter(0xFFFF0000,PorterDuff.Mode.MULTIPLY);
	        
	        notificationManager.notify(0, notification);
	    }
	    

	    
/*	  public void notifyMe() {
		    Notification note=new Notification(R.drawable.icon,"Status message!",System.currentTimeMillis());
		    PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, MySampleNotificationMessage.class),0);
		    note.setLatestEventInfo(this, "New Email","Unread Conversation", i);
		    note.number=++count;
		    note.vibrate=new long[] {500L, 200L, 200L, 500L};
		    note.flags|=Notification.FLAG_AUTO_CANCEL;
		    mgr.notify(NOTIFY_ME_ID, note);
		  }
	  public void clearNotification() {
	    mgr.cancel(NOTIFY_ME_ID);
	  } */
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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
			
		
		return false;
	}


}
