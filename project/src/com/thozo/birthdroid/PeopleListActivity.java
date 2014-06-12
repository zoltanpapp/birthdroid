package com.thozo.birthdroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

public class PeopleListActivity extends Activity {

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.people_list, menu);
		return true;
	}

}
