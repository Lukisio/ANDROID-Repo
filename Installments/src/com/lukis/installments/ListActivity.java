package com.lukis.installments;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListActivity extends Activity {

	public static String url = "http://united.webege.com/generator.php";
	public static String urlCash = "http://united.webege.com/cash.php";
	public static String urlMonthlyPayment = "http://united.webege.com/monthly_payment.php";
	public static String urlCabital = "http://united.webege.com/cabital.php";
	public static String urlCabitalMonth = "http://united.webege.com/cabitalMonth.php";
	public static String urlTotalsMonth = "http://united.webege.com/summaryDate.php";
	public static String urlZapis = "http://united.webege.com/zapisz.php";
	public static String urlRata = "http://united.webege.com/raty.php";

	// JSON Node names
	static final String TAG_TABELA = "installments";
	private static final String TAG_DATELIST = "datelist";
	private static final String TAG_PAYLIST = "pay";
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
	private static final String TAG_PAYEDTOTAL = "payedtotal";
	JSONArray zbrojenia = null;
	public Boolean czekaj = false;

//	TextView info;
	TextView eTotal;
	int lp, dlugosc;
	public String[][] lista;
	public String date, address, item, name;
	public String whichTable;
	public int buyPrice, sellPrice, downpay, monthpay, payed, total = 0;
	public KlasaUser detal = new KlasaUser();

	ProgressBar kreciolek;
	TableLayout table;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
//		info = (TextView) findViewById(R.id.textView1);
		eTotal = (TextView) findViewById(R.id.textView2);
		kreciolek = (ProgressBar) findViewById(R.id.progressBar1);

		/*
		 * // Dynamiczne dodawanie linii do tabeli TableLayout table =
		 * (TableLayout)ListActivity.this.findViewById(R.id.tableLayout);
		 * for(int i=0;i<imiona.length;i++) { lp=i; date="12.09.2013";
		 * name=imiona[i]; address="Pod mostem"; item=rzeczy[i];
		 * buyPrice=kwota[i]; sellPrice=kwotaSprzedazy[i]; downpay=50.0;
		 * monthpay=15.0; payed=23.5;
		 * 
		 * // Inflate your row "template" and fill out the fields. TableRow row
		 * =
		 * (TableRow)LayoutInflater.from(ListActivity.this).inflate(R.layout.one_row
		 * , null);
		 * ((TextView)row.findViewById(R.id.attrib_name)).setText(imiona[i]);
		 * ((TextView
		 * )row.findViewById(R.id.attrib_lp)).setText(String.valueOf(lp));
		 * ((TextView)row.findViewById(R.id.attrib_item)).setText(rzeczy[i]);
		 * ((TextView
		 * )row.findViewById(R.id.attrib_value)).setText(String.valueOf
		 * (buyPrice)); row.setClickable(true); row.setFocusable(true); //
		 * row.setFocusableInTouchMode(true);
		 * row.setOnClickListener(rowOnClickListener);
		 * 
		 * 
		 * table.addView(row); } table.requestLayout();
		 */
		/*
		 * new WypelnijTabele().execute(""); while(czekaj){}
		 * 
		 * 
		 * TableLayout table =
		 * (TableLayout)ListActivity.this.findViewById(R.id.tableLayout);
		 * for(int i=0;i<dlugosc;i++) {
		 * 
		 * // Inflate your row "template" and fill out the fields. TableRow row
		 * =
		 * (TableRow)LayoutInflater.from(ListActivity.this).inflate(R.layout.one_row
		 * , null);
		 * ((TextView)row.findViewById(R.id.attrib_lp)).setText(lista[i][0]);
		 * ((TextView)row.findViewById(R.id.attrib_name)).setText(lista[i][1]);
		 * ((TextView)row.findViewById(R.id.attrib_item)).setText(lista[i][2]);
		 * ((TextView)row.findViewById(R.id.attrib_value)).setText(lista[i][3]);
		 * row.setClickable(true); row.setFocusable(true); //
		 * row.setFocusableInTouchMode(true);
		 * row.setOnClickListener(rowOnClickListener);
		 * 
		 * table.addView(row); } table.requestLayout();
		 */

	}

	protected void onResume() {
		super.onResume();

		Bundle z = getIntent().getExtras();
		if (z == null) {
			new WypelnijTabele().execute("");
		} else {
			if (z.getString("NAME") != null) {
				name = z.getString("NAME");
				Log.i("z.name", name);
				if (z.getString("TABLE") != null) {
					whichTable = z.getString("TABLE");
				}
				Log.i("ListActivity", "WypelnijTabele().execute(name)");
				new WypelnijTabele().execute(name);
			} else if (z.getString("LP") != null) {
				String lp = z.getString("LP");
				Log.i("z.lp", lp);
				Log.i("ListActivity", "JedenDetal().execute(lp)");
				new JedenDetal().execute(lp);

			} else if (z.getString("ADDRESS") != null) {
				address = z.getString("ADDRESS");
			} else if (z.getString("TABLE") != null) {
				whichTable = z.getString("TABLE");
				Log.i("ListActivity", "WypelnijTabele().execute()");
				new WypelnijTabele().execute("");
			}
		}

//		while (czekaj) {
//		}


	}
	
	public void printTable(){
		if (!name.equals("")) {
			eTotal.setText("مجموع: " + total);
		}

		table = (TableLayout) ListActivity.this.findViewById(R.id.tableLayout);
		table.removeAllViews();
		
		//set header of table
		TableRow row = (TableRow) LayoutInflater.from(ListActivity.this)
				.inflate(R.layout.one_row, null);
		((TextView) row.findViewById(R.id.attrib_lp)).setText("رقم"); 
//	    ((TextView) row.findViewById(R.id.attrib_lp)).setGravity(Gravity.RIGHT);
	    ((TextView) row.findViewById(R.id.attrib_lp)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		((TextView) row.findViewById(R.id.attrib_name)).setText("اسم");
	    ((TextView) row.findViewById(R.id.attrib_name)).setGravity(Gravity.RIGHT);
	    ((TextView) row.findViewById(R.id.attrib_name)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		((TextView) row.findViewById(R.id.attrib_item)).setText("جهاز");
	    ((TextView) row.findViewById(R.id.attrib_item)).setGravity(Gravity.RIGHT);
	    ((TextView) row.findViewById(R.id.attrib_item)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		((TextView) row.findViewById(R.id.attrib_value1)).setText("قسط");
//	    ((TextView) row.findViewById(R.id.attrib_value1)).setGravity(Gravity.RIGHT);
	    ((TextView) row.findViewById(R.id.attrib_value1)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		((TextView) row.findViewById(R.id.attrib_value2)).setText("باقي");
//	    ((TextView) row.findViewById(R.id.attrib_value2)).setGravity(Gravity.RIGHT);
	    ((TextView) row.findViewById(R.id.attrib_value2)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		
	    table.addView(row);
		for (int i = 0; i < dlugosc; i++) {

//			Log.i("printTable lista[i][0]: ", lista[i][0]);
//			Log.i("printTable lista[i][1]: ", lista[i][1]);
//			Log.i("printTable lista[i][2]: ", lista[i][2]);
//			Log.i("printTable lista[i][3]: ", lista[i][3]);
//			Log.i("printTable lista[i][4]: ", lista[i][4]);

			// Inflate your row "template" and fill out the fields.
			row = (TableRow) LayoutInflater.from(ListActivity.this)
					.inflate(R.layout.one_row, null);
			((TextView) row.findViewById(R.id.attrib_lp)).setText(lista[i][0]);
			((TextView) row.findViewById(R.id.attrib_name)).setText(lista[i][1]
					.split(" ")[0]);
			((TextView) row.findViewById(R.id.attrib_item))
					.setText(lista[i][2]);
			((TextView) row.findViewById(R.id.attrib_value1))
					.setText(lista[i][3]);
			((TextView) row.findViewById(R.id.attrib_value2))
					.setText(lista[i][4]);
			row.setClickable(true);
			row.setFocusable(true);
			// row.setFocusableInTouchMode(true);
			row.setOnClickListener(rowOnClickListener);

			table.addView(row);
		}
		table.requestLayout();
//		info.setText("");
		
	}

	private OnClickListener rowOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			// GET TEXT HERE

			String lp = ((TextView) v.findViewById(R.id.attrib_lp)).getText()
					.toString();
			new JedenDetal().execute(lp);

		}
	};

	public void goDetails() { // wysyła dane konkretnego wpisu do kolejnej
								// Activity
		Log.i("goDetails lp", detal.lp);
		Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
		intent.putExtra("LP", detal.lp);
		intent.putExtra("DATE", detal.date);
		intent.putExtra("NAME", detal.name);
		intent.putExtra("ADDRESS", detal.address);
		intent.putExtra("ITEM", detal.item);
		intent.putExtra("BUY_PRICE", detal.buyPrice);
		intent.putExtra("SELL_PRICE", detal.sellPrice);
		intent.putExtra("DOWNPAY", detal.downpay);
		intent.putExtra("MONTHPAY", detal.monthpay);
		intent.putExtra("PAYED", detal.numberPayed);
		intent.putExtra("REMAIN", detal.remain);
		intent.putExtra("PAYED_TOTAL", detal.payedTotal);
		intent.putExtra("TOPAY", String.valueOf(detal.toPay));
		finish();
		startActivity(intent);
	}

	public void newEntry(View view) {

		Intent intent = new Intent(ListActivity.this, CreateActivity.class);
		intent.putExtra("NAME", name);
		intent.putExtra("ADDRES", address);
		// Log.i("addres intent", address);
		finish();
		startActivity(intent);
	}

	private class WypelnijTabele extends AsyncTask<String, Integer, String> {
		// wypelnia listę kilkoma kolumnami z calej tabeli
		@Override
		protected String doInBackground(String... arg0) {
			ObslugaJSON jParser = new ObslugaJSON();
			name = arg0[0];
			// TODO jeśli arg0 jest "name" to wyszukaj url
			// JSONObject json = jParser.getJSONFromUrl(url+ "?userlist=1&name="
			// + name);
			try {
				JSONObject json;
				if (whichTable != null && !name.equals(""))
					Log.i("url: ", url + "?table=" + whichTable + "&userlist=1&name=" + URLEncoder.encode(name, "UTF-8"));
					json = jParser.getJSONFromUrl(url + "?table=" + whichTable + "&userlist=1&name=" + URLEncoder.encode(name, "UTF-8"));
				if (whichTable != null && name.equals("")) {
					Log.i("url: ", url + "?table=" + whichTable);
					json = jParser.getJSONFromUrl(url + "?table=" + whichTable);
				} else if (whichTable == null && name.equals("")) {
					Log.i("url: ", url);
					json = jParser.getJSONFromUrl(url);
				} else {
					Log.i("url: ", url + "?userlist=1&name=" + URLEncoder.encode(name, "UTF-8"));
					json = jParser.getJSONFromUrl(url + "?userlist=1&name=" + URLEncoder.encode(name, "UTF-8"));
				}

				Log.i("tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);
				dlugosc = zbrojenia.length();
				lista = new String[dlugosc][5];
				total = 0;

				Log.i("dlugosc: ", "" + dlugosc);
				// looping through All Contacts
				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);

					// Storing each json item in variable
					lista[i][0] = z.getString(TAG_LP);
					lista[i][1] = z.getString(TAG_NAME);
					lista[i][2] = z.getString(TAG_ITEM);
					lista[i][3] = z.getString(TAG_MONTHPAY);
					if (!name.equals(""))
						total += Integer.valueOf(lista[i][3]);
					lista[i][4] = ""
							+ (Integer.valueOf(z.getString(TAG_SELLPRICE))
									- Integer.valueOf(z.getString(TAG_DOWNPAY)) - Integer
										.valueOf(z.getString(TAG_PAYEDTOTAL))); // toPay
					address = z.getString(TAG_ADDRES);
					Log.i("Address: ", address);
					Log.i("tag.address: ", z.getString(TAG_ADDRES));
					// publishProgress(i);

					Log.i("lista[i][0]: ", lista[i][0]);
					Log.i("lista[i][1]: ", lista[i][1]);
					Log.i("lista[i][2]: ", lista[i][2]);
					Log.i("lista[i][3]: ", lista[i][3]);
					Log.i("lista[i][4]: ", lista[i][4]);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
//			czekaj = false;

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// setProgressBar(STOP_PROGRESS);
			// button.setEnabled(true);
			kreciolek.setVisibility(View.GONE);
			// info.setText("name: "+detal.name);
			printTable();
		}

		@Override
		protected void onPreExecute() {
//			czekaj = true;
			kreciolek.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			// info.setText(""+progress[0]);
		}
	}

	public void wyszukajButton(View view) {
		// wyszukaj("3");
		// new JedenDetal().execute("2");
		new WypelnijTabele().execute("");

	}

	private class JedenDetal extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {
			String szukanyNumer = arg0[0];
			
			ObslugaJSON jParser = new ObslugaJSON();

			try {
				// getting JSON string from URL
				JSONObject json = jParser.getJSONFromUrl(url + "?lp="
						+ szukanyNumer);
				// Getting Array of Contacts

				Log.i("JedenDetal tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);

				// looping through All Contacts
				// for(int i = 0; i < zbrojenia.length(); i++){
				int i = 0; // numer linijki w obiekcie JSONObject.
				JSONObject z = zbrojenia.getJSONObject(i);

				// Storing each json item in variable
				detal.lp = z.getString(TAG_LP);
				detal.date = z.getString(TAG_DATE);
				detal.name = z.getString(TAG_NAME);
				detal.address = z.getString(TAG_ADDRES);
				detal.item = z.getString(TAG_ITEM);
				detal.buyPrice = z.getString(TAG_BUYPRICE);
				detal.sellPrice = z.getString(TAG_SELLPRICE);
				detal.downpay = z.getString(TAG_DOWNPAY);
				detal.monthpay = z.getString(TAG_MONTHPAY);
				detal.numberPayed = z.getString(TAG_PAYED);
				detal.payedTotal = z.getString(TAG_PAYEDTOTAL);
				detal.payments = new String[2][Integer.valueOf(detal.numberPayed) + 1];
				detal.toPay = Integer.valueOf(detal.sellPrice)
						- Integer.valueOf(detal.downpay)
						- Integer.valueOf(detal.payedTotal);

				for (int j = 1; j <= Integer.valueOf(detal.numberPayed); j++) {
					// Log.i("paylist: ", z.getString(TAG_PAYLIST+j));
					detal.payments[0][j] = z.getString(TAG_DATELIST + j);
					// detal.payments[1][j]=z.getString(TAG_PAYLIST+j);
					detal.payments[1][j] = detal.monthpay;
				}

			} catch (JSONException e) {
				e.printStackTrace();
				Log.i("JedenDetal: ", "" + e);
				return null;
			}
//			czekaj = false;
			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			kreciolek.setVisibility(View.GONE);
//			info.setText("name: " + detal.name);
			goDetails();
		}

		@Override
		protected void onPreExecute() {
			kreciolek.setVisibility(View.VISIBLE);
//			czekaj = true;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
		}
	}


	public void onBackPressed() // wracasz do poprzedniego activity
	{
		if (whichTable != null) {
			Intent intent = new Intent(this, MainActivity.class);
			finish();
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, UsersActivity.class);
			finish();
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
