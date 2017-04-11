package teamplaceholder.com.placeholderapp.Controller;

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

import teamplaceholder.com.placeholderapp.Data.DBAccountHandler;
import teamplaceholder.com.placeholderapp.Model.AccountHolder;
import teamplaceholder.com.placeholderapp.Model.Admin;
import teamplaceholder.com.placeholderapp.Model.Manager;
import teamplaceholder.com.placeholderapp.Model.User;
import teamplaceholder.com.placeholderapp.Model.Worker;
import teamplaceholder.com.placeholderapp.R;

/**
 * Created by ashwiniiyer on 2/19/17.
 *      REGISTER NEW USER ACTIVITY
 */

public class RegisterNewUserActivity extends AppCompatActivity {
    public static List<String> legalAccountTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    private EditText usernameText;
    private EditText passwordText;
    private Spinner accountTypeSpinner;

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
        String _accountType = (String) accountTypeSpinner.getSelectedItem();
        String _username = usernameText.getText().toString();
        String _password = passwordText.getText().toString();

        if (_username.equals("") || _password.equals("") || _username.equals("Username")) {
            Toast.makeText(this, "Invalid user and/or password", Toast.LENGTH_LONG).show();

        } else {
            if (_accountType.equals("Admin")) {
                _account = new Admin(_username, _password);

            } else if (_accountType.equals("User")) {
                _account = new User(_username, _password);

            } else if (_accountType.equals("Worker")) {
                _account = new Worker(_username, _password);

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
