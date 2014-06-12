package com.thozo.birthdroid.notifications;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

public class NotifierService extends IntentService {

	public NotifierService() {
		super("BirthdroidNotifier");
	}

	private Notifier notifier;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.notifier = new Notifier(this);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("birthdroid", "Received service intent:" + intent);

		BirthdayOpenHelper helper = new BirthdayOpenHelper(this);
		Birthdays birthdays = helper.readBirthdays();
		for (Person person : birthdays.getPeople()) {
			notifier.notifyUser(person);
		}
	}
}
