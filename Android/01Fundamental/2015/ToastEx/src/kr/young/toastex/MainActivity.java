package kr.young.toastex;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void viewToast(View v){
    	int id = v.getId();
    	
    	switch(id){
        case R.id.btn_normal:
        	Toast.makeText(this, "이름이 0", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.btn_basic:
        	Toast t = Toast.makeText(this, "이름이 1", Toast.LENGTH_SHORT);
        	
        	t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        	t.show();
        	break;
        case R.id.btn_user_ui:
        	LayoutInflater inf = this.getLayoutInflater();
        	ViewGroup vg = (ViewGroup) findViewById(R.id.loot_layout);
        	View view = inf.inflate(R.layout.toast_layout, vg);
        	// getMenuInflater().inflate(R.menu.main, menu); 이거 한줄이랑 같은 뜻임
        	
        	Toast toast = new Toast(this);
        	toast.setView(view);
        	toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        	toast.setDuration(Toast.LENGTH_LONG);
        	toast.show();
        	break;
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
        int id = item.getItemId();
        
        switch(id){
	        case R.id.action_settings:
	        	 return true;
	        case R.id.goMain:
	        	setContentView(R.layout.activity_main);
	        	break;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
