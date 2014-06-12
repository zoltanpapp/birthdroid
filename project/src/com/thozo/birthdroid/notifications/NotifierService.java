package com.thozo.birthdroid.notifications;

import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

public class NotifierService extends IntentService {

	/**
	 * Helper to trigger the service. Call this on updates.
	 */
	public static void trigger(Context context) {
		Intent serviceIntent = new Intent(context, NotifierService.class);
		context.startService(serviceIntent);
	}

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
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("birthdroid", "Received service intent:" + intent);

		BirthdayOpenHelper helper = new BirthdayOpenHelper(this);
		Birthdays birthdays = helper.readBirthdays();
		for (Person person : birthdays.getPersons()) {
			if (shouldNotify(person)) {
				Log.i("birthdroid", person.name + " has birthday today");
				notifier.notifyUser(person);
			} else {
				Log.i("birthdroid", person.name + " will not be notified.");
			}
		}

		scheduleAlarm();
	}

	private boolean shouldNotify(Person person) {
		Date now = new Date(System.currentTimeMillis());
		Log.i("birthdroid", "now: " + now);
		Log.i("birthdroid", person.name + "bday=" + person.birthday + "; month=" + person.birthday.getMonth() + "; day=" + person.birthday.getDay());
		return person.birthday.getMonth() == now.getMonth()
				&& person.birthday.getDate() == now.getDate();
	}

	private void scheduleAlarm() {
		Intent startServiceIntent = new Intent(this, AlarmReceiver.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, startServiceIntent, 0);

		// Set the alarm to start at 9:00 a.m.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 0);

		AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmMgr.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
		        1000 * 60 * 60 * 24, // repeat every day
		        alarmIntent);
	}
}
