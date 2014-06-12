package com.thozo.birthdroid.model;

import java.util.Date;

public class Person {
	/** The name doubles as the ID. */
	public String name;
	public Date birthday;
	
	public Person(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
	}
}
