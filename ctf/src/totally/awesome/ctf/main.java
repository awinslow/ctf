package totally.awesome.ctf;

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
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class main extends Activity {
    /** Called when the activity is first created. */
	public static final String PREFS_NAME = "MyPrefsFile";  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String registrationId;
        MyIntentReceiver intentReceiver = new MyIntentReceiver();
        IntentFilter intentFilter = new IntentFilter("totally.awesome.ctf.HI");
        intentFilter.setPriority(1);
        registerReceiver(intentReceiver, intentFilter); 
        
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();       
        Button l = (Button)findViewById(R.id.logout); //id is button 2
        l.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.);
				if(info.theAuth!=null){
					if(info.theAuth.logout()){
						info.theAuth=null;  
		                String value = "You are now logged out";
		                Toast.makeText(getApplicationContext(), value,
	                        Toast.LENGTH_SHORT).show();
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
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
			}
		});
        
        Button createchar = (Button)findViewById(R.id.MainCreate); //id is button 2
        createchar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.newUser");
				startActivity(i);
			}
		});
        
        Button exitb = (Button)findViewById(R.id.MainExit); //id is button 3
        exitb.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.);
				finish();
			}
		});
        
        registrationId = C2DMessaging.getRegistrationId(this);
        if(registrationId != null && !"".equals(registrationId)){
            Log.i("GenericNotifier", "Already registered. registrationId is " + registrationId);
			Context context = getApplicationContext();
			CharSequence text = "Already Registered";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
        }else{
            Log.i("GenericNotifier", "No existing registrationId. Registering..");
            C2DMessaging.register(this, "amwinslo@umich.edu");
    		Context context2 = getApplicationContext();
    		CharSequence text = "Registered With Google C2DM";
    		int duration = Toast.LENGTH_SHORT;

    		Toast toast = Toast.makeText(context2, text, duration);
    		toast.show();
        }
    }
}