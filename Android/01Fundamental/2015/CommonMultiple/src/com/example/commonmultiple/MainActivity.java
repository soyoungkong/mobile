package com.example.commonmultiple;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void com(View v){
    	EditText temp1 =  (EditText) findViewById(R.id.num1);
    	EditText temp2 = (EditText) findViewById(R.id.num2);
    	
    	int num1 = Integer.parseInt(temp1.getText().toString());
    	int num2 = Integer.parseInt(temp2.getText().toString());
    	
    	String text = "";
    	if(num1> num2){
    		int temp = num1;
    		num1 = num2;
    		num2 = temp;
    	}
    	
    	for(int i = num1; i < num2; i++){
    		if(i%3 == 0 && i%4 == 0){
    			text += i + "\n";
    		}
    	}
    	TextView temp3 = (TextView) findViewById(R.id.val);
    	temp3.setText("");
    	temp3.append( "" + text);
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
