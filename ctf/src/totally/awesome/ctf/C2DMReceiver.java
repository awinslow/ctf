package totally.awesome.ctf;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.c2dm.C2DMBaseReceiver;

public class C2DMReceiver extends C2DMBaseReceiver{
    public C2DMReceiver() {
        super("amwinslo@umich.edu");
    }
    @Override
    public void onRegistered(Context context, String registrationId) throws IOException {
        // registrationId will also be saved
        Log.i("GenericNotify", "registered and got key: " + registrationId);
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i("GenericNotifier", "onMessage called");

       Bundle extras = intent.getExtras();
        String message = (String)extras.get("message");

       if(message != null){
          Log.i("GenericNotifier", message);
          Intent i = new Intent("totally.awesome.ctf.HI");
          i.putExtra("message", message);
          sendBroadcast(i);  
       }
    }

	@Override
	public void onError(Context context, String errorId) {
		// TODO Auto-generated method stub
		Log.i("Error", "The shit hit the fan");
	}
}