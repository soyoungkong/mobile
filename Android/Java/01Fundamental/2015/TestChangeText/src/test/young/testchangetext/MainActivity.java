package test.young.testchangetext;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	int temp_num = 0;
	String[] temp_data = {
			"¹Ù²îÁÒ1","¹Ù²îÁÒ2","¹Ù²îÁÒ3","¹Ù²îÁÒ4"
	};
	Button btn;
	TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
         tv = (TextView) findViewById(R.id.txt_view);
        
        tv.setText(temp_data[temp_num]);
        btn = (Button) findViewById(R.id.btn_next);
    }


    public void next(View v){
    	temp_num++;
    	if(temp_num >= temp_data.length){
    		temp_num = 0;
    		tv.setVisibility(tv.INVISIBLE);
    		btn.setVisibility(btn.INVISIBLE);
    	}
    	tv.setText(temp_data[temp_num]);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
