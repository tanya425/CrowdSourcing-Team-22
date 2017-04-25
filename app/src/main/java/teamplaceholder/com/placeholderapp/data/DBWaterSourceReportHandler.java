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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import teamplaceholder.com.placeholderapp.model.WaterSourceReport;
import teamplaceholder.com.placeholderapp.data.UserDBContract.*;


/**
 * Created by Jack on 3/1/2017.
 * This File handles Water Report database creation and operations
 */

public class DBWaterSourceReportHandler extends DBHandler{

    private SQLiteDatabase db;
    private RequestQueue queue;

    /**
     * Calls the super constructor using the context passed in
     * @param context is the current context being used
     */
    public DBWaterSourceReportHandler(Context context) {
        super(context);
        queue = Volley.newRequestQueue(context);
    }

    /**
     * adds water source report to the database
     * @param wsr the water source report to be added
     */
    public void addWaterSourceReport(final WaterSourceReport wsr) {
        String url = "http://crowdsourcing-php.000webhostapp.com/insertWaterSourceReport.php";
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
                params.put("timeStamp", String.valueOf(wsr.getDateCreated().getTime()));
                params.put("workerName", wsr.getReporterName());
                params.put("latitude", String.valueOf(wsr.getLatitude()));
                params.put("longitude", String.valueOf(wsr.getLongitude()));
                params.put("waterType", wsr.getWaterType().toString());
                params.put("waterCondition", wsr.getCondition().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }


    /**
     * This method gives the number of reports in the DB
     * @return the number of reports in the database
     */
    public int getMaxId() {
        db = super.getReadableDatabase();
        final String COUNT_REPORTS = "SELECT MAX(" + WSRTable.COLUMN_REPORT_ID + ") FROM " + WSRTable.TABLE_NAME;
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
     * This method returns an ArrayList of all the water source reports in the database
     * @return ArrayList of water reports
     */
    public ArrayList<WaterSourceReport> getReports() {
        ArrayList<WaterSourceReport> list = new ArrayList<>();

        db = getReadableDatabase();

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
        Log.d("NUMREPORTS", String.valueOf(cursor.getCount()));
        if (cursor != null) {
            cursor.moveToFirst();
        } else {
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
        cursor.close();
        return list;
    }

    public void updateWaterReports(){
        String url = "http://crowdsourcing-php.000webhostapp.com/getWaterSourceReports.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        db = DBWaterSourceReportHandler.super.getWritableDatabase();
                        Gson gson = new Gson();
                        Type stringStringMap = new TypeToken<HashMap<String, String>[]>(){}.getType();
                        HashMap<String, String>[] reportMapArr = gson.fromJson(response, stringStringMap);
                        for (HashMap<String, String> reportMap: reportMapArr) {
                            ContentValues values = new ContentValues();
                            values.put(WSRTable.COLUMN_TIME_STAMP, reportMap.get("timeStamp"));
                            values.put(WSRTable.COLUMN_REPORT_ID, reportMap.get("id"));
                            values.put(WSRTable.COLUMN_WORKER_NAME, reportMap.get("workerName"));
                            values.put(WSRTable.COLUMN_LOC_LAT, reportMap.get("latitude"));
                            values.put(WSRTable.COLUMN_LOC_LONG, reportMap.get("longitude"));
                            values.put(WSRTable.COLUMN_WATER_TYPE, reportMap.get("waterType"));
                            values.put(WSRTable.COLUMN_WATER_CONDITION, reportMap.get("waterCondition"));
                            db.replace("waterReports", null, values);
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
