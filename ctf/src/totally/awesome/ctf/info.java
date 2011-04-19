package totally.awesome.ctf;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;

public class info {
	public static auth theAuth;
	//public static int myHealth;
	public static fight currentFight;
	public static Battle battleInst;
	public static Player myPlayer;
	static Drawable myPicture;
	static heartbeat h;
	static ProgressDialog loading;
	static boolean inMatchMaking;
	static myTimer gameTimer;
	static int attacknum;
	static int myClass;
	static int checkingOutPlayer;
	static boolean haveSomeone;
	static boolean mmbitch;
	static int target;
	public static Vector<LatitudeLongitude> EnemyLocations;
	public static Drawable getPic(int id){
		return myPicture;
	}
	private static MediaPlayer battleSong;
       
	
	public static void setPic(int id, boolean isEnemy)
	{
		try
	    {
        	String theID = new String();
        	theID = Integer.toString(id);
        	URL u;
        	u = new URL("http://ctf.awins.info/pics/"+theID+".jpg");
        	InputStream is = (InputStream) u.getContent();
        	if (isEnemy)
        	{
        		if (info.currentFight == null)
        		{
        			Log.i("error","CURRENT FIGHT NOT INITIALIZED");
        		}
        		else
        			info.currentFight.enemyPic = Drawable.createFromStream(is, "src name");
        	}
        	if (!isEnemy)
        	{
        		myPicture = Drawable.createFromStream(is, "src name");
        	}
        	
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Exc="+e);
        }
	       
	}
	
	public static void setPlayerClass(int classNum)
	{
		myClass=classNum;
		switch(classNum)
		{
			case 0:
				myPlayer = new Soldier();
				break;
			
			case 1:
				myPlayer = new Sentinel();
				break;
				
			case 2:
				myPlayer = new Hacker();
				break;
		}
	}
	
	public static void playBackgroundSong(){
		battleSong = MediaPlayer.create(info.battleInst, R.raw.battlesongfinal);
		battleSong.setLooping(true);
		battleSong.setVolume(0.4f, 0.4f);
		try {
			battleSong.prepare();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        battleSong.start();
	}
	public static void stopBackgroundSound(){
		battleSong.pause();
	}
}
	
