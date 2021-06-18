package kr.young.storageex;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class InteralListActivity extends Activity implements OnItemClickListener {

	final int MY_RESULT_CODE = 4444;
	String fileName = "mynote";
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interal_list);
		
		lv = (ListView)findViewById(R.id.listView1);
	    lv.setOnItemClickListener(this);
	}
	
	public void push(View v){
		TextView str =(TextView) v;
		
		Intent intent = new Intent();
		intent.putExtra(fileName, str.getText());
		setResult(MY_RESULT_CODE, intent);
		finish();
	}
	
	@Override
	protected void onResume() {
		ArrayList<String> al = new ArrayList<String>();
		ArrayAdapter<String> adapter = null;
		
		try {
			Context cont =  getApplicationContext();
			File file = cont.getFilesDir();
			File[] files = file.listFiles();
			for(File f: files){
				al.add(f.getName());
			}
			adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
			lv.setAdapter(adapter);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interal_list, menu);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		push(view);
	}
}
