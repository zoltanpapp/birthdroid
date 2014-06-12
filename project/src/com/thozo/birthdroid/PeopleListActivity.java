package com.thozo.birthdroid;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.notifications.NotifierService;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

public class PeopleListActivity extends Activity {

	private TextView currentDateView;
	private Button addBirthdayButton;
	private ListView birthdayListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_list);

		BirthdayOpenHelper birthdayOpenHelper = new BirthdayOpenHelper(this);
		Birthdays birthdays = birthdayOpenHelper.readBirthdays();
//		birthdays.putPerson(new Person("Nikolay Zherebtsov", new Date(0, 9, 7)));
//		birthdays.putPerson(new Person("Zoltan Papp", new Date(0, 11, 31)));
//		birthdays.putPerson(new Person("Thomas Wittek", new Date(0, 2, 22)));
//		birthdayOpenHelper.storeBirthdays(birthdays);
				
		currentDateView = (TextView) findViewById(R.id.currentDateView);
		currentDateView.setText("Current date: " + DateFormat.format("yyyy-MM-dd", new Date()));

		birthdayListView = (ListView) findViewById(R.id.birthdayListView);
		birthdayListView.setAdapter(new BirthdayListAdapter(this, birthdays));
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		

	}
	
	public void handleAddBirthdayButtonClick(View view) {
		Intent intent = new Intent(this, EditBirthdayActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.people_list, menu);
		return true;
	}

}
