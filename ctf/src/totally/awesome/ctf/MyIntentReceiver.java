package totally.awesome.ctf;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import totally.awesome.ctf.Battle.RefreshHandler;

import android.app.AlertDialog;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

class setup extends Thread{
	CharSequence text;
	
	setup(CharSequence t){
		text=t;
	}
	
	public void run(){
		URL u;
		Log.i("Battle", "Button accepted clicked");
		try {
			u = new URL("http://ctf.awins.info/battle.php?accept=1&target="+text.subSequence(7, text.length())+"&token="+info.theAuth.getToken());
			
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			Log.i("Battle", "Battle accept url connected to");
			if(h.getResponseCode()==200){
				h.disconnect();
				Log.i("Battle", "Response code 200");
				
				int eid = Integer.parseInt(text.subSequence(7, text.length()).toString().trim());
				
				Log.i("Battle", "Accepted Battle.  Enemy ID: " + Integer.toString(eid));
				Log.i("Battle", "Making a new fight");
				info.currentFight = new fight(eid);
				Log.i("Battle", "Done Making fight");
				if(info.battleInst != null)
				{
					info.battleInst.finish();
				}
				
				info.loading.dismiss();
				MyIntentReceiver.gotoBattle.sendMessage(new Message());
			}
			else{
				
				Log.i("Battle", "Response Code not 200: " + h.getResponseCode());

				//CharSequence text = "Error";
				//int duration = Toast.LENGTH_SHORT;

				//Toast toast2 = Toast.makeText(context, text, duration);
				//toast2.show();			
				
			}

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			Log.i("Battle","Malformed URL Exception: " + e1);
			e1.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			Log.i("Battle","IOException: " + e1);
			e1.printStackTrace();
		}	
	}
}

public class MyIntentReceiver extends BroadcastReceiver {
	/**
	* @see adroid.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	*/
	
	
	static Context theContext;
	
