package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class fight {
	int myHealth;
	int enemyHealth;
	int enemyID;
	int myId;
	String enemyName = "Name Not Set";
	String myName;
	int myMaxHealth;
	int enemyMaxHealth;
	
	int myAttackVal;
	int enemyAttackVal;
	
	int myDefenseVal;
	int enemyDefenseVal;
	
	boolean myTurn;
	
	fight(int eid){
		
		enemyID = eid;
		myName = info.theAuth.name;
		myId = info.theAuth.id;
		
		InitializeStats(true);//Enemy Stats
		InitializeStats(false);//My Stats
		
		/*
		//myMaxHealth = m;
		//enemyMaxHealth = e;

		
		try {
			String text = "";
			String inputLine;
			
			//ME
			
			URL u = new URL("http://ctf.awins.info/stats.php?uid=" + Integer.toString(myId));

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
				    	setMyMaxHealth(Integer.parseInt(text.substring(7).trim()));
				    }
				    else if(text.indexOf("Attack:") != -1)
				    {
				    	myAttackVal = Integer.parseInt(text.substring(7).trim());
				    }
				    else if(text.indexOf("Defense:") != -1)
				    {
				    	myDefenseVal = Integer.parseInt(text.substring(8).trim());
				    }
				    else if(text.indexOf("Username:") != -1)
				    {
				    	myName = text.substring(10);
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
				
			//ENEMY
			u = new URL("http://ctf.awins.info/stats.php?uid=" + Integer.toString(enemyID));

			h = (HttpURLConnection) u.openConnection();
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
				    	setEnemyMaxHealth(Integer.parseInt(text.substring(7).trim()));
				    }
				    else if(text.indexOf("Attack:") != -1)
				    {
				    	enemyAttackVal = Integer.parseInt(text.substring(7).trim());
				    }
				    else if(text.indexOf("Defense:") != -1)
				    {
				    	enemyDefenseVal = Integer.parseInt(text.substring(8).trim());
				    }
				    else if(text.indexOf("Username:") != -1)
				    {
				    	enemyName = text.substring(10);
				    }
				    else
				    {
				    	//Not parsing correctly
				    }
				}
				in.close();
			
				}
				else{
			
					Log.i("Fight initialization error:", "Response code NOT 200!!! for ENEMY");
				}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("URL BREAKING SHIT", "Malformed URL");
		} catch (IOException e){
			e.printStackTrace();
			Log.i("IO", "IO exception in fight constructor");
		}
		*/
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
				    		myName = text.substring(10);
				    	else
				    		enemyName = text.substring(10);

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
	
	void didWin(boolean won){
		
	}

	void attack(int hp){
		message.sendMessage(Integer.toString(enemyID), "attacked."+Integer.toString(hp));
	}
	
	void attackResult(int hp){
		enemyHealth-=hp;
	}
	
	void gotAttacked(int hp){
		myHealth-=hp;
	}
	
	int getMyHealth(){
		return myHealth;
	}
	
	int getEnemyHealth(){
		return enemyHealth;
	}
	
	//
}
