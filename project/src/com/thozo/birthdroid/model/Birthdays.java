package com.thozo.birthdroid.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Birthdays {
	public Collection<Person> people;
	
	public Birthdays() {
		this.people = new ArrayList<Person>(3);
		people.add(new Person("Nikolay Zherebtsov", new Date(0, 9, 7)));
		people.add(new Person("Zoltan Papp", new Date(0, 11, 31)));
		people.add(new Person("Thomas Wittek", new Date(0, 2, 22)));
	}
}