	static RefreshHandler gotoBattle = new RefreshHandler();
    static class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
			Intent i = new Intent();
			Log.i("Battle", "refresh handler battle start");
			i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
			theContext.startActivity(i);	
			
			
			int duration = Toast.LENGTH_SHORT;
			Toast toast2 = Toast.makeText(theContext, "You are now in battle", duration);
			toast2.show();
        }
      };

	@Override
	public void onReceive(final Context context, Intent intent) {
		theContext = context;
		final CharSequence text = intent.getStringExtra("message");
		/*int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();*/
		
		Log.i("Battle", "C2DM Received!!	 Text: " + text.toString());
		
		
		if(text.subSequence(0,3).equals("won"))
		{
			Log.i("Battle", "WINNER!!!!!!!!!!!!!!!!!!!!!!");
			MediaPlayer mp = MediaPlayer.create(context, R.raw.ko);
			try {
				mp.prepare();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        mp.start();
	        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

	        	public void onCompletion(MediaPlayer mp) {

	        	Log.v("log_tag","Complete Video");
	        	
	        	
				Toast toast2 = Toast.makeText(context, "Congrats, you won!", Toast.LENGTH_SHORT);
				toast2.show();
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
				info.h.interrupt();
				Log.i("Battle", "About to go to select");
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
			    context.startActivity(i);
			    
			    info.battleInst.finish();

	        	}

	        	});
			

		    
		}
		else if(text.subSequence(0, 4).equals("lost"))
		{
			Log.i("Battle", "LOSER!!!!!!!!!!!!!!!!!!!");
			Toast toast2 = Toast.makeText(context, "Sorry, you lost!", Toast.LENGTH_SHORT);
			toast2.show();
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
			info.h.interrupt();
			Log.i("Battle", "About to go to select");
			Intent i = new Intent();
			i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
		    context.startActivity(i);
		    
		    info.battleInst.finish();
		}
		else if(text.subSequence(0, 7).equals("battle."))
		{
			if(text.subSequence(7, 8).equals("a")){
				Log.i("Battle", "battle.a received");

				Toast toast2 = Toast.makeText(context, "You are now in battle", Toast.LENGTH_SHORT);
				toast2.show();
				
				Intent i = new Intent();
				Log.i("Battle", "battle.a battle starting");
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
			    context.startActivity(i);
				Log.i("Battle",Integer.toString(info.currentFight.myId));
				//info.currentFight.setTurn(info.currentFight.myId);
				Log.i("Battle", "Fight set up");
			}else{
				if(info.inMatchMaking){
					info.loading.dismiss();
					URL u;
					Log.i("Battle", "Accepting bc in match making");
	    			try {
	    				u = new URL("http://ctf.awins.info/battle.php?accept=1&target="+text.subSequence(7, text.length())+"&token="+info.theAuth.getToken());
	    				
	    				HttpURLConnection h = (HttpURLConnection) u.openConnection();
	    				h.setRequestMethod("GET");
	    				h.connect();
	    				Log.i("Battle", "Battle accept url connected to");
	    				if(h.getResponseCode()==200){
	    					
	    					Log.i("Battle", "Response code 200");
		    				//Context context = message.this;

		    				
		    				int eid = Integer.parseInt(text.subSequence(7, text.length()).toString().trim());
		    				
		    				Log.i("Battle", "Accepted Battle.  Enemy ID: " + Integer.toString(eid));
		    				info.currentFight = new fight(eid);
		    				
		    				if(info.battleInst != null)
		    				{
		    					info.battleInst.finish();
		    				}
		    				
							Intent i = new Intent();
							Log.i("Battle", "matchmaking battle starting");
							i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
							context.startActivity(i);	
							
							
		    				int duration = Toast.LENGTH_SHORT;
		    				Toast toast2 = Toast.makeText(context, "You are now in battle", duration);
		    				toast2.show();
	    				}
	    				else{
	    					
	    					Log.i("Battle", "Response Code not 200: " + h.getResponseCode());

		    				
		    				int duration = Toast.LENGTH_SHORT;
		
		    				Toast toast2 = Toast.makeText(context, "Error", duration);
		    				toast2.show();			
	    					
	    				}

	    			} catch (MalformedURLException e1) {
	    				// TODO Auto-generated catch block
	    				Log.i("Battle","Malformed URL Exception: " + e1);
	    				e1.printStackTrace();
	    			}catch (IOException e1) {
	    				// TODO Auto-generated catch block
	    				Log.i("Battle","IOException: " + e1);
	    				e1.printStackTrace();
	    			}
	            
				}else{
					Log.i("Battle", "Entering Battle accept dialog");
					final AlertDialog.Builder alert = new AlertDialog.Builder(context);
			        alert.setTitle("Battle Challenge");
			        alert.setMessage(text.subSequence(7, text.length())+" Has challenged you to battle! Accept?");
			        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			            	info.loading=ProgressDialog.show(context, "", 
			                        "Please wait while we set up your battle", true);
			            	new setup(text).start();
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
			}
		} 
		else if(text.subSequence(0,5).equals("dodge"))
		{
			Toast.makeText(context, "Attack Missed!!!", Toast.LENGTH_SHORT).show();
		}
		else if(text.subSequence(0, 7).equals("checkin"))//Ask server for updated stats
		{
			Log.i("Battle", "Reached Check in");
			String inText = "";
			String inText2 = "";
			String inText3 = "";
			String inputLine = "input line";
			
			URL u;
			try {
				u = new URL("http://ctf.awins.info/battle.php?token=" + info.theAuth.getToken() + "&stats=1");

				Log.i("Battle", "Url: " + "http://ctf.awins.info/battle.php?token=" + info.theAuth.getToken() + "&stats=1");
	
				HttpURLConnection h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				if(h.getResponseCode()==200){
	
					Log.i("Battle", "Connection made response code 200");
					BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                        h.getInputStream()));
					
					while (inputLine != null)
					{
						
						inputLine = in.readLine();
						Log.i("Battle", "Input Line: " + inputLine);
						if(inputLine == null)
						{
							Log.i("Battle", "Input Line null");
							break;
						}
					    inText=inputLine;
					    if(inText.indexOf("uid:") != -1)
					    {
					    	int uid = (Integer.parseInt(inText.substring(4).trim()));
					    	Log.i("Battle", "UID: " + Integer.toString(uid));
					    	if(info.currentFight.enemyID == uid)
					    	{
					    		inputLine = in.readLine();
					    		Log.i("Battle", "Input Line: " + inputLine);
					    		if(inputLine == null)
					    		{
									Log.i("Battle", "Input Line null");
									break;
								}
					    		inText2 = inputLine;
					    		
					    		if(inText2.indexOf("health:") != -1)
					    		{
					    			int enemyH = (Integer.parseInt(inText2.substring(7).trim()));
					    			Log.i("Battle", "ENEMYHEALTH: " + Integer.toString(enemyH));
					    			info.currentFight.setEnemyHealth(enemyH);
					    		}
					    		
					    	}
					    	else if(info.currentFight.myId == uid)
					    	{
					    		inputLine = in.readLine();
					    		Log.i("Battle", "Input Line: " + inputLine);
					    		if(inputLine == null)
					    		{
									Log.i("Battle", "Input Line null");
									break;
								}
					    		
					    		inText2 = inputLine;
					    		
					    		if(inText2.indexOf("health:") != -1)
					    		{
					    			int myH = (Integer.parseInt(inText2.substring(7).trim()));
					    			Log.i("Battle", "MYHEALTH" + Integer.toString(myH));
					    			info.currentFight.setMyHealth(myH);
					    		}
					    		
					    		inText3 = in.readLine();
					    		
					    		if(inText3.indexOf("points:") != -1)
					    		{
					    			int myAP = (Integer.parseInt(inText2.substring(7).trim()));
					    			Log.i("Battle", "ENEMYHEALTH: " + Integer.toString(myAP));
					    			info.currentFight.setMyAP(myAP);
					    		}
					    	}
					    	else
					    	{
					    		Log.i("Battle", "Battle update info not parsing correctly uid inner");
					    	}
					    }
					    else if(inText.indexOf("turn:") != -1)
					    {
					    	int curTurn = Integer.parseInt(inText.substring(5).trim());
					    	info.currentFight.setTurn(curTurn);
					    	Log.i("Battle", "Setting turn to: " + Integer.toString(curTurn));
					    }
					    else
					    {
					    	Log.i("Battle", "Battle update info not parsing correctly uid");
					    }
					}
					in.close();
					
					Log.i("Battle", "Starting Battle Refresh");

					info.battleInst.enemh.setText("Health: " + Integer.toString(info.currentFight.getEnemyHealth()) + " / " + Integer.toString(info.currentFight.enemyMaxHealth));
					info.battleInst.youh.setText("Health: " + Integer.toString(info.currentFight.getMyHealth()) + " / " + Integer.toString(info.currentFight.myMaxHealth));
					info.battleInst.enemyHBar.setProgress((int) (((float) info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100));
					info.battleInst.youHBar.setProgress((int) (((float) info.currentFight.getMyHealth()/(float)info.currentFight.myMaxHealth)*100));
					
				    Log.i("Battle", Integer.toString((int) (((float)info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100))); 
				       
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
			
		}
		else
		{
			Log.i("Battle", "C2DM text: " + text.toString());
		}
	}
}