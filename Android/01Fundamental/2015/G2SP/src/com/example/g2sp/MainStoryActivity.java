package com.example.g2sp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainStoryActivity extends Activity {

	Button go_tutorial;
	Button skip_tutorial;

	Intent intent;

	HashMap<Integer, String[]> story_txt_list;
	HashMap<String, Integer> char_img_list;
	HashMap<String, Integer> back_img_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_story);
		intent = getIntent();
		story_txt_list = (HashMap<Integer, String[]>) intent.getExtras().getSerializable("story_txt_list");
		char_img_list = (HashMap<String, Integer>) intent.getExtras().getSerializable("char_img_list");
		back_img_list = (HashMap<String, Integer>) intent.getExtras().getSerializable("back_img_list");
		
		go_tutorial = (Button) findViewById(R.id.btn_go_tutorial);
		skip_tutorial = (Button) findViewById(R.id.btn_skip_tutorial);
	}

	public void goTutorial(View v){
		Intent intent = new Intent(getApplicationContext(), TalkActivity.class);
		intent.putExtra("story_txt_list", story_txt_list);
		intent.putExtra("char_img_list", char_img_list);
		intent.putExtra("back_img_list", back_img_list);
		startActivity(intent);
	}

	public void skipTutorial(View v) {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_story, menu);
		intent.putExtra("char_img_list", char_img_list);
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
