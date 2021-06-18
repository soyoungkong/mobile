package kr.young.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MemberInputResultActivity extends Activity {
	String id ="";
	String pw = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_input_result);
		
		Intent it = getIntent();
		id = it.getStringExtra("show_id");
		pw = it.getStringExtra("show_pw");
		
		Log.d("", id + pw);
		TextView show_id = (TextView)findViewById(R.id.show_id);
		TextView show_pw = (TextView)findViewById(R.id.show_pw);
		show_id.setText(id);
		show_pw.setText(pw);
	}

	public void goMain(View v){
		Intent intent = null;
    	intent = new Intent(this,MainActivity.class);
    	this.startActivity(intent);
	}

	public void modify(View v){
		Intent intent = getIntent();
    	
    	intent.putExtra("id", intent.getStringExtra("show_id"));
    	intent.putExtra("pw", intent.getStringExtra("show_pw"));
    	
    	intent.setClass(getApplicationContext(), MemberModifyActivity.class);
    	this.startActivity(intent);
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
