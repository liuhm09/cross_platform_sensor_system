package com.geekerchina.feeltheworld;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import java.io.PrintStream;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;

public class sensorgetter{
	final static String FILE_NAME = "temperature.txt";
	private static SensorManager sMgr;
	  private static SensorEventListener sEvl;
	  private Sensor mPressure;
	  private Sensor mAccelerometer;
	  private static boolean mInitialized; 
	  private final static float NOISE = (float) 2.0;
	  private static float mLastX;
	private static float mLastY;
	private static float mLastZ;
	public static byte[] gettemperature(final Context context) throws IOException {
		
		mInitialized = false;
	    sMgr = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
	    final StringBuilder data = new StringBuilder();
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
	            	//TextView tvX= (TextView)findViewById(R.id.xaccel);
	    			//TextView tvY= (TextView)findViewById(R.id.yaccel);
	    			//TextView tvZ= (TextView)findViewById(R.id.zaccel);
//	    			ImageView iv = (ImageView)findViewById(R.id.image);
	    			float x = event.values[0];
	    			float y = event.values[1];
	    			float z = event.values[2];
	    			if (!mInitialized) 
	    			{
		    			mLastX = x;
		    			mLastY = y;
		    			mLastZ = z;
		    			//tvX.setText("0.0");
		    			//tvY.setText("0.0");
		    			//tvZ.setText("0.0");
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
		    			data.append("x: "+Float.toString(deltaX)+"\n");
		    			data.append("y: "+Float.toString(deltaY)+"\n");
		    			data.append("z: "+Float.toString(deltaZ)+"\n");

		    			//tvX.setText(Float.toString(deltaX));
		    			//tvY.setText(Float.toString(deltaY));
		    			//tvZ.setText(Float.toString(deltaZ));
	    			}
	    		}
	    		else if (sensor.getType() == Sensor.TYPE_TEMPERATURE) 
	    		{
	                //TODO: get values
	            	//TextView t = (TextView)findViewById(R.id.temperature_show);
	            	//t.setText("00.0");
	            	data.append("temperature: "+Float.toString(event.values[0])+"\n");

	            	//t.setText(Float.toString(event.values[0]));
	            }
	    		else if (sensor.getType() == Sensor.TYPE_GRAVITY) 
	    		{
	                //TODO: get values
	            	//TextView gravity = (TextView)findViewById(R.id.gravity_show);
	            	//gravity.setText("00.0");
	            	//gravity.setText(Float.toString(event.values[0]));
	            }
	        }

	    };
	    // mAccelerometer = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		// sMgr.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	    sMgr.registerListener(sEvl, sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	    sMgr.registerListener(sEvl, sMgr.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_GAME);

	    sMgr.registerListener(sEvl, sMgr.getDefaultSensor(Sensor.TYPE_TEMPERATURE), SensorManager.SENSOR_DELAY_GAME);
        
	    
	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                Thread.sleep(5000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
	        }
	    }).start();
	    
	    if (data==null){
			
        	return data.append("no sensors information!sorry!").toString().getBytes();
        }
		
        return data.append("\n").toString().getBytes("UTF-8");
        
	}
	private static void fwrite(Context context,String content)
	{
		try
		{
			FileOutputStream fos =  context.getApplicationContext().openFileOutput(FILE_NAME, Context.MODE_APPEND);
			PrintStream ps = new PrintStream(fos);
			ps.println(content);
			ps.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}




