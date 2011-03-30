package totally.awesome.ctf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class select extends Activity{
	//MyIntentReceiver intentReceiver;
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        //intentReceiver = new MyIntentReceiver();
        //IntentFilter intentFilter = new IntentFilter("totally.awesome.ctf.HI");
        //intentFilter.setPriority(1);

        //registerReceiver(intentReceiver, intentFilter); 
        
        Button battle = (Button)findViewById(R.id.battleMode); //id is button 1
        battle.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Arena");
				startActivity(i);

				//finish();
			}
		});
        
        Button adventure = (Button)findViewById(R.id.adventureMode); //id is button 1
        adventure.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.adventure");
				startActivity(i);

				//finish();
			}
		});
        
        Button miniG = (Button)findViewById(R.id.minigames);
        miniG.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.minigames");
				startActivity(i);

				//finish();
			}
		});
        
        
		AlertDialog.Builder builder = new AlertDialog.Builder(select.this);
		builder.setMessage("How would you like to set your picture?");
		builder.setPositiveButton("Take a picture!", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
				//switch intents
					Intent i = new Intent();
					i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.myCamera");
					startActivity(i);
					
				}
			});
		builder.setNeutralButton("Use a picture!", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			//switch intents
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.selectPic");
				startActivity(i);
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		final AlertDialog alert = builder.create();
        
        Button pict = (Button)findViewById(R.id.pict);
        pict.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
//				Intent i = new Intent();
//				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.minigames");
//				startActivity(i);
//
//				finish();
				alert.show();
				

			}
		});
        
	}
}
  