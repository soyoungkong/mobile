package kr.young.fragmentex;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		int id = item.getItemId();
		
		FragmentTransaction manger = 
				getFragmentManager().beginTransaction();
		Fragment frag = null;
		
		switch (id) {
			case R.id.menu_feag_add:
				frag = new SecondFragment();
				manger.add(R.id.fragment1,frag);
				manger.addToBackStack(null);
				manger.commitAllowingStateLoss();
				manger.commit();
				break;
			case R.id.menu_feag_replace:
				frag = new ThirdFragment();
				manger.replace(R.id.fragment1,frag);
				manger.commit();
				break;
			case R.id.menu_layout_add:
				frag = new SecondFragment();
				manger.add(R.id.loot_layout,frag);
				manger.commit();
				break;
			case R.id.menu_layout_replace:
				frag = new ThirdFragment();
				manger.replace(R.id.loot_layout,frag);
				manger.commit();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
