package teamplaceholder.com.placeholderapp.controller;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import teamplaceholder.com.placeholderapp.data.DBWaterQualityReportHandler;
import teamplaceholder.com.placeholderapp.model.WaterQualityReport;
import teamplaceholder.com.placeholderapp.R;

public class SubmitQualityReportActivity extends AppCompatActivity {

    private SharedPreferences loginInfo;

    private Date now;
    private int report_id;
    private String username;

    private EditText latitude;
    private EditText longitude;
    private Spinner overall_condition;
    private EditText virusPPM;
    private EditText contaminantPPM;
    private DBWaterQualityReportHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_quality_report);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        loginInfo = getSharedPreferences("login_info", 0);
        username = loginInfo.getString("logged_user", null);

        latitude = (EditText) findViewById(R.id.latitude_et);
        longitude = (EditText) findViewById(R.id.longitude_et);
        virusPPM = (EditText) findViewById(R.id.virus_ppm_et);
        contaminantPPM = (EditText) findViewById(R.id.contaminant_ppm_et);
        overall_condition = (Spinner) findViewById(R.id.overall_condition_spinner);

         /*
          Set up the adapter to display the allowable accounts in the spinner
         */
        ArrayAdapter<String> type_adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                WaterQualityReport.WaterOverallConditionList);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overall_condition.setAdapter(type_adapter);

        db = new DBWaterQualityReportHandler(this);
    }

    /**
     * onSubmitPressed is the button handler code for the submit button
     * @param view is the current view passed into the button handler
     */
    public void onSubmitPress(View view){
        now = new Date();
        report_id = db.getMaxId() + 1;
        username = loginInfo.getString("logged_user", null);

        double lat = Double.parseDouble(latitude.getText().toString());
        double lon = Double.parseDouble(longitude.getText().toString());
        double virPPM = Double.parseDouble(virusPPM.getText().toString());
        double conPPM = Double.parseDouble(contaminantPPM.getText().toString());

        WaterQualityReport.OverallCondition overall_C =
                (WaterQualityReport.OverallCondition) overall_condition.getSelectedItem();

        WaterQualityReport report = new WaterQualityReport(
                username,
                report_id,
                lat, lon,
                overall_C,
                virPPM,
                conPPM);

        db.addWaterQualityReport(report);
        this.finish();
    }

    /**
     * onCancelPressed is the button handler code for the cancel button
     * @param view is the current view passed into the button handler
     */
    public void onCancelPress(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(SubmitQualityReportActivity.this);
        alert.setTitle("Discard Changes");
        alert.setMessage("Do you really wish to discard your changes?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                SubmitQualityReportActivity.this.finish();
            }
        });
        final AlertDialog dialog = alert.create();
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
