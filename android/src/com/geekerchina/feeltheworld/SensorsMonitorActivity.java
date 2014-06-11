package com.geekerchina.feeltheworld;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SensorsMonitorActivity extends Activity{
	  private SensorManager sMgr;
	  private SensorEventListener sEvl;
	  private Sensor mPressure;
	  private Sensor mAccelerometer;
	  private boolean mInitialized; 
	  private final float NOISE = (float) 2.0;
	  private float mLastX, mLastY, mLastZ;
	  
	  private LocationManager locationManager;
	   
	  
	  //private EditText show;
	  
	  @Override
	  public final void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sensorsmonitor);
	    TextView lng = (TextView)findViewById(R.id.LNG_show);
		TextView lat = (TextView)findViewById(R.id.LAT_show);
	    
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    if (location != null) {
			StringBuffer s_lng = new StringBuffer();
			
			s_lng.append(location.getLongitude());
			lng.setText(s_lng.toString());
			
			StringBuffer s_lat = new StringBuffer();
			s_lat.append(location.getLatitude());
			lat.setText(s_lat.toString());
		} else {
			// 如果传入的Location对象为空则清空EditText
			lng.setText("");
			lat.setText("");
		}
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000, 8, new LocationListener() {

					@Override
					public void onLocationChanged(Location location) {
						// 当GPS定位信息发生改变时，更新位置
						updateGPS(location);
					}

					@Override
					public void onProviderDisabled(String provider) {
						updateGPS(null);
					}

					@Override
					public void onProviderEnabled(String provider) {
						// 当GPS LocationProvider可用时，更新位置
						updateGPS(locationManager
								.getLastKnownLocation(provider));

					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
					}
				});
				
	    new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				TextView _tvX= (TextView)findViewById(R.id.xaccel);
				TextView _tvY= (TextView)findViewById(R.id.yaccel);
				TextView _tvZ= (TextView)findViewById(R.id.zaccel);
				TextView _temper = (TextView)findViewById(R.id.temperature_show);
				TextView _lng = (TextView)findViewById(R.id.LNG_show);
				TextView _lat = (TextView)findViewById(R.id.LAT_show);
				TextView _gravity = (TextView)findViewById(R.id.gravity_show);
			    final DefaultHttpClient client = new DefaultHttpClient();
				//http post
			    Date date = new Date();
			    List<NameValuePair> list = new ArrayList<NameValuePair>();
			    final HttpGet post = new HttpGet("http://202.112.51.64:8000?");
			    UrlEncodedFormEntity entity;
				StringBuffer accel_sss = new StringBuffer();
				StringBuffer gps_sss = new StringBuffer();
				StringBuffer temper_sss = new StringBuffer();
				StringBuffer gravity_sss = new StringBuffer();


				while(true)
				{
					accel_sss.setLength(0);
					temper_sss.setLength(0);
					gravity_sss.setLength(0);
					gps_sss.setLength(0);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				    
					date = new Date();
					/*
					list.removeAll(list);
					NameValuePair pair1 = new BasicNameValuePair("platform", "android");//name.getText().toString());
					NameValuePair pair2 = new BasicNameValuePair("time",String.valueOf(date.getTime()));// age.getText().toString());
					NameValuePair pair3 = new BasicNameValuePair("type","accel");// age.getText().toString());
					NameValuePair pair4 = new BasicNameValuePair("data",_tvX.getText().toString());// age.getText().toString());
					list.add(pair1);
					list.add(pair2);
					list.add(pair3);
					list.add(pair4);
					*/
					accel_sss.append("http://202.112.51.64:8000?platform=android&");
					accel_sss.append("time="+String.valueOf(date.getTime()));
					accel_sss.append("&type=accel");
					accel_sss.append("&id=1");
					accel_sss.append("&data="+_tvX.getText().toString());
					try {
						post.setURI(new URI(accel_sss.toString()));
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						HttpResponse response = client.execute(post);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					//send the temperature
					date = new Date();
					temper_sss.append("http://202.112.51.64:8000?platform=android&");
					temper_sss.append("time="+String.valueOf(date.getTime()));
					temper_sss.append("&type=temper");
					temper_sss.append("&id=1");
					temper_sss.append("&data="+_temper.getText().toString());
					try {
						post.setURI(new URI(temper_sss.toString()));
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						HttpResponse response = client.execute(post);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					//send the gravity
					date = new Date();
					gravity_sss.append("http://202.112.51.64:8000?platform=android&");
					gravity_sss.append("time="+String.valueOf(date.getTime()));
					gravity_sss.append("&type=gravity");
					gravity_sss.append("&id=1");
					gravity_sss.append("&data="+_gravity.getText().toString());
					try {
						post.setURI(new URI(gravity_sss.toString()));
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						HttpResponse response = client.execute(post);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					//send the GPS
					
					date = new Date();
					gps_sss.append("http://202.112.51.64:8000?platform=android&");
					gps_sss.append("time="+String.valueOf(date.getTime()));
					gps_sss.append("&type=gps");
					gps_sss.append("&id=1");
					gps_sss.append("&data="+_lng.getText().toString()+","+_lat.getText().toString());
					try {
						post.setURI(new URI(gps_sss.toString()));
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						HttpResponse response = client.execute(post);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					
				}
				
				
				
				
				
				
			}
		}.start();
	    
	    
	    
	    
	    
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
		    			
						
						
						
						/*
						

						/*
						HttpResponse response;
						try {
							response = client.execute(post);
							if(response.getStatusLine().getStatusCode()==200){
								InputStream in = response.getEntity().getContent();//接收服务器的数据，并在Toast上显示
								String str = in.toString();//readString(in);
								Toast.makeText(SensorsMonitorActivity.this, str, Toast.LENGTH_SHORT).show();
								
								
							}
							else Toast.makeText(SensorsMonitorActivity.this, "POST提交失败", Toast.LENGTH_SHORT).show();
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						*/
						
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
	 
	  private void updateGPS(Location location) {
		  TextView lng = (TextView)findViewById(R.id.LNG_show);
		TextView lat = (TextView)findViewById(R.id.LAT_show);
		  if (location != null) {
				StringBuffer s_lng = new StringBuffer();
				
				s_lng.append(location.getLongitude());
				lng.setText(s_lng.toString());
				StringBuffer s_lat = new StringBuffer();
				s_lat.append(location.getLongitude());
				lat.setText(s_lat.toString());
			} else {
				// 如果传入的Location对象为空则清空EditText
				lng.setText("");
				lat.setText("");
			}
	  }
	  
	  
	}