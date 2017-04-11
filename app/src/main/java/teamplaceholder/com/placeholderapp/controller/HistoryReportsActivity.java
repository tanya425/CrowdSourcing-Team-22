package teamplaceholder.com.placeholderapp.controller;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import teamplaceholder.com.placeholderapp.data.DBWaterQualityReportHandler;
import teamplaceholder.com.placeholderapp.model.WaterQualityReport;
import teamplaceholder.com.placeholderapp.R;

public class HistoryReportsActivity extends AppCompatActivity {

    private EditText year_et;
    private EditText lat_et;
    private EditText lon_et;

    private int sYear;
    private double sLat;
    private double sLon;

    private DBWaterQualityReportHandler db;

    private ArrayList<WaterQualityReport> full_report_list;
    private ArrayList<WaterQualityReport> graph_report_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_reports);

        year_et = (EditText) findViewById(R.id.Year_ET);
        lat_et = (EditText) findViewById(R.id.latitude_ET);
        lon_et = (EditText) findViewById(R.id.longitude_ET);

        db = new DBWaterQualityReportHandler(this);
        full_report_list = db.getReports();
    }

    /**
     *  Shows virus parts per million vs time for a given loc during a given year
     * @param view the view of the button press
     */
    public void onVirusPressed(View view) {
        String sy = year_et.getText().toString();
        String sla = lat_et.getText().toString();
        String slo = lon_et.getText().toString();
        int[] ms = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (sy.equals("")) {
            Toast.makeText(this, "Please enter Year", Toast.LENGTH_LONG).show();
        } else if (sla.equals("")) {
            Toast.makeText(this, "Please enter Latitude", Toast.LENGTH_LONG).show();
        } else if (slo.equals("")) {
            Toast.makeText(this, "Please enter Longitude", Toast.LENGTH_LONG).show();
        } else {
            sYear = Integer.parseInt(year_et.getText().toString());
            sLat = Double.parseDouble(lat_et.getText().toString());
            sLon = Double.parseDouble(lon_et.getText().toString());

            List<Entry> Viruses_Entries = new ArrayList<>();

            for (WaterQualityReport wrpt : full_report_list) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(wrpt.getDateCreated());
                int rYear = cal.get(Calendar.YEAR);
                Double rLat = wrpt.getLatitude();
                Double rLon = wrpt.getLongitude();

                if ((sLat == rLat) && (sLon == rLon) && (sYear == rYear)) {
                    //int months;
                    //int mins, hours;
                    int days, secs;

                    int month = cal.get(Calendar.MONTH);
                    days = 0;
                    for (int i = 0; i < month; i++) {
                        days += ms[i];
                    }

                    //int day = cal.get(Calendar.DAY_OF_MONTH);
                    //days += day - 1;

                    //int hour = cal.get(Calendar.HOUR_OF_DAY);
                    //hours = hour + days * 24;

                    int min = cal.get(Calendar.MINUTE);
                    //mins = min + hours * 60;

                    int sec = cal.get(Calendar.SECOND);
                    secs = sec + min * 60;

                    Viruses_Entries.add(new Entry(secs, (int) wrpt.getVirusPPM()));
                }
            }
            setContentView(R.layout.activity_vgraph);
            LineChart Vchart = (LineChart) findViewById(R.id.Vchart);

            LineDataSet virData = new LineDataSet(Viruses_Entries, "Virus PPM");
            virData.setColor(Color.BLUE);
            LineData vLD = new LineData(virData);
            Vchart.setData(vLD);
            Vchart.invalidate();
        }
    }

    /**
     * Shows Contaminants parts per million vs time for a given loc during a given year
     * @param view the view of the button press
     */
    public void onContamPressed(View view) {
        String sy = year_et.getText().toString();
        String sla = lat_et.getText().toString();
        String slo = lon_et.getText().toString();
        int[] ms = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (sy.equals("")) {
            Toast.makeText(this, "Please enter Year", Toast.LENGTH_LONG).show();
        } else if (sla.equals("")) {
            Toast.makeText(this, "Please enter Latitude", Toast.LENGTH_LONG).show();
        } else if (slo.equals("")) {
            Toast.makeText(this, "Please enter Longitude", Toast.LENGTH_LONG).show();
        } else {
            sYear = Integer.parseInt(year_et.getText().toString());
            sLat = Double.parseDouble(lat_et.getText().toString());
            sLon = Double.parseDouble(lon_et.getText().toString());

            List<Entry> Contaminants_Entries = new ArrayList<>();

            for (WaterQualityReport wrpt : full_report_list) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(wrpt.getDateCreated());

                int rYear = cal.get(Calendar.YEAR);
                Double rLat = wrpt.getLatitude();
                Double rLon = wrpt.getLongitude();

                if ((sLat == rLat) && (sLon == rLon) && (sYear == rYear)) {
                    //int months;
                    //int mins;
                    //int hours
                    int days,secs;

                    int month = cal.get(Calendar.MONTH);
                    days = 0;
                    for (int i = 0; i < month; i++) {
                        days += ms[i];
                    }

                    //int day = cal.get(Calendar.DAY_OF_MONTH);
                    //days += day - 1;

                    //int hour = cal.get(Calendar.HOUR_OF_DAY);
                    //hours = hour + days * 24;

                    int min = cal.get(Calendar.MINUTE);
                    //mins = min + hours * 60;

                    int sec = cal.get(Calendar.SECOND);
                    secs = sec + min * 60;

                    Contaminants_Entries.add(new Entry(secs, (int) wrpt.getContaminantPPM()));
                }
            }
            setContentView(R.layout.activity_cgraph);

            LineChart Cchart = (LineChart) findViewById(R.id.Cchart);

            LineDataSet contData = new LineDataSet(Contaminants_Entries, "Contaminant PPM");
            contData.setColor(Color.RED);

            LineData cLD = new LineData(contData);
            Cchart.setData(cLD);
            Cchart.invalidate();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HistoryReportsActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
