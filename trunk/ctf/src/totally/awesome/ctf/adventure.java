package totally.awesome.ctf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

class InterestingLocations extends ItemizedOverlay<OverlayItem>{
	  
	  private List<OverlayItem> locations = 
	   new ArrayList<OverlayItem>();
	  private Drawable marker;
	  Context mContext;

	  public InterestingLocations(Drawable defaultMarker) {
		  super(boundCenterBottom(defaultMarker));
	  }
	  public InterestingLocations(Drawable defaultMarker, Context context) {
		  super(boundCenterBottom(defaultMarker));
		  mContext = context;
		}

	  @Override
	  protected boolean onTap(final int index) {
	    final OverlayItem item = locations.get(index);
	    /*AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	    dialog.setTitle(item.getTitle());
	    dialog.setMessage(item.getSnippet());
	    dialog.show();*/
	    if(item.getSnippet().contains("item")){
	    	String name = "that shouldnt happen";
	    	String description = "that shouldnt happen";
	    	HttpURLConnection h;
			try {
				URL u = new URL("http://ctf.awins.info/item.php?red=1&token="+info.theAuth.getToken()+"&stats=1&iid="+item.getTitle());
				//Log.i("UPDATING LOCATION", "http://ctf.awins.info/checkin.php?&token="+info.theAuth.getToken()+"&lat="+locationManager.getLastKnownLocation(provider).getLatitude()+"&lon"+locationManager.getLastKnownLocation(provider).getLongitude());
				h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				if(h.getResponseCode()==200){
					BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                        h.getInputStream()));
					String inputLine;
					
					while ((inputLine = in.readLine()) != null){
						name = inputLine;
						description = in.readLine();
					}
					    
					in.close();
					Log.i("LOCATION", "update good");
				}
				else{

					Log.i("LOCATION", "update failed");
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
 
		    
			final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
	        alert.setTitle("Pickup Item");
	        alert.setMessage("Would you like to pick up the "+name+ " which "+description+"?");
	        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	Log.i("getItem", item.getSnippet());
	            	int latStart = item.getSnippet().indexOf("lat:");
	            	int lonStart = item.getSnippet().indexOf("lon:");
	            	int latEnd = item.getSnippet().indexOf(" ", latStart);
	            	//int lonEnd = item.getSnippet().indexOf(" ", lonStart);
	            	String lat = item.getSnippet().substring(latStart+4, latEnd);
	            	Log.i("getItem", lat);
	            	String lon = item.getSnippet().substring(lonStart+4);
	            	
	            	
	            	Log.i("getItem", lon);
	            	
	    	    	HttpURLConnection h;
	    			try {
	    				URL u = new URL("http://ctf.awins.info/item.php?red=1&token="+info.theAuth.getToken()+"&pickup=1&iid="+item.getTitle()+"&lat="+lat+"&lon="+lon);
	    				Log.i("ITEM SHIT", "http://ctf.awins.info/item.php?red=1&token="+info.theAuth.getToken()+"&pickup=1&iid="+item.getTitle()+"&lat="+lat+"&lon="+lon);
	    				h = (HttpURLConnection) u.openConnection();
	    				h.setRequestMethod("GET");
	    				h.connect();
	    				if(h.getResponseCode()==200){

	    					removeOverlay(index);
	    					Log.i("LOCATION", "update good");
	    				}
	    				else{

	    					Log.i("LOCATION", "update failed");
	    					
	    				}
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	            }
	        });

	        alert.setNegativeButton("No",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                        dialog.cancel();
	                    }
	                });
	        alert.show();	
		    
	    }else{
		    info.checkingOutPlayer = Integer.parseInt(item.getTitle());
			Intent i = new Intent();
			i.setClassName("totally.awesome.ctf", "totally.awesome.ctf.adventureperson");
			mContext.startActivity(i);
	    }
	    return true;
	  }
	  
	  public void addOverlay(OverlayItem overlay) {
		  locations.add(overlay);
		  populate();
		}
	  
	  public void removeOverlay(int location){
		  locations.remove(location);
		  populate();
	  }
	  
	  @Override
	  protected OverlayItem createItem(int i) {
	   // TODO Auto-generated method stub
	   return locations.get(i);
	  }

	  @Override
	  public int size() {
	   // TODO Auto-generated method stub
	   return locations.size();
	  }

	/*  @Override
	  public void draw(Canvas canvas, MapView mapView, 
	    boolean shadow) {
	   // TODO Auto-generated method stub
	   super.draw(canvas, mapView, shadow);
	   
	   boundCenterBottom(marker);
	  }*/
	 }

public class adventure extends MapActivity{
	MyIntentReceiver intentReceiver;	
	
	public void onPause(){
		super.onPause();
		unregisterReceiver(intentReceiver);
	}
	
	public void onResume(){
		super.onResume();
		info.inMatchMaking=false;
        intentReceiver = new MyIntentReceiver();
        IntentFilter intentFilter = new IntentFilter("totally.awesome.ctf.HI");
        intentFilter.setPriority(1);

        registerReceiver(intentReceiver, intentFilter); 
	}
	
	
	 public void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        super.onCreate(savedInstanceState);
	        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	        String provider = locationManager.getBestProvider(new Criteria(), false);
	        setContentView(R.layout.adventure); 
	      // LinearLayout linearLayout;
	        MapView mapView;
	        mapView = (MapView) findViewById(R.id.mapview);
	        mapView.setBuiltInZoomControls(true);
	        mapView.setSatellite(true);
	        final MapController myMapController;
	        myMapController = mapView.getController(); 
	        if (provider != null && locationManager != null && locationManager.getLastKnownLocation(provider) != null) myMapController.animateTo(new GeoPoint((int) (1000000*locationManager.getLastKnownLocation(provider).getLatitude()),(int) (1000000*locationManager.getLastKnownLocation(provider).getLongitude())));
	        myMapController.setZoom(19);

