package kr.young.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Member_Input_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_input);
	}

    public void goMain(View v){
    	Intent intent = null;
    	intent = new Intent(this,MemberInputResultActivity.class);
    	EditText mid = (EditText) findViewById(R.id.txt_id);
    	EditText pwd = (EditText) findViewById(R.id.txt_pw);
    	
    	intent.putExtra("show_id", mid.getText().toString());
    	intent.putExtra("show_pw", pwd.getText().toString());
    	
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
