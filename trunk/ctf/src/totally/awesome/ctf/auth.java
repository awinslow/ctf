package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

import org.apache.http.HttpConnection;

import totally.awesome.ctf.HttpMultipartRequest;

import android.util.Log;

public class auth {
	String token;
	public auth(){/*ONLY USE IF TOKEN ISNT NEEDED */}
	public auth(String user, String pword){
		try{
			String tmp = pword+user;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String hash;
			md.update(tmp.getBytes("UTF-8"));
			byte[] phash = md.digest();
			
		      StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < phash.length; i++) {
		          sb.append(Integer.toString((phash[i] & 0xff) + 0x100, 16).substring(1));
		        }
			
			hash = sb.toString();

	        URL u;
			try {
				u = new URL("http://ctf.awins.info/auth.php?authenticate=1&uname="+user+"&phash="+hash);
				
				HttpURLConnection h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				if(h.getResponseCode()==200){
					BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                        h.getInputStream()));
					String inputLine;
					
					while ((inputLine = in.readLine()) != null) 
					    token=inputLine;
					in.close();
				}
				else{
					Log.i("error", "URL connection errored with code: "+Integer.toString((h.getResponseCode())));
					
				}
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				Log.i("a","errormalform");
			
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.i("a","errorio");
				e1.printStackTrace();
			}
		}catch(NoSuchAlgorithmException a){} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public auth(String user, String pword, String email){
		try{
			String tmp = pword+user;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String hash;
			String r = "bad";
			md.update(tmp.getBytes());
			byte[] phash = md.digest();
		      StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < phash.length; i++) {
		          sb.append(Integer.toString((phash[i] & 0xff) + 0x100, 16).substring(1));
		        }
			
			hash = sb.toString();
	        URL u;
			try {
				u = new URL("http://ctf.awins.info/auth.php?addUser=1&uname="+user+"&phash="+hash+"&email="+email);
				
				HttpURLConnection h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				if(h.getResponseCode()==200){
					BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                        h.getInputStream()));
					String inputLine;
					
					while ((inputLine = in.readLine()) != null) 
					    r=inputLine;
					in.close();
				}
				else{
					Log.i("error", "URL connection errored with code: "+Integer.toString((h.getResponseCode())));
					
				}
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				Log.i("a","error");
			
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.i("a","error");
			
			}		
			if(!r.equals("ok")) Log.i("a","could not create user");
		}catch(NoSuchAlgorithmException a){}
	}
	public String getToken(){
		return token;	
	}	
	
	public boolean logout(){
		String r="bad";
        URL u;
		try {
			u = new URL("http://ctf.awins.info/auth.php?logout=1&token="+token);
			
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if(h.getResponseCode()==200){
				BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        h.getInputStream()));
				String inputLine;
				
				while ((inputLine = in.readLine()) != null) 
				    r=inputLine;
				in.close();
			}
			else{
				Log.i("error", "URL connection errored with code: "+Integer.toString((h.getResponseCode())));
				return false;
				
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			Log.i("a","error");
			return false;
		
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			Log.i("a","error");
			return false;
		}		
		if(!r.equals("ok")) {
			Log.i("a","could not logout");	
			return false;
		}
		return true;
	}
	public boolean forgot(String email){
		String r="bad";
        URL u;
		try {
			u = new URL("http://ctf.awins.info/auth.php?forgot=1&email="+email);
			
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if(h.getResponseCode()==200){
				BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        h.getInputStream()));
				String inputLine;
				
				while ((inputLine = in.readLine()) != null) 
				    r=inputLine;
				in.close();
			}
			else{
				Log.i("error", "URL connection errored with code: "+Integer.toString((h.getResponseCode())));
				return false;
				
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			Log.i("a","error");
			return false;
		
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			Log.i("a","error");
			return false;
		}		
		if(!r.equals("Mail Sent")) {
			Log.i("a","couldnt reset password");	
			return false;
		}
		return true;
	}
	public boolean reset(String user, String pword, String npword){
		String r="bad";
		try {
			String tmp = pword+user;
			String tmp2 = npword+user;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String hash;
			md.update(tmp.getBytes());
			byte[] phash = md.digest();
		      StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < phash.length; i++) {
		          sb.append(Integer.toString((phash[i] & 0xff) + 0x100, 16).substring(1));
		        }
			
			hash = sb.toString();
			
			MessageDigest md2 = MessageDigest.getInstance("SHA-256");
			String nhash;
			md2.update(tmp2.getBytes());
			byte[] nphash = md2.digest();
		      StringBuffer sb1 = new StringBuffer();
		        for (int i = 0; i < nphash.length; i++) {
		          sb1.append(Integer.toString((nphash[i] & 0xff) + 0x100, 16).substring(1));
		        }
			
			nhash = sb1.toString();
			

	        URL u;
		
			u = new URL("http://ctf.awins.info/auth.php?reset=1&uname="+user+"&phash="+hash+"&nphash="+nhash);
			
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if(h.getResponseCode()==200){
				BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        h.getInputStream()));
				String inputLine;
				
				while ((inputLine = in.readLine()) != null) 
				    r=inputLine;
				in.close();
			}
			else{
				Log.i("error", "URL connection errored with code: "+Integer.toString((h.getResponseCode())));
				return false;
				
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			Log.i("a","error");
			return false;
		
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			Log.i("a","error");
			return false;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(!r.equals("ok")) {
			Log.i("a","couldnt change password");	
			Log.i("a",r);	
			return false;
		}
		return true;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setPic(byte[] fileBytes) throws Exception{		 
		Hashtable params = new Hashtable();
		params.put("token", token);
		//params.put("custom_param2", "param_value2");
		 
		HttpMultipartRequest req = new HttpMultipartRequest(
			"http://ctf.awins.info/auth.php?pic=1",
			params,
			"file", "pic.jpg", "image/jpeg", fileBytes
		);
		 
		@SuppressWarnings("unused")
		byte[] response = req.send();
	}
	
}
