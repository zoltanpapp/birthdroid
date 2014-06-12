package com.thozo.birthdroid.model;

import java.util.Date;
import java.util.GregorianCalendar;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

public class Person implements Comparable<Person> {
	private final static int THUMBNAIL_SIZE = 64;
			
	/** The email doubles as the ID. */
	public String email;
	public String name;
	public Date birthday;
	public Bitmap photo;

	public Person(String email, String name, Date birthday, Bitmap photo) {
		this.email = email;
		this.name = name;
		this.birthday = birthday;
		setPhoto(photo);
	}

	/** Sets the photo, and resizes it to a thumbnail. */
	public void setPhoto(Bitmap photo) {
		Bitmap scaledBitmap = null;
		if (photo != null) {
			scaledBitmap = ThumbnailUtils.extractThumbnail(
				photo, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
		}
		this.photo = scaledBitmap;
	}

	private int getDaysUntilBirthday() {
		Date currentDate = new Date();
		int result = getDayNumber(birthday) - getDayNumber(currentDate);
		int numberOfDaysInCurrentYear = isLeapYear(currentDate.getYear()) ? 366 : 365;
		return (result + numberOfDaysInCurrentYear) % numberOfDaysInCurrentYear;
	}

	private int getDayNumber(Date date) {
		return date.getDate() + 12 * date.getMonth();
	}

	private boolean isLeapYear(int year) {
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		return cal.isLeapYear(year);
	}

	@Override
	public int compareTo(Person another) {
		return getDaysUntilBirthday() - another.getDaysUntilBirthday();
	}
}
