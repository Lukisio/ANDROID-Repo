package com.lukis.installments;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

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
	    String note;
	    String buyPrice;
	    String sellPrice;
	    String downpay;
	    String monthpay;
	    String numberPayed;
	    String payedTotal;
	    String remain;
	    int toPay;
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
		private static final String TAG_NOTE = "note";
		private static final String TAG_BUYPRICE = "buyprice";
		private static final String TAG_SELLPRICE = "sellprice";
		private static final String TAG_DOWNPAY = "downpay";
		private static final String TAG_MONTHPAY = "monthpay";
		private static final String TAG_PAYED = "numberpayed";
		private static final String TAG_PAYEDTOTAL = "payedtotal";
	    
		
		public static void utworz(KlasaUser detal) {
			String pelnyAdres=ListActivity.urlZapis;
			try {
				pelnyAdres+="?date=" + detal.date + "&name=" + URLEncoder.encode(detal.name, "UTF-8") + "&address=" + detal.address + 
						"&item=" + URLEncoder.encode(detal.item, "UTF-8") + "&buyprice=" + detal.buyPrice + "&sellprice=" + detal.sellPrice + "&downpay=" + detal.downpay
						+ "&monthpay=" + detal.monthpay + "&numberpayed=" + detal.numberPayed;
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		
		
		
		
		public static void nowaWplata(String lp, String date, String payment, String nrRaty, String payedTotal, String note) {
			String pelnyAdres=ListActivity.urlRata;
			int nowyNrRaty = Integer.valueOf(nrRaty);
			nowyNrRaty++;
			pelnyAdres+="?lp=" + lp + "&datelist" + nowyNrRaty + "=" + date + "&numberpayed=" + nowyNrRaty + "&payedtotal=" + payedTotal + "&note=" + note;

//			pelnyAdres+="?lp=" + lp + "&paylist" + nowyNrRaty + "=" + payment + "&datelist" + nowyNrRaty + "=" + date + "&numberpayed=" + nowyNrRaty + "&payedtotal=" + payedTotal;
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
		
		
		public static void nowyKoszt(String lp, String name, String date, String value, String description) {
			String pelnyAdres=NewExpenseActivity.urlExp;

			try {
				pelnyAdres+="&lp=" + lp + "&name=" + URLEncoder.encode(name, "UTF-8") + "&date=" + date + "&value=" + value + "&description=" +  URLEncoder.encode(description, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
