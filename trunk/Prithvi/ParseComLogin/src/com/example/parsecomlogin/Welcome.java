package com.example.parsecomlogin;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseUser;

@SuppressWarnings("deprecation")
public class Welcome extends ActionBarActivity implements LocationListener {

	Button logout;
	Button emergency;
	GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

        //To get MapFragment reference from xml layout
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        //To get map object
        map = mapFragment.getMap();

        //to show current location in the map
        map.setMyLocationEnabled(true);

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

			@Override
			public void onMapClick(LatLng point) {
                Toast.makeText(getApplicationContext(), point.toString(), Toast.LENGTH_LONG).show();
				
			}
        });

        //To setup location manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //To request location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);

		ParseUser currentUser = ParseUser.getCurrentUser();

		String struser = currentUser.getUsername().toString();

		TextView txtUser = (TextView) findViewById(R.id.txtuser);
		txtUser.setText("You are logged in as " + struser);

		logout = (Button) findViewById(R.id.logout);

		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ParseUser.logOut();
				finish();
			}
		});
		emergency = (Button) findViewById(R.id.emergency);
		emergency.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
	}
	@Override
	public void onLocationChanged(Location location) {

        //To clear map data
        map.clear();

        //To hold location
      LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //To create marker in map
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My Location");
        //adding marker to the map
        map.addMarker(markerOptions);

        //opening position with some zoom level in the map
      map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
    }		
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}