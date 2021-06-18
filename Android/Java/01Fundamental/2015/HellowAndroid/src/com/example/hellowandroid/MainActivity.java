package com.example.hellowandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // 어플이 처음에 실행됬을 때 한번만 실행됨
												// setContentView 띄워줄 턴텐츠
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
		} else if (id == R.id.main1) {
			setContentView(R.layout.activity_main);
		} else if (id == R.id.main2) {
			setContentView(R.layout.layout_main2);
		} else if (id == R.id.main3) {
			setContentView(R.layout.layout_main3);
		}
		return super.onOptionsItemSelected(item);
	}
	
}
