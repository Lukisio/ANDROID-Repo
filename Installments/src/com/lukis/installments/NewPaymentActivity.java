package com.lukis.installments;

import java.util.Date;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NewPaymentActivity extends Activity {

	public KlasaUser det = new KlasaUser();
	Date todayDate = new Date();
	TextView eDate, ePayment, eNote;
	Button bSave;
	Button bCancel;
	ProgressBar kreciolek;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_payment);
		kreciolek = (ProgressBar) findViewById(R.id.progressBar1);

		Bundle z = getIntent().getExtras();

		det.lp = z.getString("LP");
		det.date = z.getString("DATE");
		det.name = z.getString("NAME");
		det.address = z.getString("ADDRESS");
		det.item = z.getString("ITEM");
		det.note = z.getString("NOTE");
		det.buyPrice = z.getString("BUY_PRICE");
		det.sellPrice = z.getString("SELL_PRICE");
		det.downpay = z.getString("DOWNPAY");
		det.monthpay = z.getString("MONTHPAY");
		det.numberPayed = z.getString("PAYED");
		det.payedTotal = z.getString("PAYED_TOTAL");
		det.currentProfit = z.getString("CURRENT_PROFIT");
		det.toPay = Integer.valueOf(z.getString("TOPAY"));

		int monthpay = Integer.valueOf(det.monthpay);
		Log.i("monthpay: ", ""+monthpay);
		Log.i("toPay: ", ""+det.toPay);
		// det.payments[][] = z.getString("PAYMENTS");
		// TODO trzeba przerobić na parcellable lub serializable

		eDate = (TextView) findViewById(R.id.editText1);
		eDate.setText((1900 + todayDate.getYear()) + "-"
				+ (todayDate.getMonth() + 1) + "-" + todayDate.getDate());
		
		eNote = (TextView) findViewById(R.id.editText3);

		ePayment = (TextView) findViewById(R.id.editText2);
		if (monthpay<det.toPay){
			ePayment.setText(""+monthpay);			
			eNote.setText(det.note);
		}else {
			ePayment.setText(""+det.toPay);						
			eNote.setText("اخر قسط");
		}

		bSave = (Button) findViewById(R.id.button1);
		bSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				save(v);
			}
		});
		
		bCancel = (Button) findViewById(R.id.button2);
		bCancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				cancel(v);
			}
		});

		eDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				rolkaDaty(v);
			}
		});
	}

	public void rolkaDaty(View view) {
		DatePickerDialog dpd = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						eDate.setText(year + "-" + (monthOfYear + 1) + "-"
								+ dayOfMonth);
					}
				}, (1900 + todayDate.getYear()), (todayDate.getMonth()),
				todayDate.getDate());
		dpd.show();
	}

	public void save(View view) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Add payment");
		alert.setMessage("Do you want to save your new payment?");
		alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String lp = det.lp;
				String nrRaty = det.numberPayed;
				det.numberPayed = ("" + nrRaty);
				String data;
				String rata;
				int splacone;
				int monthpay;
				String note = eNote.getText().toString();

				data = eDate.getText().toString();
				if (data.matches(""))
					data = ""
							+ ((1900 + todayDate.getYear()) + "-"
									+ (todayDate.getMonth() + 1) + "-" + todayDate
										.getDate());

				rata = ePayment.getText().toString();
				if (rata.matches(""))
					rata = "0";
				Log.i("rata", rata);

				monthpay = Integer.valueOf(det.monthpay);
				if (monthpay <= Integer.valueOf(rata)) {
					monthpay = 0;
				}
				splacone = Integer.valueOf(rata)
						+ Integer.valueOf(det.payedTotal);

				Log.i("det.sellPrice", "" + det.sellPrice);
				Log.i("det.buyPrice", "" + det.buyPrice);
				Log.i("det.currentProfit", "" + det.currentProfit);
				Log.i("monthpay", "" + monthpay);

				// int newCurrentProfit =
				// Integer.valueOf(rata)*(Integer.valueOf(det.sellPrice)-Integer.valueOf(det.buyPrice))/Integer.valueOf(det.sellPrice);
				// Log.i("newCurrentProfit1", ""+newCurrentProfit);
				// newCurrentProfit += Integer.valueOf(det.currentProfit);
				// Log.i("newCurrentProfit2", ""+newCurrentProfit);

				int intDownpay = Integer.valueOf(det.downpay);

				int intSellprice = Integer.valueOf(det.sellPrice);
				int intBuyprice = Integer.valueOf(det.buyPrice);

				Log.i("intDownpay", "" + intDownpay);
				Log.i("splacone", "" + splacone);
				Log.i("intSellprice", "" + intSellprice);
				Log.i("intBuyprice", "" + intBuyprice);

				int newCurrentProfit = Math.round((intDownpay + splacone)
						* (intSellprice - intBuyprice) / intSellprice);
				// (downpay+payedtotal)*(sellprice-buyprice)/sellprice, 0

				new DodajRate().execute(lp, data, rata, nrRaty, "" + splacone,
						note, "" + monthpay, "" + newCurrentProfit);
				/*
				 * intent.putExtra("BUY_PRICE", det.buyPrice);
				 * intent.putExtra("SELL_PRICE", det.sellPrice);
				 * intent.putExtra("DOWNPAY", det.downpay);
				 * intent.putExtra("MONTHPAY", det.monthpay);
				 * intent.putExtra("PAYED", det.numberPayed);
				 * intent.putExtra("REMAIN", det.remain);
				 */
				// DetailsActivity.zapisz(det);
				// new EdytujDetal().execute(det);

			}
		});

		alert.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						return;
					}
				});
		alert.show();
	}

	public void gotoList() {
		Log.i("gotoList() lp: ", det.lp);
		Intent intent = new Intent(NewPaymentActivity.this, ListActivity.class);
		intent.putExtra("LP", det.lp);
		finish();
		startActivity(intent);
	}

	public void cancel(View view) {
		Intent intent = new Intent(NewPaymentActivity.this, HistoryActivity.class);
		intent.putExtra("LP", det.lp);
		intent.putExtra("NAME", det.name);
		intent.putExtra("PAYED", det.numberPayed);
		intent.putExtra("PAYED_TOTAL", det.payedTotal);
		intent.putExtra("TOPAY", String.valueOf(det.toPay));
//		Log.i("name: ", det.name);
		finish();
		startActivity(intent);
	}

	private class DodajRate extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {
			KlasaUser.nowaWplata(arg0[0], arg0[1], arg0[2], arg0[3], arg0[4],
					arg0[5], arg0[6], arg0[7]);
			Log.i("arg0[0] lp : ", arg0[0]);
			Log.i("arg0[1] data: ", arg0[1]);
			Log.i("arg0[2] rata: ", arg0[2]);
			Log.i("arg0[3] nrRaty: ", arg0[3]);
			Log.i("arg0[4] już spłacone: ", arg0[4]);
			Log.i("arg0[5] note: ", arg0[5]);
			Log.i("arg0[6] monthpay: ", arg0[6]);
			Log.i("arg0[7] current profit: ", arg0[7]);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			kreciolek.setVisibility(View.GONE);
			gotoList();
		}

		@Override
		protected void onPreExecute() {
			kreciolek.setVisibility(View.VISIBLE);
		}
	}

	public void onBackPressed() // wracasz do poprzedniego activity
	{
//		Intent intent = new Intent(NewPaymentActivity.this, ListActivity.class);
//		intent.putExtra("NAME", det.name);
//		Log.i("name: ", det.name);
//		finish();
//		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_payment, menu);
		return true;
	}

}
