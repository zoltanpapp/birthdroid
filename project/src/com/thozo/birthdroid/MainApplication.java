package com.thozo.birthdroid;

import android.app.Application;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

public class MainApplication extends Application {
  private Birthdays birthdays;
  private BirthdayOpenHelper birthdayOpenHelper;

	public void onCreate() {
		birthdayOpenHelper = new BirthdayOpenHelper(this);
		birthdays = birthdayOpenHelper.readBirthdays();
  }
	
	public Birthdays getBirthdays() {
		return birthdays;
	}
	
	public void storeBirthdays() {
		birthdayOpenHelper.storeBirthdays(birthdays);
	}
}
