package com.thozo.birthdroid;

import java.util.ArrayList;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

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
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
