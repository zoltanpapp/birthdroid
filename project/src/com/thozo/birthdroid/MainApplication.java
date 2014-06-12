package com.thozo.birthdroid;

import java.util.Date;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

import android.app.Application;

public class MainApplication extends Application {
  private Birthdays birthdays;

	public void onCreate() {
		BirthdayOpenHelper birthdayOpenHelper = new BirthdayOpenHelper(this);
		birthdays = birthdayOpenHelper.readBirthdays();
		birthdays.addPerson(new Person(
				"evs@google.com", "Nikolay Zherebtsov", new Date(0, 9, 7), null /* photo */));
		birthdays.addPerson(new Person(
				"wittek@google.com", "Thomas Wittek", new Date(0, 2, 22), null /* photo */));
		birthdays.addPerson(new Person(
				"zoltanp@google.com", "Zoltan Papp", new Date(0, 11, 31), null /* photo */));
//		birthdayOpenHelper.storeBirthdays(birthdays);
  }
	
	public Birthdays getBirthdays() {
		return birthdays;
	}
}
