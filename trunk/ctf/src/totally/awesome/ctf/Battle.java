package totally.awesome.ctf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Battle extends Activity{
	//public static Context thisContext = this;
	public String enemy;
	public int enemyID;
	public static int myOldHealth = info.currentFight.myMaxHealth;
	public static int enemOldHealth = info.currentFight.enemyMaxHealth;
	public static TextView myDmgAnim;
	public static TextView enemDmgAnim;
	public static Vibrator v;
	public static Animation shake;
	public static Animation fadein;
    static LinearLayout screen;
	public TextView enemh;
	public TextView youh;
	public static ImageView enemp;
	ProgressBar youHBar;
	ProgressBar enemyHBar;
	Button attack0,attack1,attack2,attack3;
	MyIntentReceiver intentReceiver;

	public void onPause(){		
		super.onPause();
		info.h.interrupt();
		unregisterReceiver(intentReceiver);
	}
	
	public void onResume(){
		super.onResume();
		info.inMatchMaking=false;
        intentReceiver = new MyIntentReceiver();
        IntentFilter intentFilter = new IntentFilter("totally.awesome.ctf.HI");
        intentFilter.setPriority(1);

        registerReceiver(intentReceiver, intentFilter); 
	}
	static RefreshHandler mRedrawHandler = new RefreshHandler();
    static class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
        	Log.i("GUI UPDATE", "Time to update the GUI");
       	String dmg;
        	if (myOldHealth > info.currentFight.getMyHealth())
        	{
        		dmg = Integer.toString(myOldHealth - info.currentFight.getMyHealth());
        		Log.i("SCREEN SHAKING","Screen should shake RIGHT NOWWWWW");
        		myOldHealth = info.currentFight.getMyHealth();
        		screen.startAnimation(shake);
        		v.vibrate(300);
        		myDmgAnim.setText("-"+dmg);
        		//myDmgAnim.setTextColor(100);
        		myDmgAnim.setVisibility(1);
        		myDmgAnim.startAnimation(fadein);
        		myDmgAnim.setVisibility(0);
        	}
        	
        	if (enemOldHealth > info.currentFight.getEnemyHealth())
        	{
        		Log.i("SCREEN SHAKING","Screen should shake RIGHT NOWWWWW");
        		enemOldHealth = info.currentFight.getEnemyHealth();
        		enemp.startAnimation(shake);
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
       // myOldHealth;
       // enemyHBar.setMinimumWidth(10);
       // youHBar.setMinimumWidth(10);
        //youHBar.set
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        fadein = AnimationUtils.loadAnimation(this, R.anim.damage);
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
        
        enemp = (ImageView)findViewById(R.id.enemyPic);
        enemp.setImageDrawable(info.currentFight.enemyPic);
        
        enemh = (TextView)findViewById(R.id.enemyHealth);
        enemh.setText("Health: " + Integer.toString(info.currentFight.getEnemyHealth()) + " / " + Integer.toString(info.currentFight.enemyMaxHealth));
        
        youh = (TextView)findViewById(R.id.yourHealth);
        youh.setText("Health: " + Integer.toString(info.currentFight.getMyHealth()) + " / " + Integer.toString(info.currentFight.myMaxHealth));
        
        myDmgAnim = (TextView)findViewById(R.id.myDmg);
        enemDmgAnim = (TextView)findViewById(R.id.enemyDmg);

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
