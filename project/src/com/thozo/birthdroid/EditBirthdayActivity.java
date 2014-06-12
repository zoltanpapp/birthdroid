package com.thozo.birthdroid;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;

public class EditBirthdayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_birthday);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment())
					.commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private static final int SELECT_PHOTO = 100;
		private ImageView photoImageView;

		private Birthdays birthdays;
		private EditText addPersonEmailView;
		private EditText addPersonNameView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_edit_birthday, container, false);

			birthdays = ((MainApplication) getActivity().getApplication()).getBirthdays();
			addPersonNameView = (EditText) rootView.findViewById(R.id.addPersonName);
			addPersonEmailView = (EditText) rootView.findViewById(R.id.addPersonEmail);

			photoImageView = (ImageView) rootView.findViewById(R.id.photoImageView);
			photoImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
					photoPickerIntent.setType("image/*");
					startActivityForResult(photoPickerIntent, SELECT_PHOTO);
				}
			});

			Button saveButton = (Button) rootView.findViewById(R.id.saveButton);
			saveButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Person person = new Person(
							addPersonEmailView.getText().toString(),
				      addPersonNameView.getText().toString(), new Date(), null);
					birthdays.addPerson(person);
					getActivity().finish();
				}
			});

			return rootView;
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
						Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
						photoImageView.setImageBitmap(bitmap);
					} catch (FileNotFoundException e) {
					}
				}
			}
		}
	}

}
