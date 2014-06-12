package com.thozo.birthdroid;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

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
		if (name == null) {
			Toast.makeText(this, "Could not trigger service!", Toast.LENGTH_LONG).show();
		}

		// Forward to the PeopleListActivity.
		Intent intent = new Intent(this, PeopleListActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}
