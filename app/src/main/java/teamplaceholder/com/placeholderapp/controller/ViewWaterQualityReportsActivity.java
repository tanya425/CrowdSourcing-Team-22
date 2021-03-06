package teamplaceholder.com.placeholderapp.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import teamplaceholder.com.placeholderapp.data.DBWaterQualityReportHandler;
import teamplaceholder.com.placeholderapp.model.WaterQualityReport;
import teamplaceholder.com.placeholderapp.R;

public class ViewWaterQualityReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_water_quality_reports);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DBWaterQualityReportHandler db = new DBWaterQualityReportHandler(this);
        ArrayList<WaterQualityReport> waterQualityList = db.getReports();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new WaterQualityAdapter(waterQualityList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    /**
     * Adapter for recycler view to read the data from the database
     */
    public class WaterQualityAdapter extends RecyclerView.Adapter<WaterQualityAdapter.WaterQualityViewHolder> {
        private final ArrayList<WaterQualityReport> waterQualityList;

        public WaterQualityAdapter(ArrayList<WaterQualityReport> waterQualityList) {
            this.waterQualityList = waterQualityList;
        }

        @Override
        public WaterQualityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.water_quality_card,
                    parent, false);
            return new WaterQualityViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(WaterQualityViewHolder holder, int position) {
            WaterQualityReport source = waterQualityList.get(position);
            holder.vCondition.setText(source.getCondition().toString());
            holder.vReportNum.setText(getString(R.string.report_num, String.valueOf(source.getReportNumber())));
            holder.vReportedBy.setText(source.getWorkerName());
            holder.vDate.setText(source.getDateString());
            holder.vLatitude.setText(String.valueOf(source.getLatitude()));
            holder.vLongitude.setText(String.valueOf(source.getLongitude()));
            holder.vVirusPPM.setText(String.valueOf(source.getVirusPPM()));
            holder.vContaminantPPM.setText(String.valueOf(source.getContaminantPPM()));
        }

        @Override
        public int getItemCount() {
            return waterQualityList.size();
        }

        /**
         * handles view of water quality report
         */
        public class WaterQualityViewHolder extends RecyclerView.ViewHolder {
            final TextView vCondition;
            final TextView vReportNum;
            final TextView vReportedBy;
            final TextView vDate;
            final TextView vLatitude;
            final TextView vLongitude;
            final TextView vVirusPPM;
            final TextView vContaminantPPM;

            /**
             * view handler code for water quality view
             * @param v is the current view passed into the button handler
             */
            public WaterQualityViewHolder(View v) {
                super(v);
                vCondition = (TextView) v.findViewById(R.id.txtCondition);
                vReportNum = (TextView) v.findViewById(R.id.txtReportNum);
                vReportedBy = (TextView) v.findViewById(R.id.txtReportedBy);
                vDate = (TextView) v.findViewById(R.id.txtDate);
                vLatitude = (TextView) v.findViewById(R.id.txtLatitude);
                vLongitude = (TextView) v.findViewById(R.id.txtLongitude);
                vVirusPPM = (TextView) v.findViewById((R.id.txtVirusPPM));
                vContaminantPPM = (TextView) v.findViewById((R.id.txtContaminantPPM));
            }
        }
    }

}
