package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
import android.widget.TextView;
import android.widget.Toast;

public class Battle extends Activity{
	public String enemy;
	public int enemyID;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);
  
        
        TextView you = (TextView)findViewById(R.id.yourName);
        you.setText(info.currentFight.myName);
        
        ImageView youp = (ImageView)findViewById(R.id.yourPic);
        youp.setImageDrawable(info.getPic(info.currentFight.myId));
        
        TextView enem = (TextView)findViewById(R.id.enemyName);
        enem.setText(info.currentFight.enemyName);
        
        ImageView enemp = (ImageView)findViewById(R.id.enemyPic);
        enemp.setImageDrawable(info.getPic(info.currentFight.enemyID));
        
        TextView enemh = (TextView)findViewById(R.id.enemyHealth);
        enemh.setText("Health: " + Integer.toString(info.currentFight.getEnemyHealth()) + " / " + Integer.toString(info.currentFight.enemyMaxHealth));
        
        TextView youh = (TextView)findViewById(R.id.yourHealth);
        youh.setText("Health: " + Integer.toString(info.currentFight.getMyHealth()) + " / " + Integer.toString(info.currentFight.myMaxHealth));
        
        
        Button attack = (Button)findViewById(R.id.attack);
        attack.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				//Intent i = new Intent();
				//i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.main");
				//startActivity(i);
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
			}
		});
	}
}
