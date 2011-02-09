package totally.awesome.ctf;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MyIntentReceiver extends BroadcastReceiver {
	/**
	* @see adroid.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	*/

	@Override
	public void onReceive(final Context context, Intent intent) {
		//Context context2 = Context.getApplicationContext();
		final CharSequence text = intent.getStringExtra("message");
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();   	
		
		if(text.subSequence(0, 7).equals("battle.")){
			if(text.subSequence(7, 8).equals("a")){
	//			CharSequence text1 = "You are now in battle";
	//			int duration1 = Toast.LENGTH_SHORT;

				Toast toast2 = Toast.makeText(context, "You are now in battle", Toast.LENGTH_SHORT);
				toast2.show();				
			}else{
				
				final AlertDialog.Builder alert = new AlertDialog.Builder(context);
		       // final EditText input = new EditText(context);
		        alert.setTitle("Battle Challenge");
		        alert.setMessage(text.subSequence(7, text.length())+" Has challenged you to battle! Accept?");
		      //  alert.setView(input);
		        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
						URL u;
		    			try {
		    				u = new URL("http://ctf.awins.info/battle.php?accept=1&target="+text.subSequence(7, text.length())+"&token="+info.theAuth.getToken());
		    				
		    				HttpURLConnection h = (HttpURLConnection) u.openConnection();
		    				h.setRequestMethod("GET");
		    				h.connect();
		    				if(h.getResponseCode()==200){
			    				//Context context = message.this;
			    				CharSequence text = "You are now in battle";
			    				int duration = Toast.LENGTH_SHORT;
			
			    				Toast toast2 = Toast.makeText(context, text, duration);
			    				toast2.show();
		    				}
		    				else{
			    				//Context context = getApplicationContext();
			    				CharSequence text = "Error";
			    				int duration = Toast.LENGTH_SHORT;
			
			    				Toast toast2 = Toast.makeText(context, text, duration);
			    				toast2.show();   					
		    					
		    				}
		    				Log.i("a", new String(Integer.toString(h.getResponseCode())));
		    	            Log.i("a","opened");
		    	            //Log.i("a","http://141.212.113.248/c2dm.php?register=1&rid="+registrationId+"&a="+id);
		    			} catch (MalformedURLException e1) {
		    				// TODO Auto-generated catch block
		    				Log.i("a","error");
		    				e1.printStackTrace();
		    			}catch (IOException e1) {
		    				// TODO Auto-generated catch block
		    				Log.i("a","error");
		    				e1.printStackTrace();
		    			}
		            }
		        });
	
		        alert.setNegativeButton("Cancel",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int whichButton) {
		                        dialog.cancel();
		                    }
		                });
		        alert.show();		
			}
		}
	}
}