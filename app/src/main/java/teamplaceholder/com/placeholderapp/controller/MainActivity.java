package teamplaceholder.com.placeholderapp.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import teamplaceholder.com.placeholderapp.R;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences loginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginInfo = getSharedPreferences("logged_user", 0);

        // makes sure to clear any logged-in user
        SharedPreferences.Editor loginInfoEditor = loginInfo.edit();
        loginInfoEditor.putString("logged_user", null);
        loginInfoEditor.commit();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    /**
     * Called when the login button is pressed*
     * @param view is the where the button was clicked
     */
    public void onLoginPress(View view) {
        Intent intent = new Intent(this, LoginActivity.class); // change back to LoginActivity.class
        startActivity(intent);
    }

    /**
     * Called when the login button is pressed*
     * @param view is the where the button was clicked
     */
    public void onRegisterPress(View view) {
        Intent intent = new Intent(this, RegisterNewUserActivity.class);
        startActivity(intent);
    }
    /**
     * Override functionality
     */
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
