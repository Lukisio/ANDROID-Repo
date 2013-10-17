package com.imprezowa.pizza.lukis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;





public class activity2 extends GlowneOkno{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

	        Button next = (Button) findViewById(R.id.button1);
	        next.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                Intent intent = new Intent();
	                setResult(RESULT_OK, intent);
	                finish();
	            }
	        	});
	}
}
