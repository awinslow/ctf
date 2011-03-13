package totally.awesome.ctf;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class hacknumpad extends Activity {
	Random rand = new Random();
	int counter;
	TextView codeMsg, yourMsg;
	String code, yourcode;
	Integer codeArray[] = new Integer[5];
	ArrayList<Button> bArray = new ArrayList<Button>();
	
	public void buttonSetup(int index,int n)
	{
		String indexString = Integer.toString(index);
		Button b = null;
		switch(n)
		{
		case 0:
			b = (Button) findViewById(R.id.button1);
			break;
		case 1:
			b = (Button) findViewById(R.id.button2);
			break;
		case 2:
			b = (Button) findViewById(R.id.button3);
			break;
		case 3:
			b = (Button) findViewById(R.id.button4);
			break;
		case 4:
			b = (Button) findViewById(R.id.button5);
			break;
		case 5:
			b = (Button) findViewById(R.id.button6);
			break;
		case 6:
			b = (Button) findViewById(R.id.button7);
			break;
		case 7:
			b = (Button) findViewById(R.id.button8);
			break;
		case 8:
			b = (Button) findViewById(R.id.button9);
			break;
		}
		
		b.setText(indexString);
		//final int value = Integer.parseInt((String) b.getText());
		final int value = index;
		b.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==value)
				{
					if (counter==4)
					{
						//success
						yourcode+= value;
						yourMsg.setText(yourcode);
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						scramble();
						counter++;
						yourcode+= value;
						yourMsg.setText(yourcode);
					}
						
				}
				else
				{
					counter = 0;
					yourcode = "Input Password: ";
					yourMsg.setText(yourcode);
				}
				
				
			}
        });
		
		bArray.add(b);
	}
	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hacknumpad);
	    counter = 0;
	    //Context context = getApplicationContext();
	    //bArray = new ArrayList<Button>();
	    bArray.clear();
	    for (int i=0;i<9;i++)
	    {
	    	Log.i("hacknumpad","Button id is " + R.id.button1+i);
	    	Log.i("hacknumpad","Button id1 should be " + R.id.button1);

	    	buttonSetup(i+1, i);
	    }
	    
	    while (counter<5)
	    {
	    	int value = rand.nextInt(9)+1;
	    	Log.i("VALUE MAY HAVE FUCKED UP","VALUE IS "+value);
	    	codeArray[counter]=value;
	    	counter++;
	    }
	    yourcode = "Input Password: ";
	    code = "Code: ";
	    for (int i=0;i<5;i++)
	    	code+=codeArray[i].toString();
	    
	    codeMsg = (TextView)findViewById(R.id.CodeText);
	    codeMsg.setText(code);
	    
	    //int playerArray[] = new int[9];
	    yourMsg = (TextView)findViewById(R.id.YourText);
	    yourMsg.setText("Input Password: ");
	    
	    counter = 0;
	    
        
	    scramble();
	    info.gameTimer = new myTimer(10000, 1000, this);
	    info.gameTimer.timeText = (TextView)findViewById(R.id.thetimer);
	    info.gameTimer.start();

	    
	}
	
	
	public void onDestroy()
	{
		Log.i("HACKNUMPAD","CALLED DESTROY WITH COUNTER = " + counter);
		if (info.gameTimer.gameWon)
		{
			Log.i("HACKNUMPAD","GAME HAS BEEN WON");
			if (info.attacknum == 3)
			{
				Log.i("HACKNUMPAD","TRYING TO ATTACK0");
				if (!info.myPlayer.attack3(-1))
				{
					CharSequence text = "Attack message to server failed";
					int duration = Toast.LENGTH_SHORT;

					Toast toast2 = Toast.makeText(getApplicationContext(), text, duration);
					toast2.show();
				}
			}
			
		}
		else
		{
			Log.i("HACKNUMPAD","GAME LOST BRO");
			//do attack fail
			//info.currentFight.myTurn = false;
			
			try {
				URL u = new URL("http://ctf.awins.info/battle.php?token=" + info.theAuth.getToken() + "&fail=1&target="+info.currentFight.enemyID);
				HttpURLConnection h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				
				if(h.getResponseCode()==200)
				{
					Log.i("Hacknumpad","sending attackfail succeeded");
				}
				else
					Log.i("Hacknumpad","sending attackfail died");
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
		super.onDestroy();
	}
	
	public void scramble()
	{
//		for (int i=0;i<9;i++)
//			buttonArray[i] = false;
		
		boolean buttonArray[] = new boolean[9];
		int count = 0;
		//int i=0;
		bArray.clear();
		while (count<9)
		{
			Integer index = rand.nextInt(9);
			if (buttonArray[index]!=true)
			{
				buttonArray[index]=true;
				buttonSetup(count+1, index);
				count++;	
			}
		}
	}
	
}
