package com.geekerchina.feeltheworld;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.Certificate;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.geekerchina.feeltheworld.MainActivity;
import com.geekerchina.feeltheworld.R;

import android.net.wifi.WifiConfiguration.Protocol;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.telephony.SmsMessage;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;





public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager sMgr;
	private Sensor mAccelerometer;
	private boolean mInitialized; 
	private final float NOISE = (float) 2.0;
	private float mLastX, mLastY, mLastZ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mInitialized = false;
		sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);
		mAccelerometer = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sMgr.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		
	}


	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
	      getMenuInflater().inflate(R.menu.activity_main, menu);
	      return true;
	   }

	
	   protected void onResume() {
		   super.onResume();
		   sMgr.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		   }
		   protected void onPause() {
		   super.onPause();
		   sMgr.unregisterListener(this);
		   }

		   @Override
			  public final void onAccuracyChanged(Sensor sensor, int accuracy) {
			    // Do something here if sensor accuracy changes.
			  }
		   
	public static String convertStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),1024);
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
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

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		TextView tvX= (TextView)findViewById(R.id.textView2);
		TextView tvY= (TextView)findViewById(R.id.textView3);
		TextView tvZ= (TextView)findViewById(R.id.textView4);
//		ImageView iv = (ImageView)findViewById(R.id.image);
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
		//iv.setVisibility(View.VISIBLE);
		//if (deltaX > deltaY) {
		//iv.setImageResource(R.drawable.horizontal);
		//} else if (deltaY > deltaX) {
		//iv.setImageResource(R.drawable.vertical);
		//} else {
		//iv.setVisibility(View.INVISIBLE);
		//}
		}
		
	}  
	}
	
