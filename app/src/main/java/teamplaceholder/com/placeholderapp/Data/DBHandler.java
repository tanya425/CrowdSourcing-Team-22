package teamplaceholder.com.placeholderapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import teamplaceholder.com.placeholderapp.Model.AccountHolder;

/**
 * Created by Jason Ngor on 2/21/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appDatabase";

    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String KEY_USER= "user";
    private static final String KEY_PASS= "pass";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + KEY_USER + " TEXT," + KEY_PASS + " TEXT" + ")";
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
        values.put()
    }
}
