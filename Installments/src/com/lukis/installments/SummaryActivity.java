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

	ProgressBar kreciolek;

	KlasaSummary summary = new KlasaSummary();

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
		
		

		refresh();

		v.setOnTouchListener(new OnSwipeTouchListener() {
			public void onSwipeRight() {
				monthNumber--;
				// eDate.setText("dzień dobry "+dateNumber);
				Toast.makeText(SummaryActivity.this, "previous month",
						Toast.LENGTH_SHORT).show();

				// kreciolek.setVisibility(View.VISIBLE);
				month();

			}

			public void onSwipeLeft() {
				monthNumber++;
				// eDate.setText("dzień dobry "+dateNumber);
				Toast.makeText(SummaryActivity.this, "next month", Toast.LENGTH_SHORT)
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


		if (sumYear == true) {
			eDate.setText("SumYear");
			summary.obliczCash(this);
			summary.obliczTotalProfit(this);
			summary.obliczTotalsMonth(this, yearNumber + todayDate.getYear(), 0 );
			summary.obliczCoCabitalMonth(this, yearNumber + todayDate.getYear(), 0 );
			summary.obliczDeposits(yearNumber + todayDate.getYear(), 99, this);
			// eDeposits.setText("" + summary.deposits);
			// eDownpayments.setText(""
			// + summary.obliczDownPayments(
			// yearNumber + todayDate.getYear(), 99));
			// eTotalInstallments.setText(""
			// + summary.obliczTotalInstallments(
			// yearNumber + todayDate.getYear(), 99));
			// eCurrentProfit.setText(""
			// + summary.obliczCurrentProfit(
			// yearNumber + todayDate.getYear(), 99));
		}
		if (sumAll == true) {
			eDate.setText("SumAll");
			summary.obliczCash(this);
			summary.obliczTotalProfit(this);
			summary.obliczTotals(this);
			summary.obliczCoCabital(this);
			summary.obliczDeposits(99, 99, this);
			// eDownpayments.setText("" + summary.obliczDownPayments(99, 99));
			// eTotalInstallments.setText(""
			// + summary.obliczTotalInstallments(99, 99));
			// eCurrentProfit.setText("" + summary.obliczCurrentProfit(99, 99));
		}
		if (sumYear == false && sumAll == false) {
			eDate.setText("Month: " + (monthNumber + todayDate.getMonth())
					+ "." + (yearNumber + todayDate.getYear()));
			
			summary.obliczDeposits(yearNumber + todayDate.getYear(),
					monthNumber + todayDate.getMonth(), this);
			summary.obliczCoCabitalMonth(this, yearNumber + todayDate.getYear(),
					monthNumber + todayDate.getMonth() );
			summary.obliczTotalsMonth(this, yearNumber + todayDate.getYear(),
					monthNumber + todayDate.getMonth() );
			// eDownpayments.setText(""
			// + summary.obliczDownPayments(
			// yearNumber + todayDate.getYear(), monthNumber
			// + todayDate.getMonth()));
			// eTotalInstallments.setText(""
			// + summary.obliczTotalInstallments(
			// yearNumber + todayDate.getYear(), monthNumber
			// + todayDate.getMonth()));
			// eCurrentProfit.setText(""
			// + summary.obliczCurrentProfit(
			// yearNumber + todayDate.getYear(), monthNumber
			// + todayDate.getMonth()));

		}

		// -- totals
		// eCoCabital.setText("" + summary.obliczCoCabital());
		// eCash.setText("" + summary.obliczCash());
		// eCurrentProfit.setText("" + summary.currentProfit);

		// eDownpayments.setText("" + summary.downPayments);
		// eTotalInstallments.setText("" + summary.totalInstallments);

		// eAllBoughts.setText("" + summary.allBoughts);
		// eAllSold.setText("" + summary.allSold);
		// eMonthlyPay.setText("" + summary.monthlyPay);
		// summary.coCabital = summary.deposits + summary.totalProfit
		// - summary.expenses - summary.aWithdrawals
		// - summary.mWithdrawals - summary.sWithdrawals;
//		eExpenses.setText("" + summary.expenses);
//		eAWithdrawals.setText("" + summary.aWithdrawals);
//		eMWithdrawals.setText("" + summary.mWithdrawals);
//		eSWithdrawals.setText("" + summary.sWithdrawals);
		// eRemaining.setText("" + summary.remaining);
//		int netProfit = summary.currentProfit - summary.expenses;
//		netProfit = (int) Math.ceil(netProfit * 100) / 100;
//		eTotalProfit.setText("" + summary.totalProfit);
//		eNetProfit.setText("" + netProfit);
//		int netSProfit = (int) (Math.ceil(netProfit * 30) / 100);
//		int netMProfit = (int) (Math.ceil(netProfit * 20) / 100);
//		int netAProfit = (int) (Math.ceil(netProfit * 50) / 100);
//		eSProfit.setText("" + netSProfit);
//		eMProfit.setText("" + netMProfit);
//		eAProfit.setText("" + netAProfit);
//		int netSBalance = (int) (Math
//				.ceil((netSProfit - summary.sWithdrawals) * 100) / 100);
//		int netMBalance = (int) (Math
//				.ceil((netMProfit - summary.mWithdrawals) * 100) / 100);
//		int netABalance = (int) (Math
//				.ceil((netAProfit - summary.aWithdrawals) * 100) / 100);
//		eSBalance.setText("" + netSBalance);
//		eMBalance.setText("" + netMBalance);
//		eABalance.setText("" + netABalance);

//		kreciolek.setVisibility(View.GONE);
	}

	public void printDeposits() {
		eDeposits.setText("" + summary.deposits);
		eAWithdrawals.setText("" + summary.aWithdrawals);
		eMWithdrawals.setText("" + summary.mWithdrawals);
		eSWithdrawals.setText("" + summary.sWithdrawals);
		eExpenses.setText("" + summary.expenses);
		printProfits();
	}
	
	public void printCash(Integer cash) {
		eCash.setText("" + cash);
	}
	
	public void printTotals() {
		eAllBoughts.setText("" + summary.allBoughts);
		eAllSold.setText("" + summary.allSold);
		eDownpayments.setText("" + summary.downPayments);
		eMonthlyPay.setText("" + summary.monthlyPay);
		eTotalInstallments.setText("" + summary.totalInstallments);
		eRemaining.setText("" + summary.remaining);
		eCurrentProfit.setText("" + summary.currentProfit);
		printProfits();
	}

	public void printProfits() {
		kreciolek.setVisibility(View.GONE);
		int netProfit = summary.currentProfit + summary.expenses;
		netProfit = (int) Math.ceil(netProfit * 100) / 100;
		eTotalProfit.setText("" + summary.totalProfit);
		eNetProfit.setText("" + netProfit);
		int netSProfit = (int) (Math.ceil(netProfit * 30) / 100);
		int netMProfit = (int) (Math.ceil(netProfit * 20) / 100);
		int netAProfit = (int) (Math.ceil(netProfit * 50) / 100);
		eSProfit.setText("" + netSProfit);
		eMProfit.setText("" + netMProfit);
		eAProfit.setText("" + netAProfit);
		int netSBalance = (int) (Math
				.ceil((netSProfit + summary.sWithdrawals) * 100) / 100);
		int netMBalance = (int) (Math
				.ceil((netMProfit + summary.mWithdrawals) * 100) / 100);
		int netABalance = (int) (Math
				.ceil((netAProfit + summary.aWithdrawals) * 100) / 100);
		eSBalance.setText("" + netSBalance);
		eMBalance.setText("" + netMBalance);
		eABalance.setText("" + netABalance);
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
