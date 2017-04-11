package teamplaceholder.com.placeholderapp.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import teamplaceholder.com.placeholderapp.Data.DBWaterSourceReportHandler;
import teamplaceholder.com.placeholderapp.Model.WaterSourceReport;
import teamplaceholder.com.placeholderapp.R;

public class ViewWaterSourceReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_water_source_reports);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DBWaterSourceReportHandler db = new DBWaterSourceReportHandler(this);
        ArrayList<WaterSourceReport> waterSourceList = db.getReports();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new WaterSourceAdapter(waterSourceList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    /**
     * Adapter for recycler view to read the data from the database
     */
    public class WaterSourceAdapter extends RecyclerView.Adapter<WaterSourceAdapter.WaterSourceViewHolder> {
        private ArrayList<WaterSourceReport> waterSourceList;

        public WaterSourceAdapter(ArrayList<WaterSourceReport> waterSourceList) {
            this.waterSourceList = waterSourceList;
        }

        @Override
        public WaterSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.water_source_card,
                    parent, false);
            return new WaterSourceViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(WaterSourceViewHolder holder, int position) {
            WaterSourceReport source = waterSourceList.get(position);
            holder.vWaterType.setText(source.getWaterType().toString());
            holder.vReportNum.setText(getString(R.string.report_num, String.valueOf(source.getReportNumber())));
            holder.vReportedBy.setText(source.getReporterName());
            holder.vDate.setText(source.getDateString());
            holder.vWaterCondition.setText(source.getCondition().toString());
            holder.vLatitude.setText(String.valueOf(source.getLatitude()));
            holder.vLongitude.setText(String.valueOf(source.getLongitude()));
        }

        @Override
        public int getItemCount() {
            return waterSourceList.size();
        }

        /**
         * handles view of water source
         */
        public class WaterSourceViewHolder extends RecyclerView.ViewHolder {
            protected TextView vWaterType;
            protected TextView vReportNum;
            protected TextView vReportedBy;
            protected TextView vDate;
            protected TextView vWaterCondition;
            protected TextView vLatitude;
            protected TextView vLongitude;

            /**
             * view handler code for water source view
             * @param v is the current view passed into the button handler
             */
            public WaterSourceViewHolder(View v) {
                super(v);
                vWaterType = (TextView) v.findViewById(R.id.txtWaterType);
                vReportNum = (TextView) v.findViewById(R.id.txtReportNum);
                vReportedBy = (TextView) v.findViewById(R.id.txtReportedBy);
                vDate = (TextView) v.findViewById(R.id.txtDate);
                vWaterCondition = (TextView) v.findViewById(R.id.txtCondition);
                vLatitude = (TextView) v.findViewById(R.id.txtLatitude);
                vLongitude = (TextView) v.findViewById(R.id.txtLongitude);
            }
        }
    }

}
