package com.thozo.birthdroid;

import android.content.Context;
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
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    TextView view = (TextView) inflater.inflate(R.layout.person_item, parent, false);
	    Person person = birthdays.getPeople().get(position);
		view.setText(person.name);
		return view;
	}

	@Override
	public int getCount() {
		return birthdays.getPeople().size();
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
