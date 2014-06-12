package com.thozo.birthdroid.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Birthdays {
	private List<Person> persons;

	public Birthdays(Collection<Person> persons) {
		this.persons = new ArrayList<Person>(persons);
	}

	/** Adds a person. */
	public void addPerson(Person person) {
		persons.add(person);
	}

	public List<Person> getPersons() {
		return persons;
	}

	public Person getPerson(int position) {
		return persons.get(position);
	}

	public void deletePerson(Person person) {
		persons.remove(person);
	}

	public int size() {
		return persons.size();
	}
}
