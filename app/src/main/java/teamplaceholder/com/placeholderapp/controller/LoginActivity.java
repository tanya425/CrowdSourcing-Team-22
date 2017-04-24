package teamplaceholder.com.placeholderapp.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import teamplaceholder.com.placeholderapp.data.DBAccountHandler;
import teamplaceholder.com.placeholderapp.model.AccountHolder;
import teamplaceholder.com.placeholderapp.model.Admin;
import teamplaceholder.com.placeholderapp.model.Manager;
import teamplaceholder.com.placeholderapp.model.Worker;
import teamplaceholder.com.placeholderapp.R;

/**
 * Created by Jack on 2/11/2017.
 * This activity is used to login a user given that they input
 * a valid username and password into the given input boxes
 */

public class LoginActivity  extends AppCompatActivity {

    private SharedPreferences loginInfo;
    private DBAccountHandler db;
    private Context context;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBAccountHandler(this);
        loginInfo = getSharedPreferences("login_info", 0);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        context = this;
        queue = Volley.newRequestQueue(context);

    }


    /**
     *  Called when the submit login button is pressed
     *  @param view is the view in which the onSubmitLogin button was pressed
     */
    public void onSubmitLoginPress(View view) {
        final String username = ((EditText) findViewById(R.id.usernameBox)).getText().toString();
        final String password = ((EditText) findViewById(R.id.passwordBox)).getText().toString();
        try {
            String url = "http://crowdsourcing-php.000webhostapp.com/login.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("failure")) {
                                throw new IllegalArgumentException();
                            } else {
                                SharedPreferences.Editor loginInfoEditor = loginInfo.edit();

                                loginInfoEditor.putString("user_type", response);
                                loginInfoEditor.putString("logged_user", username);
                                loginInfoEditor.apply();

                                Intent intent = new Intent(context, HomeActivity.class);
                                startActivity(intent);
                            }

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
                    return params;
                }
            };
            queue.add(stringRequest);

        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Incorrect user and/or password", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Called when the cancel login button is pressed
     * @param view is the view in which the cancel login button was pressed
     */
    public void onCancelLoginPress(View view) {
        this.finish();
    }
}