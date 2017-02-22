package teamplaceholder.com.placeholderapp.Data;

import android.provider.BaseColumns;

/**
 * Created by Jack on 2/21/2017.
 * UserDBContract is the contract class for the main backend db we utilize
 * to store and display user account information
 */

public final class UserDBContract {

    //private constructor to prevent instantiation
    private UserDBContract() {}

    /* Inner class to represent the user db table */
    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String _ID = "username";
        public static final String COLUMN_USER_PASSWORD = "password";
        public static final String COLUMN_USER_TYPE = "type";
    }


}
