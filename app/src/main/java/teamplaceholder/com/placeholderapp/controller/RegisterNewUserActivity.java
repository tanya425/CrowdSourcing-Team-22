package teamplaceholder.com.placeholderapp.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import teamplaceholder.com.placeholderapp.data.DBAccountHandler;
import teamplaceholder.com.placeholderapp.model.AccountHolder;
import teamplaceholder.com.placeholderapp.model.Admin;
import teamplaceholder.com.placeholderapp.model.Manager;
import teamplaceholder.com.placeholderapp.model.User;
import teamplaceholder.com.placeholderapp.model.Worker;
import teamplaceholder.com.placeholderapp.R;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class RegisterNewUserActivity extends AppCompatActivity {
    public static List<String> legalAccountTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    private EditText usernameText;
    private EditText passwordText;
    private Spinner accountTypeSpinner;

    private String _username;
    private String _password;
    private String _accountType;
    private AccountHolder _account;
    private DBAccountHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("REGISTERACTIVITY","ENTERED ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        usernameText = (EditText) findViewById(R.id.usernameBox);
        passwordText = (EditText) findViewById(R.id.passwordBox);
        accountTypeSpinner = (Spinner) findViewById(R.id.spinner);

        /*
          Set up the adapter to display the allowable accounts in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, legalAccountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);
        db = new DBAccountHandler(this);

    }

    /**
     * onSubmitPressed is the button handler code for the submit button
     * @param view is the current view passed into the button handler
     */
    public void onSubmitPressed(View view) {
        _accountType = (String) accountTypeSpinner.getSelectedItem();
        _username = usernameText.getText().toString();
        _password = passwordText.getText().toString();

        if (_username.equals("") || _password.equals("") || _username.equals("Username")) {
            Toast.makeText(this, "Invalid user and/or password", Toast.LENGTH_LONG).show();

        } else {
            if (_accountType.equals("Admin")) {
                _account = new Admin(_username,_password);

            } else if (_accountType.equals("User")) {
                _account = new User(_username,_password);

            } else if (_accountType.equals("Worker")) {
                _account = new Worker(_username,_password);

            } else if (_accountType.equals("Manager")) {
                _account = new Manager(_username, _password);
            }
        }
        db.addAccount(_account);
        Toast.makeText(this, "Account created!", Toast.LENGTH_LONG).show();
        this.finish();
    }

    /**
     * onCancelPressed is called when cancel button is pressed to handel action;
     *      probably should end the activity instead of calling a new mainactivity -jason
     * @param view is the current view passed into the button press code
     */
    public void onCancelPressed(View view) {
        this.finish();
    }
}
