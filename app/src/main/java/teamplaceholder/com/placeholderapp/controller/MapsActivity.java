package teamplaceholder.com.placeholderapp.Controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import teamplaceholder.com.placeholderapp.Data.DBWaterSourceReportHandler;
import teamplaceholder.com.placeholderapp.Model.WaterSourceReport;
import teamplaceholder.com.placeholderapp.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ArrayList<WaterSourceReport> waterSourceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        DBWaterSourceReportHandler db = new DBWaterSourceReportHandler(this);
        waterSourceList = db.getReports();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        for (int i = 0; i < waterSourceList.size(); i++) {
            WaterSourceReport report = waterSourceList.get(i);
            LatLng temp = new LatLng(report.getLatitude(),report.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(temp).title(report.getCondition().
                    toString()).snippet(report.getReporterName() + " " + report.getDateString()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
        }

    }
}