package com.geekerchina.feeltheworld;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class SensorsMonitorActivity extends Activity implements SensorEventListener {
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
		mAccelerometer = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sMgr.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  public final void onAccuracyChanged(Sensor sensor, int accuracy) {
	    // Do something here if sensor accuracy changes.
	  }

	  @Override
	  public final void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
			TextView tvX= (TextView)findViewById(R.id.xaccel);
			TextView tvY= (TextView)findViewById(R.id.yaccel);
			TextView tvZ= (TextView)findViewById(R.id.zaccel);
//			ImageView iv = (ImageView)findViewById(R.id.image);
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			tvX.setText("0.0");
			tvY.setText("0.0");
			tvZ.setText("0.0");
			mInitialized = true;
			} else {
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
	    // Do something with this sensor data.
	  }

	  @Override
	  protected void onResume() {
	    // Register a listener for the sensor.
	    super.onResume();
	    
	    sMgr.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	   }

	  @Override
	  protected void onPause() {
	    // Be sure to unregister the sensor when the activity pauses.
	    super.onPause();
	    sMgr.unregisterListener(this);
	  }
	}