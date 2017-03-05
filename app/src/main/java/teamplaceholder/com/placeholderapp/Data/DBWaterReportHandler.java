package teamplaceholder.com.placeholderapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

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
        values.put(WSRTable.COLUMN_TIME_STAMP, wsr.getDateCreated().getTime());
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
            cursor.moveToFirst();
            int ctr = cursor.getInt(0);
            return ctr;
        } else {
            return 0;
        }
    }

    public ArrayList<WaterSourceReport> getReports() {
        ArrayList<WaterSourceReport> list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, WSRTable.TABLE_NAME);

        Cursor cursor = db.query(WSRTable.TABLE_NAME,
                new String[] {
                        WSRTable.COLUMN_TIME_STAMP,
                        WSRTable.COLUMN_WORKER_NAME,
                        WSRTable.COLUMN_REPORT_ID,
                        WSRTable.COLUMN_LOC_LAT,
                        WSRTable.COLUMN_LOC_LONG,
                        WSRTable.COLUMN_WATER_TYPE,
                        WSRTable.COLUMN_WATER_CONDITION
                }, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return list;
        }

        while (!cursor.isAfterLast()) {
            WaterSourceReport.WaterType type;
            WaterSourceReport.Condition condition;

            switch (cursor.getString(5)) {
                case "Bottled":
                    type = WaterSourceReport.WaterType.BOTTLED;
                    break;
                case "Well":
                    type = WaterSourceReport.WaterType.WELL;
                    break;
                case "Stream":
                    type = WaterSourceReport.WaterType.STREAM;
                    break;
                case "Lake":
                    type = WaterSourceReport.WaterType.LAKE;
                    break;
                case "Spring":
                    type = WaterSourceReport.WaterType.SPRING;
                    break;
                case "Other":
                    type = WaterSourceReport.WaterType.OTHER;
                    break;
                default:
                    type = WaterSourceReport.WaterType.OTHER;
                    break;
            }

            switch (cursor.getString(6)) {
                case "Waste":
                    condition = WaterSourceReport.Condition.WASTE;
                    break;
                case "Treatable Clear":
                    condition = WaterSourceReport.Condition.TREATABLECLEAR;
                    break;
                case "Treatable Muddy":
                    condition = WaterSourceReport.Condition.TREATABLEMUDDY;
                    break;
                case "Potable":
                    condition = WaterSourceReport.Condition.POTABLE;
                    break;
                default:
                    condition = WaterSourceReport.Condition.WASTE;
                    break;
            }

            WaterSourceReport report = new WaterSourceReport(new Date(cursor.getLong(0)),
                    cursor.getString(1), cursor.getInt(2), cursor.getDouble(3), cursor.getDouble(4),
                    type, condition);

            list.add(report);
            cursor.moveToNext();
        }
        return list;
    }
}
