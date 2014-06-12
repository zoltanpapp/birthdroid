package com.thozo.birthdroid.notifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.thozo.birthdroid.R;
import com.thozo.birthdroid.model.Person;

public class Notifier {

	private Context ctx;
	
	Notifier(Context ctx) {
		this.ctx = ctx;
	}

	public void notifyUser(Person person) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/html");
//		intent.putExtra(Intent.EXTRA_EMAIL, person.email); FIXME
		intent.putExtra(Intent.EXTRA_SUBJECT, person.name + ", happy birthday!");
		intent.putExtra(Intent.EXTRA_TEXT, "Let's go out and get some drinks!");

		PendingIntent pendingIntent = PendingIntent.getActivity(ctx, person.hashCode() /* requestCode */,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
	
		
		NotificationCompat.Builder notification = new NotificationCompat.Builder(ctx);
		notification.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle(person.name + " haz birthday!")
			.setContentText("Send him an email to make him feel special.")
			.setContentIntent(pendingIntent)
			.setAutoCancel(true);

		NotificationManager notificationMgr = 
		        (NotificationManager) ctx.getSystemService(Activity.NOTIFICATION_SERVICE);
		// Builds the notification and issues it.
		notificationMgr.notify(person.hashCode(), notification.build());
	}
}
