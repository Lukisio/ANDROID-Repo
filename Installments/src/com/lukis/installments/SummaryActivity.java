package com.lukis.installments;

import java.util.Date;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends Activity {

	TextView eCash, eDeposits, eDownpayments, eTotalInstallments, eAllBoughts,
			eAllSold, eMonthlyPay, eExpenses;
	TextView eCoCabital, eSWithdrawals, eMWithdrawals, eAWithdrawals;
	TextView eCurrentProfit, eNetProfit, eTotalProfit, eSProfit, eMProfit,
			eAProfit, eSBalance, eMBalance, eABalance, eRemaining;
	TextView eDate;
	Date todayDate = new Date();
	int monthNumber = 3;
	int yearNumber = 1900;
	boolean sumAll = true;
	boolean sumYear = false;

	private ProgressBar kreciolek;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		kreciolek = (ProgressBar) findViewById(R.id.progressBar1);

		eDate = (TextView) findViewById(R.id.textView36);
		eCash = (TextView) findViewById(R.id.textView16);
		eDeposits = (TextView) findViewById(R.id.textView17);
		eDownpayments = (TextView) findViewById(R.id.textView18);
		eTotalInstallments = (TextView) findViewById(R.id.textView19);
		eAllBoughts = (TextView) findViewById(R.id.textView20);
		eAllSold = (TextView) findViewById(R.id.textView020);
		eMonthlyPay = (TextView) findViewById(R.id.textView021);
		eCoCabital = (TextView) findViewById(R.id.textView022);
		eExpenses = (TextView) findViewById(R.id.textView21);
		eSWithdrawals = (TextView) findViewById(R.id.textView22);
		eMWithdrawals = (TextView) findViewById(R.id.textView23);
		eAWithdrawals = (TextView) findViewById(R.id.textView24);
		eCurrentProfit = (TextView) findViewById(R.id.textView25);
		eTotalProfit = (TextView) findViewById(R.id.textView025);
		eNetProfit = (TextView) findViewById(R.id.textView26);
		eSProfit = (TextView) findViewById(R.id.textView27);
		eMProfit = (TextView) findViewById(R.id.textView28);
		eAProfit = (TextView) findViewById(R.id.textView29);
		eSBalance = (TextView) findViewById(R.id.textView33);
		eMBalance = (TextView) findViewById(R.id.textView34);
		eABalance = (TextView) findViewById(R.id.textView35);
		eRemaining = (TextView) findViewById(R.id.TextView02);
	}

	protected void onResume() {
		super.onResume();

		View v = (View) findViewById(R.id.background);
		// kreciolek.setVisibility(View.VISIBLE);
		refresh();

		// kreciolek.setVisibility(View.GONE);

		// eDate.setText("Month: " + (monthNumber+todayDate.getMonth()) + "." +
		// (yearNumber+todayDate.getYear()));

		v.setOnTouchListener(new OnSwipeTouchListener() {
			public void onSwipeRight() {
				monthNumber--;
				// eDate.setText("dzień dobry "+dateNumber);
				Toast.makeText(SummaryActivity.this, "right",
						Toast.LENGTH_SHORT).show();

				// kreciolek.setVisibility(View.VISIBLE);
				month();

			}

			public void onSwipeLeft() {
				monthNumber++;
				// eDate.setText("dzień dobry "+dateNumber);
				Toast.makeText(SummaryActivity.this, "left", Toast.LENGTH_SHORT)
						.show();

				// kreciolek.setVisibility(View.VISIBLE);
				month();
			}
		});
	}

	public void month() {
		if (monthNumber > 3 && yearNumber == 1900) {
			monthNumber = 3;
		}
		if (monthNumber == 2 && yearNumber == 1900) {
			sumYear = true;
		} else
			sumYear = false;
		if (monthNumber == 3 && yearNumber == 1900) {
			sumAll = true;
		} else
			sumAll = false;
		if ((monthNumber + todayDate.getMonth()) > 12 && yearNumber < 1900) {
			yearNumber++;
			monthNumber -= 12;
		}
		if ((monthNumber + todayDate.getMonth()) < 1) {
			yearNumber--;
			monthNumber += 12;
		}

		refresh();

	}

	public void refresh() {
		kreciolek.setVisibility(View.VISIBLE);
		KlasaSummary summary = new KlasaSummary();
		if (sumYear == true) {
			eDate.setText("SumYear");
			eDeposits.setText(""
					+ summary.obliczDeposits(yearNumber + todayDate.getYear(),
							99));
			eDownpayments.setText(""
					+ summary.obliczDownPayments(
							yearNumber + todayDate.getYear(), 99));
			eTotalInstallments.setText(""
					+ summary.obliczTotalInstallments(
							yearNumber + todayDate.getYear(), 99));
			eCurrentProfit.setText(""
					+ summary.obliczCurrentProfit(
							yearNumber + todayDate.getYear(), 99));
			// TODO current profit do poprawy
		}
		if (sumAll == true) {
			eDate.setText("SumAll");
			eDeposits.setText("" + summary.obliczDeposits(99, 99));
			eDownpayments.setText("" + summary.obliczDownPayments(99, 99));
			eTotalInstallments.setText(""
					+ summary.obliczTotalInstallments(99, 99));
			eCurrentProfit.setText("" + summary.obliczCurrentProfit(99, 99));
		}
		if (sumYear == false && sumAll == false) {
			eDate.setText("Month: " + (monthNumber + todayDate.getMonth())
					+ "." + (yearNumber + todayDate.getYear()));
			eDeposits.setText(""
					+ summary.obliczDeposits(yearNumber + todayDate.getYear(),
							monthNumber + todayDate.getMonth()));
			eDownpayments.setText(""
					+ summary.obliczDownPayments(
							yearNumber + todayDate.getYear(), monthNumber
									+ todayDate.getMonth()));
			eTotalInstallments.setText(""
					+ summary.obliczTotalInstallments(
							yearNumber + todayDate.getYear(), monthNumber
									+ todayDate.getMonth()));
			eCurrentProfit.setText(""
					+ summary.obliczCurrentProfit(
							yearNumber + todayDate.getYear(), monthNumber
									+ todayDate.getMonth()));
		}

		eAllBoughts.setText("" + summary.allBoughts);
		eAllSold.setText("" + summary.allSold);
		eMonthlyPay.setText("" + summary.monthlyPay);
		summary.coCabital = summary.deposits + summary.totalProfit
				- summary.expenses - summary.aWithdrawals
				- summary.mWithdrawals - summary.sWithdrawals;
		eCoCabital.setText("" + summary.coCabital);
		eExpenses.setText("" + summary.expenses);
		eAWithdrawals.setText("" + summary.aWithdrawals);
		eMWithdrawals.setText("" + summary.mWithdrawals);
		eSWithdrawals.setText("" + summary.sWithdrawals);
		eRemaining.setText("" + summary.remaining);
		double netProfit = summary.currentProfit - summary.expenses;
		netProfit = (Double) Math.ceil(netProfit * 100) / 100;
		eTotalProfit.setText("" + summary.totalProfit);
		eNetProfit.setText("" + netProfit);
		double netSProfit = Math.ceil(netProfit * 30) / 100;
		double netMProfit = Math.ceil(netProfit * 20) / 100;
		double netAProfit = Math.ceil(netProfit * 50) / 100;
		eSProfit.setText("" + netSProfit);
		eMProfit.setText("" + netMProfit);
		eAProfit.setText("" + netAProfit);
		double netSBalance = Math
				.ceil((netSProfit - summary.sWithdrawals) * 100) / 100;
		double netMBalance = Math
				.ceil((netMProfit - summary.mWithdrawals) * 100) / 100;
		double netABalance = Math
				.ceil((netAProfit - summary.aWithdrawals) * 100) / 100;
		eSBalance.setText("" + netSBalance);
		eMBalance.setText("" + netMBalance);
		eABalance.setText("" + netABalance);

		double cash = Double.parseDouble(eDeposits.getText().toString())
				+ Double.parseDouble(eDownpayments.getText().toString())
				+ Double.parseDouble(eTotalInstallments.getText().toString())
				- Double.parseDouble(eAllBoughts.getText().toString())
				- Double.parseDouble(eExpenses.getText().toString())
				- Double.parseDouble(eSWithdrawals.getText().toString())
				- Double.parseDouble(eMWithdrawals.getText().toString())
				- Double.parseDouble(eAWithdrawals.getText().toString());
		eCash.setText("" + cash);

		kreciolek.setVisibility(View.GONE);
	}

	public void onBackPressed() // wracasz do poprzedniego activity
	{
		Intent intent = new Intent(this, MainActivity.class);
		finish();
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}

}
