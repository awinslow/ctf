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
import android.widget.Toast;

public class Arena extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.arenamode);
    	
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
                        Intent i = new Intent();
                        i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
                        startActivity(i);
                        
                        Log.i("Arena", "Battle initiation canceled by user");
                        finish();
                    }
                });
        
        alert.show();		
	}
}
