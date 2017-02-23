package teamplaceholder.com.placeholderapp.Data;

import android.provider.BaseColumns;

/**
 * Created by Jack on 2/21/2017.
 * UserDBContract is the contract class for the main backend db we utilize
 * to store and display user account information
 */

public final class UserDBContract {

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


}
