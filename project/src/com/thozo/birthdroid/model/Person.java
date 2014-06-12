package com.thozo.birthdroid.model;

import java.util.Date;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

public class Person {
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
		Bitmap scaledBitmap = ThumbnailUtils.extractThumbnail(
				photo, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
		this.photo = scaledBitmap;
	}
}
