package teamplaceholder.com.placeholderapp.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import teamplaceholder.com.placeholderapp.Data.DBHandler;
import teamplaceholder.com.placeholderapp.Model.AccountHolder;
import teamplaceholder.com.placeholderapp.R;

/**
 * Created by Jack on 2/11/2017.
 * This activity is used to login a user given that they input
 * a valid username and password into the given input boxes
 */

public class LoginActivity  extends AppCompatActivity {

    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHandler(this);
    }


    /**
     *  Called when the submit login button is pressed
     *  @param view is the view in which the onSubmitLogin button was pressed
     */
    public void onSubmitLoginPress(View view) {
        String username = ((EditText) findViewById(R.id.usernameBox)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordBox)).getText().toString();
        try {
            AccountHolder acc = db.getAccount(username);
            if (!acc.getPassword().equals(password)) {
                throw new IllegalArgumentException("Invalid password");
            }
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
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
