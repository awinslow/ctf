package totally.awesome.ctf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class classes extends Activity 
{
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classes);
        Button soldier = (Button)findViewById(R.id.soldier);
        Button sentinel = (Button)findViewById(R.id.sentinel);
        Button hacker = (Button)findViewById(R.id.hacker);
        soldier.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{
				// TODO set class
				
				Toast toast = Toast.makeText(getApplicationContext(), "User Created",  Toast.LENGTH_SHORT);
				toast.show();
				
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
				
				finish();
			}
        });
        sentinel.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{
				// TODO set class
				
				Toast toast = Toast.makeText(getApplicationContext(), "User Created",  Toast.LENGTH_SHORT);
				toast.show();
				
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
				
				finish();
			}
        });
        hacker.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{
				// TODO set class
				Toast toast = Toast.makeText(getApplicationContext(), "User Created",  Toast.LENGTH_SHORT);
				toast.show();
				
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
				
				finish();
			}
        });        
    }
}
