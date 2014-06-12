package com.thozo.birthdroid;

import java.util.Date;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.notifications.NotifierService;
import com.thozo.birthdroid.model.Person;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

public class PeopleListActivity extends Activity {

	private TextView currentDateView;
	private Button addBirthdayButton;
	private ListView birthdayListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_list);

		BirthdayOpenHelper birthdayOpenHelper = new BirthdayOpenHelper(this);
		final Birthdays birthdays = birthdayOpenHelper.readBirthdays();
		birthdays.putPerson(new Person("Nikolay Zherebtsov", new Date(0, 9, 7)));
		birthdays.putPerson(new Person("Zoltan Papp", new Date(0, 11, 31)));
		birthdays.putPerson(new Person("Thomas Wittek", new Date(0, 2, 22)));
//		birthdayOpenHelper.storeBirthdays(birthdays);
				
		currentDateView = (TextView) findViewById(R.id.currentDateView);
		currentDateView.setText("Current date: " + DateFormat.format("yyyy-MM-dd", new Date()));

		birthdayListView = (ListView) findViewById(R.id.birthdayListView);
		birthdayListView.setAdapter(new BirthdayListAdapter(this, birthdays));
		birthdayListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
			          int position, long id) {
			  Person person = birthdays.getPerson(position);	
			  Toast.makeText(PeopleListActivity.this, person.name, Toast.LENGTH_SHORT).show();
			}
		});
		birthdayListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, final View view,
			          int position, long id) {
			  Person person = birthdays.getPerson(position);	
			  Toast.makeText(PeopleListActivity.this, person.birthday.toString(), Toast.LENGTH_SHORT).show();
			  return true;
			}
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent startServiceIntent = new Intent(this, NotifierService.class);
		ComponentName name = startService(startServiceIntent);
		Log.e("birthdroid", "name: " + name);
	}
	
	public void handleAddBirthdayButtonClick(View view) {
		Intent intent = new Intent(this, EditBirthdayActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.people_list, menu);
		return true;
	}

}
