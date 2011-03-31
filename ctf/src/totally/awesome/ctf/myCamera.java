package totally.awesome.ctf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class myCamera extends Activity {
	
		private static final String TAG = "CameraDemo";
		Camera camera;
		Preview preview;
		Button buttonClick;
		String thisPic;
		
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.mycam);

			preview = new Preview(this);
			((FrameLayout) findViewById(R.id.preview)).addView(preview);

			buttonClick = (Button) findViewById(R.id.buttonClick);
			buttonClick.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					preview.camera.takePicture(shutterCallback, rawCallback,
							jpegCallback);
				}
			});

			Button use = (Button) findViewById(R.id.usepic);
			use.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					URL u;
					try {
						u = new URL("http://www.google.com");
//						u = new URL("http://ctf.awins.info/auth=thisPic);
//						//u = new URL("http://ctf.awins.info/chooseClass.php?id=" + info.theAuth.id + "&class=" + Integer.toString(classID));
//						
//						Log.i("Create", "URL: " + "http://ctf.awins.info/chooseClass.php?id=" + info.theAuth.id + "&class=" + Integer.toString(classID));
//						HttpURLConnection h = (HttpURLConnection) u.openConnection();
//						h.setRequestMethod("GET");
//						h.connect();
//						if(h.getResponseCode()==200){
//							Log.i("Create", "Response code 200!");
//							
//							BufferedReader in = new BufferedReader(
//			                        new InputStreamReader(
//			                        h.getInputStream()));
//							String inClassResponse = in.readLine();
//						}
							Log.i("myCamera", "Use Pic");
							
							//assumes it works because reading from server failing
					}catch(MalformedURLException e1)
					{
						Log.i("myCamera", "Malformed URL Exception: " + e1);
					}
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						Log.i("myCamera", "IO Exception: " + e);
//					}
					
					
					
					info.setPic(info.theAuth.id, false);
					Intent i = new Intent();
					i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
					startActivity(i);
				}
			});
			
			Button back = (Button) findViewById(R.id.back);
			back.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent();
					i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.select");
					startActivity(i);
				}
			});

			Log.d(TAG, "onCreate'd");
		}
		
		

		ShutterCallback shutterCallback = new ShutterCallback() {
			public void onShutter() {
				Log.d(TAG, "onShutter'd");
			}
		};

		/** Handles data for raw picture */
		PictureCallback rawCallback = new PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				Log.d(TAG, "onPictureTaken - raw");
			}
		};

		/** Handles data for jpeg picture */
		PictureCallback jpegCallback = new PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				FileOutputStream outStream = null;
				try {
					// write to local sandbox file system
					// outStream =
					// CameraDemo.this.openFileOutput(String.format("%d.jpg",
					// System.currentTimeMillis()), 0);
					// Or write to sdcard
//					outStream = new FileOutputStream(String.format(
//							"/sdcard/%d.jpg", System.currentTimeMillis()));
					thisPic = String.format("%d.jpg",System.currentTimeMillis());
					outStream =
					myCamera.this.openFileOutput(thisPic, 0);
					
					outStream.write(data);
					outStream.close();
					Log.i(TAG, "onPictureTaken - wrote bytes: " + data.length);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
				}
				Log.i(TAG, "onPictureTaken - jpeg");
			}

		};	
	
	
}
