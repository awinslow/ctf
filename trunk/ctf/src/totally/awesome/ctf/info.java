package totally.awesome.ctf;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class info {
	public static auth theAuth;
	public static int myHealth;
	public static fight currentFight;
	public static Battle battleInst;
	public static Player myPlayer;
	static Drawable myPicture;
	static heartbeat h;
	public static Drawable getPic(int id){
		return myPicture;
	}
       
	
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
}
	
