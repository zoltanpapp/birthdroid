package com.thozo.birthdroid.model;

import java.util.Date;
import java.util.GregorianCalendar;

public class Person implements Comparable<Person> {
	/** The name doubles as the ID. */
	public String name;
	public Date birthday;

	public Person(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
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
