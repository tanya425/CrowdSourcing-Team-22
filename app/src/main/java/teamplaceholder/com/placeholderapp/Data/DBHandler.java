package teamplaceholder.com.placeholderapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import teamplaceholder.com.placeholderapp.Model.AccountHolder;
import teamplaceholder.com.placeholderapp.Data.UserDBContract.*;

/**
 * Created by Jason Ngor on 2/21/2017.
 * This File handles USER database creation and operations
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "appDatabase.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_USER_DATABASE = "CREATE TABLE " +
                UserTable.TABLE_NAME + "(" +
                UserTable.COLUMN_USER_USERNAME + " TEXT NOT NULL UNIQUE, " +
                UserTable.COLUMN_USER_PASSWORD + " TEXT NOT NULL, " +
                UserTable.COLUMN_USER_TYPE + " TEXT NOT NULL, " +
                UserTable.COLUMN_USER_EMAIL + " TEXT, " +
                UserTable.COLUMN_USER_ADDRESS + " TEXT, " +
                UserTable.COLUMN_USER_TITLE + " TEXT" + ");";

        db.execSQL(CREATE_USER_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* will need to update this method in future if new columns are added to USER table */
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        onCreate(db);
    }

    public void addAccount(AccountHolder acc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_USER_USERNAME, acc.getUsername());
        values.put(UserTable.COLUMN_USER_PASSWORD, acc.getPassword());
        values.put(UserTable.COLUMN_USER_TYPE, acc.getAccountType());

        db.insert(UserTable.TABLE_NAME, null, values);
        db.close();
    }

    public AccountHolder getAccount(String username) throws IllegalArgumentException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UserTable.TABLE_NAME,
                new String[] {
                        UserTable.COLUMN_USER_USERNAME,
                        UserTable.COLUMN_USER_PASSWORD,
                        UserTable.COLUMN_USER_TYPE,
                        UserTable.COLUMN_USER_EMAIL,
                        UserTable.COLUMN_USER_ADDRESS,
                        UserTable.COLUMN_USER_TITLE,},
                UserTable.COLUMN_USER_USERNAME + "=?",
                new String[] {username},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            throw new IllegalArgumentException("getAccount - username does not exist");
        }
        AccountHolder acc = new AccountHolder(cursor.getString(0), cursor.getString(1),
                cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return acc;
    }

    public void setProfile(String username, String email, String address, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_USER_EMAIL, email);
        values.put(UserTable.COLUMN_USER_ADDRESS, address);
        values.put(UserTable.COLUMN_USER_TITLE, title);

        db.update(UserTable.TABLE_NAME, values, UserTable.COLUMN_USER_USERNAME + "=?", new String[] {username});
    }
}
