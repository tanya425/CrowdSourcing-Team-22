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
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "appDatabase.db";

    /*
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private static final String KEY_TYPE = "type";
    */
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_USER_DATABASE = "CREATE TABLE" +
                UserTable.TABLE_NAME + " (" +
                UserTable._ID + " TEXT NOT NULL UNIQUE," +
                UserTable.COLUMN_USER_PASSWORD + " TEXT NOT NULL," +
                UserTable.COLUMN_USER_TYPE + " TEXT NOT NULL" + ");";

        db.execSQL(CREATE_USER_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        onCreate(db);
    }

    public void addAccount(AccountHolder acc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable._ID, acc.getUsername());
        values.put(UserTable.COLUMN_USER_PASSWORD, acc.getPassword());
        values.put(UserTable.COLUMN_USER_TYPE, acc.getWorkerType());

        db.insert(UserTable.TABLE_NAME, null, values);
        db.close();
    }

    public AccountHolder getAccount(String username) throws IllegalArgumentException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UserTable.TABLE_NAME,
                new String[] {
                        UserTable._ID,
                        UserTable.COLUMN_USER_PASSWORD,
                        UserTable.COLUMN_USER_TYPE },
                UserTable._ID + "=?",
                new String[] {username},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            throw new IllegalArgumentException("getAccount - username does not exist");
        }
        AccountHolder acc = new AccountHolder(cursor.getString(0), cursor.getString(1));
        return acc;
    }
}
