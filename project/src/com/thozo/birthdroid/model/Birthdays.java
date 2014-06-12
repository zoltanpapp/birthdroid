package com.thozo.birthdroid.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Birthdays {
	private Map<String, Person> people;
	
	public Birthdays(Collection<Person> people) {
		this.people = new HashMap<String, Person>();
		for (Person person : people) {
			putPerson(person);
		}
	}
	
	/** Adds a person. Replaces it if a person with that name already exist. */
	public void putPerson(Person person) {
		people.put(person.name, person);
	}
	
	public List<Person> getPeople() {
		// TODO(evs): store the list instead of the map.
		return new ArrayList<Person>(people.values());
	}
}
