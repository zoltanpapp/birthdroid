package com.thozo.birthdroid;

import java.io.FileNotFoundException;
import java.io.InputStream;

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
import android.widget.ImageView;
import android.widget.Toast;

public class EditBirthdayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_birthday);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public void handleAddBirthdayButtonClick(View view) {
		// Intent intent = new Intent(this, EditBirthdayActivity.class);
		// startActivity(intent);
		Toast.makeText(this, "est", Toast.LENGTH_SHORT).show();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private static final int SELECT_PHOTO = 100;
		private ImageView photoImageView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_edit_birthday,
					container, false);

			photoImageView = (ImageView) rootView.findViewById(R.id.photoImageView);
			photoImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
					photoPickerIntent.setType("image/*");
					startActivityForResult(photoPickerIntent, SELECT_PHOTO);
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
