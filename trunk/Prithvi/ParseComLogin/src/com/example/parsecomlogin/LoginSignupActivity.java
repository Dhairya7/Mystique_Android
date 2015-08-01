package com.example.parsecomlogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class LoginSignupActivity extends Activity implements
		LocationListener {

	Button loginButton;
	Button signupButton;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;
	LocationManager lm;
	String provider;
	Location l;
	ParseObject parseObject;
	ParseGeoPoint geoPoint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_signup);

		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		loginButton = (Button) findViewById(R.id.login);
		signupButton = (Button) findViewById(R.id.signup);

		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();
				parseObject = new ParseObject(usernametxt);
				parseObject.put("Location", geoPoint);
				parseObject.saveInBackground();
				ParseUser.logInInBackground(usernametxt, passwordtxt,
						new LogInCallback() {

							@Override
							public void done(ParseUser user, ParseException e) {
								if (user != null) {
									
									Intent intent = new Intent(
											LoginSignupActivity.this,
											Welcome.class);
									startActivity(intent);
									finish();

								} else {

									Toast.makeText(
											getApplicationContext(),
											"This user doesn't exist. Please signup",
											Toast.LENGTH_SHORT).show();
								}
							}
						});
			}
		});

		signupButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();

				if (usernametxt.equals("") && passwordtxt.equals("")) {

					Toast.makeText(getApplicationContext(),
							"Please complete the sign up form",
							Toast.LENGTH_SHORT).show();

				} else {

					ParseUser user = new ParseUser();
					user.setUsername(usernametxt);
					user.setPassword(passwordtxt);
					parseObject = new ParseObject(usernametxt);
					parseObject.put("Location", geoPoint);
					parseObject.saveInBackground();
					user.signUpInBackground(new SignUpCallback() {

						@Override
						public void done(ParseException e) {
							if (e == null) {
								
								Toast.makeText(getApplicationContext(),
										"Successfully Signed up!",
										Toast.LENGTH_SHORT).show();

							} else {

								Toast.makeText(getApplicationContext(),
										"Sign up error", Toast.LENGTH_SHORT)
										.show();

							}
						}
					});

				}
			}
		});
		// code snippet to fetch location and store it in database
		lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		Criteria c = new Criteria();
		// criteria object will select best service based on
		// Accuracy, power consumption, response, bearing and monetary cost
		// set false to use best service otherwise it will select the default
		// Sim network
		// and give the location based on sim network
		// now it will first check satellite than Internet than Sim network
		// location
		provider = lm.getBestProvider(c, false);
		// now you have best provider
		// get location
		l = lm.getLastKnownLocation(provider);
		if (l != null) {
			// get latitude and longitude of the location
			double lng = l.getLongitude();
			double lat = l.getLatitude();
			geoPoint = new ParseGeoPoint(lat, lng);
			
		} else {

		}
	}

	// If you want location on changing place also than use below method
	// otherwise remove all below methods and don't implement location listener
	@Override
	public void onLocationChanged(Location arg0) {
		double lng = l.getLongitude();
		double lat = l.getLatitude();
		geoPoint = new ParseGeoPoint(lat, lng);

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
	}
}
