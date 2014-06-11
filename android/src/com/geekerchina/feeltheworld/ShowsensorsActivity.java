package com.geekerchina.feeltheworld;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ShowsensorsActivity extends Activity{
	  private SensorManager sMgr;
	  @Override
	  public final void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_showsensors);

	    sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);
		
	    List<Sensor> list = sMgr.getSensorList(Sensor.TYPE_ALL);
	    StringBuilder data = new StringBuilder();
	    int temp=1;
	    for(Sensor sensor: list){
	    	data.append("Sensor "+temp+"\n");
	    	temp=temp+1;
	        data.append("Name: " + sensor.getName() + "\n");
	        data.append("Type: " + sensor.getType() + "\n");
	        data.append("MaximumRange: " + sensor.getMaximumRange() + "\n");
	        data.append("MinDelay: " + sensor.getMinDelay() + "\n");
	        data.append("Power: " + sensor.getPower() + "\n");
	        data.append("Resolution: " + sensor.getResolution() + "\n");
	        data.append("Vendor: "+sensor.getVendor() + "\n");
	        data.append("Version: "+sensor.getVersion() + "\n");
	        data.append("\n");
	    }
	    TextView sensorsData = (TextView)findViewById(R.id.sensorsinfo);
	    sensorsData.setMovementMethod(new ScrollingMovementMethod());
	    sensorsData.setText(data);
	  }

	}