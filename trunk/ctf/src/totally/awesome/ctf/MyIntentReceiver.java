package totally.awesome.ctf;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyIntentReceiver extends BroadcastReceiver {
	/**
	* @see adroid.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	*/

	@Override
	public void onReceive(Context context, Intent intent) {
Log.i("aaa","aaaaaaaaaaaaaaaaaa");



//Context context2 = Context.getApplicationContext();
CharSequence text = intent.getStringExtra("message");
int duration = Toast.LENGTH_SHORT;

Toast toast = Toast.makeText(context, text, duration);
toast.show();   	
	}
}