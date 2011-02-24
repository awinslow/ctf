package totally.awesome.ctf;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        
	}
}
  