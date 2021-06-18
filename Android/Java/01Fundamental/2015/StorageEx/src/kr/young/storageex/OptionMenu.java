package kr.young.storageex;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

public class OptionMenu {

	public OptionMenu(Activity act, MenuItem item) {
		int id = item.getItemId();
		Intent intent = null;
		
		switch(id){
		case R.id.home:
			intent = new Intent(act, MainActivity.class);
			break;
		case R.id.sharedPreferences:
			intent = new Intent(act, SharedPreferencesActivity.class);
			break;
		case R.id.interalStorage:
			intent = new Intent(act, InteralStorageActivity.class);
			break;
		case R.id.exteralStorage:
			intent = new Intent(act, ExteralStorageActivity.class);
			break;
		}
		act.startActivity(intent);
	}

}
