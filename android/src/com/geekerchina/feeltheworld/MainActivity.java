package com.geekerchina.feeltheworld;

import com.geekerchina.feeltheworld.MainActivity;
import com.geekerchina.feeltheworld.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;





public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.activity_main, menu);
	    return true;
	}
	public void startShowsensors(View view)
	{
		Intent intent = new Intent(this, ShowsensorsActivity.class);
		startActivity(intent);
	}
	public void startSensorsMonitor(View view)
	{
		Intent intent = new Intent(this, SensorsMonitorActivity.class);
		startActivity(intent);
	}	
}
	
