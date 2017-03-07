package teamplaceholder.com.placeholderapp.Controller;

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

import java.sql.Time;
import java.util.Date;

import teamplaceholder.com.placeholderapp.Data.DBWaterReportHandler;
import teamplaceholder.com.placeholderapp.Model.WaterSourceReport;
import teamplaceholder.com.placeholderapp.R;

public class SubWaterRepoActivity extends AppCompatActivity {

    private SharedPreferences loginInfo;

    private Date now;
    private int report_id;
    private String username;

    private EditText latitude;
    private EditText longitude;
    private Spinner water_type;
    private Spinner water_condition;

    private DBWaterReportHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_water_repo);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        loginInfo = getSharedPreferences("login_info", 0);
        username = loginInfo.getString("logged_user", null);

        latitude = (EditText) findViewById(R.id.latitude_et);
        longitude = (EditText) findViewById(R.id.longitude_et);
        water_type = (Spinner) findViewById(R.id.water_type_spinner);
        water_condition = (Spinner) findViewById(R.id.water_condition_spinner);

         /*
          Set up the adapter to display the allowable accounts in the spinner
         */
        ArrayAdapter<String> type_adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                WaterSourceReport.WaterTypeList);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        water_type.setAdapter(type_adapter);

        ArrayAdapter<String> condition_adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                WaterSourceReport.WaterConditionList);
        condition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        water_condition.setAdapter(condition_adapter);

        db = new DBWaterReportHandler(this);
    }

    /**
     * onSubmitPressed is the button handler code for the submit button
     * @param view is the current view passed into the button handler
     */
    protected void onSubmitPress(View view){
        now = new Date();
        report_id = db.getMaxId() + 1;
        username = loginInfo.getString("logged_user", null);

        double lat = Double.parseDouble(latitude.getText().toString());
        double lon = Double.parseDouble(longitude.getText().toString());

        WaterSourceReport.WaterType water_T =
                (WaterSourceReport.WaterType) water_type.getSelectedItem();

        WaterSourceReport.Condition water_C =
                (WaterSourceReport.Condition) water_condition.getSelectedItem();

        WaterSourceReport report = new WaterSourceReport(
                username,
                report_id,
                lat, lon,
                water_T,
                water_C);

        db.addWaterSourceReport(report);
        this.finish();
    }

    /**
     * onCancelPressed is the button handler code for the cancel button
     * @param view is the current view passed into the button handler
     */
    protected void onCancelPress(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(SubWaterRepoActivity.this);
        alert.setTitle("Discard Changes");
        alert.setMessage("Do you really wish to discard your changes?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                SubWaterRepoActivity.this.finish();
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
