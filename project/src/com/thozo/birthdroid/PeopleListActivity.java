package com.thozo.birthdroid;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;
import com.thozo.birthdroid.persistance.BirthdayOpenHelper;

public class PeopleListActivity extends Activity {

	private TextView currentDateView;
	private ListView birthdayListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_list);

		BirthdayOpenHelper birthdayOpenHelper = new BirthdayOpenHelper(this);
		final Birthdays birthdays = birthdayOpenHelper.readBirthdays();
		birthdays.addPerson(new Person(
				"evs@google.com", "Nikolay Zherebtsov", new Date(0, 9, 7), null /* photo */));
		birthdays.addPerson(new Person(
				"wittek@google.com", "Thomas Wittek", new Date(0, 2, 22), null /* photo */));
		birthdays.addPerson(new Person(
				"zoltanp@google.com", "Zoltan Papp", new Date(0, 11, 31), null /* photo */));
//		birthdayOpenHelper.storeBirthdays(birthdays);
				
		currentDateView = (TextView) findViewById(R.id.currentDateView);
		currentDateView.setText("Current date: " + DateFormat.format("yyyy-MM-dd", new Date()));

		birthdayListView = (ListView) findViewById(R.id.birthdayListView);
		final BirthdayListAdapter birthdayListAdapter = new BirthdayListAdapter(this, birthdays);
		birthdayListView.setAdapter(birthdayListAdapter);
		birthdayListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
			          int position, long id) {
			  Person person = birthdays.getPerson(position);	

				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/html");
				intent.putExtra(Intent.EXTRA_EMAIL, new String[]{person.email});
				intent.putExtra(Intent.EXTRA_SUBJECT, person.name + ", happy birthday!");
				intent.putExtra(Intent.EXTRA_TEXT, "Let's go out and get some drinks!");
				startActivity(intent);
			}
		});
		birthdayListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, final View view,
			          int position, long id) {
			  final Person person = birthdays.getPerson(position);	
				AlertDialog dialog = new AlertDialog.Builder(PeopleListActivity.this)
						.setMessage(getResources().getString(R.string.delete_user, person.name))
						.setPositiveButton(R.string.delete_user_ok, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               birthdays.deletePerson(person);
		               birthdayListAdapter.notifyDataSetChanged();
		           }
			       })
						.setNegativeButton(R.string.delete_user_cancel, null)
						.create();
				dialog.show();
			  return true;
			}
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	public void handleAddBirthdayButtonClick(View view) {
		Intent intent = new Intent(this, EditBirthdayActivity.class);
		startActivity(intent);
	}
}
