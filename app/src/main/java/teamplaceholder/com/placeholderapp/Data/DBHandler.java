package teamplaceholder.com.placeholderapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import teamplaceholder.com.placeholderapp.Model.AccountHolder;

/**
 * Created by Jason Ngor on 2/21/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appDatabase";

    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private static final String KEY_TYPE = "type";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + KEY_USER + " TEXT," + KEY_PASS + " TEXT," + KEY_TYPE + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        onCreate(db);
    }

    public void addAccount(AccountHolder acc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER, acc.getUsername());
        values.put(KEY_PASS, acc.getPassword());
        values.put(KEY_TYPE, acc.getWorkerType());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    public AccountHolder getAccount(String username) throws IllegalArgumentException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACCOUNTS,
                new String[] {KEY_USER, KEY_PASS, KEY_TYPE},
                KEY_USER + "=?",
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
