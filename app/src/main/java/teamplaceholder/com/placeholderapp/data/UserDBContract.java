package teamplaceholder.com.placeholderapp.data;

import android.provider.BaseColumns;

/**
 * Created by Jack on 2/21/2017.
 * UserDBContract is the contract class for the main backend db we utilize
 * to store and display user account information
 */

final class UserDBContract {

    /**
     * private constructor to prevent instantiation
     */
    private UserDBContract() {}

    /* Inner class to represent the user db table */
    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "accounts";
        public static final String COLUMN_USER_USERNAME = "username";
        public static final String COLUMN_USER_PASSWORD = "password";
        public static final String COLUMN_USER_TYPE = "type";
        public static final String COLUMN_USER_EMAIL = "email";
        public static final String COLUMN_USER_ADDRESS = "address";
        public static final String COLUMN_USER_TITLE = "title";
    }

    /* Inner class to represent the water source table */
    public static class WSRTable implements BaseColumns {
        public static final String TABLE_NAME = "waterReports";
        public static final String COLUMN_TIME_STAMP = "timeStamp";
        public static final String COLUMN_REPORT_ID = "id";
        public static final String COLUMN_WORKER_NAME = "workerName";
        public static final String COLUMN_LOC_LAT = "latitude";
        public static final String COLUMN_LOC_LONG = "longitude";
        public static final String COLUMN_WATER_TYPE = "waterType";
        public static final String COLUMN_WATER_CONDITION = "waterCondition";
    }

    /* Inner class to represent the water quality report table */
    public static class WQTable implements BaseColumns {
        public static final String TABLE_NAME = "waterQualityReports";
        public static final String COLUMN_TIME_STAMP = "timeStamp";
        public static final String COLUMN_REPORT_ID = "id";
        public static final String COLUMN_WORKER_NAME = "workerName";
        public static final String COLUMN_LOC_LAT = "latitude";
        public static final String COLUMN_LOC_LONG = "longitude";
        public static final String COLUMN_CONDITION = "condition";
        public static final String COLUMN_VIRUS_PPM = "virusPPM";
        public static final String COLUMN_CONTAMINANT_PPM = "contaminantPPM";
    }

}
