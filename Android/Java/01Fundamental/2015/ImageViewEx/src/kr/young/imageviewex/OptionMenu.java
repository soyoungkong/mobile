package kr.young.imageviewex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

public class OptionMenu {

	public OptionMenu() {

	}

	public OptionMenu(Activity context, MenuItem item) {

		 int id = item.getItemId();
	        Intent intent = null;
	        
	        switch(id){
	        case R.id.option_menu1:
	        	intent = new Intent(context,ImageView1Activity.class);
	        	context.startActivity(intent);
	        	break;
	        case R.id.option_menu2:
	        	intent = new Intent(context,ImageView2Activity.class);
	        	context.startActivity(intent);
	        	break;
	        case R.id.option_menu3:
	        	intent = new Intent(context,ImageView3Activity.class);
	        	context.startActivity(intent);
	        	break;
	        }
	}

}
