package totally.awesome.ctf;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changePass extends Activity {
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepword);
        Button back = (Button)findViewById(R.id.ChangepassBack);
        back.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
				startActivity(i);
				
				finish();
			}
		});
        Button submit = (Button)findViewById(R.id.ChangepassSubmit);
        submit.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				String user, password, newPassword;
            	EditText uname = (EditText) findViewById(R.id.ChangepassUname);
            	user=uname.getText().toString();
            	EditText pword = (EditText) findViewById(R.id.ChangepassOldPass);
            	password=pword.getText().toString();   
            	EditText npword = (EditText) findViewById(R.id.ChangepassNewPass);
            	newPassword=npword.getText().toString();
            	EditText cnpword = (EditText) findViewById(R.id.ChangepassConfirmPass);
            	if(!newPassword.equals(cnpword.getText().toString())){
    				Context context = getApplicationContext();
    				CharSequence text = "Passwords did not match";
    				int duration = Toast.LENGTH_SHORT;

    				Toast toast = Toast.makeText(context, text, duration);
    				toast.show();
         
            	}else if(newPassword.equals("") || newPassword==null){
    				Context context = getApplicationContext();
    				CharSequence text = "Password can not be blank";
    				int duration = Toast.LENGTH_SHORT;

    				Toast toast = Toast.makeText(context, text, duration);
    				toast.show();           		
            	}else{
            		auth a = new auth();
            		if(!a.reset(user, password, newPassword)){
        				Context context = getApplicationContext();
        				CharSequence text = "Error changing password";
        				int duration = Toast.LENGTH_SHORT;

        				Toast toast = Toast.makeText(context, text, duration);
        				toast.show();             			
            		}else{
        				Toast toast = Toast.makeText(getApplicationContext(), "Password changed",  Toast.LENGTH_SHORT);
        				toast.show();    
        				Intent i = new Intent();
        				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.signin");
        				startActivity(i);
        				
        				finish();
            		}
            	}

				
			}
		});
    }
}
