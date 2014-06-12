package com.thozo.birthdroid.persistance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thozo.birthdroid.model.Birthdays;
import com.thozo.birthdroid.model.Person;

public class BirthdayOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "birthdroid";
    
    public BirthdayOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE birthdays (" +
                "name TEXT, " +
                // Note that SQLite doesn't have a DATE type, so we use a String.
                "birthday TEXT" +
                ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void storeBirthdays(Birthdays birthdays) {
		// TODO(wittek): Use transactions... Hahahahah. :)
		// Bit hacky to clear the whole table, but... why not :)
		this.getWritableDatabase().delete("birthdays", "", new String[]{});
		for (Person person : birthdays.getPeople()) {
			ContentValues values = new ContentValues();
			values.put("name", person.name);
			values.put("birthday", person.birthday.toGMTString());
			this.getWritableDatabase().insert("birthdays", null /* nullColumnHack */, values);
		}
		close();
	}

	public Birthdays readBirthdays() {
		Cursor cursor = this.getReadableDatabase().query(
				"birthdays" /* table */,
				new String[]{"name", "birthday"} /* columns */,
				null /* selection */,
				null /* selectionArgs */,
				null /* groupBy */,
				null /* having */,
	            null /* orderBy */);
		cursor.moveToFirst();
		List<Person> people = new ArrayList<Person>(cursor.getCount());
		while (!cursor.isAfterLast()) {
			String name = cursor.getString(0);
			Date birthday = new Date(cursor.getString(1));
			people.add(new Person(name, birthday));
		    cursor.moveToNext();
		}
		cursor.close();
		close();
		return new Birthdays(people);
	}
}
