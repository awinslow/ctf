package totally.awesome.ctf;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;

public class info {
	public static auth theAuth;
	public static int myHealth;
	public static fight currentFight;
	public static Drawable getPic(int id){
        try
        {
        	String theID = new String();
        	theID = Integer.toString(id);
        	URL u;
        	u = new URL("http://ctf.awins.info/pics/"+theID+".jpg");
        	InputStream is = (InputStream) u.getContent();
        	Drawable d = Drawable.createFromStream(is, "src name");
        	return d;
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Exc="+e);
        	return null;
        }
       
	}
}
