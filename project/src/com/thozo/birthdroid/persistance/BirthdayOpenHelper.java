package com.thozo.birthdroid.persistance;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;

public class BirthdayOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "birthdroid";
    
    public BirthdayOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE birthdays (" +
                "email TEXT, " +
                "name TEXT, " +
                // Note that SQLite doesn't have a DATE type, so we use a String.
                "birthday TEXT, " +
                "photo BLOB" +
                ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// :D
		db.execSQL("DROP TABLE birthdays;");
		onCreate(db);
	}

	public void storeBirthdays(Birthdays birthdays) {
		// TODO(wittek): Use transactions... Hahahahah. :)
		// Bit hacky to clear the whole table, but... why not :)
		this.getWritableDatabase().delete("birthdays", "", new String[]{});
		for (Person person : birthdays.getPersons()) {
			ContentValues values = new ContentValues();
			values.put("email", person.email);
			values.put("name", person.name);
			values.put("birthday", person.birthday.toGMTString());
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			if (person.photo != null) {
				person.photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				values.put("photo", baos.toByteArray());
			}
			this.getWritableDatabase().insert("birthdays", null /* nullColumnHack */, values);
		}
		close();
	}

	public Birthdays readBirthdays() {
		Cursor cursor = this.getReadableDatabase().query(
				"birthdays" /* table */,
				new String[]{"email", "name", "birthday", "photo"} /* columns */,
				null /* selection */,
				null /* selectionArgs */,
				null /* groupBy */,
				null /* having */,
	            null /* orderBy */);
		cursor.moveToFirst();
		List<Person> people = new ArrayList<Person>(cursor.getCount());
		while (!cursor.isAfterLast()) {
			String email = cursor.getString(0);
			String name = cursor.getString(1);
			Date birthday = new Date(cursor.getString(2));
			Bitmap photo = null;
			if (!cursor.isNull(3)) {
				byte[] photoBytes = cursor.getBlob(3);
				ByteArrayInputStream bais = new ByteArrayInputStream(photoBytes);
				photo = BitmapFactory.decodeStream(bais);
			}
			people.add(new Person(email, name, birthday, photo));
		    cursor.moveToNext();
		}
		cursor.close();
		close();
		return new Birthdays(people);
	}
}
