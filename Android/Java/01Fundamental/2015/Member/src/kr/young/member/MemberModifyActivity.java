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

public class MemberModifyActivity extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_modify);
		Intent it = getIntent();
		
		String id = it.getStringExtra("id");
		String pw = it.getStringExtra("pw");
		Log.d("", id + pw);
		TextView show_id = (TextView)findViewById(R.id.text_id);
		TextView show_pw = (TextView)findViewById(R.id.text_pw);
		show_id.setText(id);
		show_pw.setText(pw);
	}

	
	 public void finish(View v){
	    	Intent intent = null;
	    	intent = new Intent(this,MemberModifyResultActivity.class);
	    	EditText mid = (EditText) findViewById(R.id.text_id);
	    	EditText pwd = (EditText) findViewById(R.id.text_pw);
	    	
	    	intent.putExtra("id", mid.getText().toString());
	    	intent.putExtra("pw", pwd.getText().toString());
	    	
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
