package persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BirthdayOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "birthdroid";
    private static final String TABLE_NAME = "birthdays";
    private static final String COL_NAME = "name";
    private static final String COL_BIRTHDAY = "birthday";
    
    public BirthdayOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                "UNIQUE " + COL_NAME + " TEXT, " +
                // Note that SQLite doesn't have a DATE type, so we use a String.
                COL_BIRTHDAY + " TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
