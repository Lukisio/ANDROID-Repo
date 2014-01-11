package com.lukis.installments;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class KlasaBudget  extends Activity {
	
	public static String urlBudSave = "http://united.webege.com/zapisz.php?table=budget";
	
	private static final String TAG_TABELA = "installments";
	private static final String TAG_LP = "lp";
	private static final String TAG_DATE = "date";
	private static final String TAG_WIRE1 = "wire1";
	private static final String TAG_WIRE2 = "wire2";
	private static final String TAG_WIRE3 = "wire3";
	private static final String TAG_MATERIAL = "material";
	private static final String TAG_INCOME = "income";
	private static final String TAG_DIVIDENDS = "dividends";
	private static final String TAG_FUEL = "fuel";
	private static final String TAG_REPAIRS = "repairs";
	private static final String TAG_INSURANCE = "insurance";
	private static final String TAG_CAR = "car";
	
	String lp, date;
	double wire1, wire2, wire3, material, income, dividends, fuel, repairs, insurance, car;
	String lista;
	public Boolean czekaj = false;
    public KlasaBudget(){
        super();
	
}


public String wypelnijBudget(int year, int month){
	String strMonth;
	if (month<10){
		strMonth="0"+month;
	} else {
		strMonth=""+month;
	}
	czekaj=true;
	new WypelnijBudget().execute(""+year, ""+strMonth);
	while(czekaj){}
	
	return date;
}


private class WypelnijBudget extends AsyncTask<String, Integer, String>{
@Override
protected String doInBackground(String... arg0) {
// TODO Auto-generated method stub
 ObslugaJSON jParser = new ObslugaJSON();
 try {
     JSONObject json = jParser.getJSONFromUrl(ADepositActivity.urlBud);
// 	Log.i("tabela: ", TAG_TABELA);
 	JSONArray budget = json.getJSONArray(TAG_TABELA);    

 	lp = "0";
	wire1 = 0;
	wire2 = 0;
	wire3 = 0;
	material = 0;
	income = 0;
	dividends = 0;
	fuel = 0;
	repairs = 0;
	insurance = 0;
	car = 0;
	  
     // looping through All Contacts
	for(int i = 0; i < budget.length(); i++){
		JSONObject z = budget.getJSONObject(i);


//		Log.i("data: ", z.getString(TAG_DATE));
		date= z.getString(TAG_DATE);
//		Log.i("data2: ", "" + arg0[0] + "-" + arg0[1]);
		
		
		if (z.getString(TAG_DATE).contains("" + arg0[0] + "-" + arg0[1]))
		{
			if(z.getString(TAG_LP).length() > 0) lista = z.getString(TAG_LP); else lista="0";
			Log.i("lista: ", lista);
			lp = lista;		
			if(z.getString(TAG_WIRE1).length() > 0) lista = z.getString(TAG_WIRE1); else lista="0";
			Log.i("lista: ", lista);
			wire1 = Double.valueOf(lista);
			Log.i("lista2: ", ""+wire1);
			if(z.getString(TAG_WIRE2).length() > 0) lista = z.getString(TAG_WIRE2); else lista="0";
			Log.i("lista: ", lista);
			wire2 = Double.valueOf(lista);
			Log.i("lista2: ", ""+wire2);
			if(z.getString(TAG_WIRE3).length() > 0) lista = z.getString(TAG_WIRE3); else lista="0";
			Log.i("lista: ", lista);
			wire3 = Double.valueOf(lista);
			Log.i("lista2: ", ""+wire3);
			if(z.getString(TAG_MATERIAL).length() > 0) lista = z.getString(TAG_MATERIAL); else lista="0";
			Log.i("lista: ", lista);
			material = Double.valueOf(lista);
			if(z.getString(TAG_INCOME).length() > 0) lista = z.getString(TAG_INCOME); else lista="0";
			Log.i("lista: ", lista);
			income = Double.valueOf(lista);
			if(z.getString(TAG_DIVIDENDS).length() > 0) lista = z.getString(TAG_DIVIDENDS); else lista="0";
			Log.i("lista: ", lista);
			dividends = Double.valueOf(lista);
			if(z.getString(TAG_FUEL).length() > 0) lista = z.getString(TAG_FUEL); else lista="0";
			Log.i("lista: ", lista);
			fuel = Double.valueOf(lista);
			if(z.getString(TAG_REPAIRS).length() > 0) lista = z.getString(TAG_REPAIRS); else lista="0";
			Log.i("lista: ", lista);
			repairs = Double.valueOf(lista);
			if(z.getString(TAG_INSURANCE).length() > 0) lista = z.getString(TAG_INSURANCE); else lista="0";
			Log.i("lista: ", lista);
			insurance = Double.valueOf(lista);
			if(z.getString(TAG_CAR).length() > 0) lista = z.getString(TAG_CAR); else lista="0";
			Log.i("lista: ", lista);
			car = Double.valueOf(lista);
		} 
//		{

		
	}
			
 } catch (JSONException e) {
     e.printStackTrace();
 }
  czekaj=false;

return null;
}}


public static void nowyBudget(String lp, String date, String wire1, String wire2, String wire3,
		String material, String income, String dividends, String fuel,
		String repairs, String insurance, String car) {
	String pelnyAdres=urlBudSave;
	Log.i("datte: ", date);
	if (Integer.valueOf(lp)==0) {
		lp = "";
	}
	pelnyAdres+="&lp=" + lp + "&date=" + date + "&wire1=" + wire1 + "&wire2=" + wire2 + "&wire3=" + wire3
			+"&material=" + material + "&income=" + income + "&dividends=" + dividends
			+"&fuel=" + fuel + "&repairs=" + repairs + "&insurance=" + insurance + "&car=" + car;
	Log.i("Pelny adres: ", pelnyAdres);
    try {
		URI uriPelnyAdres = new URI(pelnyAdres.replace(" ", "%20"));
		pelnyAdres=uriPelnyAdres.toString();
		Log.i("Pelny adres bez białych znaków: ", pelnyAdres);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(pelnyAdres);
        httpClient.execute(httpPost);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
    	e.printStackTrace();
    } catch (URISyntaxException e) {
		e.printStackTrace();
	}
}

}