	        List<Overlay> mapOverlays = mapView.getOverlays();
	        Drawable drawable = this.getResources().getDrawable(R.drawable.red);
	        InterestingLocations itemizedoverlay = new InterestingLocations(drawable, this);	 
	        Drawable drawable2 = this.getResources().getDrawable(R.drawable.yellow);
	        InterestingLocations itemsOverlay = new InterestingLocations(drawable2, this);	
	        Drawable drawable3 = this.getResources().getDrawable(R.drawable.green);
	        InterestingLocations close = new InterestingLocations(drawable3, this);	
			HttpURLConnection h;
			
			try {
				URL u = new URL("http://ctf.awins.info/checkin.php?red=1&token="+info.theAuth.getToken()+"&lat="+locationManager.getLastKnownLocation(provider).getLatitude()+"&lon="+locationManager.getLastKnownLocation(provider).getLongitude());
				Log.i("UPDATING LOCATION", "http://ctf.awins.info/checkin.php?&token="+info.theAuth.getToken()+"&lat="+locationManager.getLastKnownLocation(provider).getLatitude()+"&lon"+locationManager.getLastKnownLocation(provider).getLongitude());
				h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				if(h.getResponseCode()==200){
					BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                        h.getInputStream()));
					String inputLine;
					
					while ((inputLine = in.readLine()) != null){
						String name = inputLine;
						inputLine = in.readLine();
						int lat = (int) (Float.parseFloat(inputLine.substring(5))*1000000); //(degrees * 1E6)
						Log.i("LOCATION", "lat: "+lat);
						inputLine = in.readLine();
						int lon = (int) (Float.parseFloat(inputLine.substring(5))*1000000); //(degrees * 1E6)
						Log.i("LOCATION", "lon: "+lon);
						close.addOverlay(new OverlayItem(new GeoPoint(lat, lon), name.substring(5), "FINISH HIM!!!"));
					}
					    
					in.close();
					Log.i("LOCATION", "update good");
				}
				else{

					Log.i("LOCATION", "update failed");
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				URL u = new URL("http://ctf.awins.info/checkin.php?&token="+info.theAuth.getToken()+"&lat="+locationManager.getLastKnownLocation(provider).getLatitude()+"&lon="+locationManager.getLastKnownLocation(provider).getLongitude());
				Log.i("UPDATING LOCATION", "http://ctf.awins.info/checkin.php?&token="+info.theAuth.getToken()+"&lat="+locationManager.getLastKnownLocation(provider).getLatitude()+"&lon"+locationManager.getLastKnownLocation(provider).getLongitude());
				h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				if(h.getResponseCode()==200){
					BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                        h.getInputStream()));
					String inputLine;
					
					while ((inputLine = in.readLine()) != null){
						String name = inputLine;
						inputLine = in.readLine();
						int lat = (int) (Float.parseFloat(inputLine.substring(5))*1000000); //(degrees * 1E6)
						Log.i("LOCATION", "lat: "+lat);
						inputLine = in.readLine();
						int lon = (int) (Float.parseFloat(inputLine.substring(5))*1000000); //(degrees * 1E6)
						Log.i("LOCATION", "lon: "+lon);
						itemizedoverlay.addOverlay(new OverlayItem(new GeoPoint(lat, lon), name.substring(5), "FINISH HIM!!!"));
					}
					    
					in.close();
					Log.i("LOCATION", "update good");
				}
				else{

					Log.i("LOCATION", "update failed");
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//HttpURLConnection h;
			try {
				URL u = new URL("http://ctf.awins.info/item.php?&token="+info.theAuth.getToken()+"&list=1");
				//Log.i("UPDATING LOCATION", "http://ctf.awins.info/checkin.php?&token="+info.theAuth.getToken()+"&lat="+locationManager.getLastKnownLocation(provider).getLatitude()+"&lon"+locationManager.getLastKnownLocation(provider).getLongitude());
				h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
				if(h.getResponseCode()==200){
					BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                        h.getInputStream()));
					String inputLine;
					
					while ((inputLine = in.readLine()) != null){
						Log.i("adding item", inputLine);
						String la, lo;
						String name = inputLine;
						inputLine = in.readLine();
						la = inputLine;
						int lat = (int) (Float.parseFloat(inputLine.substring(5))*1000000); //(degrees * 1E6)
						Log.i("LOCATION", "lat: "+lat);
						inputLine = in.readLine();
						lo = inputLine;
						int lon = (int) (Float.parseFloat(inputLine.substring(5))*1000000); //(degrees * 1E6)
						Log.i("LOCATION", "lon: "+lon);
						itemsOverlay.addOverlay(new OverlayItem(new GeoPoint(lat, lon), name.substring(5), "item"+" lat:"+la.substring(5)+ " lon:"+lo.substring(5)));
					}
					    
					in.close();
					Log.i("LOCATION", "update good");
				}
				else{

					Log.i("LOCATION", "update failed");
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			final MyLocationOverlay myLocation = new MyLocationOverlay(this, mapView);
			mapOverlays.add(myLocation);
			if (itemizedoverlay.size() != 0) mapOverlays.add(itemizedoverlay);
	        if (itemsOverlay.size() != 0) mapOverlays.add(itemsOverlay);
	        if (close.size() != 0) mapOverlays.add(close);
	        myLocation.enableCompass();
	        myLocation.enableMyLocation();
	        myLocation.runOnFirstFix(new Runnable() {
	            public void run() {
	            	myMapController.animateTo(myLocation.getMyLocation());
	            }
	        });
	 }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
