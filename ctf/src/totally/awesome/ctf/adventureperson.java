package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import totally.awesome.ctf.Arena.RefreshHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class adventureperson extends Activity{
	
	static RefreshHandler startBattle;
	class RefreshHandler extends Handler {
	    @Override
	    public void handleMessage(Message msg) {
	    	if(msg.arg1==1){
		    	Log.i("Match Macking", "Starting Handler to  switch to battle...");
	            Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
				adventureperson.this.startActivity(i);
				
				adventureperson.this.finish();
	    	} else if(msg.arg1==2){
	    		Toast curToast = Toast.makeText(getApplicationContext(), "Sorry, There was an error connecting to player.  They may already be in a battle", Toast.LENGTH_SHORT);
				curToast.show();	    		
	    	}
	    	else{
	    		Toast curToast = Toast.makeText(getApplicationContext(), "Sorry, noone can battle you right now!", Toast.LENGTH_SHORT);
				curToast.show();
	    	}
	    }
	  };
	
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		startBattle = new RefreshHandler();
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.adventureperson);
		String name = "idk";
		try{
			URL u = new URL("http://ctf.awins.info/stats.php?uid=" + info.checkingOutPlayer);

			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if(h.getResponseCode()==200){

				BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        h.getInputStream()));
				
				String inputLine;
				while ((inputLine = in.readLine()) != null)
				{
		    		if(inputLine.indexOf("Username:") != -1)
		    		{
		    			name = inputLine.substring(9).trim();
		    			Log.i("NAME", name);
		
		    		}
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("URL BREAKING SHIT", "Malformed URL");
		} catch (IOException e){
			e.printStackTrace();
			Log.i("IO", "IO exception in fight constructor");
		}

    	TextView nameView = (TextView)findViewById(R.id.apname);
    	nameView.setText(name);
    	
    	
    	
    	
    	Drawable thepic = null;
    	
		try
	    {
        	String theID = new String();
        	theID = Integer.toString(info.checkingOutPlayer);
        	URL u;
        	u = new URL("http://ctf.awins.info/pics/"+theID+".jpg");
        	InputStream is = (InputStream) u.getContent();

        	thepic = Drawable.createFromStream(is, "src name");
        	        	
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Exc="+e);
        }
    	
    	
    	
    	
    	
    	
    	
    	
    	ImageView icon = (ImageView)findViewById(R.id.appic);
    	icon.setImageDrawable(thepic);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	Button start = (Button)findViewById(R.id.apbattle);
    	start.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {  	
				info.loading = ProgressDialog.show(adventureperson.this, "", 
		                "Please wait while we connect you to your target", true);
				info.loading.setCancelable(true);
				new findPlayer(info.checkingOutPlayer).start();	
			}
    	});
    	Button back = (Button)findViewById(R.id.apback);
    	back.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				finish();
			}
		});
	}
}
