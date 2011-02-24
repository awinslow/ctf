package totally.awesome.ctf;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.c2dm.C2DMessaging;

public class signin extends Activity {
    /** Called when the activity is first created. */
	public static final String PREFS_NAME = "MyPrefsFile";
	String user;
	String password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
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
        signIn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
            	EditText uname = (EditText) findViewById(R.id.EditText01);
            	user=uname.getText().toString();
            	EditText pword = (EditText) findViewById(R.id.EditText02);
            	password=pword.getText().toString();          
            	auth a = new auth(user, password);
            	CheckBox c = (CheckBox)findViewById(R.id.CheckBox01);
            	if(c.isChecked()){
            		editor.putString("user", user);
            		editor.putString("password", password);
            	} else{
            		editor.remove("user");
            		editor.remove("password");
            	}
            	editor.putString("token", a.getToken());
            	editor.commit();
            	info.theAuth=a;
            
            
				//Context context = getApplicationContext();
				//CharSequence text = a.getToken();
				//int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(getApplicationContext(), a.getToken(), Toast.LENGTH_SHORT);
				//toast.show();
				String registrationId = C2DMessaging.getRegistrationId(getApplicationContext());
				URL u;
    			try {
    				u = new URL("http://ctf.awins.info/c2dm.php?register=1&rid="+registrationId+"&token="+a.getToken());
    				
    				HttpURLConnection h = (HttpURLConnection) u.openConnection();
    				h.setRequestMethod("GET");
    				h.connect();
    				if(h.getResponseCode()==200){
    					
    					
	    				Context context = getApplicationContext();
	    				CharSequence text = "Incorrect username or password";
	    				if(!info.theAuth.getToken().equals("-1")){
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