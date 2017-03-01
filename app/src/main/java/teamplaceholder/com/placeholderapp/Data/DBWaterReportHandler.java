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
 * This File handles Water Report database creation and operations
 */

public class DBWaterReportHandler extends DBHandler{

    public DBWaterReportHandler(Context context) {
        super(context);
    }

}
