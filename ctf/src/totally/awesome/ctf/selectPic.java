package totally.awesome.ctf;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class selectPic extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.selectpic);
    	
    	
	}
	
}
