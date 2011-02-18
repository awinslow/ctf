package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Message;
import android.util.Log;

public class heartbeat extends Thread{
	heartbeat(){
		
	}
	
	public void run(){
		while(true){
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
	
	//				info.battleInst.enemh.setText("Health: " + Integer.toString(info.currentFight.getEnemyHealth()) + " / " + Integer.toString(info.currentFight.enemyMaxHealth));
	//				info.battleInst.youh.setText("Health: " + Integer.toString(info.currentFight.getMyHealth()) + " / " + Integer.toString(info.currentFight.myMaxHealth));
	//				info.battleInst.enemyHBar.setProgress((int) (((float) info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100));
	//				info.battleInst.youHBar.setProgress((int) (((float) info.currentFight.getMyHealth()/(float)info.currentFight.myMaxHealth)*100));
		
					//Battle.RefreshHandler handle = new Battle.RefreshHandler();
					Battle.mRedrawHandler.sendMessage(new Message());
					
				    Log.i("Battle", Integer.toString((int) (((float)info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100))); 
				       
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
				Log.i("Battle", "Malformed URL Exception: " + e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i("Battle", "IOException: " + e);
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
