package totally.awesome.ctf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends Activity {
    /** Called when the activity is first created. */
	String user;
	String password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        Button back = (Button)findViewById(R.id.Signinback);
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.main");
				startActivity(i);
			}
		});
        Button signIn = (Button) findViewById(R.id.SigninSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
            	EditText uname = (EditText) findViewById(R.id.EditText01);
            	user=uname.getText().toString();
            	EditText pword = (EditText) findViewById(R.id.EditText02);
            	password=pword.getText().toString();          
            	auth a = new auth(user, password);
            	info.theAuth=a;
				Context context = getApplicationContext();
				CharSequence text = a.getToken();
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		});
        Button cpass = (Button) findViewById(R.id.Signinchange);
        cpass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.changePass");
				startActivity(i);
			}
		});
        
        Button rpass = (Button) findViewById(R.id.Signinforgot);
        rpass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
 
				final AlertDialog.Builder alert = new AlertDialog.Builder(signin.this);
		        final EditText input = new EditText(signin.this);
		        alert.setTitle("Reset Password");
		        alert.setMessage("Please enter the email to registered with to reset your password");
		        alert.setView(input);
		        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		            	auth a = new auth();
		            	if(a.forgot(input.getText().toString())){         	
			                String value = "Password reset email has been sent to you";
			                Toast.makeText(getApplicationContext(), value,
		                        Toast.LENGTH_SHORT).show();
		            	}else{
			                String value = "Error resetting password";
			                Toast.makeText(getApplicationContext(), value,
		                        Toast.LENGTH_SHORT).show();		            		
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
		});

        
    }
}