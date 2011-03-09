package totally.awesome.ctf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class minigames extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.minigames);
	    
	    Log.i("DAMNIT","QUIT FUCKING UP");
	    
	    Button HackNumPad = (Button)findViewById(R.id.hnumpad);
	    HackNumPad.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.hacknumpad");
				startActivity(i);

				//finish();
			}
		});
	}
}
