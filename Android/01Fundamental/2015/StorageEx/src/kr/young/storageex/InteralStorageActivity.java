package kr.young.storageex;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InteralStorageActivity extends Activity {

	final int INTERNAL_STORAGE = 1;
	final int INTERNAL_STORAGE_DEFAULT = 0;
	final int MY_RESULT_CODE = 4444;
	String fileName = "mynote";
	EditText et;
	
//	AlertDialog.Builder ad = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interal_storage);
		
		//et = (EditText) findViewById(R.id.text_val);
	}

	public void fileSave(View v){
		save();
	}
	
	@Override
	protected void onStop() {
		//save();
		
		super.onStop();
	}
	
	public void save(){
		FileOutputStream fos = null;
		
		try {
			fos = openFileOutput(fileName, Activity.MODE_PRIVATE);
			EditText et = (EditText) findViewById(R.id.text_val);
			
			String temp = et.getText().toString();
			
			fos.write(temp.getBytes());
			fos.close();
			Toast.makeText(getApplicationContext(), "파일이 저장됩니다",
					Toast.LENGTH_SHORT).show();
			
			SharedPreferences sp = getSharedPreferences("myname", Activity.MODE_PRIVATE);
			SharedPreferences.Editor edit = sp.edit();
			edit.putString("key", et.getText().toString());
			edit.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	protected void save_message(){
//		ad = new AlertDialog.Builder(this);
//		ad.setTitle("파일 저장");
//		ad.setMessage("저장하시겠습니까?");
//		ad.setPositiveButton("저장", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(getApplicationContext(), "파일이 저장됩니다",
//						Toast.LENGTH_SHORT).show();
//			}
//		});
//
//		ad.setNegativeButton("저장 안함", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(getApplicationContext(), "취소하였습니다.",
//						Toast.LENGTH_SHORT).show();
//			}
//		});
//		ad.show();
//	}
	
	public void fileLoad(View v) {
		
	}

	@Override
	protected void onStart() {
		
		SharedPreferences sp = getSharedPreferences("myname", Activity.MODE_PRIVATE);
		String str = sp.getString("key",  "값이 없어요");
		this.fileName = str;
		load();
		Toast.makeText(getApplicationContext(), fileName,
				Toast.LENGTH_SHORT).show();
		super.onStart();
	}


	protected void load(){
		FileInputStream fis = null;
		EditText et = null;
		try {
			fis = openFileInput(fileName);
			byte[] data = new byte[fis.available()];
			
			fis.read(data);
			et = (EditText) findViewById(R.id.text_val);
			et.setText(new String(data));
			
			fis.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void fileList(View v){
		Intent intent = new Intent(this, InteralListActivity.class);
		startActivityForResult(intent, INTERNAL_STORAGE);
	}
	public void addNote(View v){
		Intent intent = new Intent(this, AddNoteActivity.class);
		startActivityForResult(intent, INTERNAL_STORAGE_DEFAULT);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String str = "";
		
		if(requestCode == INTERNAL_STORAGE && resultCode == MY_RESULT_CODE){
			str = data.getStringExtra("mynote");
			Toast.makeText(this, str,
					Toast.LENGTH_SHORT).show();
			fileName = str;
		}
		else if(requestCode == INTERNAL_STORAGE_DEFAULT && resultCode == MY_RESULT_CODE){
			str = data.getStringExtra("mynote");
			Toast.makeText(this, str,
					Toast.LENGTH_SHORT).show();
			fileName = str;
			save();
			
		}
		SharedPreferences sp = getSharedPreferences("myname", Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit = sp.edit();
		edit.putString("key", str);
		edit.commit();
		
		load();
		super.onActivityResult(requestCode, resultCode, data);
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
