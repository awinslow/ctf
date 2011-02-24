package totally.awesome.ctf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Battle extends Activity{
	public String enemy;
	public int enemyID;
	public static int oldHealth = info.currentFight.myMaxHealth;
	public static Animation shake;
    static LinearLayout screen;
	public TextView enemh;
	public TextView youh;
	ProgressBar youHBar;
	ProgressBar enemyHBar;
	Button attack0,attack1,attack2,attack3;

	
	static RefreshHandler mRedrawHandler = new RefreshHandler();
    static class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
        	Log.i("GUI UPDATE", "Time to update the GUI");
       	
        	if (oldHealth > info.currentFight.getMyHealth())
        	{
        		Log.i("SCREEN SHAKING","Screen should shake RIGHT NOWWWWW");
        		oldHealth = info.currentFight.getMyHealth();
        		screen.startAnimation(shake);
        	}
			info.battleInst.enemh.setText("Health: " + Integer.toString(info.currentFight.getEnemyHealth()) + " / " + Integer.toString(info.currentFight.enemyMaxHealth));
			info.battleInst.youh.setText("Health: " + Integer.toString(info.currentFight.getMyHealth()) + " / " + Integer.toString(info.currentFight.myMaxHealth));
			info.battleInst.enemyHBar.setProgress((int) (((float) info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100));
			info.battleInst.youHBar.setProgress((int) (((float) info.currentFight.getMyHealth()/(float)info.currentFight.myMaxHealth)*100));
			info.currentFight.setTurn(info.currentFight.curTurn);
        }
      };
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		info.battleInst = this;
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);
       // oldHealth;
       // enemyHBar.setMinimumWidth(10);
       // youHBar.setMinimumWidth(10);
        //youHBar.set
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        screen = (LinearLayout)findViewById(R.id.BattleScreen);
        enemyHBar = (ProgressBar)findViewById(R.id.EnemyHealthBar);
        enemyHBar.setProgress((int) (((float)info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100));
        youHBar = (ProgressBar)findViewById(R.id.MyHealthBar);
        youHBar.setProgress((int) (((float)info.currentFight.getMyHealth()/(float)info.currentFight.myMaxHealth)*100));
        
        //youHBar.set

        
        
        Log.i("Battle", Integer.toString((int) (((float)info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100))); 
        TextView you = (TextView)findViewById(R.id.yourName);
        if(info.currentFight.myName != null)
        {
        	Log.i("Battle", "Fight name null in current fight!");
        	you.setText(info.currentFight.myName);
        }
        
        TextView enemClass = (TextView)findViewById(R.id.enemyClass);
        enemClass.setText(info.currentFight.enemyClass);
        
        TextView myClass = (TextView)findViewById(R.id.yourClass);
        myClass.setText(info.myPlayer.getName());
        
        
        ImageView youp = (ImageView)findViewById(R.id.yourPic);
        youp.setImageDrawable(info.myPicture);
        
        TextView enem = (TextView)findViewById(R.id.enemyName);
        enem.setText(info.currentFight.enemyName);
        
        ImageView enemp = (ImageView)findViewById(R.id.enemyPic);
        enemp.setImageDrawable(info.currentFight.enemyPic);
        
        enemh = (TextView)findViewById(R.id.enemyHealth);
        enemh.setText("Health: " + Integer.toString(info.currentFight.getEnemyHealth()) + " / " + Integer.toString(info.currentFight.enemyMaxHealth));
        
        youh = (TextView)findViewById(R.id.yourHealth);
        youh.setText("Health: " + Integer.toString(info.currentFight.getMyHealth()) + " / " + Integer.toString(info.currentFight.myMaxHealth));
        
        

        attack0 = (Button)findViewById(R.id.attack0);
        attack0.setText(info.myPlayer.name0);

        if(!info.currentFight.myTurn)
        	attack0.setEnabled(false);
        attack0.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (!info.myPlayer.attack0())
				{
					CharSequence text = "Attack message to server failed";
					int duration = Toast.LENGTH_SHORT;
	
					Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
					toast2.show();
				}
				else {
					Log.i("Battle", "Attack sent");
					
					//Button attk = (Button)findViewById(R.id.attack);
					//attk.setEnabled(false);
					info.currentFight.myTurn = false;
				}
			}
		});
        
        attack1 = (Button)findViewById(R.id.attack1);
        attack1.setText(info.myPlayer.name1);
        if(!info.currentFight.myTurn)
        	attack1.setEnabled(false);
        attack1.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (!info.myPlayer.attack1(-1))
				{
					CharSequence text = "Attack message to server failed";
					int duration = Toast.LENGTH_SHORT;
	
					Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
					toast2.show();
				}
				else {
					Log.i("Battle", "Attack1 sent");
					
					//Button attk = (Button)findViewById(R.id.attack);
					//attk.setEnabled(false);
					info.currentFight.myTurn = false;
				}
			}
		});
        
        attack2 = (Button)findViewById(R.id.attack2);
        attack2.setText(info.myPlayer.name2);
        if(!info.currentFight.myTurn)
        	attack2.setEnabled(false);
        attack2.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (!info.myPlayer.attack2(-1))
				{
					CharSequence text = "Attack message to server failed";
					int duration = Toast.LENGTH_SHORT;
	
					Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
					toast2.show();
				}
				else {
					Log.i("Battle", "Attack2 sent");
					
					//Button attk = (Button)findViewById(R.id.attack);
					//attk.setEnabled(false);
					info.currentFight.myTurn = false;
				}
			}
		});
        
        attack3 = (Button)findViewById(R.id.attack3);
        attack3.setText(info.myPlayer.name3);
        if(!info.currentFight.myTurn)
        	attack3.setEnabled(false);
        attack3.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (!info.myPlayer.attack3(-1))
				{
					CharSequence text = "Attack message to server failed";
					int duration = Toast.LENGTH_SHORT;
	
					Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
					toast2.show();
				}
				else {
					Log.i("Battle", "Attack3 sent");
					
					//Button attk = (Button)findViewById(R.id.attack);
					//attk.setEnabled(false);
					info.currentFight.myTurn = false;
				}
			}
		});
        
        Button back = (Button)findViewById(R.id.battleBack);
        back.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
				startActivity(i);
				
				finish();
				
			}
		});
        
        info.h = new heartbeat();
       info.h.start();
	}
}
