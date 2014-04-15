package com.geekerchina.feeltheworld;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorsMonitorActivity extends Activity{
	  private SensorManager sMgr;
	  private SensorEventListener sEvl;
	  private Sensor mPressure;
	  private Sensor mAccelerometer;
	  private boolean mInitialized; 
	  private final float NOISE = (float) 2.0;
	  private float mLastX, mLastY, mLastZ;

	  @Override
	  public final void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sensorsmonitor);

	    // Get an instance of the sensor service, and use that to get an instance of
	    // a particular sensor.
	    mInitialized = false;
	    sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);
	    sEvl = new SensorEventListener() {
	        @Override
	        public void onAccuracyChanged(Sensor arg0, int arg1) {
	        }

	        @Override
	        public void onSensorChanged(SensorEvent event) {
	            Sensor sensor = event.sensor;
	            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) 
	            {
	                //TODO: get values
	            	TextView tvX= (TextView)findViewById(R.id.xaccel);
	    			TextView tvY= (TextView)findViewById(R.id.yaccel);
	    			TextView tvZ= (TextView)findViewById(R.id.zaccel);
//	    			ImageView iv = (ImageView)findViewById(R.id.image);
	    			float x = event.values[0];
	    			float y = event.values[1];
	    			float z = event.values[2];
	    			if (!mInitialized) 
	    			{
		    			mLastX = x;
		    			mLastY = y;
		    			mLastZ = z;
		    			tvX.setText("0.0");
		    			tvY.setText("0.0");
		    			tvZ.setText("0.0");
		    			mInitialized = true;
	    			} 
	    			else 
	    			{
		    			float deltaX = Math.abs(mLastX - x);
		    			float deltaY = Math.abs(mLastY - y);
		    			float deltaZ = Math.abs(mLastZ - z);
		    			if (deltaX < NOISE) deltaX = (float)0.0;
		    			if (deltaY < NOISE) deltaY = (float)0.0;
		    			if (deltaZ < NOISE) deltaZ = (float)0.0;
		    			mLastX = x;
		    			mLastY = y;
		    			mLastZ = z;
		    			tvX.setText(Float.toString(deltaX));
		    			tvY.setText(Float.toString(deltaY));
		    			tvZ.setText(Float.toString(deltaZ));
	    			}
	    		}
	    		else if (sensor.getType() == Sensor.TYPE_TEMPERATURE) 
	    		{
	                //TODO: get values
	            	TextView t = (TextView)findViewById(R.id.temperature_show);
	            	t.setText("00.0");
	            	t.setText(Float.toString(event.values[0]));
	            }
	    		else if (sensor.getType() == Sensor.TYPE_GRAVITY) 
	    		{
	                //TODO: get values
	            	TextView gravity = (TextView)findViewById(R.id.gravity_show);
	            	gravity.setText("00.0");
	            	gravity.setText(Float.toString(event.values[0]));
	            }
	        }
	    };
	    // mAccelerometer = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		// sMgr.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	    sMgr.registerListener(sEvl, sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	    sMgr.registerListener(sEvl, sMgr.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_GAME);

	    sMgr.registerListener(sEvl, sMgr.getDefaultSensor(Sensor.TYPE_TEMPERATURE), SensorManager.SENSOR_DELAY_GAME);
	  }
	}