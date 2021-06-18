package kr.young.storageex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	Intent intent = null;
	
	public void sharedPreferences(View v) {
		intent = new Intent(this, SharedPreferencesActivity.class);
		startActivity(intent);
	}

	public void interalStorage(View v) {
		intent = new Intent(this, InteralStorageActivity.class);
		startActivity(intent);
	}

	public void exteralStorage(View v) {
		intent = new Intent(this, ExteralStorageActivity.class);
		startActivity(intent);
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
		new OptionMenu(this, item);
		return super.onOptionsItemSelected(item);
	}
}
