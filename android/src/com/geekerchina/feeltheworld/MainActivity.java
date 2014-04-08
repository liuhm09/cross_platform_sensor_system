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





public class MainActivity extends Activity {

	private SensorManager sMgr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Button button1=(Button)findViewById(R.id.button1);
		//button1.setOnClickListener(new ssltest());
		sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);
	      List<Sensor> list = sMgr.getSensorList(Sensor.TYPE_ALL);

	      StringBuilder data = new StringBuilder();
	      for(Sensor sensor: list){
	         data.append(sensor.getName() + "\n");
	         data.append(sensor.getVendor() + "\n");
	         data.append(sensor.getVersion() + "\n");

	   }
	      TextView sensorsData = (TextView)findViewById(R.id.textView1);
	      sensorsData.setMovementMethod(new ScrollingMovementMethod());

	   sensorsData.setText(data);
	}


	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
	      getMenuInflater().inflate(R.menu.activity_main, menu);
	      return true;
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
	class ssltest implements View.OnClickListener
	{
	@Override
	public void onClick(View arg0)
	{
		TextView textView1 = (TextView)findViewById(R.id.textView1);
		textView1.setText("You become a TuHao!");
//		SensorActivity SA;


		   
		
		
		Log.d("sensor", "we get it");
		/*
		HttpResponse response = null;
		try {        
		        HttpClient client = new DefaultHttpClient();
		        HttpGet request = new HttpGet();
		        request.setURI(new URI("https://10.0.2.2/"));
		        response = client.execute(request);
		    } catch (URISyntaxException e) {
		        e.printStackTrace();
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }   
		    
		String tmp = null;
		try {
			tmp=convertStreamToString(response.getEntity().getContent());
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Log.d("sensor", tmp);
		try {
			JSONObject json = new JSONObject(tmp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
		
		
		
		
		
		
		
		// Load CAs from an InputStream
		// (could be from a resource or ByteArrayInputStream or ...)
		CertificateFactory cf = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// From https://www.washington.edu/itconnect/security/ca/load-der.crt
		InputStream caInput = null;
		InputStream ksInput = null;
		caInput=getResources().openRawResource(R.raw.server);
		ksInput=getResources().openRawResource(R.raw.keystore);

		/*
		try {
			caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		java.security.cert.Certificate ca = null;
		try {
		    try {
				ca = cf.generateCertificate(caInput);
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
		} finally {
		    try {
				caInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.d("sensors",ca.toString());
		// Create a KeyStore containing our trusted CAs
		String keyStoreType = KeyStore.getDefaultType();
		KeyStore keyStore = null;
		try {
			keyStore = KeyStore.getInstance(keyStoreType);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			keyStore.load(null, null);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			keyStore.setCertificateEntry("ca", ca);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//load our keystore**********
		String defaultType = KeyStore.getDefaultType();
        KeyStore trustedStore = null;
		try {
			trustedStore = KeyStore.getInstance(defaultType);
		} catch (KeyStoreException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
        
        try {
			trustedStore.load(ksInput, "mysecret".toCharArray());
		} catch (NoSuchAlgorithmException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (CertificateException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
        try {
			ksInput.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//*******
		
		
		// Create a TrustManager that trusts the CAs in our KeyStore
		String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory tmf = null;
		try {
			tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tmf.init(keyStore);///////change keyStore to trustedStore
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create an SSLContext that uses our TrustManager
		SSLContext context = null;
		try {
			context = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			context.init(null, tmf.getTrustManagers(), null);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		// Tell the URLConnection to use a SocketFactory from our SSLContext
		URL url = null;
		try {
			//url = new URL("https://certs.cac.washington.edu/CAtest/");
			url = new URL("https://wx.qq.com/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpsURLConnection urlConnection = null;
		try {
			urlConnection = (HttpsURLConnection)url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		urlConnection.setSSLSocketFactory(context.getSocketFactory());
		
		
		
		InputStream in = null;
		try {
			in = urlConnection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		copyInputStreamToOutputStream(in, System.out);
		String tmp = null;
		try {
			tmp=convertStreamToString(in);
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Log.d("sensor", tmp);
		*/
		
		
		
		
		
		
	
	}
	        
    }  
	}
	
