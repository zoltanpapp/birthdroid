package com.thozo.birthdroid.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.widget.BaseAdapter;

public class Birthdays {
	private List<Person> persons;
	private List<BaseAdapter> adapters = new ArrayList<BaseAdapter>();

	public Birthdays(Collection<Person> persons) {
		this.persons = new ArrayList<Person>(persons);
	}

	/** Adds a person. */
	public void addPerson(Person person) {
		persons.add(person);
		update();
	}

	private void update() {
		Collections.sort(persons);
		for (BaseAdapter adapter : adapters) {
			adapter.notifyDataSetChanged();
		}
	}

	public List<Person> getPersons() {
		return persons;
	}

	public Person getPerson(int position) {
		return persons.get(position);
	}

	public void deletePerson(Person person) {
		persons.remove(person);
		update();
	}

	public int size() {
		return persons.size();
	}
	
	public void attachAdapter(BaseAdapter adapter) {
		adapters.add(adapter);
	}
}
