package com.thozo.birthdroid;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.thozo.birthdroid.notifications.NotifierService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		// Initial trigger of the service.
		Intent startServiceIntent = new Intent(this, NotifierService.class);
		ComponentName name = startService(startServiceIntent);
		Log.i("birthdroid", "name: " + name);

		
		// Forward to the PeopleListActivity.
		Intent intent = new Intent(this, PeopleListActivity.class);
		startActivity(intent);
	}
	
}