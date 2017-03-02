package teamplaceholder.com.placeholderapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import teamplaceholder.com.placeholderapp.Model.AccountHolder;
import teamplaceholder.com.placeholderapp.Data.UserDBContract.*;


/**
 * Created by Jack on 3/1/2017.
 * This File handles USER database creation and operations
 */
/**
 * This class handles the table in the database that stores water report details.
 */
public class DBAccountHandler extends DBHandler{

    public DBAccountHandler(Context context) {
        super(context);
    }


    /**
     * Adds an account to the database.
     * @param acc - AccountHolder object to be added to the database
     */
    public void addAccount(AccountHolder acc) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_USER_USERNAME, acc.getUsername());
        values.put(UserTable.COLUMN_USER_PASSWORD, acc.getPassword());
        values.put(UserTable.COLUMN_USER_TYPE, acc.getAccountType());

        db.insert(UserTable.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Reconstructs and returns an AccountHolder that's stored in the database.
     * @param username - username to search for in the database
     * @return the AccountHolder stored in the database
     * @throws IllegalArgumentException when the user does not exist in the database
     */
    public AccountHolder getAccount(String username) throws IllegalArgumentException {
        SQLiteDatabase db = super.getReadableDatabase();
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

    /**
     * Adds or updates the profile of a user in the database
     * @param username - username of the AccountHolder whose profile is to be added/updated
     * @param email - email associated with the AccountHolder
     * @param address - address associated with the AccountHolder
     * @param title - title associated with the AccountHolder
     */
    public void setProfile(String username, String email, String address, String title) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_USER_EMAIL, email);
        values.put(UserTable.COLUMN_USER_ADDRESS, address);
        values.put(UserTable.COLUMN_USER_TITLE, title);

        db.update(UserTable.TABLE_NAME, values, UserTable.COLUMN_USER_USERNAME + "=?", new String[] {username});
    }
}