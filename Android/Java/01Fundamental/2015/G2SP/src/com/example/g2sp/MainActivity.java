package com.example.g2sp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends Activity {

	 ImageView iv ;
	 Animation ani;
	 CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv = (ImageView) findViewById(R.id.main_logo);
        
        timer = new CountDownTimer(5 * 1000, 1000) {
        	@Override
			public void onTick(long millisUntilFinished) {
        		
			}

			@Override
			public void onFinish() {		
				Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
				startActivity(intent);
			}			
        };
        
        //Ÿ�̸� ����
        timer.start();
        
        ani = AnimationUtils.loadAnimation(getApplication(), R.anim.set_main);
    	iv.startAnimation(ani);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
