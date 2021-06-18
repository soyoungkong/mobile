package kr.young.framelayout;

import java.lang.reflect.Field;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class FrameLayout2Activity extends Activity {

	int[] ids = null;
	int pos = 0;
	String[] val = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame_layout2);
		
		//res/drawable 안에 있는 모든 리소스를 가져옴
		Field[] idFields = R.drawable.class.getFields();		// R.java안에 drawable에 컴파일된 아이디 값을 가져옴
		
		FrameLayout frame = (FrameLayout) findViewById(R.id.frame_body);
		ids = new int[idFields.length];
		val = new String[idFields.length];
		
		try {
			for(int i = 0; i<ids.length; i++){
				ids[i] = idFields[i].getInt(null);
				val[i] = idFields[i].getName();
				//id에 해당하는 이미지 리소스 생성
				Drawable drawable = getResources().getDrawable(ids[i]);
				ImageView iv = new ImageView(this);
				if(i>0){
					iv.setVisibility(View.INVISIBLE);
				}
				iv.setImageDrawable(drawable);
				frame.addView(iv);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		TextView temp = (TextView) findViewById(R.id.txt_view2);
		temp.setText(val[pos]);
	}

	public void go_back_next2(View v){
		
		int id = v.getId();
		Field[] idFields = R.drawable.class.getFields();		// R.java안에 drawable에 컴파일된 아이디 값을 가져옴
		
		FrameLayout frame = (FrameLayout) findViewById(R.id.frame_body);
//		val = getResources().getStringArray(R.array.poster_doc);
		frame.removeAllViews();
		ids = new int[idFields.length];
		
		ImageView iv = null;
		
		switch(id){
		case R.id.btn_back2:
			pos--;
			if(pos<0){
				pos = ids.length-1;
			}
			break;
		case R.id.btn_next2:
			pos++;
			if(pos>= ids.length){
				pos = 0;
			}
			break;
		}
		
		try {
			for(int i = 0; i<ids.length; i++){
				ids[i] = idFields[i].getInt(null);
				
				//id에 해당하는 이미지 리소스 생성
				Drawable drawable = getResources().getDrawable(ids[i]);
				iv = new ImageView(this);
				
				if(i  == pos){
					iv.setVisibility(View.VISIBLE);
				}
				else{
					iv.setVisibility(View.INVISIBLE);
				}
				
				iv.setImageDrawable(drawable);
				frame.addView(iv);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		TextView temp = (TextView) findViewById(R.id.txt_view2);
		temp.setText(val[pos]);
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
