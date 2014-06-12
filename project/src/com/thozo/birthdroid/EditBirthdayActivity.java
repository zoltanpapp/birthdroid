package com.thozo.birthdroid;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.thozo.birthdroid.model.Person;

public class EditBirthdayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_birthday);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new EditBirthdayFragment())
					.commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class EditBirthdayFragment 
			extends Fragment
			implements DatePickerDialog.OnDateSetListener {
		private static final int SELECT_PHOTO = 100;
		private TextView birthdayTextView;
		private ImageView photoImageView;
		private MainApplication mainApplication;
		private EditText addPersonEmailView;
		private EditText addPersonNameView;
		private Bitmap bitmap = null;

		public EditBirthdayFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_edit_birthday, container, false);

			mainApplication = (MainApplication) getActivity().getApplication();
			addPersonNameView = (EditText) rootView.findViewById(R.id.addPersonName);
			addPersonEmailView = (EditText) rootView.findViewById(R.id.addPersonEmail);

			// Birthday date picker.
			birthdayTextView = (TextView) rootView.findViewById(R.id.birthdayTextView);
			birthdayTextView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showDatePickerDialog();
				}
			});

			// Photo ImageView.
			photoImageView = (ImageView) rootView.findViewById(R.id.photoImageView);
			photoImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showPhotoPickerDialog();
				}
			});

			// Save button.
			Button saveButton = (Button) rootView.findViewById(R.id.saveButton);
			saveButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Person person = new Person(
							addPersonEmailView.getText().toString(),
				      addPersonNameView.getText().toString(), new Date(),
				      bitmap);
					mainApplication.getBirthdays().addPerson(person);
					mainApplication.storeBirthdays();
					getActivity().finish();
				}
			});

			return rootView;
		}

		private void showDatePickerDialog() {
		    DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
		    datePickerDialogFragment.setListner(this);
		    datePickerDialogFragment.show(getFragmentManager(), "datePicker");
		}
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
			birthdayTextView.setText(month + "-" + day);
			// TODO(wittek): Set on Model.
		}
		
		private void showPhotoPickerDialog() {
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, SELECT_PHOTO);
		}
				
		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent imageReturnedIntent) {
			super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

			switch (requestCode) {
			case SELECT_PHOTO:
				if (resultCode == RESULT_OK) {
					Uri selectedImage = imageReturnedIntent.getData();
					try {
						InputStream imageStream = getActivity()
								.getContentResolver()
								.openInputStream(selectedImage);
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 8;
					  bitmap = BitmapFactory.decodeStream(imageStream, null, options);
						photoImageView.setImageBitmap(bitmap);
					} catch (FileNotFoundException e) {
					}
				}
			}
		}
	}

}
