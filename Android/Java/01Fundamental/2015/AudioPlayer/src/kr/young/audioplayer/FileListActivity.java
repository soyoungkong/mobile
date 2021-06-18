package kr.young.audioplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FileListActivity extends Activity implements OnItemClickListener {

	String folder = "/mnt/";
	String playfile;
	ListView lv;
	boolean isRoot = false;
	File file;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_list);
		
		lv = (ListView) findViewById(R.id.listView1);
		lv.setOnItemClickListener(this);
	}

	
	
	@Override
	protected void onResume() {
	
		load();
		super.onResume();
	}


	public void load(){
		file = new File(folder);
		ArrayList<String> files = new ArrayList<String>();
		
		try {
			String[] temp = file.list();
			
			if(file.getParent() != null){
				files.add("..");
			}
			
			Collections.addAll(files,  temp);
			
			ArrayAdapter<String> adapter = 
					new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
			
			lv.setAdapter(adapter);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_list, menu);
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
		
		String str = ((TextView)view).getText().toString();
//		Toast.makeText(this, "file : " + str, Toast.LENGTH_SHORT).show();
		File temp = new File(folder);
		
		if(str.equals("..")){
			folder = temp.getParent();
			load();
		}
		
		else if(temp.isDirectory()){
			folder = temp.getPath() + "/" +str;
			Toast.makeText(this, "file : " + folder, Toast.LENGTH_SHORT).show();
			load();
		}
		else if(temp.isFile()){
			Intent intent =  new Intent(this, MainActivity.class);
			String playfile = temp.getPath() + "/" + str;
			intent.putExtra("playfile", playfile);
			
			startActivity(intent);
		}
	}
}
