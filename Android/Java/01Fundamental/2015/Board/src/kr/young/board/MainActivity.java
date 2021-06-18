package kr.young.board;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_goList).setOnClickListener(this);
	}

	public void goList(View v){
		setContentView(R.layout.board_list);
	}
	
	public void modify(View v){
		setContentView(R.layout.board_modify);
	}
	
	public void delete(View v){
		setContentView(R.layout.board_list);
	}
	public void back(View v){
		setContentView(R.layout.board_list);
	}
	
	public void output(View v){
		EditText mid = (EditText) findViewById(R.id.mid);
		EditText subject = (EditText) findViewById(R.id.subject);
		EditText content = (EditText) findViewById(R.id.content);
		
		Log.d("input", mid.getText().toString());
		Log.d("input", subject.getText().toString());
		Log.d("input", content.getText().toString());
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
			setContentView(R.layout.activity_main);
			return true;
		} else if (id == R.id.board_list) {
			setContentView(R.layout.board_list);
		} else if (id == R.id.board_view) {
			setContentView(R.layout.board_view);
		} else if (id == R.id.board_insert) {
			setContentView(R.layout.board_insert);
		} else if (id == R.id.board_modify) {
			setContentView(R.layout.board_modify);
		} else if (id == R.id.board_delete) {
			setContentView(R.layout.board_delete);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_goList){
			setContentView(R.layout.board_list);
		}
	}
}
