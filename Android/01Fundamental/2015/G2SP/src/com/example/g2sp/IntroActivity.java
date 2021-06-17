package com.example.g2sp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroActivity extends Activity {
	 Display display;
	 CountDownTimer timer_opening_img, timer_opening_text, timer_closing;
	 ImageView iv;
	 int round = 0;
	 int count = 0;
	 int countDown = 1;	// 1 ~ 10
	 boolean countCheck = false;
	 
	 String[] intro_txt_arr;
	 TextView intro_text;
	 StringBuilder temp_txt;
	 int textSize = 0;
	 int tempTime = 0;
	 int intColor = 6600;
	 String textColor = "#66000000";
	 int textAlpha = 128;
	 
	 int testcount = 0;
	 
	 float textX;
	 float textY;
	 Animation introAni;
	 int skipNumber = 1;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		
		intro_txt_arr = getResources().getStringArray(R.array.intro_text);
		
		iv = (ImageView) findViewById(R.id.intro_image);
		
		intro_text = (TextView) findViewById(R.id.intro_text);
		temp_txt = new StringBuilder();
		
		intro_text.setText("");
		
		textX = display.getWidth() /2;
		textY = display.getHeight() /2;
		Log.d("textX", "" +textX);
		Log.d("textY", "" +textY);
		
		timer_opening_img = new CountDownTimer(90 * 100, 100) {
	        	@Override
				public void onTick(long millisUntilFinished) {
	        		
	        		if(!countCheck){
	        			iv.setAlpha(countDown*12);
	        			countDown++;
	        			if(countDown == 20){
	        				round++;
	        				countCheck = true;
	        			}
	        		}else{
	        			count++;
	        			if(round == 2 && count % 2 == 0){
	        				
	        			}else{
		        			countDown--;
		        			iv.setAlpha(countDown*12);
		        			if(countDown == 1){
		        				countCheck = false;
		        			}
	        			}
	        		}
				}

				@Override
				public void onFinish() {		
					iv.setAlpha(0);
					for(int i = 0; i< intro_txt_arr.length; i++){
			        	Log.d("", "size: "+ intro_txt_arr[i].length() +"intro_text["+i+"]" +  intro_txt_arr[i]);
			        	textSize += intro_txt_arr[i].length();
			        }
					countDown = 0;
					round = 0;
					count = 10;
					introText();
					
				}			
	    };
	    timer_opening_img.start();
	    
	   
	        
	}

	public void introText(){
		skipNumber = 2;
		timer_opening_text = new CountDownTimer(((intro_txt_arr.length*4)+ textSize) * 100, 100) {
	        	@Override
				public void onTick(long millisUntilFinished) {
//	        		intro_text.setText(intro_txt_arr[countDown]);
//	        		countDown++;
	        		Log.d("전체 카운트 다운 = ",((intro_txt_arr.length*4)+ textSize) + "");
	        		Log.d("현재 카운트 다운 = ",testcount + "");
	        		if(tempTime == 0){
		        		if(countDown == intro_txt_arr[round].length()-1){
		        			intro_text.append(String.valueOf(intro_txt_arr[round].charAt(countDown)) + "\n");
		        			
		        			tempTime = 4;
		        			round++;
		        			countDown = 0;
		        			if(round == 9){
		        				intro_text.setText("");
		        			}
		        			if(round == 10){
		        				intro_text.setText("");
		        			}
		        		}
		        		else{
		        			if(round == 9){
		        				intColor +=11;
		        				textAlpha +=10;
		        				if(intColor >= 99){
		        					intColor = 99;
		        				}
		        				if(textAlpha >250){
		        					textAlpha = 255;
		        				}
		        				intro_text.setAlpha(textAlpha);
		        				textColor = "#"+intColor+"0000";
		        				intro_text.setTextSize(++count);
		        				intro_text.setTextColor(Color.parseColor(textColor));
		        			}
		        			else if(round == 10){
		        				intro_text.setTextColor(Color.parseColor("#ff0000"));
		        			}
		        			
	        				intro_text.append(String.valueOf(intro_txt_arr[round].charAt(countDown)));
	        				countDown++;
		        		}
	        		}
	        		else{
	        			tempTime--;
	        		}
	        		testcount++;
				}
				@Override
				public void onFinish() {
					countDown = 0;
					introClosing();
				}			
	        };
	        timer_opening_text.start();
	}
	
	public void introClosing(){
		skipNumber = 3;
//		introAni = AnimationUtils.loadAnimation(getApplication(), R.anim.intro_closing);
//		intro_text.startAnimation(introAni);
		timer_closing = new CountDownTimer(3000, 50) {
    		@Override
			public void onTick(long arg0) {
    			if(countDown % 2 == 0){
    			intro_text.setWidth((int) (Math.random()*textX));
    			intro_text.setHeight((int) (Math.random()*textY));
    			}
    			else if(countDown % 3 == 0){
        			intro_text.setWidth((int)(Math.random()*textX));
        			intro_text.setHeight((int) (Math.random()*textY));
        		}
    			else if(countDown % 5 == 0){
        			intro_text.setWidth((int)(Math.random()*textX));
        			intro_text.setHeight((int) (Math.random()*textY));
        		}
    			else if(countDown % 7 == 0){
        			intro_text.setWidth((int)(Math.random()*textX));
        			intro_text.setHeight((int) (Math.random()*textY));
        		}
    			countDown++;
    		}
    		
			@Override
			public void onFinish() {
				Intent intent = new Intent(getApplicationContext(), TitleActivity.class);
				startActivity(intent);
			}
    	};
    	timer_closing.start();
	}
	
	public void skipIntro(View v){
		if(skipNumber ==1){
			timer_opening_img.cancel();
		}
		else if(skipNumber == 2){
			timer_opening_text.cancel();
		}
		else if(skipNumber == 3){
			timer_closing.cancel();
		}
		else{
			
		}
		Intent intent = new Intent(getApplicationContext(), TitleActivity.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
