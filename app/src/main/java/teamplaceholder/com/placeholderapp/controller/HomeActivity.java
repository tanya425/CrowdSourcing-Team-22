package teamplaceholder.com.placeholderapp.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import teamplaceholder.com.placeholderapp.data.DBWaterQualityReportHandler;
import teamplaceholder.com.placeholderapp.data.DBWaterSourceReportHandler;
import teamplaceholder.com.placeholderapp.model.WaterSourceReport;
import teamplaceholder.com.placeholderapp.R;

/**
 * Created by Jack on 2/12/2017.
 * This activity will be the main home page of the app
 */

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SharedPreferences loginInfo;
    private String userType;
    private DBWaterSourceReportHandler sourceDB;
    private DBWaterQualityReportHandler qualityDB;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        sourceDB = new DBWaterSourceReportHandler(this);
        qualityDB = new DBWaterQualityReportHandler(this);

        loginInfo = getSharedPreferences("login_info", 0);
        //String username = loginInfo.getString("logged_user", "");
        userType = loginInfo.getString("user_type", "");

        //Sets up actionbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Sets up navigation drawer
        String[] options;
        switch (userType) {
            case "Admin":
                options = new String[] {"Edit Profile", "Add Water Source Report", "View Water Source Reports"};
                break;
            case "Manager":
                options = new String[] {"Edit Profile", "Add Water Source Report", "View Water Source Reports ",
                        "Add Water Quality Report", "View Water Quality Reports", "View Historical Purity Report"};
                break;
            case "Worker":
                options = new String[] {"Edit Profile", "Add Water Source Report", "View Water Source Reports",
                        "Add Water Quality Report"};
                break;
            case "User":
                options = new String[] {"Edit Profile", "Add Water Source Report", "View Water Source Reports"};
                break;
            default:
                options = new String[] {"Edit Profile", "Add Water Source Report", "View Water Source Reports"};
                break;
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, options));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open,
                R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
    }

    //For navigation drawer icon functionality
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }
    //For navigation drawer icon functionality
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Private listener class for navigation drawer
     **/
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (userType) {
                case "Admin":
                    switch (position) {
                        case 0: onEditPress(view);
                            break;
                        case 1: onAddSourcePress(view);
                            break;
                        case 2: onViewSourcesPress(view);
                            break;
                        default:
                            break;
                    }
                    break;
                case "Manager":
                    switch (position) {
                        case 0: onEditPress(view);
                            break;
                        case 1: onAddSourcePress(view);
                            break;
                        case 2: onViewSourcesPress(view);
                            break;
                        case 3: onAddQualityPress(view);
                            break;
                        case 4: onViewQualityReportsPress(view);
                            break;
                        case 5: onViewHistoryReportsPress(view);
                        default:
                            break;
                    }
                    break;
                case "Worker":
                    switch (position) {
                        case 0: onEditPress(view);
                            break;
                        case 1: onAddSourcePress(view);
                            break;
                        case 2: onViewSourcesPress(view);
                            break;
                        case 3: onAddQualityPress(view);
                            break;
                        default:
                            break;
                    }
                    break;
                case "User":
                    switch (position) {
                        case 0: onEditPress(view);
                            break;
                        case 1: onAddSourcePress(view);
                            break;
                        case 2: onViewSourcesPress(view);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    switch (position) {
                        case 0: onEditPress(view);
                            break;
                        case 1: onAddSourcePress(view);
                            break;
                        case 2: onViewSourcesPress(view);
                            break;
                        default:
                            break;
                    }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    /**
     * Called when the logout button is pressed
     * @param view is the view in which the logout button is pressed
     */
    private void onLogoutPress(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setTitle("Confirm Logout");
        alert.setMessage("Do you really wish to log out?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                SharedPreferences.Editor loginInfoEditor = loginInfo.edit();
                loginInfoEditor.putString("logged_user", null);
                loginInfoEditor.apply();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //final AlertDialog dialog = alert.create();
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    /**
     * Called when the edit info button is pressed
     * @param view is the view in which the edit button is pressed
     */
    private void onEditPress(View view) {
        Intent intent = new Intent(HomeActivity.this, EditProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Displays the submit water report activity
     * @param view is the view in which the add source button is pressed
     */
    private void onAddSourcePress(View view) {
        Intent intent = new Intent(HomeActivity.this, SubmitSourceReportActivity.class);
        startActivity(intent);
    }

    /**
     * Displays the History reports activity
     * @param view is the view in which the view history button is pressed
     */
    private void onViewHistoryReportsPress(View view) {
        Intent intent = new Intent(HomeActivity.this, HistoryReportsActivity.class);
        startActivity(intent);
    }

    /**
     * Displays the water source reports activity
     * @param view is the view in which the view water source report button is pressed
     */
    private void onViewSourcesPress(View view) {
        Intent intent = new Intent(HomeActivity.this, ViewWaterSourceReportsActivity.class);
        startActivity(intent);
    }

    /**
     * Displays the submit water quality report activity
     * @param view is the view in which the logout button is pressed
     */
    private void onAddQualityPress(View view) {
        Intent intent = new Intent(HomeActivity.this, SubmitQualityReportActivity.class);
        startActivity(intent);
    }

    /**
     * Displays the water quality reports activity
     * @param view is the view in which the view water quality report button is pressed
     */
    private void onViewQualityReportsPress(View view) {
        Intent intent = new Intent(HomeActivity.this, ViewWaterQualityReportsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case R.id.logout_toolbar_item:
                this.onLogoutPress(findViewById(R.id.logout_toolbar_item));
                break;
            default:
                break;
        }

        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    /**
     * Called when back button is pressed
     *      asks user if they want to discard changes and exit app if yes is selected
     */
    public void onBackPressed() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setTitle("Confirm Logout");
        alert.setMessage("Do you really wish to log out?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //final AlertDialog dialog = alert.create();
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.dismiss();
            }
        });
        alert.show();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        DBWaterSourceReportHandler waterSourceDB = new DBWaterSourceReportHandler(this);
        ArrayList<WaterSourceReport> waterSourceList = waterSourceDB.getReports();

        for (int i = 0; i < waterSourceList.size(); i++) {
            WaterSourceReport report = waterSourceList.get(i);
            LatLng temp = new LatLng(report.getLatitude(),report.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(temp).title(report.getCondition().
                    toString()).snippet(report.getReporterName() + " " + report.getDateString()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Sets up map fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        sourceDB.updateWaterReports();
        qualityDB.updateQualityReports();
    }
}
