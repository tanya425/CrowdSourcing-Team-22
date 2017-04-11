package teamplaceholder.com.placeholderapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import teamplaceholder.com.placeholderapp.Data.UserDBContract.*;

/**
 * Created by Jason Ngor on 2/21/2017.
 * This File Initialiizes and updates database
 */

/**
 * This class handles the database that stores data for the app. It contains an accounts table.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
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

        final String CREATE_WATER_SOURCE_DATABASE = "CREATE TABLE " +
                WSRTable.TABLE_NAME + "(" +
                WSRTable.COLUMN_TIME_STAMP + " DATETIME NOT NULL, " +
                WSRTable.COLUMN_REPORT_ID + " INT NOT NULL UNIQUE, " +
                WSRTable.COLUMN_WORKER_NAME + " TEXT, " +
                WSRTable.COLUMN_LOC_LAT + " DECIMAL, " +
                WSRTable.COLUMN_LOC_LONG + " DECIMAL, " +
                WSRTable.COLUMN_WATER_TYPE + " TEXT, " +
                WSRTable.COLUMN_WATER_CONDITION + " TEXT " + ");";

        final String CREATE_WATER_QUALITY_DATABASE = "CREATE TABLE " +
                WQTable.TABLE_NAME + "(" +
                WQTable.COLUMN_TIME_STAMP + " DATETIME NOT NULL, " +
                WQTable.COLUMN_REPORT_ID + " INT NOT NULL UNIQUE, " +
                WQTable.COLUMN_WORKER_NAME + " TEXT, " +
                WQTable.COLUMN_LOC_LAT + " DECIMAL, " +
                WQTable.COLUMN_LOC_LONG + " DECIMAL, " +
                WQTable.COLUMN_CONDITION + " TEXT, " +
                WQTable.COLUMN_VIRUS_PPM + " INT NOT NULL, " +
                WQTable.COLUMN_CONTAMINANT_PPM + " INT NOT NULL " + ");";

        db.execSQL(CREATE_USER_DATABASE);
        db.execSQL(CREATE_WATER_SOURCE_DATABASE);
        db.execSQL(CREATE_WATER_QUALITY_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* will need to update this method in future if new columns are added to USER table */
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WSRTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WQTable.TABLE_NAME);
        onCreate(db);
    }
}
