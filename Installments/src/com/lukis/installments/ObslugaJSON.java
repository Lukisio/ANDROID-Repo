package com.lukis.installments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.util.Log;

public class ObslugaJSON {

	     InputStream is = null;
	     JSONObject jObj = null;
	     String json = "";


	public ObslugaJSON() {
		// TODO Auto-generated constructor stub
	}

	
	public String bzdury() {
		String tekst = "Takie bzdury";
		tekst+=", ze strach";
		return ListActivity.url;
	}
	
   public int dlugoscJson() {
	   ObslugaJSON jParser = new ObslugaJSON();
	   JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
	   JSONArray lista=null;
       try {
    	   lista = json.getJSONArray(ListActivity.TAG_TABELA);
       } catch (JSONException e) {
           e.printStackTrace();
       }
	   return lista.length();
   }
	 
    public JSONObject getJSONFromUrl(String url) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        
        
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }
/*
	public static void zapisz(KlasaUser detal) {
		InputStream isZ = null;
		String pelnyAdres=ListActivity.urlZapis;

//		detal.zbbmg="zarażony!";
		pelnyAdres+="?=lp" + detal.lp + "&date=" + detal.date + "&name=" + detal.name + "&address=" + detal.address + 
				"&item=" + detal.item + "&buyprice=" + detal.buyPrice + "&sellprice=" + detal.sellPrice + "&downpay=" + detal.downpay
				+ "&monthpay=" + detal.monthpay + "&payed=" + detal.payed;
				

	//	pelnyAdres+="?numer=" + detal.numer + "&info=" + detal.info + "&zbrojbmg=" + detal.zbbmg + "&zbrojbmd=" + detal.zbbmd + "&zbroj5130g=" + detal.zb5130g + "&zbroj5130d=" + detal.zb5130d + "&zbroj7036g=" + detal.zb7036g + "&zbroj7036d=" + detal.zb7036d;
		Log.i("Pelny adres: ", pelnyAdres);

        try {
    		URI uriPelnyAdres = new URI(pelnyAdres.replace(" ", "%20"));
    		pelnyAdres=uriPelnyAdres.toString();
    		Log.i("Pelny adres bez białych znaków: ", pelnyAdres);
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(pelnyAdres);
 
            httpClient.execute(httpPost);
            
    //        HttpResponse httpResponse = httpClient.execute(httpPost);
    //        HttpEntity httpEntity = httpResponse.getEntity();   
    //        isZ = httpEntity.getContent();   
   //         isZ.close();
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
			e.printStackTrace();
		}
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    isZ, "utf8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isZ.close();
            String tekstZapisu = sb.toString();
            Log.i("Info ze strony po zapisie: ", tekstZapisu);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
	}
*/	
}
