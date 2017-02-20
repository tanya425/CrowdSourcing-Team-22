package teamplaceholder.com.placeholderapp;

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

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class RegisterNewUserActivity extends AppCompatActivity {
    public static List<String> legalAccountTypes = Arrays.asList("Admin", "User", "Worker");
    private EditText usernameText;
    private EditText passwordText;
    private Spinner accountTypeSpinner;

    private String _username;
    private String _password;
    private String _accountType;
    private AccountHolder _account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("REGISTERACTIVITY","ENTERED ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //setContentView(R.layout.register);
        usernameText = (EditText) findViewById(R.id.usernameBox);
        passwordText = (EditText) findViewById(R.id.passwordBox);
        accountTypeSpinner = (Spinner) findViewById(R.id.spinner);

        /*
          Set up the adapter to display the allowable accounts in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, legalAccountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);
    }

    protected void onSubmitPressed(View view) {
        _accountType = (String) accountTypeSpinner.getSelectedItem();
        _username = usernameText.getText().toString();
        _password = passwordText.getText().toString();
        if (_username.equals("") || _password.equals("") || _username.equals("Username")) {
            //Log.d("onSubmitPressed", "_username or _password is null");
            Toast.makeText(this, "Incorrect user and/or password", Toast.LENGTH_LONG).show();
        } else {
            //Log.d("onSubmitPressed", "_username or _password is not null");
            //Log.d("onSubmitPressed", "_username: "+_username);
            //Log.d("onSubmitPressed", " _password: "+_password);
            if (_accountType.equals("Admin")) {
                _account = new Admin(_username,_password);
            } else if (_accountType.equals("User")) {
                _account = new User(_username,_password);
            } else if (_accountType.equals("Worker")) {
                _account = new Worker(_username,_password);
            } else {
                //they entered nothing, should never be the case because you cant choose null
                //can be removed
                _account = null;
            }
        }
        //send _account to whatever database or file it needs to go to
    }

    protected void onCancelPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
