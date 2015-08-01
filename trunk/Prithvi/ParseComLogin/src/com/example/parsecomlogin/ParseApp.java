package com.example.parsecomlogin;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApp extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Parse.initialize(this, "lcMmJJsLlnQJ6xqpYQ2HBx4NUQzovkyvyGvgbiFm",
				"a6MH4tJII87qbD8BdtercxIcP8oQCjtSqveFTTve");
		
		ParseUser.enableAutomaticUser();
		ParseACL defauAcl = new ParseACL();
		
		defauAcl.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defauAcl, true);
	}
}