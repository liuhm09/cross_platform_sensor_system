package com.geekerchina.feeltheworld;

import com.geekerchina.feeltheworld.MainActivity;
import com.geekerchina.feeltheworld.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;





public class MainActivity extends Activity {
	
	static {
	    System.loadLibrary("ndkfoo");
	}
	private native String invokeNativeFunction();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	String hello = invokeNativeFunction();
	/*ImageButton mBtn;
	mBtn = (ImageButton) findViewById(R.id.button1); //btn为layout中的ButtonID
	mBtn.setBackgroundResource(R.drawable.normal);  
	/*
	mBtn.setOnTouchListener(new OnTouchListener(){
	    public boolean onTouch(View arg0,MotionEvent arg1) {
	        if(arg1.getAction() == MotionEvent.ACTION_DOWN) {
	            arg0.setBackgroundResource(R.drawable.pressed); //按下的图片对应pressed
	        }
	        else if (arg1.getAction() == MotionEvent.ACTION_UP) {
	            arg0.setBackgroundResource(R.drawable.normal); //常态下的图片对应normal
	        }
	        //else if()这里还可以继续实现MotionEvent.ACTION_MOVE和MotionEvent.ACTION_CANCEL等实现更多的特效
	         
	        return false;
	    }
	});
	/*
	Button mBtn2;
	mBtn2 = (Button) findViewById(R.id.button2); //btn为layout中的ButtonID
	mBtn2.setOnTouchListener(new OnTouchListener(){
	    public boolean onTouch(View arg0,MotionEvent arg1) {
	        if(arg1.getAction() == MotionEvent.ACTION_DOWN) {
	            arg0.setBackgroundResource(R.drawable.pressed); //按下的图片对应pressed
	        }
	        else if (arg1.getAction() == MotionEvent.ACTION_UP) {
	            arg0.setBackgroundResource(R.drawable.normal); //常态下的图片对应normal
	        }
	        //else if()这里还可以继续实现MotionEvent.ACTION_MOVE和MotionEvent.ACTION_CANCEL等实现更多的特效
	         
	        return false;
	    }
	});
	
	*/
	
	
    
    //new AlertDialog.Builder(this).setMessage(hello).show();
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
	
