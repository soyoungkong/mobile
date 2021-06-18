package com.example.sqllight;

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
        FragmentTransaction trans =  getFragmentManager().beginTransaction();
        
        Fragment frag = null;
       switch(id){
       case R.id.menu_insert:
    	   frag = new InputFrag();
    	   trans.replace(R.id.root_layout, frag);
    	   trans.commit();
    	   break;
       case R.id.menu_list:
    	   frag = new ListFrag();
    	   trans.replace(R.id.root_layout, frag);
    	   trans.commit();
    	   break;
       case R.id.menu_modify:
    	   break;
       case R.id.menu_delete:
    	   break;
       }
        return super.onOptionsItemSelected(item);
    }
}
