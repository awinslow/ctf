package totally.awesome.ctf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class select extends Activity{
	//MyIntentReceiver intentReceiver;
	
	String selectedImagePath;
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//info.loading.dismiss();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        //intentReceiver = new MyIntentReceiver();
        //IntentFilter intentFilter = new IntentFilter("totally.awesome.ctf.HI");
        //intentFilter.setPriority(1);

        //registerReceiver(intentReceiver, intentFilter); 
        
        Button battle = (Button)findViewById(R.id.battleMode); //id is button 1
        battle.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.Arena");
				startActivity(i);

				//finish();
			}
		});
        
        Button adventure = (Button)findViewById(R.id.adventureMode); //id is button 1
        adventure.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.adventure");
				startActivity(i);

				//finish();
			}
		});
        
        Button miniG = (Button)findViewById(R.id.minigames);
        miniG.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
				Intent i = new Intent();
				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.minigames");
				startActivity(i);

				//finish();
			}
		});
        
        
		AlertDialog.Builder builder = new AlertDialog.Builder(select.this);
		builder.setMessage("How would you like to set your picture?");
		builder.setPositiveButton("Take a picture!", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
				//switch intents
					Intent i = new Intent();
					i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.myCamera");
					startActivity(i);
					
				}
			});
		builder.setNeutralButton("Use a picture!", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			//switch intents
//				Intent i = new Intent();
//				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.selectPic");
//				startActivity(i);
				
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), 1);

			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		final AlertDialog alert = builder.create();
        
        Button pict = (Button)findViewById(R.id.pict);
        pict.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//requestWindowFeature(Window.FEATURE_NO_TITLE);
//				Intent i = new Intent();
//				i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.minigames");
//				startActivity(i);
//
//				finish();
				alert.show();
				

			}
		});
        
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	        if (requestCode == 1) {
	            Uri selectedImageUri = data.getData();
	            //selectedImagePath = getPath(selectedImageUri);
	            selectedImagePath = selectedImageUri.getPath();
	            
	            BitmapFactory.Options options = new BitmapFactory.Options();
	            options.inSampleSize = 8;
	            
	            AssetFileDescriptor afd;
	            
	            Bitmap bitmap = null;
	            try {
	            	
	            	ExifInterface exif = new ExifInterface(selectedImagePath);
	            	String orient = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
	            	Log.i("SELECT","orient is "+orient);
	            	
	            	
	            	afd = this.getContentResolver().openAssetFileDescriptor(selectedImageUri, "r");
	            	
					//Bitmap bitmap = Media.getBitmap(getContentResolver(), selectedImageUri);
					bitmap = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor(), null, options);
	            	afd.close();
	            	
	            	Matrix m = new Matrix();
	            	if (orient == "portrait")
	            		m.postRotate(90.0f);
	            	
					Bitmap fixed = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					fixed.compress(Bitmap.CompressFormat.JPEG, 40, baos);
					
					
					
					byte[] b = baos.toByteArray();
					info.theAuth.setPic(b);
					info.setPic(info.theAuth.id, false);
					
					//bitmap = null;
					//fixed = null;
					
	            } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}

	public String getPath(Uri uri) {
	    String[] projection = { MediaStore.Images.Media.DATA };
	    Cursor cursor = managedQuery(uri, projection, null, null, null);
	    int column_index = cursor
	            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}

	
}
  