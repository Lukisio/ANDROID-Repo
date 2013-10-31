package com.przelicznik.vat.lukis;

import java.util.Locale;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	
	public Integer[] mThumbIds = {
            R.drawable.pl, R.drawable.pl2,
            R.drawable.at, R.drawable.be, R.drawable.bg,
            R.drawable.cy, R.drawable.cz, R.drawable.dk, R.drawable.ee, R.drawable.fi, R.drawable.fr,
            R.drawable.gr, R.drawable.es, R.drawable.nl, R.drawable.ie, R.drawable.lt,
            R.drawable.lu, R.drawable.lv, R.drawable.mt, R.drawable.de, R.drawable.pt,
            R.drawable.ro, R.drawable.sk, R.drawable.sl, R.drawable.se, 
            R.drawable.hu, R.drawable.gb, R.drawable.it
    };
	
	
	public ImageAdapter(Context c){
	    mContext = c;
	    
	}
	
	
	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return mThumbIds[position];
	}

	public long getItemId(int position) {
		
/*		switch(position){
			case 1:
				return 1;
			case 0:
				return 0;
			case 2:
				return 2;
			default:
				return 0;
		}
*/
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		
	        String [] krajPL = { //PL
	    			("Polska do 2010"), ("Polska od 2011"), ("Austria"), ("Belgia"), ("Bułgaria"), ("Cypr"),
	    			("Czechy"), ("Dania"), ("Estonia"), ("Finlandia"), ("Francja"), ("Grecja"),
	    			("Hiszpania"),	("Holandia"), ("Irlandia"), ("Litwa"),
	    			("Luksemburg"), ("Łotwa"), ("Malta"), ("Niemcy"), ("Portugalia"),
	    			("Rumunia"), ("Słowacja"), ("Słowenia"), ("Szwecja"),
	    			("Węgry"), ("Wielka Brytania"), ("Włochy")
	    	};
		//EN
	        String [] krajEN = {
	    			("Poland before 2011"), ("Poland since 2011"), ("Austria"), ("Belgium"), ("Bulgaria"), ("Cyprus"),
	    			("Czech Republic"), ("Denmark"), ("Estonia"), ("Finland"), ("France"), ("Greece"),
	    			("Spain"),	("Netherlands"), ("Ireland"), ("Lithuania"),
	    			("Luxembourg"), ("Latvia"), ("Malta"), ("Germany"), ("Portugal"),
	    			("Romania"), ("Slovakia"), ("Slovenia"), ("Sweden"),
	    			("Hungary"), ("United Kingdom"), ("Italy")
	        };
		
		
		
		
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth()/4;
		int height = display.getHeight()/4;
		int mniejszy;
		if (height<width) mniejszy = height; else mniejszy = width;
	//	ImageView imageView = new ImageView(mContext);
    //	imageView.setImageResource(mThumbIds[position]);
    //	imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    //	imageView.setLayoutParams(new GridView.LayoutParams(mniejszy, mniejszy));
      
		
	    View v;
	    if (convertView == null) {  // if it's not recycled, initialize some attributes
	    	LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        v = li.inflate(R.layout.icon, null);
	    } else {
	        v = (View) convertView;
	    }
	        
	        TextView tv = (TextView)v.findViewById(R.id.icon_text);
	        if(Locale.getDefault().getLanguage().equals("pl")) {tv.setText(krajPL[position]);} else {tv.setText(krajEN[position]);}
	        ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
	        iv.setImageResource(mThumbIds[position]);
	        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
	        iv.setLayoutParams(new LinearLayout.LayoutParams(mniejszy, mniejszy));

	    return v;
		
        
    //    return imageView;
	}







}
