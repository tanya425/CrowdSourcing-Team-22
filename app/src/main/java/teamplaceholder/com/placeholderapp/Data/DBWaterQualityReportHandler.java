package teamplaceholder.com.placeholderapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import teamplaceholder.com.placeholderapp.Data.UserDBContract.*;

import teamplaceholder.com.placeholderapp.Model.WaterQualityReport;

/**
 * Created by Jason Ngor on 3/23/2017.
 */

public class DBWaterQualityReportHandler extends DBHandler {
    private SQLiteDatabase db;

    public DBWaterQualityReportHandler(Context context) {
        super(context);
    }

    /**
     * Adds a water quality report to the database
     * @param report - WaterQualityReport object to add to database
     */
    public void addWaterQualityReport(WaterQualityReport report) {
        db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WQTable.COLUMN_TIME_STAMP, report.getDateCreated().getTime());
        values.put(WQTable.COLUMN_REPORT_ID, report.getReportNumber());
        values.put(WQTable.COLUMN_WORKER_NAME, report.getWorkerName());
        values.put(WQTable.COLUMN_LOC_LAT, report.getLatitude());
        values.put(WQTable.COLUMN_LOC_LONG, report.getLongitude());
        values.put(WQTable.COLUMN_CONDITION, report.getCondition().toString());
        values.put(WQTable.COLUMN_VIRUS_PPM, report.getVirusPPM());
        values.put(WQTable.COLUMN_CONTAMINANT_PPM, report.getContaminantPPM());

        db.insert(WQTable.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * This method gives the number of water quality reports in the database
     * @return the number of reports in the database
     */
    public int getMaxId() {
        final String COUNT_REPORTS = "SELECT MAX(" + WQTable.COLUMN_REPORT_ID + ") FROM " + WQTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(COUNT_REPORTS, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }

    /**
     * This method returns an ArrayList of all the water quality reports in the database
     * @return ArrayList of water quality reports
     */
    public ArrayList<WaterQualityReport> getReports() {
        ArrayList<WaterQualityReport> list = new ArrayList<>();

        db = getReadableDatabase();

        Cursor cursor = db.query(WQTable.TABLE_NAME,
                new String[] {
                        WQTable.COLUMN_TIME_STAMP,
                        WQTable.COLUMN_WORKER_NAME,
                        WQTable.COLUMN_REPORT_ID,
                        WQTable.COLUMN_LOC_LAT,
                        WQTable.COLUMN_LOC_LONG,
                        WQTable.COLUMN_CONDITION,
                        WQTable.COLUMN_VIRUS_PPM,
                        WQTable.COLUMN_CONTAMINANT_PPM
                }, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return list;
        }

        while (!cursor.isAfterLast()) {
            WaterQualityReport.OverallCondition condition;

            switch (cursor.getString(5)) {
                case "Safe":
                    condition = WaterQualityReport.OverallCondition.SAFE;
                    break;
                case "Treatable":
                    condition = WaterQualityReport.OverallCondition.TREATABLE;
                    break;
                case "Unsafe":
                    condition = WaterQualityReport.OverallCondition.UNSAFE;
                    break;
                default:
                    condition = WaterQualityReport.OverallCondition.SAFE;
                    break;
            }

            WaterQualityReport report = new WaterQualityReport(new Date(cursor.getLong(0)),
                    cursor.getString(1), cursor.getInt(2), cursor.getDouble(3), cursor.getDouble(4),
                    condition, cursor.getInt(6), cursor.getInt(7));

            list.add(report);
            cursor.moveToNext();
        }
        return list;
    }
}
