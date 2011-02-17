package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Battle extends Activity{
	public String enemy;
	public int enemyID;
	public TextView enemh;
	public TextView youh;
	ProgressBar youHBar;
	ProgressBar enemyHBar;
	Button attack;
	public void onCreate(Bundle savedInstanceState) {
		
		info.battleInst = this;
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);
       // enemyHBar.setMinimumWidth(10);
       // youHBar.setMinimumWidth(10);
        //youHBar.set
        enemyHBar = (ProgressBar)findViewById(R.id.EnemyHealthBar);
        enemyHBar.setProgress((int) (((float)info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100));
        youHBar = (ProgressBar)findViewById(R.id.MyHealthBar);
        youHBar.setProgress((int) (((float)info.currentFight.getMyHealth()/(float)info.currentFight.myMaxHealth)*100));
        Log.i("Battle", Integer.toString((int) (((float)info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100))); 
        TextView you = (TextView)findViewById(R.id.yourName);
        
        if(info.currentFight.myName != null)
        {
        	Log.i("Battle", "Fight name null in current fight!");
        	you.setText(info.currentFight.myName);
        }
        
        ImageView youp = (ImageView)findViewById(R.id.yourPic);
        youp.setImageDrawable(info.getPic(info.currentFight.myId));
        
        TextView enem = (TextView)findViewById(R.id.enemyName);
        enem.setText(info.currentFight.enemyName);
        
        ImageView enemp = (ImageView)findViewById(R.id.enemyPic);
        enemp.setImageDrawable(info.getPic(info.currentFight.enemyID));
        
        enemh = (TextView)findViewById(R.id.enemyHealth);
        enemh.setText("Health: " + Integer.toString(info.currentFight.getEnemyHealth()) + " / " + Integer.toString(info.currentFight.enemyMaxHealth));
        
        youh = (TextView)findViewById(R.id.yourHealth);
        youh.setText("Health: " + Integer.toString(info.currentFight.getMyHealth()) + " / " + Integer.toString(info.currentFight.myMaxHealth));
        
        
        attack = (Button)findViewById(R.id.attack);
        if(!info.currentFight.myTurn)
        	attack.setEnabled(false);
        attack.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				//Intent i = new Intent();
				//i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.main");
				//startActivity(i);
				
				try {
					URL u = new URL("http://ctf.awins.info/battle.php?attack=1&target="+Integer.toString(info.currentFight.enemyID)+"&token="+info.theAuth.getToken());
				
    				HttpURLConnection h = (HttpURLConnection) u.openConnection();
    				h.setRequestMethod("GET");
    				h.connect();
    				
    				if(h.getResponseCode()==200)
    				{
    					Log.i("Battle", "Attack sent");
    					
    					//Button attk = (Button)findViewById(R.id.attack);
    					//attk.setEnabled(false);
    					info.currentFight.myTurn = false;
    				}
    				else
    				{
	    				CharSequence text = "Attack message to server failed";
	    				int duration = Toast.LENGTH_SHORT;
	
	    				Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
	    				toast2.show();  
    				}
				
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

			}
		});
        
        Button back = (Button)findViewById(R.id.battleBack);
        back.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
				startActivity(i);
				
				finish();
				
			}
		});
	}
}
