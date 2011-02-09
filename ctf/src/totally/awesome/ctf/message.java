package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.util.Log;

public class message {
	
	static void challenge(String uid){
		
	}
	
	static void sendMessage(String id, String message){
		try {
		    // Construct data
		    String data = URLEncoder.encode("collapse_key", "UTF-8") + "=" + URLEncoder.encode("testing", "UTF-8");
		    data += "&" + URLEncoder.encode("registration_id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
		    data += "&" + URLEncoder.encode("data.message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
			
		    // Send data
		    URL url = new URL("https://android.apis.google.com/c2dm/send");
		    URLConnection conn = url.openConnection();
		    conn.addRequestProperty("Authorization", 
		        "GoogleLogin auth=DQAAAJMAAABE6aDCDN4tvwNB8mimMioivKNWRUDufp2GjIavRcssVB0Lqj4THFUhB1ZQbNyGWP371zBvY76LCpF4wkXTg5wtppt8eWjLGbW4WpzwqXsdI47wbtKOCiUfhNrpc-uboHhfHXJrMTlPqpLx0F5npNxq_Bzj2pvM8n3PMYNd5dbKgKj6EIsBa2LQR5bHRBiuIqS12SbQqQ4NgVvR0WZc9Jlz");
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();

		    // Get the response
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		        Log.i("message from c2dm sending", line);
		    }
		    wr.close();
		    rd.close();
		} catch (Exception e) {
		}
	}
}
