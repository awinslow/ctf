package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import com.google.android.c2dm.C2DMessaging;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class main extends Activity {
    /** Called when the activity is first created. */
	public static final String PREFS_NAME = "MyPrefsFile";  
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);  
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String registrationId;
        MediaPlayer mp = MediaPlayer.create(this, R.raw.open);
        mp.start();
        
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();     
        
    	if(!settings.getString("token", "~~~~~~~~~~~~~~~~~~~~").equals("~~~~~~~~~~~~~~~~~~~~")){
    		auth a = new auth();
    		a.setToken(settings.getString("token", "~~~~~~~~~~~~~~~~~~~~"));
    		info.theAuth=a;
    	}
        
        
        Button l = (Button)findViewById(R.id.logout); //id is button 2
        l.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.);
				if(info.theAuth!=null){
					if(info.theAuth.logout()){
						editor.remove("token");
						editor.commit();
						info.theAuth=null;  
		                String value = "You are now logged out";
		                Toast.makeText(getApplicationContext(), value,
	                        Toast.LENGTH_SHORT).show();
						Intent i = new Intent();
						i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.main");
						startActivity(i);
						
						finish();
					}else{
		                String value = "Error logging out";
		                Toast.makeText(getApplicationContext(), value,
	                        Toast.LENGTH_SHORT).show();
					}
				}else{
	                String value = "You are not logged in";
	                Toast.makeText(getApplicationContext(), value,
                        Toast.LENGTH_SHORT).show();					
				}
			}
		});
        
        
        
        Button signin = (Button)findViewById(R.id.MainSignin); //id is button 1
        signin.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
				
				//finish();
			}
		});
        
        Button createchar = (Button)findViewById(R.id.MainCreate); //id is button 2
        createchar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.newUser");
				startActivity(i);
				
				finish();
			}
		});
        
        Button exitb = (Button)findViewById(R.id.MainExit); //id is button 3
        exitb.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.);
				finish();
			}
		});
        
        Button battle = (Button)findViewById(R.id.battle); //id is button 1
        //if(info.theAuth==null) battle.setEnabled(false);
        battle.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
				startActivity(i);
				
				finish();
			}
		});
        
        registrationId = C2DMessaging.getRegistrationId(this);
        if(registrationId != null && !"".equals(registrationId)){
            Log.i("GenericNotifier", "Already registered. registrationId is " + registrationId);
            //message.sendMessage(registrationId, "This is not a test of the emergency broadcast system, this is the real thing");
			Context context = getApplicationContext();
			CharSequence text = "Already Registered";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			//toast.show();
        }else{
            Log.i("GenericNotifier", "No existing registrationId. Registering..");
            C2DMessaging.register(this, "amwinslo@umich.edu");
    		Context context2 = getApplicationContext();
    		CharSequence text = "Registered With Google C2DM";
    		int duration = Toast.LENGTH_SHORT;

    		Toast toast = Toast.makeText(context2, text, duration);
    		//toast.show();
        }

     // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
            	//info.updateLocation(location.getLatitude(), location.getLongitude());
              // Called when a new location is found by the network location provider.
              //makeUseOfNewLocation(location);
   			//Toast toast = Toast.makeText(getApplicationContext(), location.toString(), Toast.LENGTH_SHORT);
   			Log.i("location",location.toString());
    			//toast.show();
			if(info.theAuth != null){
				HttpURLConnection h;
				try {
					URL u = new URL("http://ctf.awins.info/checkin.php?&token="+info.theAuth.getToken()+"&lat="+location.getLatitude()+"&lon="+location.getLongitude());
					Log.i("UPDATING LOCATION", "http://ctf.awins.info/checkin.php?&token="+info.theAuth.getToken()+"&lat="+location.getLatitude()+"&lon"+location.getLongitude());
					h = (HttpURLConnection) u.openConnection();
					h.setRequestMethod("GET");
					h.connect();
					if(h.getResponseCode()==200){
						//Call update location here
						UpdateLocation(h);
						
						Context context = getApplicationContext();
						CharSequence text = "You are now signed in";
						int duration = Toast.LENGTH_SHORT;
	
						Toast toast2 = Toast.makeText(context, text, duration);
						Log.i("LOCATION", "update good");

					}
					else{
						Context context = getApplicationContext();
						CharSequence text = "Error registering device";
						int duration = Toast.LENGTH_SHORT;
	
						Toast toast2 = Toast.makeText(context, text, duration);
						//toast2.show();   
						Log.i("LOCATION", "update failed");
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else Log.i("Location", "The auth is null on location update attempt");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
          };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);   
    }
    
    public void UpdateLocation(HttpURLConnection h)
    {
    	try{
	    	Log.i("Location", "Connection made response code 200");
			BufferedReader in = new BufferedReader(
	                new InputStreamReader(
	                h.getInputStream()));
			String inputLine = "Initial Input line for Location update";
			String inText = "";
			Vector<LatitudeLongitude> tempLocations = new Vector<LatitudeLongitude>();
			boolean locationsExist = false;
			while (inputLine != null)
			{
				////////////////////////PID
					inputLine = in.readLine();
					if(inputLine == null)
					{
						Log.i("Location", "Input Line null");
						break;
					}
				    inText=inputLine;
				    if(inText.indexOf("pid:") == -1)
				    {
				    	Log.i("Location", "Pid not read correctly: " + inText);
				    	break;
				    }
			    	int pid = (Integer.parseInt(inText.substring(4).trim()));
			    	Log.i("Location", "PID: " + Integer.toString(pid));
			    //////////////////////////
			    ////////////Lat
			    	inputLine = in.readLine();
					if(inputLine == null)
					{
						Log.i("Location", "Input Line null");
						break;
					}
			    	inText = inputLine;
			    	if(inText.indexOf("lat") == -1)
			    	{
			    		Log.i("Location", "lat not read correctly on pid: " + pid);
			    		break;
			    	}
			    	float lat = (Float.parseFloat(inText.substring(4).trim()));
			    	Log.i("Location", "Lat: " + lat);
			    	
			    /////////////////////////////
			    //////////////Lon
			    	inputLine = in.readLine();
					if(inputLine == null)
					{
						Log.i("Location", "Input Line null");
						break;
					}
			    	inText = inputLine;
			    	if(inText.indexOf("lon") == -1)
			    	{
			    		Log.i("Location", "lon not read correctly on pid: " + pid);
			    		break;
			    	}
			    	float lon = (Float.parseFloat(inText.substring(4).trim()));
			    	Log.i("Location", "Lon: " + lon);
			    	
			    	LatitudeLongitude thisLoc = new LatitudeLongitude(pid, lat, lon);
			    	tempLocations.add(thisLoc);
			    	locationsExist = true;
			}
			if(locationsExist)
			{
				info.EnemyLocations = tempLocations;
				for(int i = 0; i < tempLocations.size(); i++)
				{
					Log.i("Location", "Added Location Pid: " + tempLocations.elementAt(i).pid + " Lat: " + tempLocations.elementAt(i).lat + " Lon: " + tempLocations.elementAt(i).lon);
				}
			}
			else
			{
				Log.i("Location", "No Locations found");
			}
			in.close();
    	}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Location", "Malformed URL Exception: " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Location", "IOException: " + e);
		}
    }
}