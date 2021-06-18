package kr.young.framelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FrameLayout1Activity extends Activity {

	int[] ids ={
			R.id.imageView1,
			R.id.imageView2,
			R.id.imageView3,
			R.id.imageView4,
			R.id.imageView5
	};
	String[] val;
	int pos = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame_layout1);
		ImageView iv = null;
		for(int i = 1; i<ids.length; i++){
			iv = (ImageView) findViewById(ids[i]);
			iv.setVisibility(View.INVISIBLE);
		}
	}

	public void go_back_next(View v){
		val = getResources().getStringArray(R.array.poster_doc);
		int id = v.getId();
		ImageView iv = null;
		iv = (ImageView) findViewById(ids[pos]);
		iv.setVisibility(View.INVISIBLE);
		
		switch(id){
		case R.id.btn_back:
			pos--;
			if(pos<0){
				pos = ids.length-1;
			}
			break;
		case R.id.btn_next:
			pos++;
			if(pos>= ids.length){
				pos = 0;
			}
			break;
		}
		iv = (ImageView) findViewById(ids[pos]);
		iv.setVisibility(View.VISIBLE);
		TextView temp = (TextView) findViewById(R.id.text_name);
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
