package com.lukis.installments;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

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
	
	JSONArray zbrojenia = null;
	int dlugosc=0;
	
	double cash=0.0;
	public double deposits=0.0;
	double downPayments=0.0;
	double totalInstallments=0.0;
	double allBoughts=0.0;
	double expenses=0.0;
	double sWithdrawals=0.0;
	double mWithdrawals=0.0;
	double aWithdrawals=0.0;
	double currentProfit=0.0;
	double netProfit=0.0;
	double sProfit=0.0;
	double mProfit=0.0;
	double aProfit=0.0;
	double payments=0.0;
	double sellPrice=0.0;
	double buyPrice=0.0;
	double remaining=0.0;
	double downPayment=0.0;
	String lista[]=new String[8];
	public Boolean czekaj = false;
    public KlasaSummary(){
        super();
    }
    
    public double obliczDeposits(){
		new WypelnijSummaryExpenses().execute("");
		while(czekaj){}
		return deposits;
    }
    
    public double obliczDownPayments(){
		new WypelnijSummaryDownPayments().execute("");
		while(czekaj){}
		return downPayments;
    }
    
    public double obliczPaymentsDone(){
		new WypelnijSummaryPaymentsDone().execute("");
		while(czekaj){}
		return payments;
    }
    
    public double obliczTotalInstallments(){
		new WypelnijSummaryTotalInstallments().execute("");
		while(czekaj){}
		Log.i("total installments: ", ""+totalInstallments);
		return totalInstallments;
    }
    
    public double obliczCurrentProfit(){
		new WypelnijSummaryCurrentProfit().execute("");
		while(czekaj){}
		Log.i("current profit: ", ""+currentProfit);
		return currentProfit;
    }
    
    
    private class WypelnijSummaryExpenses extends AsyncTask<String, Integer, String>{
    	 //wypelnia listę kilkoma kolumnami z calej tabeli
  @Override
  protected String doInBackground(String... arg0) {
   // TODO Auto-generated method stub
      ObslugaJSON jParser = new ObslugaJSON();
 
      try {
          JSONObject json = jParser.getJSONFromUrl(ADepositActivity.urlExp);
      	Log.i("tabela: ", TAG_TABELA);
          zbrojenia = json.getJSONArray(TAG_TABELA);    
          dlugosc=0;
          deposits=0;
          aWithdrawals=0.0;
		  mWithdrawals=0.0;
		  sWithdrawals=0.0;
		  expenses=0.0;
		  
          // looping through All Contacts
			for(int i = 0; i < zbrojenia.length(); i++){
				JSONObject z = zbrojenia.getJSONObject(i);
				dlugosc++;
			//	Log.i("tag name: ", z.getString(TAG_NAME));
				if (z.getString(TAG_NAME).equals("Deposit_A")){ //wypełnia Deposits dla Name == Deposit_A
					if(z.getString(TAG_DEPOSIT).length() > 0) lista[0] = z.getString(TAG_DEPOSIT);
			//		Log.i("tag deposit: ", z.getString(TAG_DEPOSIT));
				    deposits += Double.valueOf(lista[0]);
				}
				
				if (z.getString(TAG_NAME).equals("Withdraw_A")){ 
					if(z.getString(TAG_DEPOSIT).length() > 0) lista[0] = z.getString(TAG_DEPOSIT);
			//		Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
					aWithdrawals += Double.valueOf(lista[0]);
				}
				
				if (z.getString(TAG_NAME).equals("Withdraw_M")){ 
					if(z.getString(TAG_DEPOSIT).length() > 0) lista[0] = z.getString(TAG_DEPOSIT);
			//		Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
					mWithdrawals += Double.valueOf(lista[0]);
				}

				if (z.getString(TAG_NAME).equals("Withdraw_S")){
					if(z.getString(TAG_DEPOSIT).length() > 0) lista[0] = z.getString(TAG_DEPOSIT);
			//		Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
					sWithdrawals += Double.valueOf(lista[0]);
				}
				
				if (z.getString(TAG_NAME).equals("Gen_Expense")){
					if(z.getString(TAG_DEPOSIT).length() > 0) lista[0] = z.getString(TAG_DEPOSIT);
			//		Log.i("tag withdrawal: ", z.getString(TAG_DEPOSIT));
					expenses += Double.valueOf(lista[0]);
				}

			 //   publishProgress(i);
			}
      } catch (JSONException e) {
          e.printStackTrace();
      }
 	  czekaj=false;

   return null;
  }
  
  @Override
  protected void onPostExecute(String result) {
 //  setProgressBar(STOP_PROGRESS);
 //  button.setEnabled(true);
	//  info.setText("name: "+detal.name);
  }
  
  @Override
  protected void onPreExecute() {
	  czekaj=true;
  }
  
  @Override
  protected void onProgressUpdate(Integer... progress) {
 //  info.setText(""+progress[0]);
  }
}
  
  
  private class WypelnijSummaryDownPayments extends AsyncTask<String, Integer, String>{
 	 //wypelnia listę kilkoma kolumnami z calej tabeli
@Override
protected String doInBackground(String... arg0) {
   ObslugaJSON jParser = new ObslugaJSON();

   try {
       JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
       zbrojenia = json.getJSONArray(TAG_TABELA);
       downPayments=0;
       allBoughts=0;
       // looping through All Contacts
			for(int i = 0; i < zbrojenia.length(); i++){
				JSONObject z = zbrojenia.getJSONObject(i);
				if(z.getString(TAG_DOWNPAY).length() > 0) lista[1] = z.getString(TAG_DOWNPAY);
			    downPayments += Double.valueOf(lista[1]);

				if(z.getString(TAG_BUYPRICE).length() > 0) lista[1] = z.getString(TAG_BUYPRICE);
			    allBoughts += Double.valueOf(lista[1]);

			}
   } catch (JSONException e) {
       e.printStackTrace();
   }
	  czekaj=false;

return null;
}
  
  @Override
  protected void onPostExecute(String result) {
 //  setProgressBar(STOP_PROGRESS);
 //  button.setEnabled(true);
	//  info.setText("name: "+detal.name);
  }
  
  @Override
  protected void onPreExecute() {
	  czekaj=true;
  }
  
  @Override
  protected void onProgressUpdate(Integer... progress) {
 //  info.setText(""+progress[0]);
  }
}

  
  private class WypelnijSummaryPaymentsDone extends AsyncTask<String, Integer, String>{
	@Override
	protected String doInBackground(String... arg0) {
	   ObslugaJSON jParser = new ObslugaJSON();

	   try {
	       JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
	   	Log.i("tabela: ", TAG_TABELA);
	       zbrojenia = json.getJSONArray(TAG_TABELA);
	       int payed=0;
	       payments=0.0;
	       // looping through All Contacts
				for(int i = 0; i < zbrojenia.length(); i++){
					JSONObject z = zbrojenia.getJSONObject(i);
					if(z.getString(TAG_PAYED).length() > 0) {
						lista[1] = z.getString(TAG_PAYED);
						payed= Integer.valueOf(lista[1]);
						Log.i("payed: ", ""+payed);
						payed++;
						for(int j = 1; j< payed ; j++){ //UWAGA! Liczymy od pozycji paylist1!

							Log.i("length: ", ""+TAG_PAYLIST+j);
							if(z.getString(TAG_PAYLIST+j).length() > 0){
								lista[2] = z.getString(TAG_PAYLIST+j);
								Log.i("lista[2]: ", ""+Double.valueOf(lista[2]));
								payments+= Double.valueOf(lista[2]);
							};

						   
						}	
					}

				}
	   } catch (JSONException e) {
	       e.printStackTrace();
	   }
		  czekaj=false;

	return null;
	}
	
	@Override
	protected void onPreExecute() {
		czekaj=true;
	}
	  
	}

  
  
  private class WypelnijSummaryTotalInstallments extends AsyncTask<String, Integer, String>{
	@Override
	protected String doInBackground(String... arg0) {
	   ObslugaJSON jParser = new ObslugaJSON();

	   try {
	       JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
	   	Log.i("tabela: ", TAG_TABELA);
	       zbrojenia = json.getJSONArray(TAG_TABELA);
	       int payed=0;
	       
	       //TODO TOTAL INSTALLMENTS
	       // looping through All Contacts
				for(int i = 0; i < zbrojenia.length(); i++){
					sellPrice=0.0;
					downPayment=0.0;
					payments=0.0;
					JSONObject z = zbrojenia.getJSONObject(i);

					if(z.getString(TAG_SELLPRICE).length() > 0) {
						lista[1] = z.getString(TAG_SELLPRICE);
						sellPrice = Double.valueOf(lista[1]);
					}
					
					if(z.getString(TAG_DOWNPAY).length() > 0) {
						lista[1] = z.getString(TAG_DOWNPAY);
						downPayment = Double.valueOf(lista[1]);
					}
				    
					
					if(z.getString(TAG_PAYED).length() > 0) {
						lista[1] = z.getString(TAG_PAYED);
						payed= Integer.valueOf(lista[1]);
						payed++;
						for(int j = 1; j< payed ; j++){ //UWAGA! Liczymy od pozycji paylist1!
					//		Log.i("length: ", ""+TAG_PAYLIST+j);
							if(z.getString(TAG_PAYLIST+j).length() > 0){
								lista[2] = z.getString(TAG_PAYLIST+j);
					//			Log.i("lista[2]: ", ""+Double.valueOf(lista[2]));
								payments+= Double.valueOf(lista[2]);
							};
						}
					}
					
					totalInstallments+= (sellPrice-downPayment-payments);

				}
	   } catch (JSONException e) {
	       e.printStackTrace();
	   }
		  czekaj=false;

	return null;
	}
	
	@Override
	protected void onPreExecute() {
		czekaj=true;
	}
	  
	}  
  private class WypelnijSummaryCurrentProfit extends AsyncTask<String, Integer, String>{
	@Override
	protected String doInBackground(String... arg0) {
	   ObslugaJSON jParser = new ObslugaJSON();

	   try {
	       JSONObject json = jParser.getJSONFromUrl(ListActivity.url);
	   	Log.i("tabela: ", TAG_TABELA);
	       zbrojenia = json.getJSONArray(TAG_TABELA);
	       int payed=0;
	       currentProfit=0.0;
	       //TODO TOTAL INSTALLMENTS
	       // looping through All Contacts
				for(int i = 0; i < zbrojenia.length(); i++){
					sellPrice=0.0;
					buyPrice=0.0;
					downPayment=0.0;
					remaining=0.0;
					payments=0.0;
					JSONObject z = zbrojenia.getJSONObject(i);

					if(z.getString(TAG_SELLPRICE).length() > 0) {
						lista[1] = z.getString(TAG_SELLPRICE);
						sellPrice = Double.valueOf(lista[1]);
					}
					
					if(z.getString(TAG_BUYPRICE).length() > 0) {
						lista[1] = z.getString(TAG_BUYPRICE);
						buyPrice = Double.valueOf(lista[1]);
					}
					
					if(z.getString(TAG_DOWNPAY).length() > 0) {
						lista[1] = z.getString(TAG_DOWNPAY);
						downPayment = Double.valueOf(lista[1]);
					}
					
					if(z.getString(TAG_PAYED).length() > 0) {
						lista[1] = z.getString(TAG_PAYED);
						payed= Integer.valueOf(lista[1]);
						payed++;
						for(int j = 1; j< payed ; j++){ //UWAGA! Liczymy od pozycji paylist1!
					//		Log.i("length: ", ""+TAG_PAYLIST+j);
							if(z.getString(TAG_PAYLIST+j).length() > 0){
								lista[2] = z.getString(TAG_PAYLIST+j);
						//		Log.i("lista[2]: ", ""+Double.valueOf(lista[2]));
								payments+= Double.valueOf(lista[2]);
							};
						}
					}
					remaining=sellPrice-downPayment-payments;
					Log.i("sellPrice: ", ""+sellPrice);
					Log.i("remaining: ", ""+remaining);
					Log.i("downPayment: ", ""+downPayment);
					Log.i("buyPrice: ", ""+buyPrice);
					
					currentProfit+= ((sellPrice-remaining)*(sellPrice-buyPrice)/sellPrice);

				}
	   } catch (JSONException e) {
	       e.printStackTrace();
	   }

	   currentProfit = (Double) Math.ceil(currentProfit*100)/100;
		  czekaj=false;

	return null;
	}
	
	@Override
	protected void onPreExecute() {
		czekaj=true;
	}
	  
	}
 
}
