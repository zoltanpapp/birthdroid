package com.thozo.birthdroid;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;

public class PeopleListActivity extends Activity {

	private TextView currentDateView;
	private ListView birthdayListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_list);
		
		final MainApplication mainApplication = (MainApplication) getApplication();
		final Birthdays birthdays = mainApplication.getBirthdays();
				
		currentDateView = (TextView) findViewById(R.id.currentDateView);
		currentDateView.setText("Current date: " + DateFormat.format("yyyy-MM-dd", new Date()));

		birthdayListView = (ListView) findViewById(R.id.birthdayListView);
		final BirthdayListAdapter birthdayListAdapter = new BirthdayListAdapter(this, birthdays);
		birthdays.attachAdapter(birthdayListAdapter);
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
		               mainApplication.storeBirthdays();
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
