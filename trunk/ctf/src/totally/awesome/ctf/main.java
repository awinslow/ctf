package totally.awesome.ctf;

import android.app.Activity;
import android.content.Intent;
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
    }
}