package com.thozo.birthdroid.notifications;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.thozo.birthdroid.PeopleListActivity;
import com.thozo.birthdroid.R;
import com.thozo.birthdroid.model.Person;

public class Notifier {

	private static final int BIRTHDROID = 0;

	private Context ctx;
	private List<Person> persons;
	
	Notifier(Context ctx) {
		this.ctx = ctx;
		this.persons = new ArrayList<Person>();
	}

	public void addPerson(Person person) {
		persons.add(person);
	}
	
	public void showNotifications() {
		if (persons.isEmpty()) {
			return;
		}

		Intent intent = new Intent(ctx, PeopleListActivity.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(ctx,
				BIRTHDROID /* requestCode */,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder notification;
		if (persons.size() == 1) {
			notification = onePersonNotification();
		} else {
			notification = manyPersonsNotification();
		}
		notification.setContentIntent(pendingIntent);

		NotificationManager notificationMgr = 
		        (NotificationManager) ctx.getSystemService(Activity.NOTIFICATION_SERVICE);
		// Builds the notification and issues it.
		notificationMgr.notify(BIRTHDROID, notification.build());
	}

	private NotificationCompat.Builder manyPersonsNotification() {
		Person firstPerson = persons.get(0);
		return new NotificationCompat.Builder(ctx)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle(firstPerson.name + " and " + (persons.size() - 1)
					+ (persons.size() > 2 ? "others" : "other")
					+ " have birthday!")
			.setContentText("Send them an email to make him feel special.")
			.setLargeIcon(firstPerson.photo)
			.setAutoCancel(true);
	}

	private NotificationCompat.Builder onePersonNotification() {
		Person person = persons.get(0);
		return new NotificationCompat.Builder(ctx)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle(person.name + " haz birthday!")
			.setContentText("Send him/her an email to make him feel special.")
			.setLargeIcon(person.photo)
			.setAutoCancel(true);
	}
}
