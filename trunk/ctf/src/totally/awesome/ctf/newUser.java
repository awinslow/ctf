package totally.awesome.ctf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newUser extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser);
        Button back = (Button)findViewById(R.id.newUserBack);
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
        
        Button submit = (Button)findViewById(R.id.newUserSubmit);
        submit.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				String user, password, email;
            	EditText uname = (EditText) findViewById(R.id.newUserUser);
            	user=uname.getText().toString();
            	EditText pword = (EditText) findViewById(R.id.newUserPassword);
            	password=pword.getText().toString();   
            	EditText e = (EditText) findViewById(R.id.newUserEmail);
            	email=e.getText().toString();
            	EditText cnpword = (EditText) findViewById(R.id.newUserConfirm);
            	if(!password.equals(cnpword.getText().toString())){
    				Context context = getApplicationContext();
    				CharSequence text = "Passwords did not match";
    				int duration = Toast.LENGTH_SHORT;

    				Toast toast = Toast.makeText(context, text, duration);
    				toast.show();
         
            	}else if(password.equals("") || password==null){
    				Context context = getApplicationContext();
    				CharSequence text = "Password can not be blank";
    				int duration = Toast.LENGTH_SHORT;

    				Toast toast = Toast.makeText(context, text, duration);
    				toast.show();           		
            	}else{
            		auth a = new auth(user, password, email);
            		/*if(!a.reset(user, password, newPassword)){
        				Context context = getApplicationContext();
        				CharSequence text = "Error changing password";
        				int duration = Toast.LENGTH_SHORT;

        				Toast toast = Toast.makeText(context, text, duration);
        				toast.show();             			
            		}else{*/
        				Toast toast = Toast.makeText(getApplicationContext(), "User Created",  Toast.LENGTH_SHORT);
        				toast.show();    
        				Intent i = new Intent();
        				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
        				startActivity(i);
            		//}
            	}

				
			}

			
		});
    }
}
