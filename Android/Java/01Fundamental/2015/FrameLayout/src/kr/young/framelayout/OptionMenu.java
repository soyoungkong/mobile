package kr.young.framelayout;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

public class OptionMenu {

	public OptionMenu(Activity act, MenuItem item) {
		int id = item.getItemId();
		Intent intent = null;
		
		switch(id){
		case R.id.goMain:
			intent = new Intent(act, MainActivity.class);
			break;
		case R.id.menu_frame1:
			intent = new Intent(act, FrameLayout1Activity.class);
			break;
		case R.id.menu_frame2:
			intent = new Intent(act, FrameLayout2Activity.class);
			break;
		case R.id.menu_frame3:
			intent = new Intent(act, FrameLayout3Activity.class);
			break;
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		act.startActivity(intent);
	}

}
