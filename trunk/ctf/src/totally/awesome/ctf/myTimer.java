package totally.awesome.ctf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;

public class myTimer extends CountDownTimer {
	TextView timeText;
	Activity activity;
	boolean gameDone = false;
	boolean gameWon = false;
	
	public myTimer(long millisInFuture, long countDownInterval, Activity a) {
		super(millisInFuture, countDownInterval);
		activity = a;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		//finish means the player failed the game

//		Intent i = new Intent();
//		i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.minigames");
//		activity.startActivity(i);
		gameDone = true;
		gameWon = false;
		activity.finish();
		
	}
	
	@Override
	public void onTick(long arg0) {
		// TODO Auto-generated method stub
	       int seconds = (int) (arg0 / 1000);
	       int minutes = seconds / 60;
	       seconds     = seconds % 60;

	       if (seconds < 10) {
	           timeText.setText("" + minutes + ":0" + seconds);
	       } else {
	           timeText.setText("" + minutes + ":" + seconds);            
	       }
		
	}
	
}
