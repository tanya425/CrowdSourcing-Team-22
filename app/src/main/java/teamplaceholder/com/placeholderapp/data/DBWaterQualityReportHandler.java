package teamplaceholder.com.placeholderapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import teamplaceholder.com.placeholderapp.data.UserDBContract.*;

import teamplaceholder.com.placeholderapp.model.WaterQualityReport;

/**
 * Created by Jason Ngor on 3/23/2017.
 *      HANDLER FOR THE QUALITY REPORT
 */

public class DBWaterQualityReportHandler extends DBHandler {
    private SQLiteDatabase db;
    private RequestQueue queue;

    public DBWaterQualityReportHandler(Context context) {
        super(context);
        queue = Volley.newRequestQueue(context);
    }

    /**
     * Adds a water quality report to the database
     * @param report - WaterQualityReport object to add to database
     */
    public void addWaterQualityReport(final WaterQualityReport report) {
        String url = "http://crowdsourcing-php.000webhostapp.com/insertWaterQualityReport.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("INSERTREPORT", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("timeStamp", String.valueOf(report.getDateCreated().getTime()));
                params.put("workerName", report.getWorkerName());
                params.put("latitude", String.valueOf(report.getLatitude()));
                params.put("longitude", String.valueOf(report.getLongitude()));
                params.put("waterCondition", report.getCondition().toString());
                params.put("virusPPM", String.valueOf(report.getVirusPPM()));
                params.put("contaminantPPM", String.valueOf(report.getContaminantPPM()));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    /**
     * This method gives the number of water quality reports in the database
     * @return the number of reports in the database
     */
    public int getMaxId() {
        db = super.getReadableDatabase();
        final String COUNT_REPORTS = "SELECT MAX(" + WQTable.COLUMN_REPORT_ID + ") FROM " + WQTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(COUNT_REPORTS, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int maxId = cursor.getInt(0);
            cursor.close();
            return maxId;
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
        } else {
            return list;
        }/*
        if (cursor.getCount() == 0) {
            return list;
        }*/

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
        cursor.close();
        return list;
    }

    public void updateQualityReports(){
        String url = "http://crowdsourcing-php.000webhostapp.com/getWaterQualityReports.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        db = DBWaterQualityReportHandler.super.getWritableDatabase();
                        Gson gson = new Gson();
                        Type stringStringMap = new TypeToken<HashMap<String, String>[]>(){}.getType();
                        HashMap<String, String>[] reportMapArr = gson.fromJson(response, stringStringMap);
                        for (HashMap<String, String> reportMap: reportMapArr) {
                            ContentValues values = new ContentValues();
                            values.put(WQTable.COLUMN_TIME_STAMP, reportMap.get("timeStamp"));
                            values.put(WQTable.COLUMN_REPORT_ID, reportMap.get("id"));
                            values.put(WQTable.COLUMN_WORKER_NAME, reportMap.get("workerName"));
                            values.put(WQTable.COLUMN_LOC_LAT, reportMap.get("latitude"));
                            values.put(WQTable.COLUMN_LOC_LONG, reportMap.get("longitude"));
                            values.put(WQTable.COLUMN_CONDITION, reportMap.get("waterCondition"));
                            values.put(WQTable.COLUMN_VIRUS_PPM, reportMap.get("virusPPM"));
                            values.put(WQTable.COLUMN_CONTAMINANT_PPM, reportMap.get("contaminantPPM"));
                            db.replace("waterQualityReports", null, values);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
        };
        queue.add(stringRequest);
    }
}
