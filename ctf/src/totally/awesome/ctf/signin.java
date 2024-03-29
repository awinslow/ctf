package totally.awesome.ctf;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import totally.awesome.ctf.Arena.RefreshHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.c2dm.C2DMessaging;

class login extends Thread{
	String u;
	String p;
	
	login(String uu, String pp){
		u=uu;
		p=pp;
	}
	
	public void run(){
		auth a = new auth(u, p);
		info.theAuth=a;
		signin.mRedrawHandler.sendMessage(new Message());
	}
}

public class signin extends Activity {
    /** Called when the activity is first created. */
	public static final String PREFS_NAME = "MyPrefsFile";
	String user;
	String password;
	

	static RefreshHandler mRedrawHandler;
	class RefreshHandler extends Handler {
	    @Override
	    public void handleMessage(Message msg) {

			String registrationId = C2DMessaging.getRegistrationId(getApplicationContext());
			URL u;
			try {
				u = new URL("http://ctf.awins.info/c2dm.php?register=1&rid="+registrationId+"&token="+info.theAuth.getToken());
				
				HttpURLConnection h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				if(h.getResponseCode()==200){
					info.loading.dismiss();
    				Context context = getApplicationContext();
    				CharSequence text = "Incorrect username or password";
    				if(!info.theAuth.getToken().equals("-1")
    						&& !info.theAuth.getToken().subSequence(0, 4).equals("User")){
    					MediaPlayer mp = MediaPlayer.create(context, R.raw.excelent);
    			        mp.start();
    					text = "You are now signed in";
    					//info.theAuth = null;
    					Intent i = new Intent();
    					i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
    					startActivity(i);  
    					
    					finish();
    				}
    					 
    				
    				int duration = Toast.LENGTH_SHORT;

    				Toast toast2 = Toast.makeText(context, text, duration);
    				toast2.show();
				}
				else{
					info.loading.dismiss();
    				Context context = getApplicationContext();
    				CharSequence text = "Error registering device";
    				int duration = Toast.LENGTH_SHORT;

    				Toast toast2 = Toast.makeText(context, text, duration);
    				toast2.show();   					
					
				}
				Log.i("a", new String(Integer.toString(h.getResponseCode())));
	            Log.i("a","opened");
	            //Log.i("a","http://141.212.113.248/c2dm.php?register=1&rid="+registrationId+"&a="+id);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				Log.i("a","error");
				e1.printStackTrace();
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.i("a","error");
				e1.printStackTrace();
			}
		
			if(info.theAuth == null){
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
				startActivity(i);
				
				finish();
			}

	    	
	    }	    
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);  
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mRedrawHandler = new RefreshHandler();
        setContentView(R.layout.signin);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();
        CheckBox c = (CheckBox)findViewById(R.id.CheckBox01);
        if(!settings.getString("user", "~~~~~~~~~~~~~~~~~~~~").equals("~~~~~~~~~~~~~~~~~~~~")){
        	c.setChecked(true);
        }
        Button back = (Button)findViewById(R.id.Signinback);
        back.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				CheckBox c = (CheckBox)findViewById(R.id.CheckBox01);
            	if(!c.isChecked()){
            		editor.remove("user");
            		editor.remove("password");
            		editor.commit();
            	}
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.main");
				startActivity(i);
				
				finish();
			}
		});
        Button signIn = (Button) findViewById(R.id.SigninSignIn);
    	EditText uname = (EditText) findViewById(R.id.EditText01);
    	EditText pword = (EditText) findViewById(R.id.EditText02);
    	if(!settings.getString("user", "~~~~~~~~~~~~~~~~~~~~").equals("~~~~~~~~~~~~~~~~~~~~")){
    		uname.setText(settings.getString("user", "~~~~~~~~~~~~~~~~~~~~"));
    		pword.setText(settings.getString("password", "~~~~~~~~~~~~~~~~~~~~"));
    	}
    	
    	uname.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				CheckBox c = (CheckBox)findViewById(R.id.CheckBox01);
				c.setChecked(false);
			}
			
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});

    	pword.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				CheckBox c = (CheckBox)findViewById(R.id.CheckBox01);
				c.setChecked(false);
			}
			
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	
        signIn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
            	EditText uname = (EditText) findViewById(R.id.EditText01);
            	user=uname.getText().toString();
            	EditText pword = (EditText) findViewById(R.id.EditText02);
            	password=pword.getText().toString();   
        	
            	
            	CheckBox c = (CheckBox)findViewById(R.id.CheckBox01);
            	if(c.isChecked()){
            		editor.putString("user", user);
            		editor.putString("password", password);
            	} else{
            		editor.remove("user");
            		editor.remove("password");
            	}
            	//editor.putString("token", a.getToken());
            	editor.commit();
            	
            	new login(user, password).start();
            	
            	info.loading = ProgressDialog.show(signin.this, "", 
                        "Logging in...", true);
            	
 			}
		});
        Button cpass = (Button) findViewById(R.id.Signinchange);
        cpass.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.changePass");
				startActivity(i);
				
				finish();
			}
		});
        
        Button rpass = (Button) findViewById(R.id.Signinforgot);
        rpass.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
 
				final AlertDialog.Builder alert = new AlertDialog.Builder(signin.this);
		        final EditText input = new EditText(signin.this);
		        alert.setTitle("Reset Password");
		        alert.setMessage("Please enter the email you registered with to reset your password");
		        alert.setView(input);
		        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		            	auth a = new auth();
		            	if(a.forgot(input.getText().toString())){         	
			                String value = "Password reset email has been sent to you";
			                Toast.makeText(getApplicationContext(), value,
		                        Toast.LENGTH_SHORT).show();
		            	}else{
			                String value = "Error resetting password";
			                Toast.makeText(getApplicationContext(), value,
		                        Toast.LENGTH_SHORT).show();		            		
		            	}
		            }
		        });

		        alert.setNegativeButton("Cancel",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int whichButton) {
		                        dialog.cancel();
		                    }
		                });
		        alert.show();

			}
		});

        
    }
}