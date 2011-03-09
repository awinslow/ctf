package totally.awesome.ctf;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
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
	Integer codeArray[] = new Integer[9];
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hacknumpad);
	    counter = 0;
	    //rand = new Random();
	    
	    //Integer codeArray[] = new Integer[9];
	    
	    while (counter<9)
	    {
	    	int value = rand.nextInt(9)+1;
	    	Log.i("VALUE MAY HAVE FUCKED UP","VALUE IS "+value);
	    	codeArray[counter]=value;
	    	counter++;
	    }
	    yourcode = "Input Password: ";
	    code = "Code: ";
	    for (int i=0;i<9;i++)
	    	code+=codeArray[i].toString();
	    
	    codeMsg = (TextView)findViewById(R.id.CodeText);
	    codeMsg.setText(code);
	    
	    //int playerArray[] = new int[9];
	    yourMsg = (TextView)findViewById(R.id.YourText);
	    yourMsg.setText("Input Password: ");
	    
	    counter = 0;
	    
        Button num1 = (Button)findViewById(R.id.button1);
        num1.setText("1");
        num1.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==1)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=1;
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
        
        Button num2 = (Button)findViewById(R.id.button2);
        num2.setText("2");
        num2.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==2)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=2;
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
        
        Button num3 = (Button)findViewById(R.id.button3);
        num3.setText("3");
        num3.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==3)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=3;
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
        
        Button num4 = (Button)findViewById(R.id.button4);
        num4.setText("4");
        num4.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==4)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=4;
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
        
        Button num5 = (Button)findViewById(R.id.button5);
        num5.setText("5");
        num5.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==5)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=5;
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
	    
        Button num6 = (Button)findViewById(R.id.button6);
        num6.setText("6");
        num6.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==6)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=6;
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
        
        Button num7 = (Button)findViewById(R.id.button7);
        num7.setText("7");
        num7.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==7)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=7;
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
        
        Button num8 = (Button)findViewById(R.id.button8);
        num8.setText("8");
        num8.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==8)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=8;
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
        
        Button num9 = (Button)findViewById(R.id.button9);
        num9.setText("9");
        num9.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (codeArray[counter]==9)
				{
					if (counter==8)
					{
						//success
						info.gameTimer.gameDone = true;
						info.gameTimer.gameWon = true;
						finish();
					}
					else
					{
						counter++;
						yourcode+=9;
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
        
	    info.gameTimer = new myTimer(30000, 1000, this);
	    info.gameTimer.timeText = (TextView)findViewById(R.id.thetimer);
	    info.gameTimer.start();

	    
	}
	
	
	public void onDestroy()
	{
		Log.i("HACKNUMPAD","CALLED DESTROY WITH COUNTER = " + counter);
		if (info.gameTimer.gameWon)
		{
			Log.i("HACKNUMPAD","GAME HAS BEEN WON");
			if (info.attacknum == 0)
			{
				Log.i("HACKNUMPAD","TRYING TO ATTACK0");
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
			
		}
		else
		{
			Log.i("HACKNUMPAD","GAME LOST BRO");
			//do attack fail
		}
		super.onDestroy();
	}
	
}
