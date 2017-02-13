package teamplaceholder.com.placeholderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.data;

/**
 * Created by Jack on 2/11/2017.
 * This activity is used to login a user given that they input
 * a valid username and password into the given input boxes
 */

public class LoginActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    /* Called when the cancel login button is pressed*/
    public void onSubmitLoginPress(View view) {
        String username = ((EditText) findViewById(R.id.usernameBox)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordBox)).getText().toString();
        if (username.equals("username") && password.equals("password")) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast invalid_login = new Toast(this);
            invalid_login.setView(view);
            invalid_login.setDuration(Toast.LENGTH_LONG);
            invalid_login.show();
        }
    }

    /* Called when the cancel login button is pressed*/
    public void onCancelLoginPress(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
