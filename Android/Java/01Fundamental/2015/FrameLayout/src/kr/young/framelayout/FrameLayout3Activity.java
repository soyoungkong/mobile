package kr.young.framelayout;

import java.lang.reflect.Field;

import android.R.drawable;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FrameLayout3Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame_layout3);
		
		Field[] idFields = R.drawable.class.getFields();
		LinearLayout layout = (LinearLayout) findViewById(R.id.scroll_body);
		int ids = 0;
		
		try {
			for(int i=0; i<idFields.length; i++){
				ids = idFields[i].getInt(null);
				Drawable drawable = getResources().getDrawable(ids);
				ImageView iv = new ImageView(this);
				iv.setImageDrawable(drawable);
				layout.addView(iv);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
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
