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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

class findPlayer extends Thread{
	int ID;
	
	findPlayer(){
		ID=-1;
	}
	findPlayer(int in){
		ID=in;
	}
	
	public void run(){
		Log.i("Match Making", "Starting Matchmaking...");
		info.inMatchMaking=true;
      //  MyIntentReceiver intentReceiver = new MyIntentReceiver();
      //  IntentFilter intentFilter = new IntentFilter("totally.awesome.ctf.HI");
      //  intentFilter.setPriority(1);
      //  registerReceiver(intentReceiver, intentFilter); 
		try {
			URL u = new URL("http://ctf.awins.info/join.php?gid=3&token="+info.theAuth.getToken());
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if(h.getResponseCode()!=200) Log.i("Arena", "The shit hit the fan while trying to add to game");
			h.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info.haveSomeone = false;
		info.mmbitch = false;
		boolean cancel = false;
		info.target = -1;
		URL u;
		if(ID==-1){		
			while(!info.haveSomeone){
            	if(!info.loading.isShowing()){
            		Log.i("Match Making", "User canceled loading...");
            		info.inMatchMaking=false;
            		try {
            			URL u1 = new URL("http://ctf.awins.info/join.php?gid=-1&token="+info.theAuth.getToken());
            			HttpURLConnection h = (HttpURLConnection) u1.openConnection();
            			h.setRequestMethod("GET");
            			h.connect();
            			if(h.getResponseCode()!=200) Log.i("Arena", "The shit hit the fan while trying to add to game");
            			h.disconnect();
            		} catch (MalformedURLException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		} catch (IOException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            		cancel=true;
            		break;
            	}

            	//Waiting for server to match up
            	try {
        			URL u1 = new URL("http://ctf.awins.info/nodie.php?token="+info.theAuth.getToken());
        			HttpURLConnection h = (HttpURLConnection) u1.openConnection();
        			h.setRequestMethod("GET");
        			h.connect();
        			if(h.getResponseCode()!=200) Log.i("Arena", "The shit hit the fan while trying to add to game");
        			h.disconnect();
        		} catch (MalformedURLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            	
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			info.target=ID;
			info.haveSomeone=true;				
		}
	
		if(info.haveSomeone && !info.mmbitch)
		{
			Log.i("Match Making", "Found a target...");
			try {
				u = new URL("http://ctf.awins.info/battle.php?challenge=1&target="+info.target+"&token="+info.theAuth.getToken());
				
				int eid = info.target;
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
					
					//while ((inputLine = in.readLine()) != null) 
						inputLine = in.readLine();
					    text=inputLine;
					in.close();
					
					
				//	Toast myToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
				//	myToast.show();
					
					int duration = Toast.LENGTH_SHORT;
				if(text.subSequence(0, 2).equals("id")) text = "challenge sent";
				else{
					info.loading.dismiss();
					Message m = new Message();
					m.arg1=2;
					Arena.startBattle.sendMessage(m);	
					cancel=true;
					
				}
					//Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
					//toast2.show();
				}
				else{
					info.loading.dismiss();
					Message m = new Message();
					m.arg1=2;
					Arena.startBattle.sendMessage(m);	
					cancel=true;
					
				}
				Log.i("a", new String(Integer.toString(h.getResponseCode())));
	            Log.i("a","opened");
	            //Log.i("a","http://141.212.113.248/c2dm.php?register=1&rid="+registrationId+"&a="+id);
	
	           // info.loading.dismiss();
	            //Go to Battle!
	            
	            String inputLine = "User not in battle!";
	            while(inputLine.trim().equals("User not in battle!")){
	            	if(!info.loading.isShowing()){
	            		Log.i("Match Making", "User canceled loading...");
	            		info.inMatchMaking=false;
	            		try {
	            			URL u1 = new URL("http://ctf.awins.info/join.php?gid=-1&token="+info.theAuth.getToken());
	            			HttpURLConnection h1 = (HttpURLConnection) u1.openConnection();
	            			h1.setRequestMethod("GET");
	            			h1.connect();
	            			if(h1.getResponseCode()!=200) Log.i("Arena", "The shit hit the fan while trying to add to game");
	            			h1.disconnect();
	            		} catch (MalformedURLException e) {
	            			// TODO Auto-generated catch block
	            			e.printStackTrace();
	            		} catch (IOException e) {
	            			// TODO Auto-generated catch block
	            			e.printStackTrace();
	            		}
	            		cancel=true;
	            		break;
	            	}
	            	Log.i("Match Making", "Waiting for approval...");

	    			URL u1;
	    			try {
	    				u1 = new URL("http://ctf.awins.info/battle.php?token=" + info.theAuth.getToken() + "&stats=1");
	    	
	    				Log.i("Battle", "Url: " + "http://ctf.awins.info/battle.php?token=" + info.theAuth.getToken() + "&stats=1");
	    	
	    				HttpURLConnection h1 = (HttpURLConnection) u1.openConnection();
	    				h1.setRequestMethod("GET");
	    				h1.connect();
	    				if(h1.getResponseCode()==200){
	    	
	    					Log.i("Battle", "Connection made response code 200");
	    					BufferedReader in = new BufferedReader(
	    	                        new InputStreamReader(
	    	                        h1.getInputStream()));
	    					
	    					//while (inputLine != null)
	    					//{
	    							inputLine = in.readLine();
	    							//Log.i("i got ", inputLine);
	    					//}
	    					in.close();
	    				}
	    				else{
	    					
	    					Log.i("Battle", "Response code NOT 200!!! for Attack");
	    				}
	    			} catch (MalformedURLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    				Log.i("Battle", "Malformed URL Exception: " + e);
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    				Log.i("Battle", "IOException: " + e);
	    			}
	    			
	    			try {
	    				Thread.sleep(1000);
	    			} catch (InterruptedException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			
	    		}
	            if(!cancel){
		            Log.i("Match Making", "Finshed with Matchmaking...");
		            info.loading.dismiss();
		            Message m = new Message();
		            m.arg1=1;
		            if(Arena.startBattle != null) Arena.startBattle.sendMessage(m);
		            else adventureperson.startBattle.sendMessage(m);
	            }
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
		else
		{
			//Toast curToast = Toast.makeText(getApplicationContext(), "No Users Found on Server", Toast.LENGTH_SHORT);
			//curToast.show();
		}
	}
}



public class Arena extends Activity{
	String Uname = "uname";
	//String theClass = info.myPlayer.getName();
	String Exp = "exp";
	String Lvl = "lvl";
	String MaxH = "maxh";
	String Attk = "attk";
	String Def = "def";
	String AP = "0";
	MyIntentReceiver intentReceiver;
	
	public void onPause(){
		super.onPause();
		unregisterReceiver(intentReceiver);
	}
	
	public void onResume(){
		super.onResume();
		info.inMatchMaking=false;
        intentReceiver = new MyIntentReceiver();
        IntentFilter intentFilter = new IntentFilter("totally.awesome.ctf.HI");
        intentFilter.setPriority(1);

        registerReceiver(intentReceiver, intentFilter); 
	}
	
	static RefreshHandler startBattle;
	class RefreshHandler extends Handler {
	    @Override
	    public void handleMessage(Message msg) {
	    	if(msg.arg1==1){
		    	Log.i("Match Macking", "Starting Handler to  switch to battle...");
	            Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
				Arena.this.startActivity(i);
				
				Arena.this.finish();
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
		setStats();
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.arenamode);
    	info.inMatchMaking=false;
    	TextView uname = (TextView)findViewById(R.id.username);
    	uname.setText("User Name: " + Uname);
    	TextView uClass = (TextView)findViewById(R.id.Classme);
    	uClass.setText("User Class: " + info.myPlayer.getName());
    	//TextView exp = (TextView)findViewById(R.id.Experience);
    	//exp.setText("Experience: " + Exp);
    	//TextView lvl = (TextView)findViewById(R.id.level);
    	//lvl.setText("Level: " + Lvl);
    	TextView maxH = (TextView)findViewById(R.id.maxHealth);
    	maxH.setText("Health: " + MaxH);
    	TextView attk = (TextView)findViewById(R.id.Attack);
    	attk.setText("Attack: " + Attk);
    	TextView def = (TextView)findViewById(R.id.Defense);
    	def.setText("Defense: " + Def);
    	TextView APperTurn = (TextView)findViewById(R.id.APperTurn);
    	APperTurn.setText("Attack Points per turn: " + AP);
    	ImageView icon = (ImageView)findViewById(R.id.userIcon);
    	icon.setImageDrawable(info.getPic(info.theAuth.id));
    	
    
    	
    	Button start = (Button)findViewById(R.id.StartBattle);
    	start.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				
				info.loading = ProgressDialog.show(Arena.this, "", 
                        "Please wait while we find you a challenger", true);
				info.loading.setCancelable(true);
				new findPlayer().start();
			}
		});
    	Button startID = (Button)findViewById(R.id.StartBattleID);
    	startID.setOnClickListener(new View.OnClickListener() {	
    		
			public void onClick(View v) {
				final AlertDialog.Builder alert = new AlertDialog.Builder(Arena.this);
		        final EditText input = new EditText(Arena.this);
		        alert.setTitle("Battle Challenge");
		        alert.setMessage("Who do you want to challenge?");
		        alert.setView(input);
		        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
						info.loading = ProgressDialog.show(Arena.this, "", 
		                        "Please wait while we connect you to your target", true);
						info.loading.setCancelable(true);
						new findPlayer(Integer.parseInt(input.getText().toString())).start();		            
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
		});

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
			    	else if(inText.indexOf("Class:") != -1)
			    	{
			    		//theClass = inText.substring(6).trim();
			    		Log.i("Arena", "Class: " + inText.substring(6).trim());
			    	}
			    	else if(inText.indexOf("Points:") != -1)
			    	{
			    		AP = inText.substring(7).trim();
			    		Log.i("Arena", "Class: " + inText.substring(6).trim());
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
