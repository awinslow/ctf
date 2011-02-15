package totally.awesome.ctf;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MyIntentReceiver extends BroadcastReceiver {
	/**
	* @see adroid.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	*/

	@Override
	public void onReceive(final Context context, Intent intent) {
		//Context context2 = Context.getApplicationContext();
		final CharSequence text = intent.getStringExtra("message");
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
		Log.i("Battle", "C2DM Received!!	 Text: " + text.toString());
		
		
		if(text.subSequence(0,3).equals("won"))
		{
			Log.i("Battle", "WINNER!!!!!!!!!!!!!!!!!!!!!!");
			//Toast winner = Toast.makeText(context, "Congratulations, you WON THE BATTLE!", Toast.LENGTH_LONG);
			//winner.show();
			
			
			Log.i("Battle", "About to go to select");
			Intent i = new Intent();
			i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
		    context.startActivity(i);
		    
		    info.battleInst.finish();
		}
		else if(text.subSequence(0, 4).equals("lost"))
		{
			Log.i("Battle", "LOSER!!!!!!!!!!!!!!!!!!!");
			//Toast loser = Toast.makeText(context, "Sorry, you lost the battle", Toast.LENGTH_LONG);
			//loser.show();
			
			
			Log.i("Battle", "About to go to select");
			Intent i = new Intent();
			i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
		    context.startActivity(i);
		    
		    info.battleInst.finish();
		}
		else if(text.subSequence(0, 7).equals("battle."))
		{
			if(text.subSequence(7, 8).equals("a")){
	//			CharSequence text1 = "You are now in battle";
	//			int duration1 = Toast.LENGTH_SHORT;

				info.currentFight.myTurn = true;
				Toast toast2 = Toast.makeText(context, "You are now in battle", Toast.LENGTH_SHORT);
				toast2.show();
				
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
			    context.startActivity(i);
			}else{
				
				final AlertDialog.Builder alert = new AlertDialog.Builder(context);
		       // final EditText input = new EditText(context);
		        alert.setTitle("Battle Challenge");
		        alert.setMessage(text.subSequence(7, text.length())+" Has challenged you to battle! Accept?");
		      //  alert.setView(input);
		        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
						URL u;
		    			try {
		    				u = new URL("http://ctf.awins.info/battle.php?accept=1&target="+text.subSequence(7, text.length())+"&token="+info.theAuth.getToken());
		    				
		    				HttpURLConnection h = (HttpURLConnection) u.openConnection();
		    				h.setRequestMethod("GET");
		    				h.connect();
		    				if(h.getResponseCode()==200){
			    				//Context context = message.this;
			    				CharSequence text = "You are now in battle";
			    				int duration = Toast.LENGTH_SHORT;
			    				
								Intent i = new Intent();
								i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
								context.startActivity(i);	
								
			    				Toast toast2 = Toast.makeText(context, text, duration);
			    				toast2.show();
		    				}
		    				else{
			    				//Context context = getApplicationContext();
			    				CharSequence text = "Error";
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
		else if(text.subSequence(0, 7).equals("checkin"))
		{
			Log.i("Battle", "Reached Check in");
			String inText = "";
			String inText2 = "";
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
					    	}
					    	else
					    	{
					    		Log.i("Battle", "Battle update info not parsing correctly uid inner");
					    	}
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

				       Log.i("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", Integer.toString((int) (((float)info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100))); 
				       
					//Intent i = new Intent();
					//i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Battle");
				    //context.startActivity(i);

				}
				else{
					
					Log.i("Battle", "Response code NOT 200!!! for Attack");
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			Log.i("Battle", "C2DM text: " + text.toString());
		}
	}
}