package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
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
	public static Animation moveDown;
    static LinearLayout screen;
	public TextView enemh;
	public TextView youh;
	//public static TextView apcount;
	public static ImageView enemp;
	ProgressBar youHBar;
	ProgressBar enemyHBar;
	Button attack0,attack1,attack2,attack3, item;
	MyIntentReceiver intentReceiver;
	TextView apcount;

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
        info.h = new heartbeat();
        info.h.start();
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
        		v.vibrate(500);
        		myDmgAnim.setText("-"+dmg);
        		myDmgAnim.setTextColor(Color.RED);
        		//myDmgAnim.setVisibility(1);
        		myDmgAnim.startAnimation(fadein);
        		myDmgAnim.startAnimation(moveDown);
        		//myDmgAnim.setVisibility(0);
        	}
        	else
        		myOldHealth = info.currentFight.getMyHealth();
        	
        	if (enemOldHealth > info.currentFight.getEnemyHealth())
        	{
        		Log.i("SCREEN SHAKING","Screen should shake RIGHT NOWWWWW");
        		dmg = Integer.toString(enemOldHealth - info.currentFight.getEnemyHealth());
        		enemOldHealth = info.currentFight.getEnemyHealth();
        		enemp.startAnimation(shake);
        		enemDmgAnim.setText("-"+dmg);
        		enemDmgAnim.setTextColor(Color.RED);
        		//myDmgAnim.setVisibility(1);
        		enemDmgAnim.startAnimation(fadein);
        		enemDmgAnim.startAnimation(moveDown);
        	}
        	else
        		enemOldHealth = info.currentFight.getEnemyHealth();
           // apcount.setText("AP: " + Integer.toString(info.currentFight.curAP));
			info.battleInst.enemh.setText("Health: " + Integer.toString(info.currentFight.getEnemyHealth()) + " / " + Integer.toString(info.currentFight.enemyMaxHealth));
			info.battleInst.youh.setText("Health: " + Integer.toString(info.currentFight.getMyHealth()) + " / " + Integer.toString(info.currentFight.myMaxHealth));
			info.battleInst.enemyHBar.setProgress((int) (((float) info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100));
			info.battleInst.youHBar.setProgress((int) (((float) info.currentFight.getMyHealth()/(float)info.currentFight.myMaxHealth)*100));
			info.currentFight.setTurn(info.currentFight.curTurn);
			info.battleInst.apcount.setText("AP: "+Integer.toString(info.currentFight.ap));
        }
      };
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		info.battleInst = this;
		
		MediaPlayer mp = MediaPlayer.create(this, R.raw.battlestart);
        mp.start();
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);
       // myOldHealth;
       // enemyHBar.setMinimumWidth(10);
       // youHBar.setMinimumWidth(10);
        //youHBar.set
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        fadein = AnimationUtils.loadAnimation(this, R.anim.alpha);
        moveDown = AnimationUtils.loadAnimation(this, R.anim.translate);
        screen = (LinearLayout)findViewById(R.id.BattleScreen);
        enemyHBar = (ProgressBar)findViewById(R.id.EnemyHealthBar);
        enemyHBar.setProgress((int) (((float)info.currentFight.getEnemyHealth()/(float)info.currentFight.enemyMaxHealth)*100));
        youHBar = (ProgressBar)findViewById(R.id.MyHealthBar);
        youHBar.setProgress((int) (((float)info.currentFight.getMyHealth()/(float)info.currentFight.myMaxHealth)*100));
        
        apcount = (TextView)findViewById(R.id.apcount);
        apcount.setText("AP: 0");// + Integer.toString(info.currentFight.curAP));
        
        
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
        
        apcount = (TextView)findViewById(R.id.apcount);
        apcount.setText("AP: 0");
        
        if(!info.currentFight.myTurn)
        	attack0.setEnabled(false);
        attack0.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(info.currentFight.ap>=1){
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
				} else {
					Toast.makeText(getApplicationContext(), "Sorry, you dont have enough AP to do that", Toast.LENGTH_SHORT).show();
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
				if((info.myClass == 0 && info.currentFight.ap>=3) ||
						(info.myClass == 1 && info.currentFight.ap>=4) ||
						(info.myClass == 2 && info.currentFight.ap>=5)){
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
				} else {
					Toast.makeText(getApplicationContext(), "Sorry, you dont have enough AP to do that", Toast.LENGTH_SHORT).show();

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
				if((info.myClass == 0 && info.currentFight.ap>=3) ||
						(info.myClass == 1 && info.currentFight.ap>=6) ||
						(info.myClass == 2 && info.currentFight.ap>=6)){
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
				} else {
					Toast.makeText(getApplicationContext(), "Sorry, you dont have enough AP to do that", Toast.LENGTH_SHORT).show();

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

				if((info.myClass == 0 && info.currentFight.ap>=8) ||
						(info.myClass == 1 && info.currentFight.ap>=8) ||
						(info.myClass == 2 && info.currentFight.ap>=12)){
					info.attacknum = 3;
					Intent i = new Intent();
					i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.hacknumpad");
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

					startActivity(i);
					
				} else {
					Toast.makeText(getApplicationContext(), "Sorry, you dont have enough AP to do that", Toast.LENGTH_SHORT).show();

				}

			}
		});
        
        item = (Button)findViewById(R.id.UseItem);
        item.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {      
				
			    //CharSequence[] items = CharSequence[100];//{"Red", "Green", "Blue"};
			    //final Vector<CharSequence> items = new Vector<CharSequence>();
				final ArrayList<CharSequence> items = new ArrayList<CharSequence>();
		        URL u;
				try {
					u = new URL("http://ctf.awins.info/item.php?token="+info.theAuth.getToken());
					
					HttpURLConnection h = (HttpURLConnection) u.openConnection();
					h.setRequestMethod("GET");
					h.connect();
					if(h.getResponseCode()==200){
						//Log.i("Inventory", ");
						BufferedReader in = new BufferedReader(
		                        new InputStreamReader(
		                        h.getInputStream()));
						String inputLine;
						
						while ((inputLine = in.readLine()) != null){
							Log.i("Inventory", inputLine);
							 items.add(inputLine);
						}
						   
						in.close();
					}
					else{
						Log.i("error", "URL connection errored with code: "+Integer.toString((h.getResponseCode())));
						
					}
				} catch (Exception e){
					
				}
			    Log.i("Inventory", Integer.toString(items.size()));
			    //Integer ia[] = new Integer[al.size()];
			    //ia = al.toArray(ia);
				CharSequence tmp[] = new CharSequence[items.size()];
				tmp = items.toArray(tmp);
				//items.toArray(tmp);
		        AlertDialog.Builder builder = new AlertDialog.Builder(Battle.this);
		        
		        builder.setTitle("Pick an item");
		        builder.setItems(tmp, new DialogInterface.OnClickListener(){
		            public void onClick(DialogInterface dialogInterface, int item) {
		    	        URL u;
		    	        info.battleInst.item.setEnabled(false);
						try {
							u = new URL("http://ctf.awins.info/item.php?use="+item+"&token="+info.theAuth.getToken());
							
							HttpURLConnection h = (HttpURLConnection) u.openConnection();
							h.setRequestMethod("GET");
							h.connect();
							if(h.getResponseCode()==200){
								Log.i("Item", "Used sucessfully");
							}
							else{
								Log.i("error", "URL connection errored with code: "+Integer.toString((h.getResponseCode())));
								
							}
						} catch (Exception e){
							
						}

		            	
		            	Toast.makeText(getApplicationContext(), "You just used "+items.get(item), Toast.LENGTH_SHORT).show();
		                return;
		            }
		        });
		        builder.create().show();	
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
        info.currentFight.setTurn(info.currentFight.myId);
        info.h = new heartbeat();
       info.h.start();
	}
}
