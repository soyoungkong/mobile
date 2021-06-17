package kr.young.storageex;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SharedPreferencesActivity extends Activity {

	EditText et;
	EditText et_color;
	EditText et_size;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences);
		
		et = (EditText) findViewById(R.id.editText1);
		et_color = (EditText) findViewById(R.id.edit_color);
		et_size = (EditText) findViewById(R.id.edit_size);
	}

	public void saveStr(View v){
		
		SharedPreferences sp_size = getSharedPreferences("myname_size", Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit_size = sp_size.edit();
		
		SharedPreferences sp_color= getSharedPreferences("myname_color", Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit_color = sp_color.edit();
		
		SharedPreferences sp = getSharedPreferences("myname", Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit = sp.edit();
		
		edit_color.putString("key", et_color.getText().toString());
		edit_size.putString("key", et_size.getText().toString());
		edit.putString("key", et.getText().toString());
		
		edit.commit();
		edit_size.commit();
        edit_color.commit();
        
		et.setTextSize(Integer.parseInt(et_size.getText().toString()));
		et.setTextColor(Color.parseColor(et_color.getText().toString()));
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	@Override
	protected void onStart() {
		
		
		
		SharedPreferences sp = getSharedPreferences("myname", Activity.MODE_PRIVATE);
		String str = sp.getString("key",  "값이 없어요");
		
		SharedPreferences sp_color = getSharedPreferences("myname_color", Activity.MODE_PRIVATE);
		String str_color = sp_color.getString("key",  "#ff0000");
		
		SharedPreferences sp_font = getSharedPreferences("myname_font", Activity.MODE_PRIVATE);
		String str_font = sp_font.getString("key",  "35");
		
		et.setText(str);
		et_color.setText(str_color);
		et_size.setText(str_font);
		
		et.setTextSize(Integer.parseInt(et_size.getText().toString()));
		et.setTextColor(Color.parseColor(et_color.getText().toString()));
		
		super.onStart();
	}

	// 현재 activity가 중지되면 자동으로 현재값을 저장
	@Override
	protected void onStop() {
		
		SharedPreferences sp_size = getSharedPreferences("myname_size", Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit_size = sp_size.edit();
		SharedPreferences sp_color= getSharedPreferences("myname_color", Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit_color = sp_color.edit();
		SharedPreferences sp = getSharedPreferences("myname", Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit = sp.edit();
		
		edit_color.putString("key", et_color.getText().toString());
		edit_size.putString("key", et_size.getText().toString());
		edit.putString("key", et.getText().toString());
		
		edit.commit();
		edit_size.commit();
        edit_color.commit();
		
		super.onStop();
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
