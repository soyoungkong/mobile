package com.example.sensormanager;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String result = "";
        
        SensorManager sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> arSensor = sm.getSensorList(Sensor.TYPE_ALL);
        result = "size: "+arSensor.size()+"\n\n";
        for(Sensor s : arSensor){
        	result += ("센서의 이름: "+s.getName()+"\n센서의 타입: "+s.getType()+"\n제조사: "+s.getVendor()+"\n버전: "+s.getVersion()+
        			"\n전력 사용량: "+s.getPower()+"암페어(mA)"+"\n해상도: "+s.getResolution()+"\n최대 측정 범위: "+s.getMaximumRange()+"\n");	
        }
        TextView txtResult = (TextView)findViewById(R.id.result);
        txtResult.setText(result);
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
