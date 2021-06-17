package com.example.g2sp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TitleActivity extends Activity {

	HashMap<Integer, String[]> story_txt_list;
	HashMap<String, Integer> char_img_list;
	HashMap<String, Integer> back_img_list;
	String[] story_txt_arr;
	int arr_count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
		
		imgSet();
		storySet();
	}
	
	public void imgSet(){
		char_img_list = new HashMap<String, Integer>();
		char_img_list.put("������", R.drawable.taehoon_1);
		char_img_list.put("����", R.drawable.youngwoo_1);
		char_img_list.put("����@", R.drawable.youngwoo_2);
		char_img_list.put("�Ⱥ��� ����", R.drawable.an_1_default);
		char_img_list.put("��������", R.drawable.soyoung_1_default);
		
		back_img_list = new HashMap<String, Integer>();
		back_img_list.put("����", R.drawable.null_img);
		back_img_list.put("���ǽ�", R.drawable.back_room);
		back_img_list.put("����ö", R.drawable.back_pro_1);
		back_img_list.put("����", R.drawable.back_cave);
		back_img_list.put("�縷", R.drawable.back_desert);
		back_img_list.put("��", R.drawable.back_forest);
		back_img_list.put("�ٴ�", R.drawable.back_sea);
		back_img_list.put("�ϴ�", R.drawable.back_sky);
		
	}
	
	public void storySet(){
		arr_count = R.array.story_0;
		story_txt_list = new HashMap<Integer, String[]>();
		for(int i = 0; i<= 5; i++){
			story_txt_arr = getResources().getStringArray(arr_count);
			story_txt_list.put(i, story_txt_arr);
			arr_count++;
		}
	}

	public void gameContinue(View v){
		
	}

	public void gameStart(View v) {
		Intent intent = new Intent(getApplicationContext(), MainStoryActivity.class);
		intent.putExtra("story_txt_list", story_txt_list);
		intent.putExtra("char_img_list", char_img_list);
		intent.putExtra("back_img_list", back_img_list);
		startActivity(intent);
	}

	public void gameEnd(View v) {

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.title, menu);
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
