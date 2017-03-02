package teamplaceholder.com.placeholderapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import teamplaceholder.com.placeholderapp.Model.WaterSourceReport;
import teamplaceholder.com.placeholderapp.Data.UserDBContract.*;


/**
 * Created by Jack on 3/1/2017.
 * This File handles Water Report database creation and operations
 */

public class DBWaterReportHandler extends DBHandler{

    private SQLiteDatabase db = super.getWritableDatabase();

    public DBWaterReportHandler(Context context) {
        super(context);
    }

    public void addWaterSourceReport(WaterSourceReport wsr) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WSRTable.COLUMN_TIME_STAMP, wsr.getDateandTime());
        values.put(WSRTable.COLUMN_REPORT_ID, wsr.getReportNumber());
        values.put(WSRTable.COLUMN_WORKER_NAME, wsr.getReporterName());
        values.put(WSRTable.COLUMN_LOC_LAT, wsr.getLatitude());
        values.put(WSRTable.COLUMN_LOC_LONG, wsr.getLongitude());
        values.put(WSRTable.COLUMN_WATER_TYPE, wsr.getWaterType().toString());
        values.put(WSRTable.COLUMN_WATER_CONDITION, wsr.getCondition().toString());

        db.insert(WSRTable.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * This method gives the number of reports in the DB
     * @return
     */
    public int getMaxId() {
        final String COUNT_REPORTS = "SELECT MAX(" + WSRTable.COLUMN_REPORT_ID + ") FROM " + WSRTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(COUNT_REPORTS, null);
        if (cursor != null) {
            int ctr = cursor.getCount();
            db.close();
            return ctr;
        } else {
            return 0;
        }
    }
}
