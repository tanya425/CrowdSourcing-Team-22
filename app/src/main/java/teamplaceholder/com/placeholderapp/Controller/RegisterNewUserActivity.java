package teamplaceholder.com.placeholderapp.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import teamplaceholder.com.placeholderapp.Data.DBHandler;
import teamplaceholder.com.placeholderapp.Model.AccountHolder;
import teamplaceholder.com.placeholderapp.Model.Admin;
import teamplaceholder.com.placeholderapp.Model.Manager;
import teamplaceholder.com.placeholderapp.Model.User;
import teamplaceholder.com.placeholderapp.Model.Worker;
import teamplaceholder.com.placeholderapp.R;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class RegisterNewUserActivity extends AppCompatActivity {
    public static List<String> legalAccountTypes = Arrays.asList("Admin", "User", "Worker", "Manager");
    private EditText usernameText;
    private EditText passwordText;
    private Spinner accountTypeSpinner;

    private String _username;
    private String _password;
    private String _accountType;
    private AccountHolder _account;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("REGISTERACTIVITY","ENTERED ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameText = (EditText) findViewById(R.id.usernameBox);
        passwordText = (EditText) findViewById(R.id.passwordBox);
        accountTypeSpinner = (Spinner) findViewById(R.id.spinner);

        /*
          Set up the adapter to display the allowable accounts in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, legalAccountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);
        db = new DBHandler(this);

    }

    protected void onSubmitPressed(View view) {
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
        //send _account to whatever database or file it needs to go to
    }

    // probably should end the activity instead of calling a new mainactivity -jason
    protected void onCancelPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
