package com.thozo.birthdroid.model;

import java.util.Date;

import android.graphics.Bitmap;

public class Person {
	/** The email doubles as the ID. */
	public String email;
	public String name;
	public Date birthday;
	public Bitmap photo;

	public Person(String email, String name, Date birthday, Bitmap photo) {
		this.email = email;
		this.name = name;
		this.birthday = birthday;
		this.photo = photo;
	}

	/** Sets the photo, and resizes it to a thumbnail. */
	public void setPhoto(Bitmap photo) {
		this.photo = photo;
	}
}
