package com.lukis.installments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class KlasaSummary extends Activity {

	private static final String TAG_TABELA = "installments";
	private static final String TAG_DEPOSIT = "value";
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
	private static final String TAG_PAYEDTOTAL = "payedtotal";
	private static final String TAG_CASH = "cash";
	private static final String TAG_COCABITAL = "CoCabital";
	private static final String TAG_COCABITAL_MONTH = "cocabital";

	private static final String TAG_TOTALPROFIT = "totalprofit";

	private static final String TAG_TS_ALLBOUGHT = "All_bought";
	private static final String TAG_TS_ALLSOLD = "All_Sold";
	private static final String TAG_TS_DOWNPAYMENT = "Down_payment";
	private static final String TAG_TS_MONTHPAY = "Monthly_Payment";
	private static final String TAG_TS_INSTALLMENTS = "installment";
	private static final String TAG_TS_REMAINING = "Remaining";
	private static final String TAG_TS_CURRENTPROFIT = "Current_Profit";

	private static final String TAG_TS_ALLBOUGHT_MONTH = "All_bought";
	private static final String TAG_TS_ALLSOLD_MONTH = "All_Sold";
	private static final String TAG_TS_DOWNPAYMENT_MONTH = "Down_payment";
	private static final String TAG_TS_MONTHPAY_MONTH = "Monthly_Payment";
	private static final String TAG_TS_INSTALLMENTS_MONTH = "installment";
	private static final String TAG_TS_REMAINING_MONTH = "Remaining";
	private static final String TAG_TS_CURRENTPROFIT_MONTH = "Current_Profit";
	private static final String TAG_TS_TOTALPROFIT_MONTH = "totalprofit";

	JSONArray zbrojenia = null;
	int dlugosc = 0;

	int cash = 0;
	public int deposits = 0;
	int downPayments = 0;
	int totalInstallments = 0;
	int allBoughts = 0;
	int allSold = 0;
	int monthlyPay = 0;
	int coCabital = 0;
	int expenses = 0;
	int sWithdrawals = 0;
	int mWithdrawals = 0;
	int aWithdrawals = 0;
	int currentProfit = 0;
	int totalProfit = 0;
	int netProfit = 0;
	int sProfit = 0;
	int mProfit = 0;
	int aProfit = 0;
	int payments = 0;
	int sellPrice = 0;
	int buyPrice = 0;
	int remaining = 0;
	// int downPayment = 0;
	int paidTotal = 0;
	// String lista[] = new String[8];
	public Boolean czekaj = false;

	public static String urlTotals = "http://united.webege.com/totals.php";
	public static String urlTotalProfit = "http://united.webege.com/totalProfit.php";

	public KlasaSummary() {
		super();

		// AsyncTask at = new AsyncTask(ma);
	}

	public int obliczDeposits(int year, int month, SummaryActivity sa) {
		String strMonth;
		if (month < 10) {
			strMonth = "0" + month;
		} else {
			strMonth = "" + month;
		}
		new WypelnijSummaryExpenses(sa).execute("" + year, "" + strMonth);

		return deposits;
	}

	public int obliczExpensesDate(int year, int month, SummaryActivity sa) {
		String strMonth;
		if (month < 10) {
			strMonth = "0" + month;
		} else {
			strMonth = "" + month;
		}
		new WypelnijSummaryExpensesDate(sa, year, month).execute("" + year, ""
				+ strMonth);

		return deposits;
	}

	// public int obliczDownPayments(int year, int month) {
	// String strMonth;
	// if (month < 10) {
	// strMonth = "0" + month;
	// } else {
	// strMonth = "" + month;
	// }
	// // new WypelnijSummaryDownPayments().execute("" + year, "" + strMonth);
	// while (czekaj) {
	// }
	// return downPayments;
	// }
	//
	// public int obliczPaymentsDone(int year, int month) {
	// String strMonth;
	// if (month < 10) {
	// strMonth = "0" + month;
	// } else {
	// strMonth = "" + month;
	// }
	// // new WypelnijSummaryPaymentsDone().execute("" + year, "" + strMonth);
	// while (czekaj) {
	// }
	// return payments;
	// }

	public int obliczCash(MainActivity ma) {
		Log.i("obliczCash", "MainActivity");

		new WypelnijSummaryCash(ma).execute();
		return cash;
	}

	public int obliczMonthlyPayment(MainActivity ma) {
		Log.i("obliczCash", "MainActivity");

		new WypelnijMonthlyPayment(ma).execute();
		return cash;
	}

	public int obliczCash(SummaryActivity sa) {
		new WypelnijSummaryCash(sa).execute();
		return cash;
	}

	public int obliczCoCabital(SummaryActivity sa) {
		new WypelnijCoCabital(sa).execute();
		while (czekaj) {
		}
		return coCabital;
	}

	public void obliczCoCabitalMonth(SummaryActivity sa, int y, int m) {
		new WypelnijCoCabitalMonth(sa, y, m).execute();
		return;
	}

	public void obliczTotals(SummaryActivity sa) {
		Log.i("obliczTotals", "obliczTotals");
		new WypelnijTotals(sa).execute();
		return;
	}

	public void obliczTotalsMonth(SummaryActivity sa, int y, int m) {
		Log.i("obliczTotals", "obliczTotals");
		new WypelnijTotalsMonth(sa, y, m).execute();
		return;
	}

	public void obliczTotalProfit(SummaryActivity sa) {
		new WypelnijTotalProfit(sa).execute();
		while (czekaj) {
		}
		return;
	}

	// public int obliczTotalInstallments(int year, int month) {
	// String strMonth;
	// if (month < 10) {
	// strMonth = "0" + month;
	// } else {
	// strMonth = "" + month;
	// }
	// // new WypelnijSummaryTotalInstallments().execute("" + year, "" +
	// // strMonth);
	// while (czekaj) {
	// }
	// Log.i("total installments: ", "" + totalInstallments);
	// return totalInstallments;
	// }

	// public int obliczCurrentProfit(int year, int month) {
	// String strMonth;
	// if (month < 10) {
	// strMonth = "0" + month;
	// } else {
	// strMonth = "" + month;
	// }
	// new WypelnijSummaryCurrentProfit().execute("" + year, "" + strMonth);
	// while (czekaj) {
	// }
	// Log.i("current profit: ", "" + currentProfit);
	// return currentProfit;
	// }

	private class WypelnijSummaryExpenses extends
			AsyncTask<String, Integer, ArrayList<String>> {
		private SummaryActivity _summaryActivity;

		public WypelnijSummaryExpenses(SummaryActivity sa) {
			_summaryActivity = sa;
		}

		// wypelnia listę kilkoma kolumnami z calej tabeli
		@Override
		protected ArrayList<String> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			ObslugaJSON jParser = new ObslugaJSON();
			ArrayList<String> localList = new ArrayList<String>();

			int localDeposits = 0;
			int localAWithdrawals = 0;
			int localMWithdrawals = 0;
			int localSWithdrawals = 0;
			int localGenExpenses = 0;
			try {
				JSONObject json = jParser
						.getJSONFromUrl(ADepositActivity.urlExp);
				Log.i("tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);
				dlugosc = 0;
				String tempString;
				String searchDate;
				if (arg0[0].contains("99")) {
					searchDate = ("");
				} else
					searchDate = ("" + arg0[0] + "-");
				if (arg0[1].contains("99")) {
				} else
					searchDate += ("" + arg0[1] + "-");
				Log.i("data podana: ", "" + searchDate);

				// looping through All
				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);
					dlugosc++;
					// Log.i("tag name: ", z.getString(TAG_NAME));
					// Log.i("data: ", z.getString(TAG_DATE));
					if (z.getString(TAG_NAME).equals("Deposit_A")) {
						// wypełnia Deposits dla Name == Deposit_A
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";

						if (z.getString(TAG_DATE).contains("" + searchDate)) {
							localDeposits += Integer.valueOf(tempString);
						}
					}

					if (z.getString(TAG_NAME).equals("Withdraw_A")) {
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";
						// Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
						if (z.getString(TAG_DATE).contains("" + searchDate)) {
							localAWithdrawals += Integer.valueOf(tempString);
						}
					}

					if (z.getString(TAG_NAME).equals("Withdraw_M")) {
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";
						// Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
						if (z.getString(TAG_DATE).contains("" + searchDate)) {
							localMWithdrawals += Integer.valueOf(tempString);
						}
					}

					if (z.getString(TAG_NAME).equals("Withdraw_S")) {
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";
						// Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
						if (z.getString(TAG_DATE).contains("" + searchDate)) {
							localSWithdrawals += Integer.valueOf(tempString);
						}
					}

					if (z.getString(TAG_NAME).equals("Gen_Expense")) {
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";
						// Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
						if (z.getString(TAG_DATE).contains("" + searchDate)) {
							localGenExpenses += Integer.valueOf(tempString);
						}
					}

					// publishProgress(i);
				}
			} catch (Exception e) {
				// czekaj = false;
				return null;
			}
			// czekaj = false;

			localList.add(0, "" + localDeposits);
			localList.add(1, "" + localAWithdrawals);
			localList.add(2, "" + localMWithdrawals);
			localList.add(3, "" + localSWithdrawals);
			localList.add(4, "" + localGenExpenses);

			return localList;
		}

		@Override
		protected void onPostExecute(ArrayList<String> result) {

			deposits = Integer.valueOf(result.get(0));
			aWithdrawals = Integer.valueOf(result.get(1));
			mWithdrawals = Integer.valueOf(result.get(2));
			sWithdrawals = Integer.valueOf(result.get(3));
			expenses = Integer.valueOf(result.get(4));

			if (_summaryActivity != null) {
				_summaryActivity.kreciolek.setVisibility(View.GONE);
				_summaryActivity.printDeposits();
			}
		}

		@Override
		protected void onPreExecute() {
			_summaryActivity.kreciolek.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			// info.setText(""+progress[0]);
		}
	}

	private class WypelnijSummaryExpensesDate extends
			AsyncTask<String, Integer, ArrayList<String>> {
		private SummaryActivity _summaryActivity;
		int year, month;

		public WypelnijSummaryExpensesDate(SummaryActivity sa, int y, int m) {
			_summaryActivity = sa;
			year = y;
			month = m;
		}

		// wypelnia listę kilkoma kolumnami z calej tabeli
		@Override
		protected ArrayList<String> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			ObslugaJSON jParser = new ObslugaJSON();
			ArrayList<String> localList = new ArrayList<String>();

			int localDeposits = 0;
			int localAWithdrawals = 0;
			int localMWithdrawals = 0;
			int localSWithdrawals = 0;
			int localGenExpenses = 0;
			try {
				String fullUrl = ADepositActivity.urlExpDate + "?year=" + year;
				if (month > 0)
					fullUrl += "" + "&month=" + month;
				Log.i("cocabitalMonth: ", "" + fullUrl);
				JSONObject json = jParser.getJSONFromUrl(fullUrl);

				Log.i("tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);
				dlugosc = 0;
				String tempString;

				// looping through All
				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);
					dlugosc++;
					// Log.i("tag name: ", z.getString(TAG_NAME));
					// Log.i("data: ", z.getString(TAG_DATE));
					if (z.getString(TAG_NAME).equals("Deposit_A")) {
						// wypełnia Deposits dla Name == Deposit_A
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";

						localDeposits += Integer.valueOf(tempString);

					}

					if (z.getString(TAG_NAME).equals("Withdraw_A")) {
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";
						// Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
						localAWithdrawals += Integer.valueOf(tempString);

					}

					if (z.getString(TAG_NAME).equals("Withdraw_M")) {
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";
						// Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
						localMWithdrawals += Integer.valueOf(tempString);

					}

					if (z.getString(TAG_NAME).equals("Withdraw_S")) {
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";
						// Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
						localSWithdrawals += Integer.valueOf(tempString);

					}

					if (z.getString(TAG_NAME).equals("Gen_Expense")) {
						if (z.getString(TAG_DEPOSIT).length() > 0)
							tempString = z.getString(TAG_DEPOSIT);
						else
							tempString = "0";
						// Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
						localGenExpenses += Integer.valueOf(tempString);

					}

					// publishProgress(i);
				}
			} catch (Exception e) {
				return null;
			}

			localList.add(0, "" + localDeposits);
			localList.add(1, "" + localAWithdrawals);
			localList.add(2, "" + localMWithdrawals);
			localList.add(3, "" + localSWithdrawals);
			localList.add(4, "" + localGenExpenses);

			return localList;
		}

		@Override
		protected void onPostExecute(ArrayList<String> result) {

			deposits = Integer.valueOf(result.get(0));
			aWithdrawals = Integer.valueOf(result.get(1));
			mWithdrawals = Integer.valueOf(result.get(2));
			sWithdrawals = Integer.valueOf(result.get(3));
			expenses = Integer.valueOf(result.get(4));

			if (_summaryActivity != null) {
				_summaryActivity.kreciolek.setVisibility(View.GONE);
				_summaryActivity.printDeposits();
			}
		}

		@Override
		protected void onPreExecute() {
			_summaryActivity.kreciolek.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			// info.setText(""+progress[0]);
		}
	}

	// private class WypelnijSummaryDownPayments extends
	// AsyncTask<String, Integer, String> {
	// // wypelnia listę kilkoma kolumnami z calej tabeli
	// @Override
	// protected String doInBackground(String... arg0) {
	// ObslugaJSON jParser = new ObslugaJSON();
	//
	// try {
	// JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
	// zbrojenia = json.getJSONArray(TAG_TABELA);
	// downPayments = 0;
	// allBoughts = 0;
	// String searchDate;
	// String stringTemp;
	// if (arg0[0].contains("99")) {
	// searchDate = ("");
	// } else
	// searchDate = ("" + arg0[0] + "-");
	// if (arg0[1].contains("99")) {
	// } else
	// searchDate += ("" + arg0[1] + "-");
	// Log.i("data podana: ", "" + searchDate);
	// // looping through All Contacts
	// for (int i = 0; i < zbrojenia.length(); i++) {
	// JSONObject z = zbrojenia.getJSONObject(i);
	//
	// if (z.getString(TAG_DATE).contains("" + searchDate)) {
	// if (z.getString(TAG_DOWNPAY).length() > 0)
	// stringTemp = z.getString(TAG_DOWNPAY);
	// else
	// stringTemp = "0";
	// downPayments += Integer.valueOf(stringTemp);
	//
	// if (z.getString(TAG_BUYPRICE).length() > 0)
	// stringTemp = z.getString(TAG_BUYPRICE);
	// else
	// stringTemp = "0";
	// allBoughts += Integer.valueOf(stringTemp);
	//
	// if (z.getString(TAG_SELLPRICE).length() > 0)
	// stringTemp = z.getString(TAG_SELLPRICE);
	// else
	// stringTemp = "0";
	// allSold += Integer.valueOf(stringTemp);
	//
	// if (z.getString(TAG_MONTHPAY).length() > 0)
	// stringTemp = z.getString(TAG_MONTHPAY);
	// else
	// stringTemp = "0";
	// monthlyPay += Integer.valueOf(stringTemp);
	//
	// }
	// }
	// } catch (Exception e) {
	// czekaj = false;
	// return null;
	// }
	// czekaj = false;
	//
	// return null;
	// }
	//
	// @Override
	// protected void onPostExecute(String result) {
	// // setProgressBar(STOP_PROGRESS);
	// // button.setEnabled(true);
	// // info.setText("name: "+detal.name);
	// }
	//
	// @Override
	// protected void onPreExecute() {
	// czekaj = true;
	// }
	//
	// @Override
	// protected void onProgressUpdate(Integer... progress) {
	// // info.setText(""+progress[0]);
	// }
	// }
	//
	// private class WypelnijSummaryPaymentsDone extends
	// AsyncTask<String, Integer, String> {
	// @Override
	// protected String doInBackground(String... arg0) {
	// ObslugaJSON jParser = new ObslugaJSON();
	//
	// try {
	// JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
	// Log.i("tabela: ", TAG_TABELA);
	// zbrojenia = json.getJSONArray(TAG_TABELA);
	// int payed = 0;
	// payments = 0;
	// String searchDate;
	// String stringTemp;
	// if (arg0[0].contains("99")) {
	// searchDate = ("");
	// } else
	// searchDate = ("" + arg0[0] + "-");
	// if (arg0[1].contains("99")) {
	// } else
	// searchDate += ("" + arg0[1] + "-");
	// Log.i("data podana: ", "" + searchDate);
	// // looping through All Contacts
	// for (int i = 0; i < zbrojenia.length(); i++) {
	// JSONObject z = zbrojenia.getJSONObject(i);
	//
	// if (z.getString(TAG_DATE).contains("" + searchDate)) {
	// if (z.getString(TAG_PAYED).length() > 0) {
	// stringTemp = z.getString(TAG_PAYED);
	// payed = Integer.valueOf(stringTemp);
	// Log.i("payed: ", "" + payed);
	// payed++;
	//
	// }
	// }
	//
	// }
	// } catch (Exception e) {
	// czekaj = false;
	// return null;
	// }
	// czekaj = false;
	//
	// return null;
	// }
	//
	// @Override
	// protected void onPreExecute() {
	// czekaj = true;
	// }
	//
	// }

	public class WypelnijSummaryCash extends
			AsyncTask<String, Integer, Integer> {

		private MainActivity _mainActivity;
		private SummaryActivity _summaryActivity;

		public WypelnijSummaryCash(MainActivity ma) {
			_mainActivity = ma;
		}

		public WypelnijSummaryCash(SummaryActivity sa) {
			_summaryActivity = sa;
		}

		@Override
		protected Integer doInBackground(String... arg0) {
			ObslugaJSON jParser = new ObslugaJSON();
			int localCash = 0;
			Log.i("doInBackground", "WypelnijSummaryCash");

			try {
				JSONObject json = jParser.getJSONFromUrl(ListActivity.urlCash);
				Log.i("tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);
				String stringTemp;
				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);
					if (z.getString(TAG_CASH).length() > 0) {
						stringTemp = z.getString(TAG_CASH);
						localCash += Integer.valueOf(stringTemp);
					}
				}
			} catch (Exception e) {
				czekaj = false;
				return null;
			}
			return localCash;
		}

		@Override
		protected void onPreExecute() {
			Log.i("onPreExecute", "onPreExecute");
			if (_summaryActivity != null)
				_summaryActivity.kreciolek.setVisibility(View.VISIBLE);
			Log.i("czekaj", "" + czekaj);
		}

		@Override
		protected void onPostExecute(Integer result) {
			if (result == null)
				return;
			cash = result;
			Log.i("onPostExecute", "cash " + cash);
			if (_mainActivity != null)
				_mainActivity.printCash(cash);
			if (_summaryActivity != null) {
				_summaryActivity.kreciolek.setVisibility(View.GONE);
				_summaryActivity.printCash(cash);
			}
		}
	}

	public class WypelnijMonthlyPayment extends
			AsyncTask<String, Integer, Integer> {

		private MainActivity _mainActivity;

		public WypelnijMonthlyPayment(MainActivity ma) {
			_mainActivity = ma;
		}

		@Override
		protected Integer doInBackground(String... arg0) {
			ObslugaJSON jParser = new ObslugaJSON();
			int localMonthlyPayment = 0;
			Log.i("doInBackground", "WypelnijMonthlyPayment");

			try {
				JSONObject json = jParser
						.getJSONFromUrl(ListActivity.urlMonthlyPayment);
				Log.i("tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);
				String stringTemp;
				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);
					if (z.getString(TAG_TS_MONTHPAY).length() > 0) {
						stringTemp = z.getString(TAG_TS_MONTHPAY);
						localMonthlyPayment = Integer.valueOf(stringTemp);
					}
				}
			} catch (Exception e) {
				czekaj = false;
				return null;
			}
			return localMonthlyPayment;
		}

		@Override
		protected void onPreExecute() {
			Log.i("onPreExecute", "onPreExecute");
		}

		@Override
		protected void onPostExecute(Integer result) {
			if (result == null)
				return;

			Log.i("onPostExecute", "result " + result);
			if (_mainActivity != null)
				_mainActivity.printMonthlyPayment(result);
		}
	}

	private class WypelnijCoCabital extends AsyncTask<String, Integer, Integer> {
		SummaryActivity _summaryActivity;

		public WypelnijCoCabital(SummaryActivity sa) {
			_summaryActivity = sa;
		}

		@Override
		protected Integer doInBackground(String... arg0) {
			ObslugaJSON jParser = new ObslugaJSON();
			int localCoCabital = 0;

			try {
				JSONObject json = jParser
						.getJSONFromUrl(ListActivity.urlCabital);
				Log.i("tabela: ", TAG_TABELA);
				String stringTemp;
				zbrojenia = json.getJSONArray(TAG_TABELA);

				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);
					if (z.getString(TAG_COCABITAL).length() > 0) {
						stringTemp = z.getString(TAG_COCABITAL);
						localCoCabital = Integer.valueOf(stringTemp);
						Log.i("cocabital: ", "" + localCoCabital);
					}
				}
			} catch (Exception e) {
				return null;
			}
			return localCoCabital;
		}

		@Override
		protected void onPreExecute() {
			if (_summaryActivity != null)
				_summaryActivity.kreciolek.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onPostExecute(Integer result) {
			if (result == null)
				return;
			coCabital = result;
			if (_summaryActivity != null) {
				_summaryActivity.kreciolek.setVisibility(View.GONE);
				_summaryActivity.eCoCabital.setText("" + coCabital);
			}
		}
	}

	private class WypelnijCoCabitalMonth extends
			AsyncTask<String, Integer, Integer> {
		SummaryActivity _summaryActivity;
		int year, month;

		public WypelnijCoCabitalMonth(SummaryActivity sa, int y, int m) {
			_summaryActivity = sa;
			year = y;
			month = m;
		}

		@Override
		protected Integer doInBackground(String... arg0) {
			ObslugaJSON jParser = new ObslugaJSON();
			int localCoCabital = 0;

			try {
				String fullUrl = ListActivity.urlCabitalMonth + "?year=" + year;
				if (month > 0)
					fullUrl += "" + "&month=" + month;
				Log.i("cocabitalMonth: ", "" + fullUrl);
				JSONObject json = jParser.getJSONFromUrl(fullUrl);
				Log.i("tabela: ", TAG_TABELA);
				String stringTemp;
				zbrojenia = json.getJSONArray(TAG_TABELA);

				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);
					if (z.getString(TAG_COCABITAL_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_COCABITAL_MONTH);
						localCoCabital += Integer.valueOf(stringTemp);
						Log.i("cocabitalMonth: ", "" + localCoCabital);
					}
				}
			} catch (Exception e) {
				return 0;
			}
			return localCoCabital;
		}

		@Override
		protected void onPreExecute() {
			if (_summaryActivity != null)
				_summaryActivity.kreciolek.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onPostExecute(Integer result) {
			if (result == null)
				return;
			coCabital = result;
			if (_summaryActivity != null) {
				_summaryActivity.kreciolek.setVisibility(View.GONE);
				_summaryActivity.eCoCabital.setText("" + coCabital);
			}
		}
	}

	private class WypelnijTotals extends
			AsyncTask<String, Integer, ArrayList<String>> {

		SummaryActivity _summaryActivity;
		int localCurrentProfit = 0;

		public WypelnijTotals(SummaryActivity sa) {
			_summaryActivity = sa;
		}

		@Override
		protected ArrayList<String> doInBackground(String... arg0) {
			ObslugaJSON jParser = new ObslugaJSON();
			ArrayList<String> localList = new ArrayList<String>();
			int localAllBoughts = 0, localAllSold = 0, localDownPayments = 0, localMonthlyPay = 0, localTotalInstallments = 0, localRemaining = 0;
			try {
				JSONObject json = jParser.getJSONFromUrl(urlTotals);
				Log.i("WypelnijTotals tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);
				String stringTemp;

				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);

					if (z.getString(TAG_TS_ALLBOUGHT).length() > 0) {
						stringTemp = z.getString(TAG_TS_ALLBOUGHT);
						localAllBoughts = Integer.valueOf(stringTemp);
						localList.add(0, z.getString(TAG_TS_ALLBOUGHT));
						Log.i("allBoughts: ", "" + localAllBoughts);
					}
					if (z.getString(TAG_TS_ALLSOLD).length() > 0) {
						stringTemp = z.getString(TAG_TS_ALLSOLD);
						localAllSold = Integer.valueOf(stringTemp);
						localList.add(1, z.getString(TAG_TS_ALLSOLD));
						Log.i("allSold: ", "" + localAllSold);
					}
					if (z.getString(TAG_TS_DOWNPAYMENT).length() > 0) {
						stringTemp = z.getString(TAG_TS_DOWNPAYMENT);
						localDownPayments = Integer.valueOf(stringTemp);
						localList.add(2, z.getString(TAG_TS_DOWNPAYMENT));
						Log.i("downPayments: ", "" + localDownPayments);
					}
					if (z.getString(TAG_TS_MONTHPAY).length() > 0) {
						stringTemp = z.getString(TAG_TS_MONTHPAY);
						localMonthlyPay = Integer.valueOf(stringTemp);
						localList.add(3, z.getString(TAG_TS_MONTHPAY));
						Log.i("monthlyPay: ", "" + localMonthlyPay);
					}
					if (z.getString(TAG_TS_INSTALLMENTS).length() > 0) {
						stringTemp = z.getString(TAG_TS_INSTALLMENTS);
						localTotalInstallments = Integer.valueOf(stringTemp);
						localList.add(4, z.getString(TAG_TS_INSTALLMENTS));
						Log.i("totalInstallments: ", ""
								+ localTotalInstallments);
					}
					if (z.getString(TAG_TS_REMAINING).length() > 0) {
						stringTemp = z.getString(TAG_TS_REMAINING);
						localRemaining = Integer.valueOf(stringTemp);
						localList.add(5, z.getString(TAG_TS_REMAINING));
						Log.i("remaining: ", "" + localRemaining);
					}
					if (z.getString(TAG_TS_CURRENTPROFIT).length() > 0) {
						stringTemp = z.getString(TAG_TS_CURRENTPROFIT);
						localCurrentProfit = Integer.valueOf(stringTemp);
						localList.add(6, z.getString(TAG_TS_CURRENTPROFIT));
						Log.i("localCurrentProfit: ", "" + localCurrentProfit);
					}

					// localCurrentProfit += ((localDownPayments +
					// localMonthlyPay)
					// * (localAllSold - localAllBoughts) / localAllSold);

					// localList.add(6, "" + localCurrentProfit);
				}

			} catch (Exception e) {
				Log.i("WypelnijTotals", "Exception");
				Log.i("WypelnijTotals", "" + e);
				return null;
			}
			return localList;
		}

		@Override
		protected void onPreExecute() {
			_summaryActivity.kreciolek.setVisibility(View.VISIBLE);
			Log.i("onPreExecute", "onPreExecute");
			if (_summaryActivity != null)
				_summaryActivity.kreciolek.setVisibility(View.VISIBLE);

		}

		@Override
		protected void onPostExecute(ArrayList<String> result) {
			_summaryActivity.kreciolek.setVisibility(View.GONE);
			if (result == null)
				return;
			Log.i("onPostExecute", "WypelnijTotals ");
			if (_summaryActivity != null) {
				allBoughts = Integer.valueOf(result.get(0));
				allSold = Integer.valueOf(result.get(1));
				downPayments = Integer.valueOf(result.get(2));
				monthlyPay = Integer.valueOf(result.get(3));
				totalInstallments = Integer.valueOf(result.get(4));
				remaining = Integer.valueOf(result.get(5));
				currentProfit = Integer.valueOf(result.get(6));

				Log.i("allBoughts: ", "" + allBoughts);
				Log.i("allSold: ", "" + allSold);
				Log.i("downPayments: ", "" + downPayments);
				Log.i("monthlyPay: ", "" + monthlyPay);
				Log.i("totalInstallments: ", "" + totalInstallments);
				Log.i("remaining: ", "" + remaining);
				Log.i("currentProfit: ", "" + currentProfit);

				// _summaryActivity.eAllBoughts.setText("" + allBoughts);
				// _summaryActivity.eAllSold.setText("" + allSold);
				// _summaryActivity.eDownpayments.setText("" + downPayments);
				// _summaryActivity.eMonthlyPay.setText("" + monthlyPay);
				// _summaryActivity.eTotalInstallments.setText("" +
				// totalInstallments);
				// _summaryActivity.eRemaining.setText("" + remaining);
				// _summaryActivity.eCurrentProfit.setText("" + currentProfit);
				_summaryActivity.kreciolek.setVisibility(View.GONE);
				_summaryActivity.printTotals();
			}
		}
	}

	private class WypelnijTotalsMonth extends
			AsyncTask<String, Integer, ArrayList<String>> {

		SummaryActivity _summaryActivity;
		int localCurrentProfit = 0;
		int localTotalProfit = 0;
		int year, month;

		public WypelnijTotalsMonth(SummaryActivity sa, int y, int m) {
			_summaryActivity = sa;
			year = y;
			month = m;
		}

		@Override
		protected ArrayList<String> doInBackground(String... arg0) {
			ObslugaJSON jParser = new ObslugaJSON();
			ArrayList<String> localList = new ArrayList<String>();
			int localAllBoughts = 0, localAllSold = 0, localDownPayments = 0, localMonthlyPay = 0, localTotalInstallments = 0, localRemaining = 0;
			try {
				String fullUrl = ListActivity.urlTotalsMonth + "?year=" + year;
				if (month > 0)
					fullUrl += "" + "&month=" + month;
				Log.i("cocabitalMonth: ", "" + fullUrl);
				JSONObject json = jParser.getJSONFromUrl(fullUrl);
				Log.i("WypelnijTotals tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);
				String stringTemp;

				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);

					if (z.getString(TAG_TS_ALLBOUGHT_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_TS_ALLBOUGHT_MONTH);
						localAllBoughts += Integer.valueOf(stringTemp);
						localList.add(0, "" + localAllBoughts);
						Log.i("allBoughts: ", "" + localAllBoughts);
					}
					if (z.getString(TAG_TS_ALLSOLD_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_TS_ALLSOLD_MONTH);
						localAllSold += Integer.valueOf(stringTemp);
						localList.add(1, "" + localAllSold);
						Log.i("allSold: ", "" + localAllSold);
					}
					if (z.getString(TAG_TS_DOWNPAYMENT_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_TS_DOWNPAYMENT_MONTH);
						localDownPayments += Integer.valueOf(stringTemp);
						localList.add(2, "" + localDownPayments);
						Log.i("downPayments: ", "" + localDownPayments);
					}
					if (z.getString(TAG_TS_MONTHPAY_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_TS_MONTHPAY_MONTH);
						localMonthlyPay += Integer.valueOf(stringTemp);
						localList.add(3, "" + localMonthlyPay);
						Log.i("monthlyPay: ", "" + localMonthlyPay);
					}
					if (z.getString(TAG_TS_INSTALLMENTS_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_TS_INSTALLMENTS_MONTH);
						localTotalInstallments += Integer.valueOf(stringTemp);
						localList.add(4, "" + localTotalInstallments);
						Log.i("totalInstallments: ", ""
								+ localTotalInstallments);
					}
					if (z.getString(TAG_TS_REMAINING_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_TS_REMAINING_MONTH);
						localRemaining += Integer.valueOf(stringTemp);
						localList.add(5, "" + localRemaining);
						Log.i("remaining: ", "" + localRemaining);
					}
					if (z.getString(TAG_TS_CURRENTPROFIT_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_TS_CURRENTPROFIT_MONTH);
						localCurrentProfit += Integer.valueOf(stringTemp);
						localList.add(6, "" + localCurrentProfit);
						Log.i("localCurrentProfit: ", "" + localCurrentProfit);
					}
					if (z.getString(TAG_TS_TOTALPROFIT_MONTH).length() > 0) {
						stringTemp = z.getString(TAG_TS_TOTALPROFIT_MONTH);
						localTotalProfit += Integer.valueOf(stringTemp);
						localList.add(7, ""+localTotalProfit);
						Log.i("localTotalProfit: ", "" + localTotalProfit);
					}

					// localCurrentProfit += ((localDownPayments +
					// localMonthlyPay)
					// * (localAllSold - localAllBoughts) / localAllSold);

					// localList.add(6, "" + localCurrentProfit);
				}

			} catch (Exception e) {
				Log.i("WypelnijTotals", "Exception");
				Log.i("WypelnijTotals", "" + e);
				return null;
			}
			return localList;
		}

		@Override
		protected void onPreExecute() {
			_summaryActivity.kreciolek.setVisibility(View.VISIBLE);
			Log.i("onPreExecute", "onPreExecute");
			if (_summaryActivity != null)
				_summaryActivity.kreciolek.setVisibility(View.VISIBLE);

		}

		@Override
		protected void onPostExecute(ArrayList<String> result) {
			_summaryActivity.kreciolek.setVisibility(View.GONE);
			if (result == null)
				return;
			Log.i("onPostExecute", "WypelnijTotals ");
			if (_summaryActivity != null) {
				allBoughts = Integer.valueOf(result.get(0));
				allSold = Integer.valueOf(result.get(1));
				downPayments = Integer.valueOf(result.get(2));
				monthlyPay = Integer.valueOf(result.get(3));
				totalInstallments = Integer.valueOf(result.get(4));
				remaining = Integer.valueOf(result.get(5));
				currentProfit = Integer.valueOf(result.get(6));
				totalProfit = Integer.valueOf(result.get(7));

				Log.i("allBoughts: ", "" + allBoughts);
				Log.i("allSold: ", "" + allSold);
				Log.i("downPayments: ", "" + downPayments);
				Log.i("monthlyPay: ", "" + monthlyPay);
				Log.i("totalInstallments: ", "" + totalInstallments);
				Log.i("remaining: ", "" + remaining);
				Log.i("currentProfit: ", "" + currentProfit);
				Log.i("totalProfit: ", "" + totalProfit);

				// _summaryActivity.eAllBoughts.setText("" + allBoughts);
				// _summaryActivity.eAllSold.setText("" + allSold);
				// _summaryActivity.eDownpayments.setText("" + downPayments);
				// _summaryActivity.eMonthlyPay.setText("" + monthlyPay);
				// _summaryActivity.eTotalInstallments.setText("" +
				// totalInstallments);
				// _summaryActivity.eRemaining.setText("" + remaining);
				// _summaryActivity.eCurrentProfit.setText("" + currentProfit);
				_summaryActivity.kreciolek.setVisibility(View.GONE);
				_summaryActivity.printTotals();
			}
		}
	}

	private class WypelnijTotalProfit extends
			AsyncTask<String, Integer, Integer> {

		private SummaryActivity _summaryActivity;

		public WypelnijTotalProfit(SummaryActivity sa) {
			_summaryActivity = sa;
		}

		@Override
		protected Integer doInBackground(String... arg0) {
			ObslugaJSON jParser = new ObslugaJSON();

			int localTotalProfit = 0;
			try {
				JSONObject json = jParser.getJSONFromUrl(urlTotalProfit);
				Log.i("tabela: ", TAG_TABELA);
				zbrojenia = json.getJSONArray(TAG_TABELA);
				String stringTemp;

				for (int i = 0; i < zbrojenia.length(); i++) {
					JSONObject z = zbrojenia.getJSONObject(i);
					if (z.getString(TAG_TOTALPROFIT).length() > 0) {
						stringTemp = z.getString(TAG_TOTALPROFIT);
						localTotalProfit = Integer.valueOf(stringTemp);
						Log.i("totalProfit: ", "" + totalProfit);
					}
				}
			} catch (Exception e) {
				czekaj = false;
				return null;
			}

			return localTotalProfit;
		}

		@Override
		protected void onPreExecute() {
			if (_summaryActivity != null)
				_summaryActivity.kreciolek.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onPostExecute(Integer result) {
			if (result == null)
				return;
			totalProfit = result;
			if (_summaryActivity != null) {
				_summaryActivity.kreciolek.setVisibility(View.GONE);
				_summaryActivity.eTotalProfit.setText("" + totalProfit);
			}
		}

	}
	// private class WypelnijSummaryTotalInstallments extends
	// AsyncTask<String, Integer, String> {
	// @Override
	// protected String doInBackground(String... arg0) {
	// ObslugaJSON jParser = new ObslugaJSON();
	//
	// try {
	// JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
	// Log.i("tabela: ", TAG_TABELA);
	// zbrojenia = json.getJSONArray(TAG_TABELA);
	// int payed = 0;
	//
	// // TODO TOTAL INSTALLMENTS
	// // looping through All Contacts
	// for (int i = 0; i < zbrojenia.length(); i++) {
	// sellPrice = 0;
	// // downPayment = 0;
	// payments = 0;
	// paidTotal = 0;
	// String searchDate;
	// if (arg0[0].contains("99")) {
	// searchDate = ("");
	// } else
	// searchDate = ("" + arg0[0] + "-");
	// if (arg0[1].contains("99")) {
	// } else
	// searchDate += ("" + arg0[1] + "-");
	// Log.i("data podana: ", "" + searchDate);
	// JSONObject z = zbrojenia.getJSONObject(i);
	//
	// if (z.getString(TAG_DATE).contains("" + searchDate)) {
	// if (z.getString(TAG_SELLPRICE).length() > 0) {
	// lista[1] = z.getString(TAG_SELLPRICE);
	// sellPrice = Integer.valueOf(lista[1]);
	// }
	//
	// // if (z.getString(TAG_DOWNPAY).length() > 0) {
	// // lista[1] = z.getString(TAG_DOWNPAY);
	// // downPayment = Integer.valueOf(lista[1]);
	// // }
	//
	// if (z.getString(TAG_PAYEDTOTAL).length() > 0) {
	// lista[1] = z.getString(TAG_PAYEDTOTAL);
	// paidTotal = Integer.valueOf(lista[1]);
	// }
	//
	// if (z.getString(TAG_PAYED).length() > 0) {
	// lista[1] = z.getString(TAG_PAYED);
	// payed = Integer.valueOf(lista[1]);
	// payed++;
	// }
	//
	// totalInstallments += paidTotal;
	// }
	// }
	// } catch (JSONException e) {
	// Log.i("StackTrace", Log.getStackTraceString(e));
	// // e.printStackTrace();
	// }
	// czekaj = false;
	//
	// return null;
	// }
	//
	// @Override
	// protected void onPreExecute() {
	// czekaj = true;
	// }
	//
	// }

	// private class WypelnijSummaryCurrentProfit extends
	// AsyncTask<String, Integer, String> {
	// @Override
	// protected String doInBackground(String... arg0) {
	// ObslugaJSON jParser = new ObslugaJSON();
	//
	// try {
	// JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
	// Log.i("tabela: ", TAG_TABELA);
	// zbrojenia = json.getJSONArray(TAG_TABELA);
	// int payed = 0;
	// currentProfit = 0;
	// remaining = 0;
	// // TODO TOTAL INSTALLMENTS
	// // looping through All Contacts
	// for (int i = 0; i < zbrojenia.length(); i++) {
	// sellPrice = 0;
	// buyPrice = 0;
	// // downPayment = 0;
	// payments = 0;
	// String searchDate;
	// if (arg0[0].contains("99")) {
	// searchDate = ("");
	// } else
	// searchDate = ("" + arg0[0] + "-");
	// if (arg0[1].contains("99")) {
	// } else
	// searchDate += ("" + arg0[1] + "-");
	// Log.i("data podana: ", "" + searchDate);
	// JSONObject z = zbrojenia.getJSONObject(i);
	//
	// if (z.getString(TAG_SELLPRICE).length() > 0) {
	// lista[1] = z.getString(TAG_SELLPRICE);
	// sellPrice = Integer.valueOf(lista[1]);
	// }
	//
	// if (z.getString(TAG_BUYPRICE).length() > 0) {
	// lista[1] = z.getString(TAG_BUYPRICE);
	// buyPrice = Integer.valueOf(lista[1]);
	// }
	//
	// // if (z.getString(TAG_DOWNPAY).length() > 0) {
	// // lista[1] = z.getString(TAG_DOWNPAY);
	// // downPayment = Integer.valueOf(lista[1]);
	// // }
	//
	// if (z.getString(TAG_PAYED).length() > 0) {
	// lista[1] = z.getString(TAG_PAYED);
	// payed = Integer.valueOf(lista[1]);
	// payed++;
	//
	// if (z.getString(TAG_PAYEDTOTAL).length() > 0) {
	// lista[1] = z.getString(TAG_PAYEDTOTAL);
	// paidTotal = Integer.valueOf(lista[1]);
	// }
	//
	// Log.i("sellPrice: ", "" + sellPrice);
	// // Log.i("downPayment: ", "" + downPayment);
	// Log.i("paidTotal: ", "" + paidTotal);
	// Log.i("data podana: ", "" + searchDate);
	// // remaining += sellPrice - downPayments - paidTotal;
	// currentProfit += ((downPayments + paidTotal)
	// * (sellPrice - buyPrice) / sellPrice);
	// // totalProfit += sellPrice - buyPrice;
	//
	// Log.i("remaining: ", "" + remaining);
	//
	// if (z.getString(TAG_DATE).contains("" + searchDate)) {
	// if (z.getString(TAG_SELLPRICE).length() > 0) {
	// lista[1] = z.getString(TAG_SELLPRICE);
	// sellPrice = Integer.valueOf(lista[1]);
	// }
	//
	// if (z.getString(TAG_BUYPRICE).length() > 0) {
	// lista[1] = z.getString(TAG_BUYPRICE);
	// buyPrice = Integer.valueOf(lista[1]);
	// }
	//
	// // if (z.getString(TAG_DOWNPAY).length() > 0) {
	// // lista[1] = z.getString(TAG_DOWNPAY);
	// // downPayment = Integer.valueOf(lista[1]);
	// // }
	//
	// if (z.getString(TAG_PAYED).length() > 0) {
	// lista[1] = z.getString(TAG_PAYED);
	// payed = Integer.valueOf(lista[1]);
	// payed++;
	//
	// }
	// }
	// // remaining += sellPrice - downPayment - paidTotal;
	// Log.i("sellPrice: ", "" + sellPrice);
	// Log.i("remaining: ", "" + remaining);
	// // Log.i("downPayment: ", "" + downPayment);
	// Log.i("buyPrice: ", "" + buyPrice);
	//
	// // currentProfit += ((sellPrice - remaining)
	// // * (sellPrice - buyPrice) / sellPrice);
	// }
	// }
	// } catch (JSONException e) {
	// Log.i("StackTrace", Log.getStackTraceString(e));
	// // e.printStackTrace();
	// }
	//
	// currentProfit = (Integer) Math.ceil(currentProfit * 100) / 100;
	// czekaj = false;
	//
	// return null;
	// }
	//
	// @Override
	// protected void onPreExecute() {
	// czekaj = true;
	// }
	//
	// }

}
