package teamplaceholder.com.placeholderapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import teamplaceholder.com.placeholderapp.model.AccountHolder;
import teamplaceholder.com.placeholderapp.data.UserDBContract.*;
import teamplaceholder.com.placeholderapp.model.Admin;
import teamplaceholder.com.placeholderapp.model.Manager;
import teamplaceholder.com.placeholderapp.model.User;
import teamplaceholder.com.placeholderapp.model.Worker;


/**
 * Created by Jack on 3/1/2017.
 * This File handles USER database creation and operations
 * <p>
 * This class handles the table in the database that stores water report details.
 */
public class DBAccountHandler extends DBHandler {

    private RequestQueue queue;

    public DBAccountHandler(Context context) {
        super(context);
        queue = Volley.newRequestQueue(context);
    }


    /**
     * Adds an account to the database.
     *
     * @param acc - AccountHolder object to be added to the database
     */
    public void addAccount(final AccountHolder acc) {
        final String username = acc.getUsername();
        final String password = acc.getPassword();
        final String accountType = acc.getAccountType();
        String url = "http://crowdsourcing-php.000webhostapp.com/insertAccount.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("accountType", accountType);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void deleteAccount(String username) {
        SQLiteDatabase db = super.getWritableDatabase();
        db.delete(UserTable.TABLE_NAME, UserTable.COLUMN_USER_USERNAME + "=?", new String[]{username});
    }

    /**
     * Reconstructs and returns an AccountHolder that's stored in the database.
     *
     * @param username - username to search for in the database
     * @return the AccountHolder stored in the database
     * @throws IllegalArgumentException when the user does not exist in the database
     */
    public AccountHolder getAccount(String username) throws NullPointerException, IllegalArgumentException {
        try {
            URL url = new URL("http://crowdsourcing-php.000webhostapp.com/getAccount.php");
            Map<String, String> params = new HashMap<>();
            params.put("username", username);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param: params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char) c);
            String jsonAcc = sb.toString();
            Gson gson = new Gson();
            HashMap<String, String> accMap = gson.fromJson(jsonAcc, HashMap.class);

            switch (accMap.get("accountType")) {
                case "Admin":
                    return new Admin(
                            accMap.get("username"),
                            accMap.get("password"),
                            accMap.get("email"),
                            accMap.get("address"),
                            accMap.get("title")
                    );
                case "Manager":
                    return new Manager(
                            accMap.get("username"),
                            accMap.get("password"),
                            accMap.get("email"),
                            accMap.get("address"),
                            accMap.get("title")
                    );
                case "Worker":
                    return new Worker(
                            accMap.get("username"),
                            accMap.get("password"),
                            accMap.get("email"),
                            accMap.get("address"),
                            accMap.get("title")
                    );
                default:
                    return new User(
                            accMap.get("username"),
                            accMap.get("password"),
                            accMap.get("email"),
                            accMap.get("address"),
                            accMap.get("title")
                    );
            }

        } catch (Exception e) {
            Log.d("HELLO", e.getMessage());
            return null;
        }
    }

    /**
     * Adds or updates the profile of a user in the database
     *
     * @param username - username of the AccountHolder whose profile is to be added/updated
     * @param email    - email associated with the AccountHolder
     * @param address  - address associated with the AccountHolder
     * @param title    - title associated with the AccountHolder
     */
    public void setProfile(String username, String email, String address, String title) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_USER_EMAIL, email);
        values.put(UserTable.COLUMN_USER_ADDRESS, address);
        values.put(UserTable.COLUMN_USER_TITLE, title);

        db.update(UserTable.TABLE_NAME, values, UserTable.COLUMN_USER_USERNAME + "=?", new String[]{username});
    }
}
