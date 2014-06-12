package com.thozo.birthdroid;

import java.util.Date;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;

public class BirthdayListAdapter extends BaseAdapter {

	private Birthdays birthdays;
	private Context context;

	public BirthdayListAdapter(Context context, Birthdays birthdays) {
		super();
		this.birthdays = birthdays;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (birthdays.size() > 0) {
			return createPersonListItem(position, parent);
		} else {
			return createExplanationView(parent);
		}
	}

	private View createExplanationView(ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView view = (TextView) inflater.inflate(R.layout.explanation_how_to_add_birthdays, parent,
				false);
		return view;
	}

	private View createPersonListItem(int position, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewGroup view = (ViewGroup) inflater.inflate(R.layout.person_item, parent, false);
		Person person = birthdays.getPerson(position);
		TextView personNameView = (TextView) view.findViewById(R.id.personName);
		personNameView.setText(person.name);
		TextView personBirthday = (TextView) view.findViewById(R.id.personBirthday);
		personBirthday.setText(DateFormat.format("MM-dd", person.birthday));
		return view;
	}

	@Override
	public int getCount() {
		int size = birthdays.size();
		return size != 0 ? size : 1;
	}

	@Override
	public Person getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
