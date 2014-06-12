package com.thozo.birthdroid;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class PeopleListActivity extends Activity {

	private TextView currentDateView;
	private Button addBirthdayButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_list);
		
		currentDateView = (TextView) findViewById(R.id.currentDateView);
		currentDateView.setText("Current date: " + DateFormat.format("yyyy-MM-dd", new Date()));

		addBirthdayButton = (Button) findViewById(R.id.addBirthdayButton);
	}
	
	public void handleAddBirthdayButtonClick() {
//		Intent intent = new Intent(this, EditBirthday.class);
//		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.people_list, menu);
		return true;
	}

}
