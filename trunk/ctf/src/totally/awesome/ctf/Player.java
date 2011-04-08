package totally.awesome.ctf;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public abstract class Player {
	
	String name0, name1, name2, name3;
	
	public Player()
	{
		name0 = "Attack! (1AP)";
	}
	boolean attack0()
	{
		//boolean output = false;
		//Basic Attack
		try {
			URL u = new URL("http://ctf.awins.info/battle.php?attack=0&target="+Integer.toString(info.currentFight.enemyID)+"&token="+info.theAuth.getToken()+"&class="+info.myClass);
		
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			
			if(h.getResponseCode()==200)
			{
				return true;
			}
			else
				return false; 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "Malformed URL in attack send");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "Protocol exception in attack send to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "IOException in attack send to server");
		}
		return false;
	}
	
	boolean attack1(int type)
	{
		try {
			URL u = new URL("http://ctf.awins.info/battle.php?attack=1&target="+Integer.toString(info.currentFight.enemyID)+"&token="+info.theAuth.getToken()
					+"&class="+type);
		
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			
			if(h.getResponseCode()==200)
			{
				return true;
			}
			else
				return false; 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "Malformed URL in attack send");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "Protocol exception in attack send to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "IOException in attack send to server");
		}
		return false;
	}
	
	boolean attack2(int type)
	{
		try {
			URL u = new URL("http://ctf.awins.info/battle.php?attack=2&target="+Integer.toString(info.currentFight.enemyID)+"&token="+info.theAuth.getToken()
					+"&class="+type);
		
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			
			if(h.getResponseCode()==200)
			{
				return true;
			}
			else
				return false; 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "Malformed URL in attack send");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "Protocol exception in attack send to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "IOException in attack send to server");
		}
		return false;
	}
	
	boolean attack3(int type)
	{
		try {
			URL u = new URL("http://ctf.awins.info/battle.php?attack=3&target="+Integer.toString(info.currentFight.enemyID)+"&token="+info.theAuth.getToken()
					+"&class="+type);
		
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			
			if(h.getResponseCode()==200)
			{
				return true;
			}
			else
				return false; 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "Malformed URL in attack send");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "Protocol exception in attack send to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Battle", "IOException in attack send to server");
		}
		return false;
	}
	
	void SoundAttack0()
	{
		MediaPlayer mp = MediaPlayer.create(info.battleInst, R.raw.attack);
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
	}
	
	abstract void soundAttack1();
	abstract void soundAttack2();
	abstract void soundAttack3();
	abstract String getName();
	
	abstract int getNumber();
	

}
