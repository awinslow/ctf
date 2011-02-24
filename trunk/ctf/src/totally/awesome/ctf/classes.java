package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class classes extends Activity 
{
    public void onCreate(Bundle savedInstanceState) 
    {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classes);
        Button soldier = (Button)findViewById(R.id.soldier);
        Button sentinel = (Button)findViewById(R.id.sentinel);
        Button hacker = (Button)findViewById(R.id.hacker);
        soldier.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{				
				
				info.myPlayer = new Soldier();
				if(!updateServerWithClass(0))//Tell Database to set us with class "0" Soldier
				{
					Toast toast = Toast.makeText(getApplicationContext(), "Soldier Class choice failed.",  Toast.LENGTH_SHORT);
					toast.show();
					Log.i("Create", "Soldier Creation Failed");
					return;
				}
				
				Toast toast = Toast.makeText(getApplicationContext(), "Soldier Chosen",  Toast.LENGTH_SHORT);
				toast.show();
				
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
				
				finish();
			}
        });
        sentinel.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{				
				
				
				info.myPlayer = new Sentinel();
				if(!updateServerWithClass(1))//Tell Database to set us with class "1" Sentinel
				{
					Toast toast = Toast.makeText(getApplicationContext(), "Sentinel Class choice Failed.",  Toast.LENGTH_SHORT);
					toast.show();
					Log.i("Create", "Sentinel Creation Failed");
					return;
				}
				Toast toast = Toast.makeText(getApplicationContext(), "Sentinel Chosen",  Toast.LENGTH_SHORT);
				toast.show();
				
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
				
				finish();
			}
        });
        hacker.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{				
				
				info.myPlayer = new Hacker();
				if(!updateServerWithClass(2))//Tell Database to set us to class "2" Hacker
				{
					Toast toast = Toast.makeText(getApplicationContext(), "Hacker Class choice Faled.",  Toast.LENGTH_SHORT);
					toast.show();
					Log.i("Create", "Hacker Creation Failed");
					return;
				}
				
				Toast toast = Toast.makeText(getApplicationContext(), "Hacker Chosen",  Toast.LENGTH_SHORT);
				toast.show();
				
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
				
				finish();
			}
        });        
    }
    
    boolean updateServerWithClass(int classID)
    {
    	URL u;
		try {
			u = new URL("http://ctf.awins.info/chooseClass.php?id=" + info.theAuth.id + "&class=" + Integer.toString(classID));
			
			Log.i("Create", "URL: " + "http://ctf.awins.info/chooseClass.php?id=" + info.theAuth.id + "&class=" + Integer.toString(classID));
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if(h.getResponseCode()==200){
				Log.i("Create", "Response code 200!");
				
				BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        h.getInputStream()));
				String inClassResponse = in.readLine();
				Log.i("Create", "Line Read");
				
				//assumes it works because reading from server failing
				Log.i("Create", "Success returned");
				Context context = getApplicationContext();
				CharSequence text = "Class chosen successfully.";
				int duration = Toast.LENGTH_SHORT;
				Toast toast2 = Toast.makeText(context, text, duration);
				toast2.show();
				return true;
				/*
				if(inClassResponse == null)
				{
					Log.i("Create", "Nothing read from site");
					Context context = getApplicationContext();
					CharSequence text = "Nothing read from site.";
					int duration = Toast.LENGTH_SHORT;
					Toast toast2 = Toast.makeText(context, text, duration);
					toast2.show();
					return false;
				}
				else if(inClassResponse.substring(0, 4).equals("fail"))
				{
					Log.i("Create", "Server Fail on choose class");
					Context context = getApplicationContext();
					CharSequence text = "Server class choose fail.";
					int duration = Toast.LENGTH_SHORT;
					Toast toast2 = Toast.makeText(context, text, duration);
					toast2.show();
					return false;
				}
				else if(inClassResponse.substring(0,7).equals("success"))
				{
					Log.i("Create", "Success returned");
					Context context = getApplicationContext();
					CharSequence text = "Class chosen successfully.";
					int duration = Toast.LENGTH_SHORT;
					Toast toast2 = Toast.makeText(context, text, duration);
					toast2.show();
					return true;
				}
				else if(inClassResponse.substring(0,15).equals("class not given"))
				{
					Log.i("Create", "Class not given");
					Context context = getApplicationContext();
					CharSequence text = "Class not given.";
					int duration = Toast.LENGTH_SHORT;
					Toast toast2 = Toast.makeText(context, text, duration);
					toast2.show();
					return false;
				}
				else
				{
					Log.i("Create", "Server Response: " + inClassResponse);
					Context context = getApplicationContext();
					CharSequence text = "Server Response not parsed correctly: " + inClassResponse;
					int duration = Toast.LENGTH_LONG;
					Toast toast2 = Toast.makeText(context, text, duration);
					toast2.show();
					return false;
				}
				*/
			}
			else{
				Context context = getApplicationContext();
				CharSequence text = "Error choosing class";
				int duration = Toast.LENGTH_SHORT;
				Toast toast2 = Toast.makeText(context, text, duration);
				toast2.show();   					
				return false;
			}
		}catch(MalformedURLException e1)
		{
			Log.i("Create", "Malformed URL Exception: " + e1);
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Create", "IO Exception: " + e);
			return false;
		}
    }
}
