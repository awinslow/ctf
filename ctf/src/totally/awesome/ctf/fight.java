package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.util.Log;


public class fight {
	int myHealth = -1;
	int enemyHealth = -1;
	int enemyID = -1;
	int myId = -1;
	Drawable enemyPic;
	int curTurn;
	
	String enemyName = "Name Not Set";
	String myName = "Name Not Set";
	String enemyClass = "";
	int myMaxHealth;
	int enemyMaxHealth;
	
	int myAttackVal;
	int enemyAttackVal;
	
	int myDefenseVal;
	int enemyDefenseVal;
	
	int APperTurn;
	int ap = 0;
	

	boolean myTurn;
	
	fight(int eid){
		
		enemyID = eid;
		myName = info.theAuth.name;
		myId = info.theAuth.id;
		info.currentFight = this;
	    info.setPic(eid, true);
		InitializeStats(true);//Initialize Enemy Stats
		InitializeStats(false);//Initialize my Stats
		
		myTurn = false;
		
		
		
		
	}
	
	void InitializeStats(boolean isEnemy)
	{
		try {
			String text = "";
			String inputLine;
			
			//ME
			
			URL u;
			if(!isEnemy)
			{
				u = new URL("http://ctf.awins.info/stats.php?uid=" + Integer.toString(myId));
			}
			else
			{
				u = new URL("http://ctf.awins.info/stats.php?uid=" + Integer.toString(enemyID));
			}

			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if(h.getResponseCode()==200){

				BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        h.getInputStream()));
				
				while ((inputLine = in.readLine()) != null)
				{
				    text=inputLine;
				    
				    if(text.indexOf("Health:") != -1)
				    {
				    	if(!isEnemy)
				    	{
				    		myMaxHealth = (Integer.parseInt(text.substring(7).trim()));
				    		myHealth = myMaxHealth;
				    	}
				    	else
				    	{
				    		enemyMaxHealth = (Integer.parseInt(text.substring(7).trim()));
				    		enemyHealth = enemyMaxHealth;
				    	}

				    }
				    else if(text.indexOf("Attack:") != -1)
				    {
				    	if(!isEnemy)
				    		myAttackVal = Integer.parseInt(text.substring(7).trim());
				    	else
				    		enemyAttackVal = Integer.parseInt(text.substring(7).trim());

				    }
				    else if(text.indexOf("Defense:") != -1)
				    {
				    	if(!isEnemy)
				    		myDefenseVal = Integer.parseInt(text.substring(8).trim());
				    	else
				    		enemyDefenseVal = Integer.parseInt(text.substring(8).trim());

				    }
				    else if(text.indexOf("Username:") != -1)
				    {
				    	if(!isEnemy)
				    	{
				    		//myName = text.substring(10);
				    	}
				    	else
				    	{
				    		enemyName = text.substring(10);
				    	}

				    }
				    if(text.indexOf("Class:") != -1)
				    {
				    	if(!isEnemy)
				    	{
				    		switch (Integer.parseInt(text.substring(6).trim()))
				    		{
				    			case 0: ap = 3;
				    					APperTurn = 3;
				    				break;
				    			case 1: ap = 4;
		    							APperTurn = 4;
			    					break;
				    			case 2: ap = 5;
		    							APperTurn = 5;
			    					break;
				    		}
				    	}
				    	else
				    	{
				    		switch (Integer.parseInt(text.substring(6).trim()))
				    		{
				    			case 0: enemyClass = "Soldier";
				    				break;
				    			case 1: enemyClass = "Sentinel";
			    					break;
				    			case 2: enemyClass = "Hacker";
			    					break;
				    		}
				    	}

				    }
				    else
				    {
				    	//Not parsing correctly
				    }
				}
				in.close();
			}
			else{
				
				Log.i("Fight initialization error:", "Response code NOT 200!!! for ME");
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("URL BREAKING SHIT", "Malformed URL");
		} catch (IOException e){
			e.printStackTrace();
			Log.i("IO", "IO exception in fight constructor");
		}
	}
	
	void setMyMaxHealth(int h)
	{
		myMaxHealth = h;
	}
	
	void setEnemyMaxHealth(int h)
	{
		enemyMaxHealth = h;
	}
	
	void setMyAP(int a)
	{
		ap = a;
	}
	
	int getMyAP(int aa)
	{
		return ap;
	}
	
	void setMyHealth(int h)
	{
		//If health going down then you were attacked.  It is now your turn
		if(myHealth - h > 0)
		{
			myTurn = true;
			//info.battleInst.attack.setEnabled(true);
		}
		myHealth = h;
	}
	void setEnemyHealth(int h)
	{
		enemyHealth = h;
	}
	int getMyHealth(){
		return myHealth;
	}
	
	int getEnemyHealth(){
		return enemyHealth;
	}
	
	void setTurn(int id)
	{
		if(id == myId)
		{
			myTurn = true;
			if(info.battleInst==null)
				Log.i("setTurn", "battleinst is null");
			else if (info.battleInst.attack0==null)
				Log.i("setTurn", "button is null");
			else Log.i("setTurn", "all quiet on the western front");
			//while(info.battleInst==null) Log.i("WAITING", "battle inst is still null bc its a little bitch");
			info.battleInst.attack0.setEnabled(true);
			info.battleInst.attack0.setBackgroundResource(R.drawable.rounded_button);
			info.battleInst.attack1.setEnabled(true);
			info.battleInst.attack1.setBackgroundResource(R.drawable.rounded_button);
			info.battleInst.attack2.setEnabled(true);
			info.battleInst.attack2.setBackgroundResource(R.drawable.rounded_button);
			info.battleInst.attack3.setEnabled(true);
			info.battleInst.attack3.setBackgroundResource(R.drawable.rounded_button);
			info.battleInst.item.setEnabled(true);
			info.battleInst.item.setBackgroundResource(R.drawable.rounded_button);
		}
		else if(id == enemyID)
		{
			myTurn = false;
			info.battleInst.attack0.setEnabled(false);
			info.battleInst.attack0.setBackgroundResource(R.drawable.rounded_button1);
			info.battleInst.attack1.setEnabled(false);
			info.battleInst.attack1.setBackgroundResource(R.drawable.rounded_button1);
			info.battleInst.attack2.setEnabled(false);
			info.battleInst.attack2.setBackgroundResource(R.drawable.rounded_button1);
			info.battleInst.attack3.setEnabled(false);
			info.battleInst.attack3.setBackgroundResource(R.drawable.rounded_button1);
			info.battleInst.item.setEnabled(false);
			info.battleInst.item.setBackgroundResource(R.drawable.rounded_button1);
		}
	}
}
