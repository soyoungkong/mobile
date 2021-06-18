package kr.young.imageviewex;

import android.app.Activity;
import android.content.Intent;
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
        Intent intent = null;
        switch(id){
        case R.id.option_menu1:
        	intent = new Intent(this,ImageView1Activity.class);
        	this.startActivity(intent);
        	break;
        case R.id.option_menu2:
        	intent = new Intent(this,ImageView2Activity.class);
        	this.startActivity(intent);
        	break;
        case R.id.option_menu3:
        	intent = new Intent(this,ImageView3Activity.class);
        	this.startActivity(intent);
        	break;
        }
        return super.onOptionsItemSelected(item);
    }
}
