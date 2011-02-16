package totally.awesome.ctf;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Arena extends Activity{
	String Uname = "uname";
	String Exp = "exp";
	String Lvl = "lvl";
	String MaxH = "maxh";
	String Attk = "attk";
	String Def = "def";
	public void onCreate(Bundle savedInstanceState) {
		setStats();
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.arenamode);
    	
    	TextView uname = (TextView)findViewById(R.id.username);
    	uname.setText("Username: " + Uname);
    	TextView exp = (TextView)findViewById(R.id.Experience);
    	exp.setText("Experience: " + Exp);
    	TextView lvl = (TextView)findViewById(R.id.level);
    	lvl.setText("Level: " + Lvl);
    	TextView maxH = (TextView)findViewById(R.id.maxHealth);
    	maxH.setText("Health: " + MaxH);
    	TextView attk = (TextView)findViewById(R.id.Attack);
    	attk.setText("Attack: " + Attk);
    	TextView def = (TextView)findViewById(R.id.Defense);
    	def.setText("Defense: " + Def);
    	ImageView icon = (ImageView)findViewById(R.id.userIcon);
    	icon.setImageDrawable(info.getPic(info.theAuth.id));
    	
    	//How to get this to show up onclick?
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        alert.setTitle("Battle Challenge");
        alert.setMessage("Who do you want to challenge?");
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
				URL u;
    			try {
    				u = new URL("http://ctf.awins.info/battle.php?challenge=1&target="+input.getText().toString()+"&token="+info.theAuth.getToken());
    				
    				int eid = Integer.parseInt(input.getText().toString().trim());
    				info.currentFight = new fight(eid);
    				
    				HttpURLConnection h = (HttpURLConnection) u.openConnection();
    				h.setRequestMethod("GET");
    				h.connect();
    				if(h.getResponseCode()==200){
    					CharSequence text = "challenge sent";
	    				//Context context = message.this;
    					BufferedReader in = new BufferedReader(
    	                        new InputStreamReader(
    	                        h.getInputStream()));
    					String inputLine;
    					
    					while ((inputLine = in.readLine()) != null) 
    					    text=inputLine;
    					in.close();
    					
    					
    					Toast myToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
    					myToast.show();
	    				
	    				int duration = Toast.LENGTH_SHORT;
	    			if(text.subSequence(0, 3).equals("id")) text = "challenge sent";
	    				//Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
	    				//toast2.show();
    				}
    				else{
	    				//Context context = getApplicationContext();
	    				CharSequence text = "Error";
	    				int duration = Toast.LENGTH_SHORT;
	
	    				//Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
	    				//toast2.show();   					
    					
    				}
    				Log.i("a", new String(Integer.toString(h.getResponseCode())));
    	            Log.i("a","opened");
    	            //Log.i("a","http://141.212.113.248/c2dm.php?register=1&rid="+registrationId+"&a="+id);

    	            
    	            //Go to Battle!
    	            Intent i = new Intent();
    				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
    				startActivity(i);
    				
    				finish();
    			} catch (MalformedURLException e1) {
    				// TODO Auto-generated catch block
    				Log.i("Arena","Malformed URL Exception: " + e1);
    				e1.printStackTrace();
    			}catch (IOException e1) {
    				// TODO Auto-generated catch block
    				Log.i("Arena","IOException: " + e1);
    				e1.printStackTrace();
    			}
            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                        
                        //Go back to select page
                        //Intent i = new Intent();
                        //i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
                        //tartActivity(i);
                        
                        Log.i("Arena", "Battle initiation canceled by user");
                        //finish();
                    }
                });
        
        alert.show();		
	}
	
	public void setStats()
	{
		URL u;
		String inputLine = "INPUT LINE";
		String inText = "";
		try {
			u = new URL("http://ctf.awins.info/stats.php?uid=" + info.theAuth.id);

			Log.i("Arena", "Url: " + "http://ctf.awins.info/stats.php?uid=" + info.theAuth.id);

			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if(h.getResponseCode()==200){

				Log.i("Arena", "Connection made response code 200");
				BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        h.getInputStream()));
				
				while (inputLine != null)
				{
					
					inputLine = in.readLine();
					Log.i("Arena", "Input Line: " + inputLine);
					if(inputLine == null)
					{
						Log.i("Arena", "Input Line null");
						break;
					}
				    inText=inputLine;
				    if(inText.indexOf("Health:") != -1)
				    {
				    	MaxH = inText.substring(7).trim();
				    	Log.i("Arena", "Health: " + MaxH);

				    }
			    	else if(inText.indexOf("Attack:") != -1)
			    	{
			    		Attk = inText.substring(7).trim();
			    		Log.i("Arena", "Attack: " + Attk);
			    	}
			    	else if(inText.indexOf("Defense:") != -1)
			    	{
			    		Def = inText.substring(8).trim();
			    		Log.i("Arena", "Defense: " + Def);
			    	}
			    	else if(inText.indexOf("Username:") != -1)
			    	{
			    		Uname = inText.substring(9).trim();
			    		Log.i("Arena", "Username: " + Uname);
			    	}
			    	else if(inText.indexOf("Level:") != -1)
			    	{
			    		Lvl = inText.substring(6).trim();
			    		Log.i("Level", "Level: " + Lvl);
			    	}
			    	else if(inText.indexOf("Experience:") != -1)
			    	{
			    		Exp = inText.substring(11).trim();
			    		Log.i("Arena", "Experience: " + Exp);
			    	}
			    	else
			    	{
			    		Log.i("Arena", "Could not parse statistic: " + inText);
			    	}
			    }

				in.close();
			}
			else{
				
				Log.i("Arena", "Response code NOT 200!!! for Arena Stats");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Arena", "Malformed URL Exception: " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Arena", "IOException: " + e);
		}
	}
}
