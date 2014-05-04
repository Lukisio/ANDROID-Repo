package com.lukis.skup.butelek;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Beer {
    private int lp;
    private String code;
    private String name;
    private String producer;
    private String returnable;
    private String date;
    
    
	public int getLp() {
		return lp;
	}
	public void setLp(int lp) {
		this.lp = lp;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getReturnable() {
		return returnable;
	}
	public void setReturnable(String returnable) {
		this.returnable = returnable;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void saveToLocalDB(Activity v) {
		// TODO Auto-generated method stub
		Toast.makeText(v, "Bottle Added" + getCode() +" "+ getDate() +" "+ getName() +" "+ getProducer() +" "+ getReturnable(),  Toast.LENGTH_SHORT).show();
		Log.i("saveToLocalDB", "" + getName());
	}
    

    

    
    
    
}
