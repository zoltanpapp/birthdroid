package com.thozo.birthdroid;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

public class PeopleListActivity extends Activity {

	private TextView currentDateView;
	private Button addBirthdayButton;

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

		addBirthdayButton = (Button) findViewById(R.id.addBirthdayButton);
	}
	
	public void handleAddBirthdayButtonClick(View view) {
		Intent intent = new Intent(this, EditBirthdayActivity.class);
		startActivity(intent);
	}
}
