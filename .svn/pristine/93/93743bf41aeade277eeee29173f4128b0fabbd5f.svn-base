package com.example.parsecomlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ParseAnalytics.trackAppOpened(getIntent());

		  // inform the Parse Cloud that it is ready for notifications 
        PushService.setDefaultPushCallback(this, MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();   
		if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
			
			Intent intent = new Intent(MainActivity.this, LoginSignupActivity.class);
			startActivity(intent);
			finish();
			
		} else {
			
			ParseUser currentUser = ParseUser.getCurrentUser();
			
			if(currentUser != null) {
				
				Intent intent = new Intent(MainActivity.this, Welcome.class);
				startActivity(intent);
				finish();
				
			} else {
				
				Intent intent = new Intent(MainActivity.this, LoginSignupActivity.class);
				startActivity(intent);
				finish();
				
			}
			
		}
	}
}