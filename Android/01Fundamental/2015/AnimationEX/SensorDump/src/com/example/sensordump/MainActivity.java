package com.example.sensordump;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	SensorManager mSm;
	TextView mTxtLight, mTxtProxi, mTxtPress, mTxtAccel, mTxtMagnetic, mTxtOrient;
	int mLightCount, mProxiCount, mPressCount;
	int mAccelCount, mMagneticCount, mOrientCount;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mSm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        
        mTxtLight = (TextView)findViewById(R.id.light);
        mTxtProxi = (TextView)findViewById(R.id.proxi);
        mTxtPress = (TextView)findViewById(R.id.press);
        mTxtOrient = (TextView)findViewById(R.id.orient);
        mTxtAccel = (TextView)findViewById(R.id.accel);
        mTxtMagnetic = (TextView)findViewById(R.id.magnetic);
    }
    
    @Override
    protected void onResume() {
    	
    	super.onResume();
    	
    	int delay = SensorManager.SENSOR_DELAY_UI;
    	
    	mSm.registerListener(mSensorListener, 
    			mSm.getDefaultSensor(Sensor.TYPE_LIGHT), delay);
    	mSm.registerListener(mSensorListener, 
    			mSm.getDefaultSensor(Sensor.TYPE_PROXIMITY), delay);
    	mSm.registerListener(mSensorListener, 
    			mSm.getDefaultSensor(Sensor.TYPE_PRESSURE), delay);
    	mSm.registerListener(mSensorListener, 
    			mSm.getDefaultSensor(Sensor.TYPE_ORIENTATION), delay);
    	mSm.registerListener(mSensorListener, 
    			mSm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), delay);
    	mSm.registerListener(mSensorListener, 
    			mSm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), delay);
    	mSm.registerListener(mSensorListener, 
    			mSm.getDefaultSensor(Sensor.TYPE_TEMPERATURE), delay);
    	mSm.registerListener(mSensorListener, 
    			mSm.getDefaultSensor(Sensor.TYPE_GYROSCOPE), delay);
    }
    
    @Override
    protected void onPause() {
    	
    	super.onPause();
    	
    	mSm.unregisterListener(mSensorListener);
    }
    
    SensorEventListener mSensorListener = new SensorEventListener() {
		
    	@Override
    	public void onAccuracyChanged(Sensor sensor, int accuracy) {
    		
    	}
    	
		@Override
		public void onSensorChanged(SensorEvent event) {
			
			if(event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
				
			}
			
			float[] v = event.values;
			
			switch(event.sensor.getType()) {
			case Sensor.TYPE_LIGHT: 
				mTxtLight.setText("조도: "+ ++mLightCount+ "회: "+v[0]);
				break;
			case Sensor.TYPE_PROXIMITY: 
				mTxtProxi.setText("근접: "+ ++mProxiCount+ "회: "+v[0]);
				break;
			case Sensor.TYPE_PRESSURE: 
				mTxtPress.setText("압력: "+ ++mPressCount+ "회: "+v[0]);
				break;
			case Sensor.TYPE_ORIENTATION: 
				mTxtOrient.setText("방향: "+ ++mOrientCount+ "회: \n" +
						"azimuth: "+v[0]+"\npitch: "+v[1]+"\nroll: "+v[2]);	
				break;
			case Sensor.TYPE_ACCELEROMETER: 
				mTxtAccel.setText("가속: "+ ++mAccelCount+ "회: \nX: " +
				v[0]+"\nY: "+v[1]+"\nZ: "+v[2]);	
				break;
			case Sensor.TYPE_MAGNETIC_FIELD: 
				mTxtMagnetic.setText("자기: "+ ++mMagneticCount+ "회: \nX:" +
				v[0]+"\nY: "+v[1]+"\nZ: "+v[2]);		
				break;
			}
		}
		
	};
    
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
