package totally.awesome.ctf;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

class InterestingLocations extends ItemizedOverlay<OverlayItem>{
	  
	  private List<OverlayItem> locations = 
	   new ArrayList<OverlayItem>();
	  private Drawable marker;

	  public InterestingLocations(Drawable defaultMarker, 
	    int LatitudeE6, int LongitudeE6) {
	   super(defaultMarker);
	   // TODO Auto-generated constructor stub
	   this.marker=defaultMarker;
	   // create locations of interest
	   GeoPoint myPlace = new GeoPoint(LatitudeE6,LongitudeE6);
	   locations.add(new OverlayItem(myPlace , 
	     "My Place", "My Place"));
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

	  @Override
	  public void draw(Canvas canvas, MapView mapView, 
	    boolean shadow) {
	   // TODO Auto-generated method stub
	   super.draw(canvas, mapView, shadow);
	   
	   boundCenterBottom(marker);
	  }
	 }

public class adventure extends MapActivity{
	 public void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.adventure); 
	        LinearLayout linearLayout;
	        MapView mapView;
	        mapView = (MapView) findViewById(R.id.mapview);
	        mapView.setBuiltInZoomControls(true);
	        mapView.setSatellite(true);
	        MapController myMapController;
	        myMapController = mapView.getController(); 
	        myMapController.animateTo(new GeoPoint(42287318,-83701588));
	        myMapController.setZoom(19);

	        Drawable marker=getResources().getDrawable(
	          android.R.drawable.ic_menu_myplaces);
	        marker.setBounds(0, 0, marker.getIntrinsicWidth(), 
	          marker.getIntrinsicHeight());
	        mapView.getOverlays().add(new InterestingLocations(marker, 
	        		42287318, -83701588));
	       

	 }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
