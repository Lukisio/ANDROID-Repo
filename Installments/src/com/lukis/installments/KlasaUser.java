package com.lukis.installments;

import java.io.IOException;
import java.io.InputStream;
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
import android.util.Log;



public class KlasaUser extends Activity {
	    String lp;
	    String date;
	    String name;
	    String address;
	    String item;
	    String buyPrice;
	    String sellPrice;
	    String downpay;
	    String monthpay;
	    String numberPayed;
	    String remain;
	    Double toPay;
	    public String[][] payments;
	    public KlasaUser(){
	        super();
	    }

		private static final String TAG_TABELA = "installments";
		private static final String TAG_DATELIST = "datelist";
		private static final String TAG_PAYLIST = "paylist";
		private static final String TAG_LP = "lp";
		private static final String TAG_DATE = "date";
		private static final String TAG_NAME = "name";
		private static final String TAG_ADDRES = "address";
		private static final String TAG_ITEM = "item";
		private static final String TAG_BUYPRICE = "buyprice";
		private static final String TAG_SELLPRICE = "sellprice";
		private static final String TAG_DOWNPAY = "downpay";
		private static final String TAG_MONTHPAY = "monthpay";
		private static final String TAG_PAYED = "numberpayed";
	    
		
		public static void utworz(KlasaUser detal) {
			String pelnyAdres=ListActivity.urlZapis;
			pelnyAdres+="?date=" + detal.date + "&name=" + detal.name + "&address=" + detal.address + 
					"&item=" + detal.item + "&buyprice=" + detal.buyPrice + "&sellprice=" + detal.sellPrice + "&downpay=" + detal.downpay
					+ "&monthpay=" + detal.monthpay + "&numberpayed=" + detal.numberPayed;
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
		
		
		
		
		public static void nowaWplata(String lp, String date, String payment, String nrRaty) {
			String pelnyAdres=ListActivity.urlRata;
			int nowyNrRaty = Integer.valueOf(nrRaty);
			nowyNrRaty++;
			pelnyAdres+="?lp=" + lp + "&paylist" + nowyNrRaty + "=" + payment + "&datelist" + nowyNrRaty + "=" + date + "&numberpayed=" + nowyNrRaty;
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
		
		
		public static void nowyKoszt(String name, String date, String value) {
			String pelnyAdres=NewExpenseActivity.urlExp;

			pelnyAdres+="&name=" + name + "&date=" + date + "&value=" + value;
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
